package linstezh.logic;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "items")
public class Item {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField (canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Section section;

    @DatabaseField (canBeNull = false)
    private ItemTypes type;

    @DatabaseField (canBeNull = false)
    private int position;

    @DatabaseField(columnName = "displayText", canBeNull = false)
    private String displayText;

    @DatabaseField(columnName = "affectiveValue")
    private String affectiveValue;

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
