package linstezh.logic.ActiveExperiment;

import linstezh.logic.Item.ExperimentItem;

public class ParticipantMemResponse {
    private ExperimentItem item;
    private Participant participant;
    private String providedChunk;
    private int memorisationScore;

    public ParticipantMemResponse(){}

    public ParticipantMemResponse(ExperimentItem item, Participant participant, String response){
        this.item = item;
        this.participant = participant;
        this.providedChunk = response;
        this.memorisationScore = 0;
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

    public String getProvidedChunk() {
        return providedChunk;
    }

    public void setProvidedChunk(String providedChunk) {
        this.providedChunk = providedChunk;
    }

    public int getMemorisationScore() {
        return memorisationScore;
    }

    public void setMemorisationScore(int memorisationScore) {
        this.memorisationScore = memorisationScore;
    }

}
