package linstezh.database.dbo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "trialItems")
public class TrialItemDBO implements DBO{
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField (canBeNull = false, foreign = true, columnName = "baseItemID")
    private ItemDBO baseItemID;

    @DatabaseField(columnName = "memoryChunk", canBeNull = false)
    private String memoryChunk;

    @DatabaseField(columnName = "correctEvaluation")
    private boolean correctEvaluation;

    @DatabaseField(columnName = "evalExplanation")
    private String evalExplanation;

    public TrialItemDBO(){}

    public TrialItemDBO(ItemDBO baseItemID, String memoryChunk, boolean correctEvaluation, String evalExplanation){
        this.baseItemID = baseItemID;
        this.memoryChunk = memoryChunk;
        this.correctEvaluation = correctEvaluation;
        this.evalExplanation = evalExplanation;
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

    public boolean getCorrectEvaluation() {
        return correctEvaluation;
    }

    public void setCorrectEvaluation(boolean correctEvaluation) {
        this.correctEvaluation = correctEvaluation;
    }

    public String getEvalExplanation() {
        return evalExplanation;
    }

    public void setEvalExplanation(String evalExplanation) {
        this.evalExplanation = evalExplanation;
    }
}
