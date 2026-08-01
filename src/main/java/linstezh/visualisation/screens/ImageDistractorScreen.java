package linstezh.visualisation.screens;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import linstezh.executionManagers.ExpSectionManager;
import linstezh.visualisation.adapters.ExpItemAdapter;
import linstezh.visualisation.adapters.ImageDistractorItemAdapter;
import linstezh.visualisation.adapters.TextDistractorItemAdapter;

public class ImageDistractorScreen {
    private final ImageDistractorItemAdapter item;
    private final ExpSectionManager manager;

    public ImageDistractorScreen(ImageDistractorItemAdapter item, ExpSectionManager manager){
        this.item = item;
        this.manager = manager;
    }

    public Region createContent() {
        VBox results = new VBox(20, image(), nextButton());
        results.setAlignment(Pos.CENTER);
        return results;
    }

    private Node nextButton() {
        Button results = new Button("next");
        results.setOnAction(evt -> {
            manager.loadNextScene();
        });
        return results;
    }

    private Node image() {
        Image image = new Image(item.readDisplayImage());
        return new ImageView(image);
    }
}
