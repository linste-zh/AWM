package linstezh.executionManagers;

import javafx.stage.Stage;
import linstezh.Main;
import linstezh.database.DatabaseManager;
import linstezh.logic.ActiveExperiment.Participant;
import linstezh.logic.ActiveExperiment.ParticipantEvalResponse;
import linstezh.logic.ActiveExperiment.ParticipantMemResponse;
import linstezh.logic.Experiment.Experiment;
import linstezh.logic.Item.ExperimentItem;

import linstezh.logic.Section.SectionInterface;
import linstezh.logic.Section.SectionTypes;

import java.util.ArrayList;
import java.util.List;

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

    public void saveEvalResponse(ExperimentItem item, boolean response){
        ParticipantEvalResponse newPER = new ParticipantEvalResponse(item, currentParticipant, response);
        evalResponses.add(newPER);
    }

    public void saveMemResponse(ExperimentItem item, String response){
        ParticipantMemResponse newPMR = new ParticipantMemResponse(item, currentParticipant, response);
        memResponses.add(newPMR);
    }
}
