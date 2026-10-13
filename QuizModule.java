/*
Contributed by 
Jee Hui Ying (104511) 
Leong Jao Yuan (104742) 
Loh Zhi Yun (104788) 

Tested by 
Leong Jao Yuan (104742)
Lim Gek Hong (104763)
Jee Hui Ying (104511)

Debugged by
Lin Gek Hong (104763)
Leong Jao Yuan (104742)
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class QuizModule extends JPanel implements ActionListener {
    private JFrame parentFrame; // reference to the main page frame
    private QuizManager manager;
    private GamificationManager gamificationManager;
    private String username;
    private JLabel titleLabel;
    private JLabel questionNumberLabel;
    private JLabel questionLabel;
    private JLabel scoreLabel;
    private JRadioButton option1;
    private JRadioButton option2;
    private JRadioButton option3;
    private JRadioButton option4;
    private ButtonGroup group;
    private JButton nextButton;
    private JButton quitButton;
    private int currentQuestion = 0; //question index

    //timer
    private Timer questionTimer;
    private int timeLeft;
    private JLabel timerLabel;

    public QuizModule(JFrame parentFrame, String username) {
        this.parentFrame = parentFrame;
        this.username = username;
        setLayout(new BorderLayout());
        manager = new QuizManager(20); // 20 questions per quiz

        //timer
        //max time given is 20s
        this.timeLeft = 20;
        questionTimer = new Timer(1000, new ActionListener() {//timer updates every second
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                timerLabel.setText("Time Left: " + timeLeft + "s");

                if (timeLeft <= 0) {
                    questionTimer.stop();
                    handleTimeout();
                }
            }

        });

        //main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(220, 245, 220));

        //top panel
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(34, 139, 34));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JLabel iconLabel = new JLabel("🌍");
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        titleLabel = new JLabel("Climate Action Quiz");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Test Your SDG 13 Knowledge");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitleLabel.setForeground(Color.WHITE);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        topPanel.add(Box.createVerticalStrut(10));
        topPanel.add(iconLabel);
        topPanel.add(titleLabel);
        topPanel.add(subtitleLabel);

        // center panel
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(220, 245, 220));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // question panel
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.setBackground(Color.WHITE);
        questionPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(34, 139, 34), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        questionNumberLabel = new JLabel();
        questionNumberLabel.setFont(new Font("Arial", Font.BOLD, 18));
        questionNumberLabel.setForeground(new Color(34, 139, 34));

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 22));

        scoreLabel = new JLabel("Current Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        questionPanel.add(questionNumberLabel);
        questionPanel.add(Box.createVerticalStrut(15));
        questionPanel.add(questionLabel);
        questionPanel.add(Box.createVerticalStrut(20));
        questionPanel.add(scoreLabel);

        //timer
        timerLabel = new JLabel("Time Left: 20s");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionPanel.add(timerLabel);

        //option panel
        JPanel optionPanel = new JPanel();
        optionPanel.setBackground(new Color(220, 245, 220));
        optionPanel.setLayout(new GridLayout(4, 1, 15, 15));
        optionPanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        option1 = createRadioButton("");
        option2 = createRadioButton("");
        option3 = createRadioButton("");
        option4 = createRadioButton("");

        //group multiple selection buttons together so that only one button can be selected
        group = new ButtonGroup();
        group.add(option1);
        group.add(option2);
        group.add(option3);
        group.add(option4);

        optionPanel.add(option1);
        optionPanel.add(option2);
        optionPanel.add(option3);
        optionPanel.add(option4);

        //button panel (quit and next)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(220, 245, 220));

        //next button
        nextButton = createButton("Next Question");
        nextButton.addActionListener(this);

        //quit button (to main page)
        quitButton = createButton("Quit");
        quitButton.addActionListener(this);

        buttonPanel.add(quitButton);
        buttonPanel.add(nextButton);

        //add all the components
        centerPanel.add(questionPanel, BorderLayout.NORTH);
        centerPanel.add(optionPanel, BorderLayout.CENTER);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);
        displayQuestion();
        setVisible(true);
    }


    // display questions
    private void displayQuestion() {

        // Get question in QuizManager from ArrayList containing shuffled question types
        Question q = manager.getQuestion(currentQuestion);
        questionLabel.setText("<html><body style='width:280px'>" + (currentQuestion + 1) + ". " + q.getQuestionText() + "</body></html>");// wrap using html

        //Check whether q is an instance of the class MCQQuestion
        //current question type is MCQ
        if (q instanceof MCQQuestion) {

            //set the 3rd and 4th button back to visible state
            option3.setVisible(true);
            option4.setVisible(true);

            MCQQuestion mcq = (MCQQuestion) q;
            String[] options = mcq.getOptions();
            option1.setText("<html><body style='width: 250px'>A. " + options[0] + "</body></html>");
            option2.setText("<html><body style='width: 250px'>B. " + options[1] + "</body></html>");
            option3.setText("<html><body style='width: 250px'>C. " + options[2] + "</body></html>");
            option4.setText("<html><body style='width: 250px'>D. " + options[3] + "</body></html>");
        }

        //current question type is True/False
        else {
            option1.setText("True");
            option2.setText("False");
            option3.setVisible(false);
            option4.setVisible(false);
        }

        //restart timer
        //max time given is 20s
        timeLeft = 20;
        timerLabel.setText("Time Left: 20s");
        questionTimer.restart();
    }

    private String getSelectedAnswer() {
        Question q = manager.getQuestion(currentQuestion);

        if (q instanceof TrueFalseQuestion) {
            if (option1.isSelected())
                return "True";
            if (option2.isSelected())
                return "False";
        } else if (q instanceof MCQQuestion) {

            if (option1.isSelected())
                return "a";
            if (option2.isSelected())
                return "b";
            if (option3.isSelected())
                return "c";
            if (option4.isSelected())
                return "d";
        }
        return "";

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Back to main page
        if (e.getSource() == quitButton)
        {
            parentFrame.dispose();
            new MainPage();
            return;
        }

        questionTimer.stop();//stop the timer when next button clicked
        String answer = getSelectedAnswer();

        if (answer.equals("")) {
            JOptionPane.showMessageDialog(this, "Please select an answer first!", "No Answer Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Question q = manager.getQuestion(currentQuestion);

        if (q.checkAnswer(answer)) {
            if (timeLeft >= 15) {//if user answers within 5 secs
                manager.increaseScore(2);
            } else {//if user answers after 5 secs
                manager.increaseScore(1);
            }

            scoreLabel.setText("Current Score: " + manager.getScore());
        }

        currentQuestion++;

        //still have remaining questions 
        if (currentQuestion < manager.getTotalQuestions()) 
        {
            group.clearSelection(); //clear current choice
            displayQuestion(); //display next question
        } 

        //last question
        else 
        {
            showFinalResults();
        }
    }

    //styled button
    private JButton createButton(String text) {
        JButton button = new JButton(text);

        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(60, 179, 113));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        return button;
    }

    // styled radio button
    private JRadioButton createRadioButton(String text) {
        JRadioButton radioButton = new JRadioButton(text);

        radioButton.setFont(new Font("Arial", Font.BOLD, 16));
        radioButton.setBackground(Color.WHITE);
        radioButton.setFocusPainted(false);
        radioButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(34, 139, 34), 2),
                BorderFactory.createEmptyBorder(
                        15, 15, 15, 15)
        ));

        return radioButton;
    }

    private void showFinalResults() {
        int percentage = manager.getPercentage();
        int stars = manager.getStars();
        String starDisplay = "";
        for (int i = 0; i < stars; i++) {
            starDisplay += "⭐";
        }

        String message = "Quiz Finished!\n\n" +
                "Score: " + manager.getScore() + "\n" +
                "Percentage: " + percentage + "%\n" +
                "Rating: " + starDisplay + "\n\n" +
                manager.getMotivationMessage();

        JLabel messageLabel = new JLabel("<html>" + message.replace("\n", "<br>") + "</html>");
        messageLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));

        JOptionPane.showMessageDialog(this, messageLabel, "Results", JOptionPane.INFORMATION_MESSAGE);

        // save player to leaderboard
        PlayerProfile player = new PlayerProfile(username, manager.getScore(), manager.getStars());
        player.setTime();
        gamificationManager = new GamificationManager(player);
        gamificationManager.savePlayer();

        
        parentFrame.dispose();
        new MainPage();
    }

    private void handleTimeout() {
        JOptionPane.showMessageDialog(this, "Time's up!", "Timeout", JOptionPane.ERROR_MESSAGE);
        // Proceed to next question without increasing score
        currentQuestion++;
        if (currentQuestion < manager.getTotalQuestions()) {
            group.clearSelection();
            displayQuestion();
        } else {
            showFinalResults(); // Call results dialog logic
        }
    }
}




