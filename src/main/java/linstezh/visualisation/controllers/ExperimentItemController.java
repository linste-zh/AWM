package linstezh.visualisation.controllers;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import linstezh.executionManagers.ExpSectionManager;
import linstezh.visualisation.adapters.ExpItemAdapter;

public class ExperimentItemController {
    private ExpItemAdapter item;
    private ExpSectionManager manager;

    public void init(ExpItemAdapter item, ExpSectionManager manager){
        this.item = item;
        this.manager = manager;
    }

    public Region createContent() {
        VBox results = new VBox(20, createEvalLabel(), evalButton(true), evalButton(false));
        results.setAlignment(Pos.CENTER);
        return results;
    }

    private Node evalButton(Boolean value) {
        Button results = new Button(value.toString());
        results.setOnAction(evt -> {
            try {
                setEval(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return results;
    }

    private Node createEvalLabel() {
        return new Label(item.readEvalText());
    }

    private void setEval(Boolean value) throws Exception {
        item.reportUserEval(value);
        manager.reportEval(item);
        manager.loadNextScene();
    }
}
