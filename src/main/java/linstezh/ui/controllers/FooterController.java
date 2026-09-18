package linstezh.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class FooterController {
    @FXML
    private Label progressText;

    @FXML
    private ProgressBar progressBar;

    public void setProgress(int currentSection, int totalSections){
        progressText.setText(currentSection + "/" + totalSections);
        progressBar.setProgress((double) currentSection / totalSections);
    }
}
