package linstezh.logic.Item;


import linstezh.logic.Section.Section;

public class TrialItem extends Item {
    private int id;
    private int baseItemID;
    private String memoryChunk;
    private boolean correctEvaluation;
    private String evalExplanation;

    public TrialItem(){};

    public TrialItem(Item baseItem){
        super(baseItem.getID(), baseItem.getSection(), baseItem.getType(), baseItem.getPosition(), baseItem.getDisplayText(), baseItem.getAffectiveValue());
    }

    public TrialItem(int id, int baseItemID, Section section, ItemTypes type, int position, String displayText, String affectiveValue, String memoryChunk, boolean correctEvaluation, String evalExplanation) {
        super(baseItemID, section, type, position, displayText, affectiveValue);
        this.id = id;
        this.baseItemID = baseItemID;
        this.memoryChunk = memoryChunk;
        this.correctEvaluation = correctEvaluation;
        this.evalExplanation = evalExplanation;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void setID(int id) {
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

    public String getEvalExplanation() {
        return evalExplanation;
    }

    public void setEvalExplanation(String evalExplanation) {
        this.evalExplanation = evalExplanation;
    }

    @Override
    public String toString() {
        return String.format("%d (%s): %s (%s) -> %s // %s", this.getPosition(), this.getType(), this.getDisplayText(), this.getAffectiveValue(), this.correctEvaluation, this.memoryChunk);
    }
}
