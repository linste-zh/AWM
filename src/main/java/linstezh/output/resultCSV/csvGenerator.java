package linstezh.output.resultCSV;

import linstezh.logic.ActiveExperiment.ParticipantEvalResponse;
import linstezh.logic.ActiveExperiment.ParticipantMemResponse;
import linstezh.logic.Item.ExperimentItem;
import linstezh.logic.Item.ItemInterface;

import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Objects;

public class csvGenerator {
    public static Dictionary<String, String> createRowDict(String experimentName, String participantName, Date date, String sectionName, ItemInterface item){
        Dictionary<String, String> row = new Hashtable<>();

        row.put("Experiment", experimentName);
        row.put("Participant", participantName);
        row.put("Date", date.toString());
        row.put("Section", sectionName);
        row.put("Item_ID", Integer.toString(item.getID()));
        row.put("Item_Position", Integer.toString(item.getPosition()));
        row.put("Item_Type", item.getType().toString());
        row.put("Item_Display", item.getDisplayText());
        row.put("Item_AffectiveValue", item.getAffectiveValue());
        row.put("Evaluation_required", null);
        row.put("Evaluation_given", null);
        row.put("Evaluation_score", null);
        row.put("MemoryChunk_required", null);
        row.put("MemoryChunk_given", null);
        row.put("MemoryChunk_rememberedContent", null);
        row.put("MemoryChunk_rememberedPlace", null);

        return row;
    }

    public static Dictionary<String, String>  createRowDict(String experimentName, String participantName, Date date, String sectionName, ExperimentItem item, ParticipantEvalResponse evalRes, ParticipantMemResponse memRes){
        assert(item == evalRes.getItem() && item == memRes.getItem());
        assert(Objects.equals(participantName, evalRes.getParticipant().getName()));
        assert(Objects.equals(participantName, memRes.getParticipant().getName()));

        Dictionary<String, String> row = new Hashtable<>();

        row.put("Experiment", experimentName);
        row.put("Participant", participantName);
        row.put("Date", date.toString());
        row.put("Section", sectionName);
        row.put("Item_ID", Integer.toString(item.getID()));
        row.put("Item_Position", Integer.toString(item.getPosition()));
        row.put("Item_Type", item.getType().toString());
        row.put("Item_Display", item.getDisplayText());
        row.put("Item_AffectiveValue", item.getAffectiveValue());
        row.put("Evaluation_required", Boolean.toString(item.getCorrectEvaluation()));
        row.put("Evaluation_given", Boolean.toString(evalRes.getProvidedEvaluation()));
        row.put("Evaluation_score", Integer.toString(evalRes.getEvalScore()));
        row.put("MemoryChunk_required", item.getMemoryChunk());
        row.put("MemoryChunk_given", memRes.getProvidedChunk());
        row.put("MemoryChunk_rememberedContent", Integer.toString(memRes.getContentScore()));
        row.put("MemoryChunk_rememberedPlace", Integer.toString(memRes.getPositionScore()));

        return row;
    }
}
