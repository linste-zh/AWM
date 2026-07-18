package linstezh.output.resultCSV;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import linstezh.logic.ActiveExperiment.ParticipantEvalResponse;
import linstezh.logic.ActiveExperiment.ParticipantMemResponse;
import linstezh.logic.Item.ExperimentItem;
import linstezh.logic.Item.ItemInterface;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

public class CsvGenerator {


    public static String[] generateRow(String experimentName, String participantName, Date date, String sectionName, ItemInterface item){
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
                null,
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

    public static Path writeCsv(List<String[]> lines, Path path) throws IOException{
        try (CSVWriter writer = new CSVWriter(new FileWriter(path.toString()))) {
            writer.writeAll(lines);
        }
        return path;
    }

    public static List<String[]> readCSV(Path path) throws CsvException, IOException{
        try (Reader reader = Files.newBufferedReader(path)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
            }
        }
    }

}
