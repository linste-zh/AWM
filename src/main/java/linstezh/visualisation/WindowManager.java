package linstezh.visualisation;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import linstezh.logic.ExperimentItem;
import linstezh.logic.Item;
import linstezh.logic.ExperimentSection;

import java.util.ArrayList;
import java.util.List;

public class WindowManager {
    private final ExperimentSection experimentSection;
    private final List<ExpItemAdapter> items = new ArrayList<>();
    private int currentItem = 0;
    private Stage primaryStage;
    
    public WindowManager(ExperimentSection experimentSection){
        this.experimentSection = experimentSection;
        List<Item> dbItems = experimentSection.getItemsAsList();
        for(Item item : dbItems){
            if(item.getClass() == ExperimentItem.class){
                ExperimentItem expItem = (ExperimentItem) item;
                items.add(new ExpItemAdapter(expItem));
            }
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
