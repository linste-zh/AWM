package linstezh;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import linstezh.visualisation.ExpItemAdapter;
import linstezh.visualisation.ExperimentItemScreen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Application {
    private final List<ExpItemAdapter> items = new ArrayList<>(Arrays.asList(
            new ExpItemAdapter("Question 1", false),
            new ExpItemAdapter("Question 2", false)
    ));
    private int currentItem = 0;
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        ExpItemAdapter newItem = items.get(currentItem);
        Region newScene = new ExperimentItemScreen(newItem, this).createContent();
        primaryStage.setScene(new Scene(newScene, 400, 200));
        primaryStage.show();
    }

    public void loadNextScene(){
        currentItem += 1;
        ExpItemAdapter newItem = items.get(currentItem);
        Region newScene = new ExperimentItemScreen(newItem, this).createContent();

        primaryStage.setScene(new Scene(newScene, 400, 200));
        primaryStage.show();
    }

}