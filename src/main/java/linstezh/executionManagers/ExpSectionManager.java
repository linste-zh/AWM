package linstezh.executionManagers;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import linstezh.logic.Item.ExperimentItem;
import linstezh.logic.Item.ItemInterface;
import linstezh.logic.Item.ItemTypes;
import linstezh.logic.Section.SectionInterface;
import linstezh.ui.adapters.ExpItemAdapter;
import linstezh.ui.adapters.ImageDistractorItemAdapter;
import linstezh.ui.adapters.InfoItemAdapter;
import linstezh.ui.adapters.TextDistractorItemAdapter;
import linstezh.ui.controllers.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ExpSectionManager implements SectionManager {
    protected final ExperimentManager manager;
    protected final RootController rootController;
    protected final Stage primaryStage;
    protected List<ItemInterface> items;
    protected ItemInterface currentItem;
    protected int nextItem = 0;
    protected boolean recallOutstanding = true;
    protected Timer timer;

    public ExpSectionManager(SectionInterface experimentSection, ExperimentManager manager, RootController rootController, Stage primaryStage){
        this.manager = manager;
        this.rootController = rootController;
        this.primaryStage = primaryStage;
        items = experimentSection.getItems();
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }

    public void display() {
        nextItem = 0;
        loadNextScene();
    }

    public void loadNextScene() {
        resetTimer();

        if(nextItem < items.size()) {
            loadNextItem();
        }else if(recallOutstanding){
            loadRecallScreen();
        }else{
            concludeSection();
        }
    }

    public void resetTimer(){
        System.out.println("called scene load: " + nextItem);
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void loadNextItem(){
        currentItem = items.get(nextItem);
        currentItem.setDisplayDate(new Date());

        switch(currentItem.getType()){
            case ItemTypes.EXPERIMENT ->  loadExperimentItemScreen((ExperimentItem) currentItem);
            case ItemTypes.DISTRACTOR_TXT -> loadTxtDistractorScreen(currentItem);
            case ItemTypes.DISTRACTOR_IMG -> loadImgDistractorScreen(currentItem);
            case ItemTypes.INFORMATION -> loadInformationScreen(currentItem);
        }

    }

    public void loadExperimentItemScreen(ExperimentItem item){
        try {
            ExpItemAdapter newItem = new ExpItemAdapter(item);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/linstezh/ui/screens/ExperimentItemScreen.fxml"));
            Parent content = loader.load();
            ExperimentItemController controller = loader.getController();
            controller.init(newItem, this);
            /*rootController.getHeader().setItem(currentItem.getPosition());*/
            rootController.setContent(content);
            timer = new Timer(true);
            timer.schedule(new TimerExceeded(item), 3000);
            nextItem += 1;
        }catch(IOException e){
            nextItem += 1;  //todo: meaningful catch!
            loadNextScene();
        }
    }

    public void reportEval(ExpItemAdapter itemAdapter){
        int score = itemAdapter.readCorrectEval() == itemAdapter.readUserEval() ? 1 : 0;
        manager.saveEvalResponse(itemAdapter.getBaseItem(), itemAdapter.readUserEval(), score);
    }

    public void loadInformationScreen(ItemInterface item){
        try {
            InfoItemAdapter newInfo = new InfoItemAdapter(currentItem);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/linstezh/ui/screens/ExperimentInfoScreen.fxml"));
            Parent content = loader.load();
            ExperimentInfoController controller = loader.getController();
            controller.init(newInfo, this);
            rootController.setContent(content);
            /*rootController.getHeader().setItem(currentItem.getPosition());*/
            nextItem += 1;
        }catch(IOException e){
            nextItem += 1;  //todo: meaningful catch!
            loadNextScene();
        }
    }

    public void loadTxtDistractorScreen(ItemInterface item){
        try {
            TextDistractorItemAdapter newTxtDistractor = new TextDistractorItemAdapter(currentItem);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/linstezh/ui/screens/TextDistractorScreen.fxml"));
            Parent content = loader.load();
            TextDistractorController controller = loader.getController();
            controller.init(newTxtDistractor, this);
            rootController.setContent(content);
            /*rootController.getHeader().setItem(currentItem.getPosition());*/
            nextItem += 1;
        }catch(IOException e){
            nextItem += 1;  //todo: meaningful catch!
            loadNextScene();
        }
    }

    public void loadImgDistractorScreen(ItemInterface item){
        System.out.println("creating image");
        try {
            ImageDistractorItemAdapter newImgDistractor = new ImageDistractorItemAdapter(currentItem);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/linstezh/ui/screens/ImageDistractorScreen.fxml"));
            Parent content = loader.load();
            ImageDistractorController controller = loader.getController();
            controller.init(newImgDistractor, this);
            rootController.setContent(content);
            /*rootController.getHeader().setItem(currentItem.getPosition());*/
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

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/linstezh/ui/screens/ExperimentRecallScreen.fxml"));
            Parent content = loader.load();
            ExperimentRecallController controller = loader.getController();
            controller.init(adaptedItems, this);
            rootController.setContent(content);
            /*rootController.getHeader().setItem("Recall");*/
            nextItem += 1;
        }catch(IOException e){
            concludeSection(); //todo: meaningful catch!
        }
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

        recallOutstanding = false;
        System.out.println("recall completed");
    }

    public void concludeSection(){
        manager.nextSection();
    }

    private class TimerExceeded extends TimerTask{
        private ExperimentItem item;

        public TimerExceeded(ExperimentItem item){
            this.item = item;
        }

        @Override
        public void run() {
            Platform.runLater(() -> {
                System.out.println("Timer ran out for " + item);
                manager.saveEvalResponse(item, false, 0);  //todo: change boolean to string for NA value

                loadNextScene();
            });
        }
    }

}

