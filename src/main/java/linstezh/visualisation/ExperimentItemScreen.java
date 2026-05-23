package linstezh.visualisation;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import linstezh.Main;

public class ExperimentItemScreen {
    private final ExpItemAdapter item;
    private final WindowManager sceneSwapper;

    public ExperimentItemScreen(ExpItemAdapter item, WindowManager sceneSwapper){
        this.item = item;
        this.sceneSwapper = sceneSwapper;
    }

    public Region createContent() {
        VBox results = new VBox(20, createEvalLabel(), evalButton(true), evalButton(false));
        results.setAlignment(Pos.CENTER);
        return results;
    }

    private Node evalButton(Boolean value) {
        Button results = new Button(value.toString());
        results.setOnAction(evt -> setEval(value));
        return results;
    }

    private Node createEvalLabel() {
        return new Label(item.readEvalText());
    }

    private void setEval(Boolean value) {
        item.reportUserEval(value);
        System.out.println("User was: " + item.isUserCorrect());
        sceneSwapper.loadNextScene();
    }
}
