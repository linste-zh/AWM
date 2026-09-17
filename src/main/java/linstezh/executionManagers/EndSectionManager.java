package linstezh.executionManagers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import linstezh.logic.Item.ItemInterface;
import linstezh.logic.Section.SectionInterface;
import linstezh.output.resultCSV.CsvResultsGenerator;
import linstezh.ui.adapters.InfoItemAdapter;
import linstezh.ui.controllers.ExperimentEndController;
import linstezh.ui.controllers.RootController;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class EndSectionManager implements SectionManager {
    private final ExperimentManager manager;
    private final RootController rootController;
    private final Stage primaryStage;
    protected final SectionInterface section;
    private List<ItemInterface> items;
    private ItemInterface currentItem;
    private int nextItem = 0;

    public EndSectionManager(SectionInterface section, ExperimentManager manager, RootController rootController, Stage primaryStage){
        this.manager = manager;
        this.rootController = rootController;
        this.primaryStage = primaryStage;
        this.section = section;
        items = section.getItems();
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }

    public void display() {
        nextItem = 0;
        loadNextScene();
    }

    public String getSectionTitle(){
        return section.getName();
    }

    public void requestCsvSave(File file, CsvResultsGenerator resultGenerator) throws IOException {
        this.manager.saveResults(file, resultGenerator);
    }

    public void loadNextScene(){
        if(nextItem < items.size()) {
            currentItem = items.get(nextItem);
            try {
                InfoItemAdapter newInfoItem = new InfoItemAdapter(currentItem);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/linstezh/ui/screens/ExperimentEndScreen.fxml"));
                Parent content = loader.load();
                ExperimentEndController controller = loader.getController();
                controller.init(newInfoItem, this);
                rootController.setContent(content);
                /*rootController.getHeader().setItem(currentItem.getPosition());*/
                nextItem += 1;
            } catch (IOException e) {
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
