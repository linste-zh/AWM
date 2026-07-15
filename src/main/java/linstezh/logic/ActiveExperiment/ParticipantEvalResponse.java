package linstezh.logic.ActiveExperiment;

import linstezh.logic.Item.ExperimentItem;

public class ParticipantEvalResponse {
    private ExperimentItem item;
    private Participant participant;
    private boolean providedEvaluation;
    private int evalScore;

    public ParticipantEvalResponse(){}

    public ParticipantEvalResponse(ExperimentItem item, Participant participant, Boolean eval){
        this.item = item;
        this.participant = participant;
        this.providedEvaluation = eval;
    }

    public ExperimentItem getItem() {
        return item;
    }

    public void setItem(ExperimentItem item) {
        this.item = item;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public boolean getProvidedEvaluation() {
        return providedEvaluation;
    }

    public void setProvidedEvaluation(boolean providedEvaluation) {
        this.providedEvaluation = providedEvaluation;
    }

    public int getEvalScore() {
        return evalScore;
    }

    public void setEvalScore(int evalScore) {
        this.evalScore = evalScore;
    }

    public int calculateEvalScore(){
        if(this.providedEvaluation == item.getCorrectEvaluation()){
            this.evalScore = 1;
        }else{
            this.evalScore = 0;
        }

        return this.evalScore;
    }
}
