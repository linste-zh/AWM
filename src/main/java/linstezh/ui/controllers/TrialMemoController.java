package linstezh.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import linstezh.executionManagers.ExpSectionManager;
import linstezh.ui.adapters.ExpItemAdapter;

public class TrialMemoController {
    @FXML
    VBox contentArea;
    @FXML
    VBox contentVBox;

    @FXML
    private TextArea evalText;

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
