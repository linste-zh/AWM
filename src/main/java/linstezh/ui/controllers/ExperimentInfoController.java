package linstezh.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import linstezh.executionManagers.ExpSectionManager;
import linstezh.ui.adapters.InfoItemAdapter;
import linstezh.ui.displayTools.FXMLUtilities;

public class ExperimentInfoController {
    @FXML
    VBox contentArea;
    @FXML
    VBox contentVBox;

    @FXML
    Label title;
    @FXML
    WebView infoText;

    private InfoItemAdapter item;
    private ExpSectionManager manager;

    @FXML
    public void initialize() {
        contentVBox.maxWidthProperty().bind(contentArea.widthProperty());
        contentVBox.maxHeightProperty().bind(contentArea.heightProperty());

        contentVBox.widthProperty().addListener((obs, old, val) ->
                infoText.setPrefWidth(val.doubleValue()));
        contentVBox.heightProperty().addListener((obs, old, val) ->
                infoText.setPrefHeight(val.doubleValue() * 0.6));
    }

    public void init(InfoItemAdapter item, ExpSectionManager manager){
        this.item = item;
        this.manager = manager;
        title.setText(manager.getSectionTitle() + " INFORMATION");

        String infoTextHTML = FXMLUtilities.parseMarkdownToHTML(item.readDisplayText());
        infoText.getEngine().loadContent(infoTextHTML);
        infoText.setFocusTraversable(false);
    }

    @FXML
    private void nextScene() {
        manager.loadNextScene();
    }
}
