package linstezh.visualisation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.util.List;

public class ExperimentRecallScreen {
    private final List<ExpItemAdapter> items;
    private final SectionWindowManager sceneSwapper;
    GridPane grid = new GridPane();

    public ExperimentRecallScreen(List<ExpItemAdapter> items, SectionWindowManager sceneSwapper){
        this.items = items;
        this.sceneSwapper = sceneSwapper;
    }

    public Region createContent() {
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        for(int i = 0; i < items.size(); i++){
            TextField current = createInputBox(items.get(i).readPosition());
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
                items.get(Integer.parseInt(textField.getId()) - 1).reportUserMemoryChunk(textField.getText());
            }
        }
        for(ExpItemAdapter item : items){
            item.didUserRemember();
        }

        sceneSwapper.concludeSection();
    }
}
