package linstezh.visualisation.controllers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import linstezh.executionManagers.StartSectionManager;
import linstezh.visualisation.adapters.InfoItemAdapter;

public class ExperimentStartController {
    private final InfoItemAdapter item;
    private final StartSectionManager manager;
    VBox box = new VBox();

    public ExperimentStartController(InfoItemAdapter item, StartSectionManager manager){
        this.item = item;
        this.manager = manager;
    }

    public Region createContent() {
        box.setPadding(new Insets(10, 10, 10, 10));
        box.setSpacing(5);

        box.getChildren().add(header());
        box.getChildren().add(infoField());
        box.getChildren().add(nameInput());
        box.getChildren().add(consent());
        box.getChildren().add(start());

        box.setAlignment(Pos.TOP_LEFT);
        return box;
    }

    private Node header() {
        Label header = new Label(this.manager.getHeader());
        header.setFont(new Font(25));
        header.setAlignment(Pos.CENTER);
        return header;
    }

    private Node nameInput(){
        Label prompt = new Label("Participant Name or ID: ");

        TextField textField = new TextField();
        textField.setPromptText("Name/ID");
        textField.setId("name_input");
        textField.setPrefWidth(100);
        textField.setMaxWidth(100);
        textField.setPrefHeight(3);
        textField.setMaxHeight(3);

        HBox hbox = new HBox(prompt, textField);
        hbox.setSpacing(5);
        return hbox;
    }

    private Node infoField(){
        return new Label(this.item.readDisplayText());
    }

    private Node consent(){
        RadioButton consentButton =  new RadioButton("I consent to participating in this study");
        consentButton.setId("consent_button");
        return consentButton;
    }

    private Node start() {
        Button results = new Button("Start Experiment");
        results.setOnAction(evt -> {
            RadioButton consentButton = (RadioButton) box.lookup("#consent_button");
            if(consentButton.isSelected()){
                TextField textField = (TextField) box.lookup("#name_input");
                if(textField.getText() != ""){
                    manager.submitParticipantName(textField.getText());
                    manager.concludeSection();
                }else{
                    Alert a = new Alert(Alert.AlertType.WARNING);
                    a.setTitle("Missing Name");
                    a.setContentText("Please provide a participant name or ID.");
                    a.show();
                }
            }else{
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setTitle("Consent not given");
                a.setContentText("Please provide your consent to participate.");
                a.show();
            }


        });
        return results;
    }
}
