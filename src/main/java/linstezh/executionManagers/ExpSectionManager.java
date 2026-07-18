package linstezh.executionManagers;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import linstezh.logic.Item.ExperimentItem;
import linstezh.logic.Item.ItemInterface;
import linstezh.logic.Item.ItemTypes;
import linstezh.logic.Section.SectionInterface;
import linstezh.visualisation.adapters.ExpItemAdapter;
import linstezh.visualisation.screens.ExperimentItemScreen;
import linstezh.visualisation.screens.ExperimentRecallScreen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ExpSectionManager implements SectionManager {
    private final SectionInterface section;
    private final ExperimentManager manager;
    private List<ItemInterface> items;
    private ItemInterface currentItem;
    private int nextItem = 0;
    private Stage primaryStage;
    
    public ExpSectionManager(SectionInterface experimentSection, ExperimentManager manager){
        this.section = experimentSection;
        this.manager = manager;
        items = experimentSection.getItems();
    }

    public void display(Stage primaryStage) {
        this.primaryStage = primaryStage;
        nextItem = 0;
        loadNextScene();
    }

    public void reportEval(ExpItemAdapter itemAdapter){
        int score = itemAdapter.readCorrectEval() == itemAdapter.readUserEval() ? 1 : 0;
        manager.saveEvalResponse(itemAdapter.getBaseItem(), itemAdapter.readUserEval(), score);
    }

    public void reportMemorisedChunks(List<ExpItemAdapter> itemAdapters){
        List<ExpItemAdapter> unscoredItemAdapters = new ArrayList<>(itemAdapters);

        //Score all correctly memorised chunks as 2
        for (ExpItemAdapter itemAdapter : itemAdapters) {
            if(Objects.equals(itemAdapter.readMemoryChunk(), itemAdapter.readUserMemoryChunk())){
                itemAdapter.setScore(2);
                unscoredItemAdapters.remove(itemAdapter);
            }
        }

        //Check items that were not remembered correctly whether their chunk was noted in a different position, if so score as 1
        List<String> orphanedChunks = unscoredItemAdapters.stream()
                .map(ExpItemAdapter::readUserMemoryChunk)
                .collect(Collectors.toList());
        /*for(ExpItemAdapter itemAdapter : unscoredItemAdapters){
            if(orphanedChunks.contains(itemAdapter.readMemoryChunk())){
                itemAdapter.setScore(1);
                orphanedChunks.remove(itemAdapter.readMemoryChunk());
                unscoredItemAdapters.remove(itemAdapter);
            }
        }*/
        Iterator<ExpItemAdapter> iterator = unscoredItemAdapters.iterator();
        while (iterator.hasNext()) {
            ExpItemAdapter itemAdapter = iterator.next();
            if (orphanedChunks.contains(itemAdapter.readMemoryChunk())) {
                itemAdapter.setScore(1);
                orphanedChunks.remove(itemAdapter.readMemoryChunk());
                iterator.remove(); // safe removal during iteration
            }
        }


        for (ExpItemAdapter itemAdapter : itemAdapters) {
            manager.saveMemResponse(itemAdapter.getBaseItem(), itemAdapter.readUserMemoryChunk(), itemAdapter.readScore());
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
            System.out.println("Skipped item");
            nextItem += 1;
            loadNextScene();
        }

    }

    public void concludeSection(){
        manager.nextSection();
    }

}
