/*Contributed by 

Jee Hui Ying (104511) 
Lim Gek Hong (104763) 
Leong Jao Yuan (104742) 
Loh Zhi Yun (104788) 

*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainPage extends JFrame implements ActionListener
{

    private JButton learningButton;
    private JButton quizButton;
    private JButton leaderboardButton;
    private JButton exitButton;
    private JPanel mainPanel;
    private JPanel centerPanel;

    private String appName= "Climate Action Learning App";
    private String theme= "SDG 13: Climate Action";
    private String slogan= "Together for a Greener Future";

    public MainPage()
    {

        // frame settings
        setTitle(appName);
        setSize(450, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(220, 245, 220));//set background colour using R, G, B

        //top panel (title)
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(34, 139, 34));
        topPanel.setPreferredSize(new Dimension(450, 200));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JLabel iconLabel = new JLabel("🌍");
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 50));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel titleLabel = new JLabel(theme);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE); //set white colour for font
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Learning Application");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        subtitleLabel.setForeground(Color.WHITE); //set white colour for font
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        topPanel.add(Box.createVerticalStrut(30));
        topPanel.add(iconLabel);
        topPanel.add(Box.createVerticalStrut(10));
        topPanel.add(titleLabel);
        topPanel.add(Box.createVerticalStrut(5));
        topPanel.add(subtitleLabel);

        centerPanel = new JPanel();
        centerPanel.setBackground(new Color(220, 245, 220));

        centerPanel.setLayout(new GridLayout(4, 1, 0, 20));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        learningButton = createButton("Learning Module");
        quizButton = createButton("Quiz Module");
        leaderboardButton = createButton("🏆 Leaderboard");
        leaderboardButton.setFont(new Font("Segoe UI Emoji", Font.BOLD, 18));
        exitButton = createButton("Exit");

        learningButton.addActionListener(this);
        quizButton.addActionListener(this);
        leaderboardButton.addActionListener(this);
        exitButton.addActionListener(this);

        centerPanel.add(learningButton);
        centerPanel.add(quizButton);
        centerPanel.add(leaderboardButton);
        centerPanel.add(exitButton);

        //footer panel (slogan)
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(34, 139, 34));
        footerPanel.setPreferredSize(new Dimension(450, 50));

        JLabel footerLabel = new JLabel(slogan);
        footerLabel.setForeground(Color.WHITE); //set white colour for font
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 14));

        footerPanel.add(footerLabel);

        // Add panels to frame
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setVisible(true);
    }

    //navigate back to main menu
    public void returnToMainMenu() 
    {
        getContentPane().removeAll();
        add(mainPanel);
        revalidate();
        repaint();
    }

    // method to create styled buttons
    private JButton createButton(String text)
    {

        JButton button = new JButton(text);

        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(60, 179, 113));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(15,15,15,15)); //set size for empty spaces
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); //change mouse pointer when hovering over button

        // Hover effect on the buttons (change button colour)
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(46, 139, 87));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(60, 179, 113));
            }
        });

        return button;
    }

    private void showLeaderboard() 
    {
        new GamificationManager(null).showLeaderboardGUI(this);
    }

    //Navigate to specific page
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == learningButton) {
            // replace the entire frame content so LearningModuleGUI fills the whole window,not creating a new window
            getContentPane().removeAll();
            getContentPane().setLayout(new BorderLayout());
            getContentPane().add(new LearningModule(this), BorderLayout.CENTER);
            revalidate();
            repaint();
        }
        else if (e.getSource() == quizButton) {
            //input player username
            String username = JOptionPane.showInputDialog(this, "Enter your username:", "Player Username", JOptionPane.PLAIN_MESSAGE);
            if (username == null || username.trim().isEmpty()) return;
            getContentPane().removeAll();
            getContentPane().setLayout(new BorderLayout());
            getContentPane().add(new QuizModule(this, username.trim()), BorderLayout.CENTER);
            setLocationRelativeTo(null);
            revalidate();
            repaint();
        }
        else if (e.getSource() == leaderboardButton) {
            showLeaderboard();
        }
        else if (e.getSource() == exitButton) {
            int confirm = JOptionPane.showConfirmDialog(this,"Are you sure you want to exit?","Exit Confirmation",JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }
}