package linstezh.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import linstezh.executionManagers.TrialSectionManager;
import linstezh.ui.adapters.InfoItemAdapter;

public class ExperimentFeedbackController {
    @FXML
    VBox contentArea;
    @FXML
    VBox contentVBox;

    @FXML
    private Label title;
    @FXML
    private Label evalScore;
    @FXML
    private Label memScore;


    private InfoItemAdapter item;
    private TrialSectionManager manager;

    public void init(int evalScore, int maxEvalScore, int memScore, int maxMemScore, TrialSectionManager manager){
        this.item = item;
        this.manager = manager;
        title.setText(manager.getSectionTitle() + " FEEDBACK");
        this.evalScore.setText(evalScore + "/" + maxEvalScore);
        this.memScore.setText(memScore + "/" + maxMemScore);
    }

    @FXML
    private void nextScene() {
        manager.loadNextScene();
    }

    @FXML
    private void restartSection() {
        manager.restartSection();
    }
}
