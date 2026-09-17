package linstezh.executionManagers;

import javafx.stage.Stage;

public interface SectionManager {
    void display();
    void loadNextScene();
    void concludeSection();
    Stage getPrimaryStage();
    String getSectionTitle();
}
