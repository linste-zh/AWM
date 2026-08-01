package linstezh.visualisation.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import linstezh.executionManagers.ExpSectionManager;
import linstezh.visualisation.adapters.ExpItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class ExperimentRecallController {
    @FXML
    private GridPane grid;

    private List<ExpItemAdapter> items;
    private ExpSectionManager manager;
    private List<TextField> inputFields = new ArrayList<>();


    public void init(List<ExpItemAdapter> items, ExpSectionManager manager){
        this.items = items;
        this.manager = manager;

        for (int i = 0; i < items.size(); i++) {
            TextField tf = new TextField();
            inputFields.add(tf);
            GridPane.setConstraints(tf, 0, i);
            grid.getChildren().add(tf);
        }
    }

    @FXML
    private void submitMemoryChunks() throws Exception {
        for(Node field : grid.getChildren()){
            if(field.getClass() == TextField.class){
                TextField textField = (TextField) field;
                items.get(Integer.parseInt(textField.getId())).reportUserMemoryChunk(textField.getText());
            }
        }

        manager.reportMemorisedChunks(items);
        manager.concludeSection();
    }
}
