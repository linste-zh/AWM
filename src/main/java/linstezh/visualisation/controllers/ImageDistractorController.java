package linstezh.visualisation.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import linstezh.executionManagers.ExpSectionManager;
import linstezh.visualisation.adapters.ImageDistractorItemAdapter;

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
