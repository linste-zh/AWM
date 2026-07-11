package linstezh.visualisation;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ExperimentStartScreen {
    private final InfoItemAdapter item;
    private final StartSectionManager manager;

    public ExperimentStartScreen(InfoItemAdapter item, StartSectionManager manager){
        this.item = item;
        this.manager = manager;
    }

    public Region createContent() {
        VBox results = new VBox(20, header(), nameInput(), infoField(), consent(), start());
        results.setAlignment(Pos.CENTER);
        return results;
    }

    private Node header() {
        return new Label(this.manager.getHeader());
    }

    private Node nameInput(){
        return new Label(this.item.readDisplayText());
    }

    private Node infoField(){
        return new Label("placeholder");
    }

    private Node consent(){
        return new Label("placeholder");
    }

    private Node start() {
        Button results = new Button("Start Experiment");
        results.setOnAction(evt -> {
            manager.concludeSection();
        });
        return results;
    }
}
