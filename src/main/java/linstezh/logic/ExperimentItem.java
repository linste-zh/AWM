package linstezh.logic;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "items")
public class ExperimentItem {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField (canBeNull = false)
    private String sectionID;

    @DatabaseField (canBeNull = false)
    private int position;

    @DatabaseField(columnName = "displayText", canBeNull = false)
    private String displayText;

    @DatabaseField(columnName = "memoryChunk", canBeNull = false)
    private String memoryChunk;

    @DatabaseField(columnName = "affectiveValue")
    private String affectiveValue;

    @DatabaseField(columnName = "correctEvaluation")
    private boolean correctEvaluation;

    public ExperimentItem() {}

    public ExperimentItem(String sectionID, int position, String displayText, String memoryChunk, String affectiveValue, boolean correctEvaluation) {
        this.sectionID = sectionID;
        this.position = position;
        this.displayText = displayText;
        this.memoryChunk = memoryChunk;
        this.affectiveValue = affectiveValue;
        this.correctEvaluation = correctEvaluation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
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

    public String getMemoryChunk() {
        return memoryChunk;
    }

    public void setMemoryChunk(String memoryChunk) {
        this.memoryChunk = memoryChunk;
    }

    public String getAffectiveValue() {
        return affectiveValue;
    }

    public void setAffectiveValue(String affectiveValue) {
        this.affectiveValue = affectiveValue;
    }

    public boolean isCorrectEvaluation() {
        return correctEvaluation;
    }

    public void setCorrectEvaluation(boolean correctEvaluation) {
        this.correctEvaluation = correctEvaluation;
    }

    @Override
    public String toString() {
        return "Item{id=" + id + ", Section = " + sectionID + ", Text ='" + displayText + "', Memory ='" + memoryChunk + "', Affective ='" + affectiveValue +"', correct evaluation ='" + correctEvaluation + "'}";
    }
}
