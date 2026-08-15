package linstezh.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import linstezh.executionManagers.ExpSectionManager;
import linstezh.ui.adapters.ExpItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class ExperimentRecallController {
    @FXML
    VBox contentArea;
    @FXML
    VBox contentVBox;

    @FXML
    private GridPane grid;

    private List<ExpItemAdapter> items;
    private ExpSectionManager manager;
    private List<TextField> inputFields = new ArrayList<>();


    public void init(List<ExpItemAdapter> items, ExpSectionManager manager){
        this.items = items;
        this.manager = manager;

        for (int i = 0; i < items.size(); i++) {
            Label label = new Label();
            label.setId(Integer.toString(items.get(i).getItemID()));
            label.setText("Chunk " + (i+1) + ": ");
            GridPane.setConstraints(label, 0, i);
            grid.getChildren().add(label);

            TextField tf = new TextField();
            tf.setId(Integer.toString(items.get(i).getItemID()));
            inputFields.add(tf);
            GridPane.setConstraints(tf, 1, i);
            grid.getChildren().add(tf);
        }
    }

    @FXML
    private void submitMemoryChunks(){
        for(Node field : grid.getChildren()){
            if(field.getClass() == TextField.class){
                TextField textField = (TextField) field;
                ExpItemAdapter item = findItem(textField.getId());
                assert item != null;
                item.reportUserMemoryChunk(textField.getText());
                System.out.println(item);
            }
        }

        manager.reportMemorisedChunks(items);
        manager.concludeSection();
    }

    private ExpItemAdapter findItem(String id){
        for(ExpItemAdapter item : items){
            if(item.getItemID() == Integer.parseInt(id)){
                return item;
            }
        }
        return null;
    }
}
