package linstezh.executionManagers;

import javafx.stage.Stage;

public interface SectionManager {
    void display(Stage primaryStage);
    void loadNextScene();
    void concludeSection();
}
