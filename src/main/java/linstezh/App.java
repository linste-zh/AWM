package linstezh;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import linstezh.database.DatabaseManager;
import linstezh.executionManagers.ExperimentManager;
import linstezh.logic.Experiment.Experiment;

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
    public void start(Stage primaryStage){
        primaryStage.setScene(new Scene(new Pane(), 400, 200));
        primaryStage.show();

        ExperimentManager manager = new ExperimentManager(experiment, db, primaryStage);
        manager.start();
    }

    public ExperimentManager getManager() {
        return manager;
    }
}