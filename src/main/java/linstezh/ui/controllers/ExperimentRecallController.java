package linstezh.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import linstezh.executionManagers.ExpSectionManager;
import linstezh.ui.adapters.ExpItemAdapter;

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
            tf.setId(Integer.toString(items.get(i).getItemID()));
            GridPane.setConstraints(tf, 0, i);
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
