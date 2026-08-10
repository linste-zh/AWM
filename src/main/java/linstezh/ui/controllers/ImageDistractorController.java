package linstezh.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import linstezh.executionManagers.ExpSectionManager;
import linstezh.ui.adapters.ImageDistractorItemAdapter;

public class ImageDistractorController {
    @FXML
    private ImageView distractorImage;

    @FXML
    private AnchorPane contentArea;

    private ImageDistractorItemAdapter item;
    private ExpSectionManager manager;

    @FXML
    public void initialize() {
        distractorImage.fitWidthProperty().bind(contentArea.widthProperty().multiply(0.9));
        distractorImage.fitHeightProperty().bind(contentArea.heightProperty().multiply(0.9));
    }

    public void init(ImageDistractorItemAdapter item, ExpSectionManager manager){
        this.item = item;
        this.manager = manager;
        distractorImage.setImage(new Image(item.readDisplayImage()));
    }

    @FXML
    private void nextScene() {
        manager.loadNextScene();
    }
}
