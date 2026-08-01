package linstezh.visualisation.controllers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import linstezh.executionManagers.ExpSectionManager;
import linstezh.visualisation.adapters.ExpItemAdapter;

import java.util.List;

public class ExperimentRecallController {
    private final List<ExpItemAdapter> items;
    private final ExpSectionManager manager;
    GridPane grid = new GridPane();

    public ExperimentRecallController(List<ExpItemAdapter> items, ExpSectionManager manager){
        this.items = items;
        this.manager = manager;
    }

    public Region createContent() {
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        for(int i = 0; i < items.size(); i++){
            TextField current = createInputBox(i);
            GridPane.setConstraints(current, 0, i);
            grid.getChildren().add(current);
        }

        Button submitButton = createSubmitButton();
        GridPane.setConstraints(submitButton, 1, 0);
        grid.getChildren().add(submitButton);

        grid.setAlignment(Pos.CENTER);
        return grid;
    }

    private TextField createInputBox(Integer pos) {
        TextField textField = new TextField ();
        textField.setPromptText("Chunk " + pos);
        textField.setId(pos.toString());
        return textField;
    }

    private Button createSubmitButton(){
        Button submit = new Button("Submit");
        submit.setOnAction(evt -> {
            try {
                submitMemoryChunks();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return submit;
    }

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
