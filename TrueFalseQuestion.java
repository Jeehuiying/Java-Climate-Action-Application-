// Contributed by Jee Hui Ying (104511)

public class TrueFalseQuestion extends Question implements QuizBehavior 
{
    public TrueFalseQuestion(String questionText, String correctAnswer, String difficultyLevel)
    {
        super(questionText, correctAnswer, difficultyLevel);
    }

    @Override //from interface
    public void displayQuestion() 
    {
        System.out.println(questionText);
    }

    @Override //from interface
    public boolean checkAnswer(String answer) 
    {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}
