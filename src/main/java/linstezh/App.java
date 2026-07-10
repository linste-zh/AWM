package linstezh;

import javafx.application.Application;
import javafx.stage.Stage;
import linstezh.database.DatabaseManager;
import linstezh.logic.Experiment.Experiment;
import linstezh.logic.ExperimentManager;

public class App extends Application {

    private static Experiment experiment;
    private static DatabaseManager db;
    private ExperimentManager manager;

    // Called before launch()
    public static void init(Experiment exp, DatabaseManager database) {
        experiment = exp;
        db = database;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ExperimentManager manager = new ExperimentManager(experiment, db, primaryStage);
        manager.start();
    }

    public ExperimentManager getManager() {
        return manager;
    }
}