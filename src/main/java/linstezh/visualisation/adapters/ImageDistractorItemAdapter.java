package linstezh.visualisation.adapters;

import linstezh.logic.Item.ItemInterface;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageDistractorItemAdapter {
    private final ItemInterface baseItem;
    private final FileInputStream displayImage;
    private final Integer position;

    public ImageDistractorItemAdapter(ItemInterface item) throws FileNotFoundException {
        this.baseItem = item;
        this.displayImage = new FileInputStream(item.getDisplayText());
        this.position = item.getPosition();
    }

    public ItemInterface getBaseItem() {
        return baseItem;
    }

    public FileInputStream readDisplayImage(){
        return displayImage;
    }

    public Integer readPosition(){
        return position;
    }
}
