package linstezh.logic;

public class Item implements ItemInterface{
    private int id;
    private Section section;
    private ItemTypes type;
    private int position;
    private String displayText;
    private String affectiveValue;

    public Item(){};

    public Item(int id, Section section, ItemTypes type, int position, String displayText, String affectiveValue){
        this.id = id;
        this.section = section;
        this.type = type;
        this.position = position;
        this.displayText = displayText;
        this.affectiveValue = affectiveValue;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setSection (Section section) {
        this.section = section;
    }

    public Section getSection(){
        return this.section;
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
