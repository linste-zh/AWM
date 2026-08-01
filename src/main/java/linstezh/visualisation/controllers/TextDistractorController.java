package linstezh.visualisation.controllers;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import linstezh.executionManagers.ExpSectionManager;
import linstezh.visualisation.adapters.TextDistractorItemAdapter;

public class TextDistractorController {
    private final TextDistractorItemAdapter item;
    private final ExpSectionManager manager;

    public TextDistractorController(TextDistractorItemAdapter item, ExpSectionManager manager){
        this.item = item;
        this.manager = manager;
    }

    public Region createContent() {
        VBox results = new VBox(20, textLabel(), nextButton());
        results.setAlignment(Pos.CENTER);
        return results;
    }

    private Node nextButton() {
        Button results = new Button("next");
        results.setOnAction(evt -> {
            manager.loadNextScene();
        });
        return results;
    }

    private Node textLabel() {
        return new Label(item.readDisplayText());
    }

}
