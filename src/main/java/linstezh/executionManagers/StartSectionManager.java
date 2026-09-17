package linstezh.executionManagers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import linstezh.logic.Item.ItemInterface;
import linstezh.logic.Section.SectionInterface;
import linstezh.ui.adapters.InfoItemAdapter;
import linstezh.ui.controllers.ExperimentStartController;
import linstezh.ui.controllers.RootController;

import java.io.IOException;
import java.util.List;

public class StartSectionManager implements SectionManager {
    private final ExperimentManager manager;
    private final RootController rootController;
    private final Stage primaryStage;
    private final SectionInterface section;
    private List<ItemInterface> items;
    private ItemInterface currentItem;
    private int nextItem = 0;

    public StartSectionManager(SectionInterface section, ExperimentManager manager, RootController rootController, Stage primaryStage){
        this.manager = manager;
        this.rootController = rootController;
        this.primaryStage = primaryStage;
        this.section = section;
        items = section.getItems();
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }

    public String getSectionTitle(){
        return section.getName();
    }

    public void display() {
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
        if(nextItem < items.size()) {
            currentItem = items.get(nextItem);
            try {
                InfoItemAdapter newInfoItem = new InfoItemAdapter(currentItem);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/linstezh/ui/screens/ExperimentStartScreen.fxml"));
                Parent content = loader.load();
                ExperimentStartController controller = loader.getController();
                controller.init(newInfoItem, this);
                rootController.setContent(content);
                /*rootController.getHeader().setItem(currentItem.getPosition());*/
                nextItem += 1;
            } catch (IOException e) {
                e.printStackTrace();
                nextItem += 1;  //todo: meaningful catch!
                loadNextScene();
            }
        }else{
            concludeSection();
        }
    }

    public void concludeSection(){
        manager.nextSection();
    }

}
