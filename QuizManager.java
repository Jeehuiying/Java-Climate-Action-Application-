/* Contributed by 

Jee Hui Ying (104511) 
Loh Zhi Yun (104788) 

Tested by Leong Jao Yuan (104742)

*/


import java.util.ArrayList;
import java.util.Collections;

public class QuizManager implements ScoreBehavior
{
    private ArrayList<Question> questions;
    private int score;
    private int totalQuestions; 

    public QuizManager(int questionCount) 
    {
        int mcqCount = questionCount / 2;                    // half MCQ
        int tfCount  = questionCount - mcqCount;             // half True/False

        questions = new ArrayList<>();

        //add questions retrieved from QuestionBank into ArrayList
        questions.addAll(QuestionBank.getInstance().getRandomQuestions("MCQ", mcqCount));
        questions.addAll(QuestionBank.getInstance().getRandomQuestions("True/False", tfCount));

        Collections.shuffle(questions);   //randomly reorders the elements
        totalQuestions = questions.size();
        score = 0;
    }
    
    public void addQuestion(Question q) 
    { 
        questions.add(q); 
    }

    public Question getQuestion(int index) 
    { 
        return questions.get(index); 
    }

    public void increaseScore(int points)
    { 
        score+=points;
    }

    @Override
    public int calculateScore() 
    { 
        return score; 
    }

    @Override
    public int getStars() 
    {
        int pct = getPercentage();
        if (pct >= 90) return 5;
        if (pct >= 80) return 4;
        if (pct >= 70) return 3;
        if (pct >= 60) return 2;
        return 1;
    }

    @Override
    public void resetScore() 
    {
        score = 0;
    }

    public int getScore()          { return score; }
    public int getTotalQuestions() { return totalQuestions; }
    public int getPercentage()     { return (score * 100) / 40; }//maximum total points=40

    public String getMotivationMessage() 
    {
        int pct = getPercentage();
        if (pct >= 80) return "Outstanding!";
        if (pct >= 60) return "That's good!";
        if (pct >= 40) return "Good try!";
        if (pct >= 20) return "You can do better!";
        return "Don't give up!";
    }
}