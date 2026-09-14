package linstezh.database.dbo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "experimentItems")
public class ExperimentItemDBO implements DBO{
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField (canBeNull = false, foreign = true, columnName = "baseItemID")
    private ItemDBO baseItemID;

    @DatabaseField(columnName = "memoryChunk", canBeNull = false)
    private String memoryChunk;

    @DatabaseField(columnName = "correctEvaluation")
    private String correctEvaluation;

    public ExperimentItemDBO(){}

    public ExperimentItemDBO(ItemDBO baseItemID, String memoryChunk, String correctEvaluation){
        this.baseItemID = baseItemID;
        this.memoryChunk = memoryChunk;
        this.correctEvaluation = correctEvaluation;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public ItemDBO getBaseItemID() {
        return baseItemID;
    }

    public void setBaseItemID(ItemDBO baseItemID) {
        this.baseItemID = baseItemID;
    }

    public String getMemoryChunk() {
        return memoryChunk;
    }

    public void setMemoryChunk(String memoryChunk) {
        this.memoryChunk = memoryChunk;
    }

    public String getCorrectEvaluation() {
        return correctEvaluation;
    }

    public void setCorrectEvaluation(String correctEvaluation) {
        this.correctEvaluation = correctEvaluation;
    }
}
