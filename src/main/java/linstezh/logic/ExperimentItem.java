package linstezh.logic;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "items")
public class ExperimentItem implements Item{

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField (canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private ExperimentSection experimentSection;

    @DatabaseField (canBeNull = false)
    private ItemTypes type;

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

    public ExperimentItem(ExperimentSection experimentSection, int position, String displayText, String memoryChunk, String affectiveValue, boolean correctEvaluation) {
        this.experimentSection = experimentSection;
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

    public ExperimentSection getSection() {
        return experimentSection;
    }

    public void setSection(ExperimentSection experimentSection) {
        this.experimentSection = experimentSection;
    }

    @Override
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

    public boolean getCorrectEvaluation() {
        return correctEvaluation;
    }

    public void setCorrectEvaluation(boolean correctEvaluation) {
        this.correctEvaluation = correctEvaluation;
    }

    @Override
    public String toString() {
        return "Item{id=" + id + ", Section = " + experimentSection.getName() + ", Text ='" + displayText + "', Memory ='" + memoryChunk + "', Affective ='" + affectiveValue +"', correct evaluation ='" + correctEvaluation + "'}";
    }
}
