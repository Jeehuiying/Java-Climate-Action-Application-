//Contributed by Jee Hui Ying (104511) 

public abstract class Question 
{
    protected String questionText;
    protected String correctAnswer;
    protected String difficultyLevel; // easy, medium, hard

    public Question(String questionText, String correctAnswer, String difficultyLevel) 
    {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.difficultyLevel = difficultyLevel;
    }
    public String getQuestionText() 
    {
        return questionText;
    }
    
    public String getCorrectAnswer() 
    {
        return correctAnswer;
    }

    public String getDifficultyLevel() 
    {
        return difficultyLevel;
    }

    public abstract boolean checkAnswer(String answer);
}