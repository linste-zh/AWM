package linstezh.database.dbObjects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import linstezh.logic.ItemTypes;

@DatabaseTable(tableName = "items")
public class ItemDBO {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField (canBeNull = false, foreign = true)
    private SectionDBO sectionID;

    @DatabaseField (canBeNull = false)
    private ItemTypes type;

    @DatabaseField (canBeNull = false)
    private int position;

    @DatabaseField(columnName = "displayText", canBeNull = false)
    private String displayText;

    @DatabaseField(columnName = "affectiveValue")
    private String affectiveValue;

    public ItemDBO(){}

    public ItemDBO(SectionDBO sectionID, ItemTypes type, int position, String displayText, String affectiveValue){
        this.sectionID = sectionID;
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

    public SectionDBO getSectionID() {
        return sectionID;
    }

    public void setSectionID(SectionDBO sectionID) {
        this.sectionID = sectionID;
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
