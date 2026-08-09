package linstezh.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FooterController {
    @FXML
    private Label progress;

    public void setProgress(int currentSection, int totalSections){
        progress.setText(currentSection + "/" + totalSections);
        System.out.println(currentSection + "/" + totalSections);
    }
}
