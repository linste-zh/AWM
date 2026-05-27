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
            currentParticipant = new Participant("Trial Participant");
            launch(args);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
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
}
