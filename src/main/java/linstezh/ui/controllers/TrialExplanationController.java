package linstezh.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import linstezh.executionManagers.ExpSectionManager;
import linstezh.ui.adapters.TextDistractorItemAdapter;

public class TrialExplanationController {
    @FXML
    VBox contentArea;
    @FXML
    VBox contentVBox;

    @FXML
    private Label title;

    @FXML
    private TextArea distractorText;

    private TextDistractorItemAdapter item;
    private ExpSectionManager manager;

    public void init(TextDistractorItemAdapter item, ExpSectionManager manager){
        this.item = item;
        this.manager = manager;

        distractorText.setText(item.readDisplayText());
    }

    @FXML
    private void nextScene() {
        manager.loadNextScene();
    }
}
