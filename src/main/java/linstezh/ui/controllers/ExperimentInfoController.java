package linstezh.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import linstezh.executionManagers.ExpSectionManager;
import linstezh.ui.adapters.InfoItemAdapter;

public class ExperimentInfoController {
    @FXML
    VBox contentArea;
    @FXML
    VBox contentVBox;

    @FXML
    Label title;
    @FXML
    private TextArea infoText;

    private InfoItemAdapter item;
    private ExpSectionManager manager;

    public void init(InfoItemAdapter item, ExpSectionManager manager){
        this.item = item;
        this.manager = manager;
        title.setText(manager.getSectionTitle() + " INFORMATION");
        infoText.setText(item.readDisplayText());
    }

    @FXML
    private void nextScene() {
        manager.loadNextScene();
    }
}
