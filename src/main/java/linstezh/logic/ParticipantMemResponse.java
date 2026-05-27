package linstezh.logic;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "memResponses")
public class ParticipantMemResponse {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField (canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private ExperimentItem item;

    @DatabaseField (canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Participant participant;

    @DatabaseField(columnName = "chunkProvided")
    private String providedChunk;

    public ParticipantMemResponse(){}

    public ParticipantMemResponse(ExperimentItem item, Participant participant, String response){
        this.item = item;
        this.participant = participant;
        this.providedChunk = response;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
