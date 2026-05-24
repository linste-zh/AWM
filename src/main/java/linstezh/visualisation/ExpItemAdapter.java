package linstezh.visualisation;

import linstezh.logic.ExperimentItem;

public class ExpItemAdapter {
    private final String evalText;
    private final Boolean correctEval;
    private final String memoryChunk;
    private final Integer position;
    private Boolean userEval;
    private String userMemoryChunk;

    public ExpItemAdapter(ExperimentItem item){
        this.evalText = item.getDisplayText();
        this.correctEval = item.getCorrectEvaluation();
        this.memoryChunk = item.getMemoryChunk();
        this.position = item.getPosition();
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

    public void reportUserMemoryChunk(String memo){
        userMemoryChunk = memo;
    }

    public Boolean isUserCorrect(){
        return correctEval == userEval;
    }

    public Boolean didUserRemember(){
        System.out.println("User reported " + userMemoryChunk + "; was supposed to report " + memoryChunk);
        return userMemoryChunk.equals(memoryChunk);
    }

}
