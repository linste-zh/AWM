package linstezh.executionManagers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import linstezh.logic.Item.ExperimentItem;
import linstezh.logic.Item.ItemInterface;
import linstezh.logic.Item.ItemTypes;
import linstezh.logic.Section.SectionInterface;
import linstezh.visualisation.adapters.ExpItemAdapter;
import linstezh.visualisation.adapters.ImageDistractorItemAdapter;
import linstezh.visualisation.adapters.TextDistractorItemAdapter;
import linstezh.visualisation.controllers.ExperimentItemController;
import linstezh.visualisation.controllers.ExperimentRecallController;
import linstezh.visualisation.controllers.ImageDistractorController;
import linstezh.visualisation.controllers.TextDistractorController;

import java.io.IOException;
import java.util.*;
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

        Iterator<ExpItemAdapter> iterator = unscoredItemAdapters.iterator();
        while (iterator.hasNext()) {
            ExpItemAdapter itemAdapter = iterator.next();
            if (orphanedChunks.contains(itemAdapter.readMemoryChunk())) {
                itemAdapter.setScore(1);
                orphanedChunks.remove(itemAdapter.readMemoryChunk());
                iterator.remove();
            }
        }


        for (ExpItemAdapter itemAdapter : itemAdapters) {
            manager.saveMemResponse(itemAdapter.getBaseItem(), itemAdapter.readUserMemoryChunk(), itemAdapter.readScore());
        }
    }

    public void loadNextScene() {
        if(nextItem < items.size()) {
            currentItem = items.get(nextItem);
            currentItem.setDisplayDate(new Date());
            if (currentItem.getType() == ItemTypes.EXPERIMENT){
                loadExperimentItemScreen((ExperimentItem) currentItem);
            }else if (currentItem.getType() == ItemTypes.DISTRACTOR_TXT) {
                loadTxtDistractorScreen(currentItem);
            }else if (currentItem.getType() == ItemTypes.DISTRACTOR_IMG) {
                loadImgDistractorScreen(currentItem);
            }
        }else{
            loadRecallScreen();
        }
    }

    public void loadExperimentItemScreen(ExperimentItem item){
        try {
            ExpItemAdapter newItem = new ExpItemAdapter(item);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../screens/ExperimentItemScreen.fxml"));
            Parent root = loader.load();
            ExperimentItemController controller = loader.getController();
            controller.init(newItem, this);
            primaryStage.getScene().setRoot(root);
            nextItem += 1;
        }catch(IOException e){
            nextItem += 1;  //todo: meaningful catch!
            loadNextScene();
        }
    }

    public void loadTxtDistractorScreen(ItemInterface item){
        try {
            TextDistractorItemAdapter newTxtDistractor = new TextDistractorItemAdapter(currentItem);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../screens/TextDistractorScreen.fxml"));
            Parent root = loader.load();
            TextDistractorController controller = loader.getController();
            controller.init(newTxtDistractor, this);
            primaryStage.getScene().setRoot(root);
            nextItem += 1;
        }catch(IOException e){
            nextItem += 1;  //todo: meaningful catch!
            loadNextScene();
        }
    }

    public void loadImgDistractorScreen(ItemInterface item){
        try {
            ImageDistractorItemAdapter newImgDistractor = new ImageDistractorItemAdapter(currentItem);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../screens/ImageDistractorScreen.fxml"));
            Parent root = loader.load();
            ImageDistractorController controller = loader.getController();
            controller.init(newImgDistractor, this);
            primaryStage.getScene().setRoot(root);
            nextItem += 1;
        }catch(IOException e){
            nextItem += 1; //todo: meaningful catch!
            loadNextScene();
        }
    }

    public void loadRecallScreen(){
        try {
            List<ExpItemAdapter> adaptedItems = new ArrayList<>();
            for(ItemInterface item : items){
                if(item.getType() == ItemTypes.EXPERIMENT) {
                    adaptedItems.add(new ExpItemAdapter((ExperimentItem) item));
                }
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../screens/ExperimentRecallScreen.fxml"));
            Parent root = loader.load();
            ExperimentRecallController controller = loader.getController();
            controller.init(adaptedItems, this);
            primaryStage.getScene().setRoot(root);
            nextItem += 1;
        }catch(IOException e){
            concludeSection(); //todo: meaningful catch!
        }
    }

    public void concludeSection(){
        manager.nextSection();
    }

}
