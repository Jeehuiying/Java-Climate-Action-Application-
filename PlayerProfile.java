/*Contributed by Leong Jao Yuan (104742) 
    Tested by Loh Zhi Yun (104788)
    Debugged by Loh Zhi Yun (104788)*/

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PlayerProfile{
    private String username;
    private String badge;
    private int score;
    private int stars;
    private LocalDateTime time;

    public PlayerProfile(String username, int score, int stars)
    {
        this.username = username;
        this.score = score;
        this.stars = stars;
        this.badge = "";
    }

    //getters
    public String getUsername(){
        return username;
    }
    public int getScore(){
        return score;
    }
    public int getStars(){
        return stars;
    }
    public LocalDateTime getTime(){
        return time;
    }
    public String getBadge(){
        return badge;
    }

    //setters
    public void setUsername(String username){
        this.username = username;
    }
    public void setBadge(String badge){
        this.badge = badge;
    }
    public void setScore(int score){
        this.score = score;
    }
    public void setStars(int stars){
        this.stars = stars;
    }

    //set time to now
    public void setTime()
    {
        this.time = LocalDateTime.now();
    }

    //set time to a provided time (LocalDateTime format)
    public void setTime(LocalDateTime time)
    {
        this.time = time;
    }

    public String toFile(){
        String timeStr = (time != null) ? time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : "";
        return username + "," + score + "," + stars + "," + badge + "," + timeStr;
    }

}