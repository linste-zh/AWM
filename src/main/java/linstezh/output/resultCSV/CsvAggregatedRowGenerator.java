package linstezh.output.resultCSV;

import linstezh.logic.ActiveExperiment.ParticipantEvalResponse;
import linstezh.logic.ActiveExperiment.ParticipantMemResponse;
import linstezh.logic.Item.ExperimentItem;
import linstezh.logic.Item.ItemInterface;
import linstezh.logic.Item.ItemTypes;
import linstezh.logic.Section.SectionInterface;

import java.util.List;

public class CsvAggregatedRowGenerator {

    public static String[] generateHeaders(){
        return new String[]{
                "experimentName",
                "participantName",
                "sectionName",
                "position",
                "type",
                "displayTexts",
                "affectiveValues",
                "evals_req",
                "evals_given",
                "evals_maxScore",
                "evals_totalScore",
                "mems_req",
                "mems_given",
                "mem_maxScore",
                "mem_totalScore"
        };
    }

    public static String[] generateRow(String experimentName, String participantName, SectionInterface section, List<ParticipantEvalResponse> evalRes, List<ParticipantMemResponse> memRes){
        List<ItemInterface> sectionItems = section.getItems();

        return new String[]{
                experimentName,
                participantName,
                section.getName(),
                Integer.toString(section.getPosition()),
                section.getType().toString(),
                extractDisplayTexts(sectionItems).toString(),
                extractAffectiveValues(sectionItems).toString(),
                extractCorrectEvaluations(sectionItems).toString(),
                extractProvidedEvaluations(evalRes).toString(),
                Long.toString(section.maxEvalScore()),
                Integer.toString(calculateEvaluationsScore(evalRes)),
                extractCorrectMemoryChunks(sectionItems).toString(),
                extractProvidedMemoryChunks(memRes).toString(),
                Long.toString(section.maxMemoryScore()),
                Integer.toString(calculateMemoryScore(memRes))
        };
    }

    public static List<String> extractDisplayTexts(List<ItemInterface> items){
        return items.stream().map(ItemInterface::getDisplayText).toList();
    }

    public static List<String> extractAffectiveValues(List<ItemInterface> items){
        return items.stream().map(ItemInterface::getAffectiveValue).toList();
    }

    public static List<Boolean> extractCorrectEvaluations(List<ItemInterface> items){
        return items.stream().filter(item -> item.getType() == ItemTypes.EXPERIMENT).map(item -> (ExperimentItem) item).map(ExperimentItem::getCorrectEvaluation).toList();
    }

    public static List<Boolean> extractProvidedEvaluations(List<ParticipantEvalResponse> responses){
        return responses.stream().map(ParticipantEvalResponse::getProvidedEvaluation).toList();
    }

    public static int calculateEvaluationsScore(List<ParticipantEvalResponse> responses){
        return responses.stream().mapToInt(ParticipantEvalResponse::getEvalScore).sum();
    }

    public static List<String> extractCorrectMemoryChunks(List<ItemInterface> items){
        return items.stream().filter(item -> item.getType() == ItemTypes.EXPERIMENT).map(item -> (ExperimentItem) item).map(ExperimentItem::getMemoryChunk).toList();
    }

    public static List<String> extractProvidedMemoryChunks(List<ParticipantMemResponse> responses){
        return responses.stream().map(ParticipantMemResponse::getProvidedChunk).toList();
    }

    public static int calculateMemoryScore(List<ParticipantMemResponse> responses){
        return responses.stream().mapToInt(ParticipantMemResponse::getMemorisationScore).sum();
    }

}
