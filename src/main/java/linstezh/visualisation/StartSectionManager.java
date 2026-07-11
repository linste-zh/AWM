package linstezh.visualisation;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import linstezh.logic.ExperimentManager;
import linstezh.logic.Item.ItemInterface;
import linstezh.logic.Section.SectionInterface;

import java.util.List;

public class StartSectionManager implements SectionManager{
    private final SectionInterface section;
    private final ExperimentManager manager;
    private List<ItemInterface> items;
    private ItemInterface currentItem;
    private int nextItem = 0;
    private Stage primaryStage;

    public StartSectionManager(SectionInterface experimentSection, ExperimentManager manager){
        this.section = experimentSection;
        this.manager = manager;
        items = experimentSection.getItems();
    }

    public void display(Stage primaryStage) {
        this.primaryStage = primaryStage;
        nextItem = 0;
        loadNextScene();
    }

    public String getHeader(){
        return this.manager.getExperimentTitle();
    }

    public void loadNextScene(){
        Region newScene = null;
        newScene = new ExperimentStartScreen(new InfoItemAdapter(items.get(nextItem)), this).createContent();
        nextItem += 1;
        primaryStage.setScene(new Scene(newScene, 400, 200));
        primaryStage.show();
    }

    public void concludeSection(){
        manager.nextSection();
    }

}
