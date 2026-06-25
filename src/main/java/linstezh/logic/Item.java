package linstezh.logic;

import linstezh.database.dbObjects.ItemDBO;


public class Item implements ItemInterface{
    private int id;
    private Section section;
    private ItemTypes type;
    private int position;
    private String displayText;
    private String affectiveValue;

    public Item(int id, Section section, ItemTypes type, int position, String displayText, String affectiveValue){
        this.id = id;
        this.section = section;
        this.type = type;
        this.position = position;
        this.displayText = displayText;
        this.affectiveValue = affectiveValue;
    }

    public Item(ItemDBO item){
        this.id = item.getID();
        //this.section = item.getSectionID();
        this.type = item.getType();
        this.position = item.getPosition();
        this.displayText = item.getDisplayText();
        this.affectiveValue = item.getAffectiveValue();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Section getSection() {
        return this.section;
    }

    public void setSection (Section section) {
        this.section = section;
    }

    public int getSectionID(){
        return this.section.getID();
    }

    public ItemTypes getType() {
        return type;
    }

    public void setType(ItemTypes type) {
        this.type = type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public String getAffectiveValue() {
        return affectiveValue;
    }

    public void setAffectiveValue(String affectiveValue) {
        this.affectiveValue = affectiveValue;
    }
}
