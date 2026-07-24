package linstezh.output.resultCSV;

import linstezh.logic.ActiveExperiment.ParticipantEvalResponse;
import linstezh.logic.ActiveExperiment.ParticipantMemResponse;
import linstezh.logic.Item.ExperimentItem;
import linstezh.logic.Item.ItemInterface;

import java.util.Date;

public class CsvItemRowGenerator {


    public static String[] generateRow(String experimentName, String participantName, String sectionName, ItemInterface item){
        return new String[]{
                experimentName,
                participantName,
                sectionName,
                Integer.toString(item.getID()),
                Integer.toString(item.getPosition()),
                item.getDisplayDate() != null ? item.getDisplayDate().toString() : null,
                item.getType().toString(),
                item.getDisplayText(),
                item.getAffectiveValue(),
                null,
                null,
                null,
                null,
                null,
                null
        };
    }

    public static String[] generateRow(String experimentName, String participantName, String sectionName, ExperimentItem item, ParticipantEvalResponse evalRes, ParticipantMemResponse memRes){
        return new String[]{
                experimentName,
                participantName,
                sectionName,
                Integer.toString(item.getID()),
                Integer.toString(item.getPosition()),
                item.getDisplayDate() != null ? item.getDisplayDate().toString() : null,
                item.getType().toString(),
                item.getDisplayText(),
                item.getAffectiveValue(),
                Boolean.toString(item.getCorrectEvaluation()),
                Boolean.toString(evalRes.getProvidedEvaluation()),
                Integer.toString(evalRes.getEvalScore()),
                item.getMemoryChunk(),
                memRes.getProvidedChunk(),
                Integer.toString(memRes.getMemorisationScore())
        };
    }

    public static String[] generateHeaders(){
        return new String[]{
                "experimentName",
                "participantName",
                "sectionName",
                "id",
                "position",
                "displayTimestamp",
                "type",
                "displayText",
                "affectiveValue",
                "eval_req",
                "eval_given",
                "eval_score",
                "mem_req",
                "mem_given",
                "mem_score"
        };
    }
}
