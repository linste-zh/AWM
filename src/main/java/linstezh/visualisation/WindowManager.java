package linstezh.visualisation;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import linstezh.logic.ExperimentItem;
import linstezh.logic.Section;

import java.util.ArrayList;
import java.util.List;

public class WindowManager {
    private final Section section;
    private final List<ExpItemAdapter> items = new ArrayList<>();
    private int currentItem = 0;
    private Stage primaryStage;
    
    public WindowManager(Section section){
        this.section = section;
        List<ExperimentItem> dbItems = section.getItemsAsList();
        for(ExperimentItem item : dbItems){
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
        Region newScene;
        if(currentItem < items.size()){
            ExpItemAdapter newItem = items.get(currentItem);
            newScene = new ExperimentItemScreen(newItem, this).createContent();
        }else{
            newScene = new ExperimentRecallScreen(items, this).createContent();
        }

        primaryStage.setScene(new Scene(newScene, 400, 200));
        primaryStage.show();
    }

}
