package linstezh.logic;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "evalResponses")
public class ParticipantEvalResponse {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField (canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private ExperimentItem item;

    @DatabaseField (canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Participant participant;

    @DatabaseField(columnName = "evaluationProvided")
    private boolean providedEvaluation;

    public ParticipantEvalResponse(){}

    public ParticipantEvalResponse(ExperimentItem item, Participant participant, Boolean eval){
        this.item = item;
        this.participant = participant;
        this.providedEvaluation = eval;
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

    public boolean isProvidedEvaluation() {
        return providedEvaluation;
    }

    public void setProvidedEvaluation(boolean providedEvaluation) {
        this.providedEvaluation = providedEvaluation;
    }
}
