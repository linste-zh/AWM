package linstezh.logic;

import linstezh.database.dbObjects.ExperimentItemDBO;
import linstezh.database.dbObjects.ItemDBO;

public class ExperimentItem extends Item{
    private int id;
    private int baseItemID;
    private String memoryChunk;
    private boolean correctEvaluation;

    public ExperimentItem(int id, int baseItemID, Section section, ItemTypes type, int position, String displayText, String affectiveValue, String memoryChunk, boolean correctEvaluation) {
        super(baseItemID, section, type, position, displayText, affectiveValue);
        this.id = id;
        this.baseItemID = baseItemID;
        this.memoryChunk = memoryChunk;
        this.correctEvaluation = correctEvaluation;
    }

    public ExperimentItem(ItemDBO baseItem, ExperimentItemDBO experimentItemInfo){
        super(baseItem);
        this.baseItemID = baseItem.getID();
        this.id = experimentItemInfo.getId();
        this.memoryChunk = experimentItemInfo.getMemoryChunk();
        this.correctEvaluation = experimentItemInfo.getCorrectEvaluation();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
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
