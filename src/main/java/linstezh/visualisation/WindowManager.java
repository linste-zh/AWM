package linstezh.visualisation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import linstezh.logic.ExperimentItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WindowManager {
    private final List<ExpItemAdapter> items = new ArrayList<>();
    private int currentItem = 0;
    private Stage primaryStage;
    
    public WindowManager(List<ExperimentItem> experimentItems){
        for(ExperimentItem item : experimentItems){
            items.add(new ExpItemAdapter(item));
        }
    }

    public void display(Stage primaryStage) {
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
