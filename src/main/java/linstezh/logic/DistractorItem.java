/*package linstezh.logic;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "experimentItems")
public class DistractorItem extends Item{

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField (canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Item baseItem;

    @DatabaseField(columnName = "memoryChunk", canBeNull = false)
    private String memoryChunk;

    @DatabaseField(columnName = "correctEvaluation")
    private boolean correctEvaluation;

    public DistractorItem() {}

    public DistractorItem(Item baseItem, String memoryChunk, boolean correctEvaluation) {
        this.baseItem = baseItem;
        this.memoryChunk = memoryChunk;
        this.correctEvaluation = correctEvaluation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getBaseItem() {
        return baseItem;
    }

    public void setBaseItem(Item baseItem) {
        this.baseItem = baseItem;
    }

    public String getMemoryChunk() {
        return memoryChunk;
    }

    public void setMemoryChunk(String memoryChunk) {
        this.memoryChunk = memoryChunk;
    }

    public boolean getCorrectEvaluation() {
        return correctEvaluation;
    }

    public void setCorrectEvaluation(boolean correctEvaluation) {
        this.correctEvaluation = correctEvaluation;
    }
}*/
