package linstezh.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import linstezh.executionManagers.StartSectionManager;
import linstezh.ui.adapters.InfoItemAdapter;
import linstezh.ui.displayTools.FXMLUtilities;

import java.util.Objects;

public class ExperimentStartController {
    @FXML
    VBox contentArea;
    @FXML
    VBox contentVBox;

    @FXML
    Label title;
    @FXML
    WebView infoText;
    @FXML
    TextField nameInput;
    @FXML
    RadioButton consentButton;

    private InfoItemAdapter item;
    private StartSectionManager manager;

    @FXML
    public void initialize() {
        contentVBox.maxWidthProperty().bind(contentArea.widthProperty());
        contentVBox.maxHeightProperty().bind(contentArea.heightProperty());

        contentVBox.widthProperty().addListener((obs, old, val) ->
                infoText.setPrefWidth(val.doubleValue()));
        contentVBox.heightProperty().addListener((obs, old, val) ->
                infoText.setPrefHeight(val.doubleValue() * 0.6));
    }

    public void init(InfoItemAdapter item, StartSectionManager manager){
        this.item = item;
        this.manager = manager;

        title.setText(this.manager.getHeader());

        String infoTextHTML = FXMLUtilities.parseMarkdownToHTML(item.readDisplayText());
        infoText.getEngine().loadContent(infoTextHTML);
        infoText.setFocusTraversable(false);
    }

    @FXML
    private void start() {
        if(consentButton.isSelected()){
            if(!Objects.equals(nameInput.getText(), "")){
                manager.submitParticipantName(nameInput.getText());
                manager.loadNextScene();
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
    }
}
