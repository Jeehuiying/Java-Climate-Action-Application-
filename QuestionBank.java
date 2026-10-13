/*Contributed by Loh Zhi Yun (104788) 
    Tested by Lim GeK Hong (104763)*/
    
import java.util.ArrayList;
import java.util.Collections;

public class QuestionBank 
{
    private ArrayList<Question> allQuestions;
    private static QuestionBank instance;   // Singleton pattern
    private int totalMCQ;
    private int totalTrueFalse;

    private QuestionBank() 
    {
        allQuestions = new ArrayList<>();
        totalMCQ = 0;
        totalTrueFalse = 0;
        loadAllQuestions(); 
    }

    // Singleton — only one bank exists
    public static QuestionBank getInstance() 
    {
        if (instance == null)
            instance = new QuestionBank();
        return instance;
    }

    private void addMCQ(MCQQuestion q) {
        allQuestions.add(q);
        totalMCQ++;
    }

    private void addTrueFalse(TrueFalseQuestion q) {
        allQuestions.add(q);
        totalTrueFalse++;
    }

    //add all questions into ArrayList allQuestions
    private void loadAllQuestions()
    {
        // MCQs 
        addMCQ(new MCQQuestion("SDG 13's main goal is to:", 
            new String[]{"End all pollution", "Take urgent action to combat climate change and its impacts", "Protect only marine ecosystems", "Reduce global population"}, "b", "Easy"));
        addMCQ(new MCQQuestion("Since the 1800s, the primary cause of climate change has been:",
            new String[]{"Changes in Earth's orbit", "Volcanic eruptions", "Human activities burning fossil fuels", "Solar flares"}, "c", "Easy"));
        addMCQ(new MCQQuestion("Which gas is a major greenhouse gas?",
            new String[]{"Oxygen", "Nitrogen", "Carbon dioxide", "Helium"}, "c", "Easy"));        
        addMCQ(new MCQQuestion("Cutting down forests increases climate change mainly because:",
            new String[]{"It cools the land too much", "Stored carbon is released and fewer trees absorb CO₂", "It increases rainfall", "It reduces deserts"}, "b", "Easy"));
        addMCQ(new MCQQuestion("Transport emissions mostly come from:",
            new String[]{"Cars, planes and ships", "Bicycles and walking", "Electric scooters", "Public transportation"}, "a", "Easy"));
        addMCQ(new MCQQuestion("One major impact of rising ocean temperature and levels is: ",
            new String[]{"Larger deserts in polar regions only", "Threats to coastal cities and small island nations", "More mountain snowfall", "Lower sea acidity"}, "b", "Easy"));  
        addMCQ(new MCQQuestion("Target 13.3 of SDG 13 focuses on:",
            new String[]{"Building dams", "Improving education and awareness about climate change", "Increasing fossil fuel use", "Expanding airports"}, "b", "Easy"));
        addMCQ(new MCQQuestion("Industry contributes to climate change mainly by:",
            new String[]{"Planting trees", "Using fossil fuels and releasing greenhouse gases in production", "Reducing waste", "Storing CO₂ underground"}, "b","Medium"));
        addMCQ(new MCQQuestion("Which combination best describes SDG 13's core priorities?",
            new String[]{"Mitigation, adaptation, integration into policies, education", "Tourism, trade, migration, defense", "Space exploration, mining, robotics, AI", "Only renewable energy"}, "a", "Medium"));  
        addMCQ(new MCQQuestion("Climate change increases health risks by:",
            new String[]{"Eliminating all diseases", "Improving air quality", "Contributing to air pollution, disease spread, and injuries from extremes", "Making all regions cooler"}, "c", "Medium"));
        addMCQ(new MCQQuestion("SDG 13 Target 13.a/b mainly relate to:",
            new String[]{"Ocean cleanup", "Mobilizing finance and support for developing countries", "Banning all cars", "Expanding airports"}, "b", "Medium"));
        addMCQ(new MCQQuestion("A key benefit of climate action mentioned is:",
            new String[]{"More air pollution", "Increased premature deaths", "Green jobs and economic growth", "Higher food waste"}, "c", "Medium"));  
        addMCQ(new MCQQuestion("The Paris Agreement aims mainly to:",
            new String[]{"Limit global warming to well below 2°C above pre-industrial levels", "Eliminate all greenhouse gas emissions", "Increase fossil fuel use", "Expand international trade"}, "a", "Medium"));
        addMCQ(new MCQQuestion("NDCs (Nationally Determined Contributions) are:",
            new String[]{"Local recycling rules", "Voluntary charity projects", "National pledges that largely determine if SDG 13 goals are met", "Only university projects"}, "c", "Medium"));
        addMCQ(new MCQQuestion("The “knowledge-action gap” in climate education refers to:",
            new String[]{"Students refusing to learn science", "Lack of teachers", "Too many exams", "High knowledge but little actual behaviour change"}, "d", "Hard"));  
        addMCQ(new MCQQuestion("Effective SDG 13 education should combine:",
            new String[]{"Only cognitive learning", "Cognitive, socio-emotional, and behavioural learning", "Only outdoor activities", "Only online videos"}, "b", "Hard"));
        addMCQ(new MCQQuestion("CCS (Carbon Capture and Storage) is especially important for:",
            new String[]{"Agriculture only", "Hard-to-decarbonize industries like cement and steel", "Tourism sectors", "Education systems"}, "b", "Hard"));
        addMCQ(new MCQQuestion("One major governance barrier to SDG 13 is: ",
            new String[]{"Excessive climate finance to vulnerable countries", "Overly strong local capacity", "Too many trained officials", "Fragmented authority and poor coordination across levels of government"}, "d", "Hard"));  
        addMCQ(new MCQQuestion("A climate-just approach to SDG 13 should:",
            new String[]{"Ignore vulnerable groups", "Prioritize support to highly vulnerable regions and ensure inclusive participation", "Focus only on rich countries", "Fund only mitigation, not adaptation"}, "b", "Hard"));
        addMCQ(new MCQQuestion("SDG 13 solutions are most effective when:",
            new String[]{"Technology, finance, and governance work together", "Only technology is used", "Focus only on rich countries", "Fund only mitigation, not adaptation"}, "a", "Hard"));
        
        // True False Questions
        addTrueFalse(new TrueFalseQuestion("SDG 13 focuses on taking action to combat climate change and its impacts.", "True", "Easy"));
        addTrueFalse(new TrueFalseQuestion("Climate change is mainly caused by human activities such as burning fossil fuels.", "True", "Easy"));
        addTrueFalse(new TrueFalseQuestion("Renewable energy sources like solar and wind produce more pollution than coal.", "False", "Easy"));
        addTrueFalse(new TrueFalseQuestion("Cutting down forests can increase climate change because fewer trees absorb CO₂.", "True", "Easy"));
        addTrueFalse(new TrueFalseQuestion("Transportation such as cars, planes, and ships does not contribute to greenhouse gas emissions.", "False", "Easy"));
        addTrueFalse(new TrueFalseQuestion("Climate change can cause stronger storms, floods, and droughts.", "True", "Easy"));
        addTrueFalse(new TrueFalseQuestion("SDG 13 Target 13.3 focuses on improving climate change education and awareness.", "True", "Easy"));
        addTrueFalse(new TrueFalseQuestion("Climate action only benefits the environment and has no effect on human health or the economy.", "False", "Easy"));
        addTrueFalse(new TrueFalseQuestion("Green finance helps support projects such as renewable energy and sustainable infrastructure.", "True", "Easy"));
        addTrueFalse(new TrueFalseQuestion("Every individual can contribute to SDG 13 by saving energy and spreading climate awareness.","True", "Easy"));
        addTrueFalse(new TrueFalseQuestion("Together with agriculture, cutting down forests is responsible for roughly about one-third of global greenhouse gas emissions.", "True", "Medium"));
        addTrueFalse(new TrueFalseQuestion("Road transport is currently a smaller contributor to energy-related emissions than air and sea transport.", "False", "Medium"));
        addTrueFalse(new TrueFalseQuestion("The Paris Agreement is an international treaty that aims to limit global warming to well below 2 degrees Celsius.", "True", "Medium"));
        addTrueFalse(new TrueFalseQuestion("The Arctic is warming at approximately twice the global average rate or even faster.","True", "Medium"));
        addTrueFalse(new TrueFalseQuestion("Many climate education programs improve students' knowledge but often result in only small or limited changes in actual behaviour.", "True", "Medium"));
        addTrueFalse(new TrueFalseQuestion("SDG 13 is a stand-alone goal that is largely independent from other SDGs such as energy, food, and poverty.", "False", "Hard"));
        addTrueFalse(new TrueFalseQuestion("Target 13.2 of SDG 13 is mainly about climate education and public awareness.", "False", "Hard"));
        addTrueFalse(new TrueFalseQuestion("Many countries have fully translated their NDC pledges into coherent national policies, with only about one-quarter failing to do so.", "False", "Hard"));
        addTrueFalse(new TrueFalseQuestion("Carbon Capture and Storage (CCS) can capture a majority, around 80-90%of CO₂ emissions from some industrial sources when properly applied.", "True", "Hard"));
        addTrueFalse(new TrueFalseQuestion("Green finance in developing countries is mainly limited by low project emissions rather than institutional or market barriers.", "False", "Hard"));
    }

    public int getTotalMCQ() { 
        return totalMCQ;
    }

    public int getTotalTrueFalse() { 
        return totalTrueFalse;
    }

     // Get random N questions from the bank
    public ArrayList<Question> getRandomQuestions(String type, int count) 
    {
        ArrayList<Question> shuffled = new ArrayList<>();

        for (Question q : allQuestions) { //take each question from allQuestions one by one
            if (type.equalsIgnoreCase("MCQ") && q instanceof MCQQuestion) {
                shuffled.add(q);
            } else if (type.equalsIgnoreCase("True/False") && q instanceof TrueFalseQuestion) {
                shuffled.add(q);
            }
        }

        //return a new ArrayList containing 'count' numbers of elements from the shuffled list.
        Collections.shuffle(shuffled);
        return new ArrayList<>(shuffled.subList(0, Math.min(count, shuffled.size())));
    }

    public int getTotalQuestions() { 
        return allQuestions.size(); 
    }

    public ArrayList<Question> getAllQuestions() { 
        return allQuestions;
    }
}