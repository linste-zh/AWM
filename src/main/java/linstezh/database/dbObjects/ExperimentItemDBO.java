package linstezh.database.dbObjects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import linstezh.logic.ExperimentItem;

@DatabaseTable(tableName = "experimentItems")
public class ExperimentItemDBO {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField (canBeNull = false)
    private int baseItemID;

    @DatabaseField(columnName = "memoryChunk", canBeNull = false)
    private String memoryChunk;

    @DatabaseField(columnName = "correctEvaluation")
    private boolean correctEvaluation;

    public ExperimentItemDBO(){}

    public ExperimentItemDBO(int baseItemID, String memoryChunk, boolean correctEvaluation){
        this.baseItemID = baseItemID;
        this.memoryChunk = memoryChunk;
        this.correctEvaluation = correctEvaluation;
    }

    public ExperimentItemDBO(ExperimentItem experimentItem){
        this.baseItemID = experimentItem.getBaseItemID();
        this.memoryChunk = experimentItem.getMemoryChunk();
        this.correctEvaluation = experimentItem.getCorrectEvaluation();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBaseItemID() {
        return baseItemID;
    }

    public void setBaseItemID(int baseItemID) {
        this.baseItemID = baseItemID;
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
}
