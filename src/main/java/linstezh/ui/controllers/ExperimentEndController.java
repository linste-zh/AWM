package linstezh.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import linstezh.executionManagers.EndSectionManager;
import linstezh.output.resultCSV.CsvAggregatedResultsGenerator;
import linstezh.output.resultCSV.CsvItemResultsGenerator;
import linstezh.ui.adapters.InfoItemAdapter;

import java.io.File;
import java.io.IOException;

public class ExperimentEndController {
    @FXML
    Label infoText;

    private InfoItemAdapter item;
    private EndSectionManager manager;

    public void init(InfoItemAdapter item, EndSectionManager manager){
        this.item = item;
        this.manager = manager;
        infoText.setText(item.readDisplayText());
    }

    @FXML
    private void downloadItemFile() {
        try {
            FileChooser fileChooser = new FileChooser();

            //Set extension filter for text files
            FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
            fileChooser.getExtensionFilters().add(csvFilter);

            //Show save file dialog
            File file = fileChooser.showSaveDialog(manager.getPrimaryStage());

            if (file != null) {
                manager.requestCsvSave(file, new CsvItemResultsGenerator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void downloadFAggregatedFile() {
        try {
            FileChooser fileChooser = new FileChooser();

            //Set extension filter for text files
            FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
            fileChooser.getExtensionFilters().add(csvFilter);

            //Show save file dialog
            File file = fileChooser.showSaveDialog(manager.getPrimaryStage());

            if (file != null) {
                manager.requestCsvSave(file, new CsvAggregatedResultsGenerator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void endExperiment() {
        manager.concludeSection();
    }
}
