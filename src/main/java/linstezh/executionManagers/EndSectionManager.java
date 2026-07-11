package linstezh.executionManagers;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import linstezh.logic.Item.ItemInterface;
import linstezh.logic.Section.SectionInterface;
import linstezh.visualisation.adapters.InfoItemAdapter;
import linstezh.visualisation.screens.ExperimentEndScreen;

import java.util.List;

public class EndSectionManager implements SectionManager {
    private final SectionInterface section;
    private final ExperimentManager manager;
    private List<ItemInterface> items;
    private ItemInterface currentItem;
    private int nextItem = 0;
    private Stage primaryStage;

    public EndSectionManager(SectionInterface section, ExperimentManager manager){
        this.section = section;
        this.manager = manager;
        items = section.getItems();
    }

    public void display(Stage primaryStage) {
        this.primaryStage = primaryStage;
        nextItem = 0;
        loadNextScene();
    }

    public void requestCsvSave(){
        this.manager.saveResults();
    }

    public void loadNextScene(){
        Region newScene = null;
        newScene = new ExperimentEndScreen(new InfoItemAdapter(items.get(nextItem)), this).createContent();
        nextItem += 1;
        primaryStage.setScene(new Scene(newScene, 400, 200));
        primaryStage.show();
    }

    public void concludeSection(){
        manager.nextSection();
    }

}
