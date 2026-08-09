package linstezh.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HeaderController {
    @FXML
    private Label title;

    @FXML
    private Label section;

    @FXML
    private Label item;

    public void setTitle(String experimentName){
        title.setText(experimentName);
    }

    public void setSection(int sectionNr){
        section.setText(String.valueOf(sectionNr));
    }

    public void setItem(int itemNr){
        item.setText(String.valueOf(itemNr));
    }

    public void setItem(String text){
        item.setText(text);
    }
}
