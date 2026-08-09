package linstezh.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class RootController {
    @FXML
    private StackPane mainArea;

    @FXML
    private HeaderController headerController;

    @FXML
    private FooterController footerController;

    public HeaderController getHeader() {
        return headerController;
    }

    public FooterController getFooter() {
        return footerController;
    }


    public void setContent(Parent content) {
        mainArea.getChildren().setAll(content);
    }

}
