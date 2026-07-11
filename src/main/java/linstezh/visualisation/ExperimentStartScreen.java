package linstezh.visualisation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ExperimentStartScreen {
    private final InfoItemAdapter item;
    private final StartSectionManager manager;
    VBox box = new VBox();

    public ExperimentStartScreen(InfoItemAdapter item, StartSectionManager manager){
        this.item = item;
        this.manager = manager;
    }

    public Region createContent() {
        box.setPadding(new Insets(10, 10, 10, 10));

        box.getChildren().add(header());
        box.getChildren().add(nameInput());
        box.getChildren().add(infoField());
        box.getChildren().add(consent());
        box.getChildren().add(start());

        box.setAlignment(Pos.CENTER);
        return box;
    }

    private Node header() {
        return new Label(this.manager.getHeader());
    }

    private Node nameInput(){
        TextField textField = new TextField ();
        textField.setPromptText("Participant ID");
        textField.setId("name_input");
        return textField;
    }

    private Node infoField(){
        return new Label(this.item.readDisplayText());
    }

    private Node consent(){
        return new Label("placeholder");
    }

    private Node start() {
        Button results = new Button("Start Experiment");
        results.setOnAction(evt -> {
            TextField textField = (TextField) box.lookup("#name_input");
            manager.submitParticipantName(textField.getText());
            manager.concludeSection();
        });
        return results;
    }
}
