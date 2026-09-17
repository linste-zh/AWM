package linstezh.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import linstezh.executionManagers.ExpSectionManager;
import linstezh.ui.adapters.ImageDistractorItemAdapter;

public class ImageDistractorController {
    @FXML
    VBox contentArea;
    @FXML
    VBox contentVBox;

    @FXML
    private ImageView distractorImage;

    private ImageDistractorItemAdapter item;
    private ExpSectionManager manager;

    @FXML
    public void initialize() {
        contentVBox.maxHeightProperty().bind(contentArea.heightProperty());
        contentVBox.maxWidthProperty().bind(contentArea.widthProperty());

        distractorImage.fitWidthProperty().bind(contentVBox.widthProperty().multiply(0.9));
        distractorImage.fitHeightProperty().bind(contentVBox.heightProperty().multiply(0.9));
    }

    public void init(ImageDistractorItemAdapter item, ExpSectionManager manager){
        this.item = item;
        this.manager = manager;
        distractorImage.setImage(new Image(item.readDisplayImage()));
    }

}
