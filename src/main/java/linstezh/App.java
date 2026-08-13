package linstezh;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import linstezh.database.DatabaseManager;
import linstezh.executionManagers.ExperimentManager;
import linstezh.logic.Experiment.Experiment;
import linstezh.ui.controllers.RootController;

import java.io.IOException;

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
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("/linstezh/ui/screens/ExperimentScreenRoot.fxml"));
        Parent root = rootLoader.load();
        RootController rootController = rootLoader.getController();
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(300);
        primaryStage.show();

        ExperimentManager manager = new ExperimentManager(experiment, db, primaryStage, rootController);
        manager.start();
    }

    public ExperimentManager getManager() {
        return manager;
    }
}