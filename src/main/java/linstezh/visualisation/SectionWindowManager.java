package linstezh.visualisation;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import linstezh.logic.ExperimentManager;
import linstezh.logic.Item.ExperimentItem;
import linstezh.logic.Item.ItemInterface;
import linstezh.logic.Item.ItemTypes;
import linstezh.logic.Section.Section;
import linstezh.logic.Section.SectionInterface;

import java.util.ArrayList;
import java.util.List;

public class SectionWindowManager {
    private final SectionInterface section;
    private final ExperimentManager manager;
    private List<ItemInterface> items;
    private ItemInterface currentItem;
    private int nextItem = 0;
    private Stage primaryStage;
    
    public SectionWindowManager(SectionInterface experimentSection, ExperimentManager manager){
        this.section = experimentSection;
        this.manager = manager;
        items = experimentSection.getItems();
    }

    public void display(Stage primaryStage) {
        this.primaryStage = primaryStage;
        nextItem = 0;
        loadNextScene();
    }

    public void reportEval(ExpItemAdapter itemAdapter) throws Exception {
        manager.saveEvalResponse(itemAdapter.getBaseItem(), itemAdapter.readCorrectEval());
    }

    public void reportMemorisedChunks(List<ExpItemAdapter> itemAdapters) throws Exception {
        for(int i = 0; i < itemAdapters.size(); i++){
            manager.saveMemResponse(itemAdapters.get(i).getBaseItem(), itemAdapters.get(i).readUserMemoryChunk());
        }
    }

    public void loadNextScene(){
        Region newScene = null;
        if(nextItem < items.size()) {
            currentItem = items.get(nextItem);
            if (currentItem.getType() == ItemTypes.EXPERIMENT){
                ExpItemAdapter newItem = new ExpItemAdapter((ExperimentItem) currentItem);
                newScene = new ExperimentItemScreen(newItem, this).createContent();
                nextItem += 1;
            }

        }else{
            List<ExpItemAdapter> adaptedItems = new ArrayList<>();
            for(ItemInterface item : items){
                if(item.getType() == ItemTypes.EXPERIMENT) {
                    adaptedItems.add(new ExpItemAdapter((ExperimentItem) item));
                }
            }
            newScene = new ExperimentRecallScreen(adaptedItems, this).createContent();
        }

        if(newScene != null){
            primaryStage.setScene(new Scene(newScene, 400, 200));
            primaryStage.show();
        }else{
            nextItem += 1;
            loadNextScene();
        }

    }

    public void concludeSection() throws Exception {
        manager.nextSection();
    }

}
