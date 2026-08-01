package linstezh.executionManagers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import linstezh.logic.Item.ItemInterface;
import linstezh.logic.Section.SectionInterface;
import linstezh.ui.adapters.InfoItemAdapter;
import linstezh.ui.controllers.ExperimentStartController;

import java.io.IOException;
import java.util.List;

public class StartSectionManager implements SectionManager {
    private final SectionInterface section;
    private final ExperimentManager manager;
    private List<ItemInterface> items;
    private ItemInterface currentItem;
    private int nextItem = 0;
    private Stage primaryStage;

    public StartSectionManager(SectionInterface section, ExperimentManager manager){
        this.section = section;
        this.manager = manager;
        items = section.getItems();
    }

    public void display(Stage primaryStage) {
        this.primaryStage = primaryStage;
        nextItem = 0;
        loadNextScene();
    }

    public String getHeader(){
        return this.manager.getExperimentTitle();
    }

    public void submitParticipantName(String name){
        this.manager.createParticipant(name);
    }

    public void loadNextScene(){
        currentItem = items.get(nextItem);
        try {
            InfoItemAdapter newInfoItem = new InfoItemAdapter(currentItem);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/linstezh/ui/screens/ExperimentStartScreen.fxml"));
            Parent root = loader.load();
            ExperimentStartController controller = loader.getController();
            controller.init(newInfoItem, this);
            primaryStage.getScene().setRoot(root);
            nextItem += 1;
        }catch(IOException e){
            nextItem += 1;  //todo: meaningful catch!
            loadNextScene();
        }
    }

    public void concludeSection(){
        manager.nextSection();
    }

}
