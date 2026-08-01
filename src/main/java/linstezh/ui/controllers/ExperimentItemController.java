package linstezh.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import linstezh.executionManagers.ExpSectionManager;
import linstezh.ui.adapters.ExpItemAdapter;

public class ExperimentItemController {
    @FXML
    private Label evalText;

    private ExpItemAdapter item;
    private ExpSectionManager manager;


    public void init(ExpItemAdapter item, ExpSectionManager manager){
        this.item = item;
        this.manager = manager;
        evalText.setText(item.readEvalText());
    }

    @FXML
    private void clickTrue(){
        setEval(true);
    }

    @FXML
    private void clickFalse(){
        setEval(false);
    }

    private void setEval(Boolean value){
        item.reportUserEval(value);
        manager.reportEval(item);
        manager.loadNextScene();
    }
}
