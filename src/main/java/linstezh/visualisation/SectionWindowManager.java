package linstezh.visualisation;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import linstezh.logic.ExperimentItem;
import linstezh.logic.ExperimentManager;
import linstezh.logic.Item;
import linstezh.logic.ExperimentSection;

import java.util.ArrayList;
import java.util.List;

public class SectionWindowManager {
    private final ExperimentSection experimentSection;
    private final ExperimentManager manager;
    private final List<ExpItemAdapter> items = new ArrayList<>();
    private int nextItem = 0;
    private Stage primaryStage;
    
    public SectionWindowManager(ExperimentSection experimentSection, ExperimentManager manager){
        this.experimentSection = experimentSection;
        this.manager = manager;
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
        nextItem = 0;
        loadNextScene();
    }

    public void loadNextScene(){
        Region newScene;
        if(nextItem < items.size()){
            ExpItemAdapter newItem = items.get(nextItem);
            newScene = new ExperimentItemScreen(newItem, this).createContent();
        }else{
            newScene = new ExperimentRecallScreen(items, this).createContent();
        }

        primaryStage.setScene(new Scene(newScene, 400, 200));
        primaryStage.show();

        nextItem += 1;
    }

    public void concludeSection() throws Exception {
        manager.nextSection();
    }

}
