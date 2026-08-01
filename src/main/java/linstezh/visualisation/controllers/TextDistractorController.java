package linstezh.visualisation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import linstezh.executionManagers.ExpSectionManager;
import linstezh.visualisation.adapters.TextDistractorItemAdapter;

public class TextDistractorController {
    @FXML
    private Label distractorText;

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
