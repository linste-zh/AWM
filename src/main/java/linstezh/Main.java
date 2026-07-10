package linstezh;

import javafx.application.Application;
import linstezh.database.DatabaseManager;
import linstezh.logic.Experiment.Experiment;
import linstezh.logic.ExperimentManager;
import linstezh.App;

import java.util.List;

public class Main{
    public static void main(String[] args) throws Exception {
        DatabaseManager db = DatabaseManager.getInstance();

        db.initTables();
        List<Experiment> allExperiments = db.getAllExperiments();

        Experiment currentExperiment = db.loadExperiment(allExperiments.getFirst());


        App.init(currentExperiment, db);
        Application.launch(App.class, args);
    }

    public static void finish() throws Exception {
        DatabaseManager.getInstance().close();
        System.exit(1);
    }
}