package linstezh.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import linstezh.ui.displayTools.FXMLUtilities;

public class RootController {
    @FXML
    private StackPane mainArea;

    @FXML
    private BorderPane root;

    @FXML
    private Pane header;

    @FXML
    private HeaderController headerController;

    @FXML
    private Pane footer;

    @FXML
    private FooterController footerController;

    @FXML
    public void initialize() {
        header.prefHeightProperty().bind(root.heightProperty().multiply(0.1));
        header.minHeightProperty().bind(root.heightProperty().multiply(0.1));
        header.prefWidthProperty().bind(root.widthProperty());
        header.minWidthProperty().bind(root.widthProperty());

        mainArea.prefHeightProperty().bind(root.heightProperty().multiply(0.85));
        mainArea.minHeightProperty().bind(root.heightProperty().multiply(0.85));
        mainArea.prefWidthProperty().bind(root.widthProperty());
        mainArea.minWidthProperty().bind(root.widthProperty());

        footer.prefHeightProperty().bind(root.heightProperty().multiply(0.05));
        footer.minHeightProperty().bind(root.heightProperty().multiply(0.05));
        footer.prefWidthProperty().bind(root.widthProperty());
        footer.minWidthProperty().bind(root.widthProperty());
    }

    public HeaderController getHeader() {
        return headerController;
    }

    public FooterController getFooter() {
        return footerController;
    }


    public void setContent(Parent content) {
        mainArea.getChildren().setAll(content);
        FXMLUtilities.fillAllParents(root);
    }

}
