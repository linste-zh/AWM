package linstezh.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import linstezh.executionManagers.EndSectionManager;
import linstezh.output.resultCSV.CsvAggregatedResultsGenerator;
import linstezh.output.resultCSV.CsvItemResultsGenerator;
import linstezh.ui.adapters.InfoItemAdapter;

import java.io.IOException;

public class ExperimentEndController {
    @FXML
    VBox contentArea;
    @FXML
    VBox contentVBox;

    @FXML
    TextArea infoText;

    private InfoItemAdapter item;
    private EndSectionManager manager;

    public void init(InfoItemAdapter item, EndSectionManager manager){
        this.item = item;
        this.manager = manager;
        infoText.setText(item.readDisplayText());
        infoText.setFocusTraversable(false);
    }

    @FXML
    private void downloadItemFile() {
        try {
            manager.requestCsvSave(new CsvItemResultsGenerator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void downloadFAggregatedFile() {
        try {
            manager.requestCsvSave(new CsvAggregatedResultsGenerator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void endExperiment() {
        manager.loadNextScene();
    }
}
