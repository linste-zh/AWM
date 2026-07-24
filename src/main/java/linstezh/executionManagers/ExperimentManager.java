package linstezh.executionManagers;

import javafx.stage.Stage;
import linstezh.Main;
import linstezh.database.DatabaseManager;
import linstezh.logic.ActiveExperiment.Participant;
import linstezh.logic.ActiveExperiment.ParticipantEvalResponse;
import linstezh.logic.ActiveExperiment.ParticipantMemResponse;
import linstezh.logic.Experiment.Experiment;
import linstezh.logic.Item.ExperimentItem;
import linstezh.logic.Item.ItemInterface;
import linstezh.logic.Section.SectionInterface;
import linstezh.logic.Section.SectionTypes;
import linstezh.output.resultCSV.CsvDocumentGenerator;
import linstezh.output.resultCSV.CsvResultsGenerator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ExperimentManager{
    final private Experiment experiment;
    final private Stage primaryStage;
    private final DatabaseManager db;
    private int nextSection = 0;
    private Participant currentParticipant;
    private final List<ParticipantEvalResponse> evalResponses;
    private final List<ParticipantMemResponse> memResponses;

    public ExperimentManager(Experiment experiment, DatabaseManager db, Stage primaryStage){
        this.experiment = experiment;
        this.primaryStage = primaryStage;
        this.db = db;
        evalResponses = new ArrayList<>();
        memResponses = new ArrayList<>();
    }

    public void start(){
        nextSection = 0;
        nextSection();
    }

    public void nextSection(){
        if(nextSection < experiment.getSections().size()){
            SectionManager wm = null;
            SectionInterface section = experiment.getSections().get(nextSection);
            switch (section.getType()){
                case SectionTypes.EXPERIMENT -> wm = new ExpSectionManager(section, this);
                case SectionTypes.START -> wm = new StartSectionManager(section, this);
                case SectionTypes.END -> wm = new EndSectionManager(section, this);
            }
            assert wm != null;  //todo: better check
            wm.display(primaryStage);
            nextSection += 1;
        }else{
            System.out.println(evalResponses);
            System.out.println(memResponses);
            Main.finish();
        }
    }

    public List<SectionInterface> getSections(){
        return this.experiment.getSections();
    }

    public String getExperimentTitle(){
        return this.experiment.getName();
    }

    public String getParticipantName(){
        return this.currentParticipant.getName();
    }

    public void createParticipant(String name){
        currentParticipant = new Participant(name);
        System.out.println(currentParticipant.getName());
    }

    public void saveEvalResponse(ExperimentItem item, boolean response, int evalScore){
        ParticipantEvalResponse newPER = new ParticipantEvalResponse(item, currentParticipant, response);
        newPER.setEvalScore(evalScore);
        evalResponses.add(newPER);
    }

    public void saveMemResponse(ExperimentItem item, String response, int memScore){
        ParticipantMemResponse newPMR = new ParticipantMemResponse(item, currentParticipant, response);
        newPMR.setMemorisationScore(memScore);
        memResponses.add(newPMR);
    }

    public ParticipantEvalResponse matchEvalResponse(ItemInterface item){
        return evalResponses.stream()
                .filter(res -> res.getItem() == item)
                .reduce((first, second) -> first).
                orElse(null);
    }

    public ParticipantMemResponse matchMemResponse(ItemInterface item){
        return memResponses.stream()
                .filter(res -> res.getItem() == item)
                .reduce((first, second) -> first).
                orElse(null);
    }

    public List<ParticipantEvalResponse> getEvalResponsesOfSection(SectionInterface section){
        List<ItemInterface> itemsOfSection = section.getItems();
        return evalResponses.stream()
                .filter(res -> itemsOfSection.contains(res.getItem()))
                .toList();
    }

    public List<ParticipantMemResponse> getMemResponsesOfSection(SectionInterface section){
        List<ItemInterface> itemsOfSection = section.getItems();
        return memResponses.stream()
                .filter(res -> itemsOfSection.contains(res.getItem()))
                .toList();
    }

    public void saveResults(File file, CsvResultsGenerator resultGenerator) throws IOException {
        List<String[]> csvRows = resultGenerator.generate(this);

        Path filePath = Paths.get(file.getPath());
        CsvDocumentGenerator.writeCsv(csvRows, filePath);
    }

}
