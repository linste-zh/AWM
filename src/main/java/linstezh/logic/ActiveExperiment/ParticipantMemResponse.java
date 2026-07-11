package linstezh.logic.ActiveExperiment;

import linstezh.logic.Item.ExperimentItem;

public class ParticipantMemResponse {
    private ExperimentItem item;
    private Participant participant;
    private String providedChunk;
    private int contentScore;
    private int positionScore;

    public ParticipantMemResponse(){}

    public ParticipantMemResponse(ExperimentItem item, Participant participant, String response){
        this.item = item;
        this.participant = participant;
        this.providedChunk = response;
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

    public int getContentScore() {
        return contentScore;
    }

    public void setContentScore(int contentScore) {
        this.contentScore = contentScore;
    }

    public int getPositionScore() {
        return positionScore;
    }

    public void setPositionScore(int positionScore) {
        this.positionScore = positionScore;
    }
}
