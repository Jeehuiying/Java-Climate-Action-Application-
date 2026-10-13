//Contributed by Jee Hui Ying (104511)

import javax.swing.*;

public class ClimateActionApp {

    // Attributes
    private String appTitle;
    private String sdgTheme;
    private String currentVersion;

    // Constructor
    public ClimateActionApp() 
    {

        appTitle = "Climate Action Learning App";
        sdgTheme = "SDG 13 - Climate Action";
        currentVersion = "1.0";
    }

    public void launchApplication() 
    {

        new MainPage();
    }

    public void displayWelcomeMessage() 
    {

        JOptionPane.showMessageDialog(null,"Welcome to " + appTitle + "\nTheme: " + sdgTheme + "\nVersion: " + currentVersion);
    }


    // Main method
    public static void main(String[] args) 
    {

        ClimateActionApp app = new ClimateActionApp();
        app.displayWelcomeMessage();
        app.launchApplication();
    }
}