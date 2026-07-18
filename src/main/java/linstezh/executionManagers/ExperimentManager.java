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
import linstezh.logic.Item.ItemTypes;
import linstezh.logic.Section.Section;
import linstezh.logic.Section.SectionInterface;
import linstezh.logic.Section.SectionTypes;
import linstezh.output.resultCSV.CsvGenerator;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ExperimentManager{
    final private Experiment experiment;
    final private Stage primaryStage;
    final private List<SectionInterface> experimentSections;
    private final DatabaseManager db;
    private int nextSection = 0;
    private Participant currentParticipant;
    private final List<ParticipantEvalResponse> evalResponses;
    private final List<ParticipantMemResponse> memResponses;

    public ExperimentManager(Experiment experiment, DatabaseManager db, Stage primaryStage){
        this.experiment = experiment;
        this.primaryStage = primaryStage;
        this.db = db;
        this.experimentSections = experiment.getSections();
        evalResponses = new ArrayList<>();
        memResponses = new ArrayList<>();
    }

    public void start(){
        nextSection = 0;
        nextSection();
    }

    public void nextSection(){
        if(nextSection < experimentSections.size()){
            SectionManager wm = null;
            SectionInterface section = experimentSections.get(nextSection);
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

    public String getExperimentTitle(){
        return this.experiment.getName();
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

    public void saveResults() throws IOException {
        List<String[]> csvRows = new ArrayList<>();
        csvRows.add(CsvGenerator.generateHeaders());
        for(SectionInterface section : experiment.getExperimentSections()){
            for(ItemInterface item : section.getItems()){
                if(item.getType() == ItemTypes.EXPERIMENT){
                    ExperimentItem expItem = (ExperimentItem) item;
                    csvRows.add(CsvGenerator.generateRow(
                            experiment.getName(),
                            currentParticipant.getName(),
                            new Date(),
                            section.getName(),
                            expItem,
                            matchEvalResponse(expItem),
                            matchMemResponse(expItem)
                    ));
                }else{
                    csvRows.add(CsvGenerator.generateRow(
                            experiment.getName(),
                            currentParticipant.getName(),
                            new Date(),
                            section.getName(),
                            item
                    ));
                }
            }
        }
        Path filePath = Paths.get(URI.create("file:///C://Users//stein//Documents//results.csv"));
        CsvGenerator.writeCsv(csvRows, filePath);
    }
}
