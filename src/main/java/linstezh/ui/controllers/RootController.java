package linstezh.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import linstezh.ui.displayTools.FXMLUtilities;

public class RootController {
    @FXML
    private VBox root;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox mainArea;

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
        mainArea.minHeightProperty().bind(scrollPane.heightProperty().subtract(2));
    }

    public HeaderController getHeader() {
        return headerController;
    }

    public FooterController getFooter() {
        return footerController;
    }


    public void setContent(Parent content) {
        mainArea.getChildren().setAll(content);
        if (content instanceof Region r) {
            r.prefWidthProperty().bind(mainArea.widthProperty());
        }
        FXMLUtilities.applyProperties(root);
    }

}
