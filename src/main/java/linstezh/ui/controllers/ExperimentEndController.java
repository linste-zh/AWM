package linstezh.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import linstezh.executionManagers.EndSectionManager;
import linstezh.output.resultCSV.CsvAggregatedResultsGenerator;
import linstezh.output.resultCSV.CsvItemResultsGenerator;
import linstezh.ui.adapters.InfoItemAdapter;
import linstezh.ui.displayTools.FXMLUtilities;

import java.io.IOException;

public class ExperimentEndController {
    @FXML
    VBox contentArea;
    @FXML
    VBox contentVBox;

    @FXML
    WebView infoText;

    private InfoItemAdapter item;
    private EndSectionManager manager;

    @FXML
    public void initialize() {
        contentVBox.maxWidthProperty().bind(contentArea.widthProperty());
        contentVBox.maxHeightProperty().bind(contentArea.heightProperty());

        contentVBox.widthProperty().addListener((obs, old, val) ->
                infoText.setPrefWidth(val.doubleValue()));
        contentVBox.heightProperty().addListener((obs, old, val) ->
                infoText.setPrefHeight(val.doubleValue() * 0.6));
    }

    public void init(InfoItemAdapter item, EndSectionManager manager){
        this.item = item;
        this.manager = manager;

        String infoTextHTML = FXMLUtilities.parseMarkdownToHTML(item.readDisplayText());
        infoText.getEngine().loadContent(infoTextHTML);
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
