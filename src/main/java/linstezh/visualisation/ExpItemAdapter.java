package linstezh.visualisation;

public class ExpItemAdapter {
    private final String evalText;
    private final Boolean correctEval;
    private Boolean userEval;

    public ExpItemAdapter(String evalText, boolean correctEval){
        this.evalText = evalText;
        this.correctEval = correctEval;
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
