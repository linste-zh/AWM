package linstezh.visualisation;

import linstezh.logic.Item.ItemInterface;

public class InfoItemAdapter {
    private final ItemInterface baseItem;
    private final String displayText;
    private final Integer position;

    public InfoItemAdapter(ItemInterface item){
        this.baseItem = item;
        this.displayText = item.getDisplayText();
        this.position = item.getPosition();
    }

    public ItemInterface getBaseItem() {
        return baseItem;
    }

    public String readDisplayText(){
        return displayText;
    }

    public Integer readPosition(){
        return position;
    }
}
