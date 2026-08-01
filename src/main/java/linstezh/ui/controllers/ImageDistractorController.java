package linstezh.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import linstezh.executionManagers.ExpSectionManager;
import linstezh.ui.adapters.ImageDistractorItemAdapter;

public class ImageDistractorController {
    @FXML
    private ImageView distractorImage;

    private ImageDistractorItemAdapter item;
    private ExpSectionManager manager;

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
