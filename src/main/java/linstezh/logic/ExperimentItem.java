package linstezh.logic;


public class ExperimentItem extends Item{
    private int id;
    private int baseItemID;
    private String memoryChunk;
    private boolean correctEvaluation;

    public ExperimentItem(){};

    public ExperimentItem(Item baseItem){
        super(baseItem.getID(), baseItem.getSection(), baseItem.getType(), baseItem.getPosition(), baseItem.getDisplayText(), baseItem.getAffectiveValue());
    }

    public ExperimentItem(int id, int baseItemID, Section section, ItemTypes type, int position, String displayText, String affectiveValue, String memoryChunk, boolean correctEvaluation) {
        super(baseItemID, section, type, position, displayText, affectiveValue);
        this.id = id;
        this.baseItemID = baseItemID;
        this.memoryChunk = memoryChunk;
        this.correctEvaluation = correctEvaluation;
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

    @Override
    public String toString() {
        return String.format("%d (%s): %s (%s) -> %s // %s", this.getPosition(), this.getType(), this.getDisplayText(), this.getAffectiveValue(), this.correctEvaluation, this.memoryChunk);
    }
}
