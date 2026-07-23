package linstezh.visualisation.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import jdk.jfr.StackTrace;
import linstezh.executionManagers.EndSectionManager;
import linstezh.visualisation.adapters.InfoItemAdapter;

import java.io.File;
import java.io.IOException;

public class ExperimentEndScreen {
    private final InfoItemAdapter item;
    private final EndSectionManager manager;
    VBox box = new VBox();

    public ExperimentEndScreen(InfoItemAdapter item, EndSectionManager manager){
        this.item = item;
        this.manager = manager;
    }

    public Region createContent() {
        box.setPadding(new Insets(10, 10, 10, 10));
        box.setSpacing(5);

        box.getChildren().add(header());
        box.getChildren().add(infoField());
        box.getChildren().add(downloadFiles());
        box.getChildren().add(endExperiment());

        box.setAlignment(Pos.TOP_LEFT);
        return box;
    }

    private Node header() {
        Label header = new Label("Thank you for participating");
        header.setFont(new Font(25));
        header.setAlignment(Pos.CENTER);
        return header;
    }

    private Node infoField(){
        return new Label(this.item.readDisplayText());
    }


    private Node downloadFiles() {
        Button results = new Button("Download CSV");
        results.setOnAction(evt -> {
            try {
                FileChooser fileChooser = new FileChooser();

                //Set extension filter for text files
                FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
                fileChooser.getExtensionFilters().add(csvFilter);

                //Show save file dialog
                File file = fileChooser.showSaveDialog(manager.getPrimaryStage());

                if (file != null) {
                    manager.requestCsvSave(file);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return results;
    }

    private Node endExperiment() {
        Button results = new Button("End Experiment");
        results.setOnAction(evt -> {
            manager.concludeSection();
        });
        return results;
    }
}
