// interface for Learning Module behaviour,contribute by:Lim Gek Hong 104763

public interface LearningModuleBehavior {
    void nextPage();
    void previousPage();
    void displayPage(int page);
    String getCurrentContent();
    int getCurrentPageNumber();
    int getTotalPages();
}

