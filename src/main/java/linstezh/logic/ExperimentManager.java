package linstezh.logic;

import javafx.application.Application;
import javafx.stage.Stage;
import linstezh.Main;
import linstezh.database.DatabaseManager;
import linstezh.logic.ActiveExperiment.Participant;
import linstezh.logic.ActiveExperiment.ParticipantEvalResponse;
import linstezh.logic.ActiveExperiment.ParticipantMemResponse;
import linstezh.logic.Experiment.Experiment;
import linstezh.logic.Item.ExperimentItem;

import linstezh.logic.Section.SectionInterface;
import linstezh.visualisation.SectionWindowManager;

import java.util.List;

public class ExperimentManager{
    final private Experiment experiment;
    final private Stage primaryStage;
    private List<SectionInterface> experimentSections;
    private Participant currentParticipant;
    DatabaseManager db;
    int nextSection = 0;

    public ExperimentManager(Experiment experiment, DatabaseManager db, Stage primaryStage) throws Exception {
        this.experiment = experiment;
        this.primaryStage = primaryStage;
        this.db = db;
        experimentSections = experiment.getSections(); //PLACEHOLDER
    }

    public void start() throws Exception {
        currentParticipant = new Participant("Trial Participant");
        nextSection = 0;
        nextSection();
    }

    public void nextSection() throws Exception {
        if(nextSection < experimentSections.size()){
            SectionWindowManager wm = new SectionWindowManager(experimentSections.get(nextSection), this);
            wm.display(primaryStage);
            nextSection += 1;
        }else{
            Main.finish();
        }
    }

    public void saveEvalResponse(ExperimentItem item, boolean response) throws Exception {
        System.out.println(item);
        System.out.println(currentParticipant);
        System.out.println(response);
        ParticipantEvalResponse newPER = new ParticipantEvalResponse(item, currentParticipant, response);
    }

    public void saveMemResponse(ExperimentItem item, String response) throws Exception {
        ParticipantMemResponse newPMR = new ParticipantMemResponse(item, currentParticipant, response);
    }
}
