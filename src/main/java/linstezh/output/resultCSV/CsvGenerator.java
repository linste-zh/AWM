package linstezh.output.resultCSV;

import linstezh.logic.ActiveExperiment.ParticipantEvalResponse;
import linstezh.logic.ActiveExperiment.ParticipantMemResponse;
import linstezh.logic.Item.ExperimentItem;

import java.util.Date;

public class CsvGenerator {
    public static String[] generateRow(String experimentName, String participantName, Date date, String sectionName, ExperimentItem item){
        return new String[]{
                experimentName,
                participantName,
                date.toString(),
                sectionName,
                Integer.toString(item.getID()),
                Integer.toString(item.getPosition()),
                item.getType().toString(),
                item.getDisplayText(),
                item.getAffectiveValue(),
                Boolean.toString(item.getCorrectEvaluation()),
                null,
                null,
                null,
                null,
                null
        };
    }

    public static String[] generateRow(String experimentName, String participantName, Date date, String sectionName, ExperimentItem item, ParticipantEvalResponse evalRes, ParticipantMemResponse memRes){
        return new String[]{
                experimentName,
                participantName,
                date.toString(),
                sectionName,
                Integer.toString(item.getID()),
                Integer.toString(item.getPosition()),
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
                "date",
                "sectionName",
                "id",
                "position",
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
