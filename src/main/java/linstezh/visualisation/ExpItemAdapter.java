package linstezh.visualisation;

import linstezh.logic.ExperimentItem;

public class ExpItemAdapter {
    private final String evalText;
    private final Boolean correctEval;
    private Boolean userEval;

    public ExpItemAdapter(ExperimentItem item){
        this.evalText = item.getDisplayText();
        this.correctEval = item.getCorrectEvaluation();
    }

    public String readEvalText(){
        return evalText;
    }

    public Boolean readCorrectEval(){
        return correctEval;
    }

    public void reportUserEval(boolean eval){
        userEval = eval;
    }

    public Boolean isUserCorrect(){
        return correctEval == userEval;
    }

}
