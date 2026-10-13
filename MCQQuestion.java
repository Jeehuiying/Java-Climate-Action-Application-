//Contributed by Loh Zhi Yun (104788)

public class MCQQuestion extends Question implements QuizBehavior 
{
    private String[] options;
    public MCQQuestion(String questionText, String[] options, String correctAnswer, String difficultyLevel) 
    {
        super(questionText, correctAnswer, difficultyLevel);
        this.options = options;
    }
    public String[] getOptions() 
    {
        return options;
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
