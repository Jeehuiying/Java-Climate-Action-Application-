/*Contributed by Leong Jao Yuan (104742) 
    Tested by Loh Zhi Yun (104788)*/

import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.*;

public class GamificationManager implements StorageBehavior{
    private PlayerProfile currentPlayer;
    private ArrayList<PlayerProfile> leaderboard;
    
    private static final String FILE="leaderboard.txt";
    private static final String FILE_HEADER = "Username,Score,Stars,Badge,Time";

    public GamificationManager(PlayerProfile player)
    {
        this.currentPlayer = player;
        this.leaderboard = new ArrayList<>();
    }

    public void awardBadge(ArrayList<PlayerProfile> list)
    {
        int rank= 1;

        for(int i = 0; i < list.size(); i++) {
            PlayerProfile p = list.get(i);

            // if not first AND same score as previous = same rank
            if (i > 0 && p.getScore() == list.get(i - 1).getScore()) {
            } else {
                rank = i + 1; //actual position
            }

            //badge based on rank
            if (rank == 1) p.setBadge("🥇 Gold");
            else if (rank == 2) p.setBadge("🥈 Silver");
            else if (rank == 3) p.setBadge("🥉 Bronze");
            else p.setBadge("🌱 Beginner");
        }
    }

        // storage interface
    @Override
    public void savePlayer() {
        ArrayList<PlayerProfile> records;
 
        try {
            records = readAllFromFile();
        } catch (FileLoadException e) {
            // FileLoadException: leaderboard file is missing or unreadable before saving;
            // abort early to prevent overwriting or losing existing data
            JOptionPane.showMessageDialog(null,
                "Could not load leaderboard before saving:\n" + e.getMessage() +
                "\nTime: " + e.getTimestamp() + " | File: " + e.getFileName() + " | Code: " + e.getErrorCode(),
                "File Load Error", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (IOException e) {
            // IOException: unexpected low-level IO failure while reading the leaderboard file
            JOptionPane.showMessageDialog(null,
                "Unexpected error reading leaderboard:\n" + e.getMessage(),
                "IO Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
 
        boolean found = false;
        for (PlayerProfile p : records) {
            // if player record exists, only update if beat highscore
            if (p.getUsername().equals(currentPlayer.getUsername()) && p.getScore() < currentPlayer.getScore()) {
                p.setScore(currentPlayer.getScore());
                p.setStars(currentPlayer.getStars());
                p.setBadge(currentPlayer.getBadge());
                p.setTime();
                found = true;
                break;
            }
            if (p.getUsername().equals(currentPlayer.getUsername()) && p.getScore() >= currentPlayer.getScore()) {
                found = true;
                break;
            }
        }
        // if player record not found, add to records
        if (!found) records.add(currentPlayer);
 
        // Sort by score descending
        records.sort((a, b) -> b.getScore() - a.getScore());
 
        // award badges based on rank
        awardBadge(records);
 
        // write updated records to file
        try {
            writeAllToFile(records);
        } catch (FileLoadException e) {
            // FileLoadException: leaderboard file could not be written,
            // e.g. file is locked or the path is no longer accessible
            JOptionPane.showMessageDialog(null,
                "Could not write leaderboard:\n" + e.getMessage() +
                "\nTime: " + e.getTimestamp() + " | File: " + e.getFileName() + " | Code: " + e.getErrorCode(),
                "File Write Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            // IOException: unexpected low-level IO failure while writing the leaderboard file
            JOptionPane.showMessageDialog(null,
                "Unexpected error writing leaderboard:\n" + e.getMessage(),
                "IO Error", JOptionPane.ERROR_MESSAGE);
        }
    }
 
    @Override
    public void loadLeaderBoard() {
        try {
            leaderboard = readAllFromFile();
        } catch (FileLoadException e) {
            // FileLoadException: leaderboard file is missing or unreadable;
            // fall back to an empty leaderboard so the app can still run
            JOptionPane.showMessageDialog(null,
                "Could not load leaderboard:\n" + e.getMessage() +
                "\nTime: " + e.getTimestamp() + " | File: " + e.getFileName() + " | Code: " + e.getErrorCode(),
                "File Load Error", JOptionPane.ERROR_MESSAGE);
            leaderboard = new ArrayList<>();
        } catch (IOException e) {
            // IOException: unexpected low-level IO failure while loading the leaderboard file;
            // fall back to an empty leaderboard so the app can still run
            JOptionPane.showMessageDialog(null,
                "Unexpected error loading leaderboard:\n" + e.getMessage(),
                "IO Error", JOptionPane.ERROR_MESSAGE);
            leaderboard = new ArrayList<>();
        }
    }
 
    /**
     * Reads all player records from file.
     *
     * @throws FileLoadException if the file is missing or cannot be read
     * @throws IOException       for any other unexpected IO error
     */
    private ArrayList<PlayerProfile> readAllFromFile() throws FileLoadException, IOException {
        ArrayList<PlayerProfile> list = new ArrayList<>();
        File file = new File(FILE);
 
        // File does not exist yet — treat as empty leaderboard (first run)
        if (!file.exists()) return list;
 
        // File exists but cannot be read
        if (!file.canRead()) {
            throw new FileLoadException("Leaderboard file exists but cannot be read: " + FILE);
        }
 
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) { firstLine = false; continue; } // skip header
                String[] parts = line.split(",", -1); // split by comma
                if (parts.length < 5) continue;
 
                String username = parts[0].trim();
                int score       = Integer.parseInt(parts[1].trim());
                int stars       = Integer.parseInt(parts[2].trim());
                String badge    = parts[3].trim();
                String time     = parts[4].trim();
 
                PlayerProfile p = new PlayerProfile(username, score, stars);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                p.setTime(LocalDateTime.parse(time, formatter));
                p.setBadge(badge);
                list.add(p);
            }
        } catch (IOException e) {
            // Wrap in FileLoadException so callers get a meaningful type
            throw new FileLoadException("Failed to load leaderboard from: " + FILE, e);
        }
        return list;
    }
 
    /*
    Writes all player records to file.
    
     @throws FileLoadException if the file cannot be written
     @throws IOException for any other unexpected IO error
     */
    private void writeAllToFile(ArrayList<PlayerProfile> list) throws FileLoadException, IOException {
        File file = new File(FILE);
 
        // File exists but cannot be written to
        if (file.exists() && !file.canWrite()) {
            throw new FileLoadException("Leaderboard file exists but cannot be written: " + FILE);
        }
 
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE, false))) {
            pw.println(FILE_HEADER);
            for (PlayerProfile p : list) {
                pw.println(p.toFile());
            }
        } catch (IOException e) {
            // Wrap in FileLoadException so callers get a meaningful type
            throw new FileLoadException("Failed to write leaderboard to: " + FILE, e);
        }
    }

    //GUI for displaying learderboard
    public void showLeaderboardGUI(JFrame parentFrame) {
        loadLeaderBoard();

        String[] columns = {"Rank", "Username", "Score", "Stars", "Badge", "Time"};
        Object[][] data = new Object[leaderboard.size()][6];
        for (int i = 0; i < leaderboard.size(); i++) {
            PlayerProfile p = leaderboard.get(i);
            data[i][0] = i + 1;
            data[i][1] = p.getUsername();
            data[i][2] = p.getScore();
            //display stars as emojis in leaderboard
            String starEmojis = "";
            for(int s=0; s<p.getStars(); s++) starEmojis += "⭐";
            data[i][3] = starEmojis;

            data[i][4] = p.getBadge();
            data[i][5] = p.getTime() != null ? p.getTime().toString().replace("T", " ") : "";
        }
        //GUI for table
        JTable table = new JTable(data, columns);
        table.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 11));
        table.setRowHeight(24);
        table.getTableHeader().setFont(new Font("Segoe UI Emoji", Font.BOLD, 11));
        table.getTableHeader().setBackground(new Color(34, 139, 34));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setEnabled(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(40);  // Rank
        table.getColumnModel().getColumn(1).setPreferredWidth(80);  // Username
        table.getColumnModel().getColumn(2).setPreferredWidth(45);  // Score
        table.getColumnModel().getColumn(3).setPreferredWidth(90);  // Stars
        table.getColumnModel().getColumn(4).setPreferredWidth(80);  // Badge
        table.getColumnModel().getColumn(5).setPreferredWidth(135); // Time

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(420, 260));

        JLabel title = new JLabel("\uD83C\uDFC6 Leaderboard", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
        title.setForeground(new Color(34, 139, 34));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(title, BorderLayout.NORTH);
        if (leaderboard.isEmpty()) {
            JLabel noDataLabel = new JLabel("No leaderboard data yet.", SwingConstants.CENTER);
            noDataLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 11));
            panel.add(noDataLabel, BorderLayout.CENTER);
        } else {
            panel.add(scrollPane, BorderLayout.CENTER);
        }

        JOptionPane.showMessageDialog(parentFrame, panel, "Leaderboard", JOptionPane.PLAIN_MESSAGE);
    }
}

