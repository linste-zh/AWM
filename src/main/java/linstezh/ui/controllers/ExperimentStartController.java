package linstezh.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import linstezh.executionManagers.StartSectionManager;
import linstezh.ui.adapters.InfoItemAdapter;

import java.util.Objects;

public class ExperimentStartController {
    @FXML
    Label title;
    @FXML
    Label infoText;
    @FXML
    TextField nameInput;
    @FXML
    RadioButton consentButton;

    private InfoItemAdapter item;
    private StartSectionManager manager;

    public void init(InfoItemAdapter item, StartSectionManager manager){
        this.item = item;
        this.manager = manager;

        title.setText(this.manager.getHeader());
        infoText.setText(item.readDisplayText());
    }

    @FXML
    private void start() {
        if(consentButton.isSelected()){
            if(!Objects.equals(nameInput.getText(), "")){
                manager.submitParticipantName(nameInput.getText());
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
    }
}
