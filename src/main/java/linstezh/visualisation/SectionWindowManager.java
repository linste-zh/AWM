package linstezh.visualisation;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import linstezh.logic.ExperimentItem;
import linstezh.logic.ExperimentManager;
import linstezh.logic.ExperimentSection;

import java.util.ArrayList;
import java.util.List;

public class SectionWindowManager {
    private final ExperimentSection experimentSection;
    private final ExperimentManager manager;
    private List<ExperimentItem> items;
    private ExperimentItem currentItem;
    private int nextItem = 0;
    private Stage primaryStage;
    
    public SectionWindowManager(ExperimentSection experimentSection, ExperimentManager manager){
        this.experimentSection = experimentSection;
        this.manager = manager;
        items = experimentSection.getItemsAsList();
    }

    public void display(Stage primaryStage) {
        this.primaryStage = primaryStage;
        nextItem = 0;
        loadNextScene();
    }

    public void reportEval(ExpItemAdapter itemAdapter) throws Exception {
        manager.saveEvalResponse(currentItem, itemAdapter.readCorrectEval());
    }

    public void reportMemorisedChunks(List<ExpItemAdapter> itemAdapters) throws Exception {
        for(int i = 0; i < itemAdapters.size(); i++){
            manager.saveMemResponse((ExperimentItem) items.get(i), itemAdapters.get(i).readUserMemoryChunk());
        }
    }

    public void loadNextScene(){
        Region newScene;
        if(nextItem < items.size()){
            currentItem = (ExperimentItem) items.get(nextItem);
            ExpItemAdapter newItem = new ExpItemAdapter(currentItem);
            newScene = new ExperimentItemScreen(newItem, this).createContent();
            nextItem += 1;
        }else{
            List<ExpItemAdapter> adaptedItems = new ArrayList<>();
            for(ExperimentItem item : items){
                adaptedItems.add(new ExpItemAdapter(item));
            }
            newScene = new ExperimentRecallScreen(adaptedItems, this).createContent();
        }

        primaryStage.setScene(new Scene(newScene, 400, 200));
        primaryStage.show();
    }

    public void concludeSection() throws Exception {
        manager.nextSection();
    }

}
