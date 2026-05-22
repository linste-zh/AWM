package linstezh.visualisation;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ExperimentItemScreen {

    private final StringProperty evalText = new SimpleStringProperty("");
    private BooleanProperty evaluation = new SimpleBooleanProperty();
    private final StringProperty name = new SimpleStringProperty("");

    public Region createContent() {
        VBox results = new VBox(20, createEvalLabel(), evalButton(true), evalButton(false));
        results.setAlignment(Pos.CENTER);
        return results;
    }

    private Node input() {
        TextField textField = new TextField("");
        textField.textProperty().bindBidirectional(name);
        HBox hBox = new HBox(6, new Label("Name:"), textField);
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }

    private Node evalButton(Boolean value) {
        Button results = new Button(value.toString());
        results.setOnAction(evt -> setEval(value));
        return results;
    }

    private Node createEvalLabel() {
        Label results = new Label("Oliver is the greatest bean in the world.");
        return results;
    }

    private void setEval(Boolean value) {
        evaluation.set(value);
        System.out.println(evaluation);
    }
}
