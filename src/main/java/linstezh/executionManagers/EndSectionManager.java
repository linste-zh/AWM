package linstezh.executionManagers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import linstezh.logic.Item.ItemInterface;
import linstezh.logic.Section.SectionInterface;
import linstezh.output.resultCSV.CsvResultsGenerator;
import linstezh.visualisation.adapters.InfoItemAdapter;
import linstezh.visualisation.adapters.TextDistractorItemAdapter;
import linstezh.visualisation.controllers.ExperimentEndController;
import linstezh.visualisation.controllers.TextDistractorController;

import java.io.File;
import java.io.IOException;
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

    public Stage getPrimaryStage(){
        return primaryStage;
    }

    public void display(Stage primaryStage) {
        this.primaryStage = primaryStage;
        nextItem = 0;
        loadNextScene();
    }

    public void requestCsvSave(File file, CsvResultsGenerator resultGenerator) throws IOException {
        this.manager.saveResults(file, resultGenerator);
    }

    public void loadNextScene(){
        try {
            InfoItemAdapter newInfoItem = new InfoItemAdapter(currentItem);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../screens/ExperimentEndScreen.fxml"));
            Parent root = loader.load();
            ExperimentEndController controller = loader.getController();
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
