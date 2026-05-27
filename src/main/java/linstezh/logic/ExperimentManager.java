package linstezh.logic;

import javafx.application.Application;
import javafx.stage.Stage;
import linstezh.Main;
import linstezh.database.DatabaseManager;
import linstezh.visualisation.SectionWindowManager;

import java.util.List;

public class ExperimentManager extends Application {
    private List<ExperimentSection> experimentSections;
    private Participant currentParticipant;
    DatabaseManager db;
    Stage primaryStage;
    int nextSection = 0;

    public ExperimentManager() throws Exception {
        db = DatabaseManager.getInstance();
        experimentSections = db.sections().getAll();
    }

    public void launchExperiment(String[] args){
        try{
            launch(args);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        currentParticipant = new Participant("Trial Participant");
        db.participants().create(currentParticipant);

        this.primaryStage = primaryStage;
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
        db.participantEvalResponses().create(newPER);
    }

    public void saveMemResponse(ExperimentItem item, String response) throws Exception {
        ParticipantMemResponse newPMR = new ParticipantMemResponse(item, currentParticipant, response);
        db.participantMemResponses().create(newPMR);
    }
}
