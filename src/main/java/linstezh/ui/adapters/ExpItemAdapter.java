package linstezh.ui.adapters;

import linstezh.logic.Item.ExperimentItem;

public class ExpItemAdapter {
    private final ExperimentItem baseItem;
    private int itemID;
    private final String evalText;
    private final Boolean correctEval;
    private final String memoryChunk;
    private final Integer position;
    private Boolean userEval;
    private String userMemoryChunk;
    private int score;

    public ExpItemAdapter(ExperimentItem item){
        this.baseItem = item;
        this.itemID = item.getID();
        this.evalText = item.getDisplayText();
        this.correctEval = item.getCorrectEvaluation();
        this.memoryChunk = item.getMemoryChunk();
        this.position = item.getPosition();
    }

    public ExperimentItem getBaseItem() {
        return baseItem;
    }

    public int getItemID(){
        return itemID;
    }

    public String readEvalText(){
        return evalText;
    }

    public String readMemoryChunk(){
        return memoryChunk;
    }

    public Boolean readCorrectEval(){
        return correctEval;
    }

    public Integer readPosition(){
        return position;
    }

    public void reportUserEval(boolean eval){
        userEval = eval;
    }

    public boolean readUserEval(){
        return userEval;
    }

    public void reportUserMemoryChunk(String memo){
        userMemoryChunk = memo;
    }

    public String readUserMemoryChunk(){
        return userMemoryChunk;
    }

    public void setScore(int score){
        this.score = score;
    }

    public int readScore(){
        return score;
    }
}
