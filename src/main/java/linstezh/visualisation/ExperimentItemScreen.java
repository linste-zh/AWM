package linstezh.visualisation;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ExperimentItemScreen {
    private final ExpItemAdapter item;
    private final SectionWindowManager manager;

    public ExperimentItemScreen(ExpItemAdapter item, SectionWindowManager manager){
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
        System.out.println("User was: " + item.isUserCorrect());
        manager.reportEval(item);
        manager.loadNextScene();
    }
}
