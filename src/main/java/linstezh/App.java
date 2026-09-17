package linstezh;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import linstezh.database.DatabaseManager;
import linstezh.executionManagers.ExperimentManager;
import linstezh.logic.Experiment.Experiment;
import linstezh.ui.controllers.RootController;

import java.io.IOException;
import java.util.Optional;

public class App extends Application {

    private static Experiment experiment;
    private static DatabaseManager db;
    private ExperimentManager manager;
    private Stage primaryStage;

    // Called before launch()
    public static void init(Experiment exp, DatabaseManager database) {
        experiment = exp;
        db = database;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;

        FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("/linstezh/ui/screens/ExperimentScreenRoot.fxml"));
        Parent root = rootLoader.load();
        RootController rootController = rootLoader.getController();

        primaryStage.setOnCloseRequest(confirmCloseEventHandler);

        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.show();

        ExperimentManager manager = new ExperimentManager(experiment, db, primaryStage, rootController);
        manager.start();
    }

    private EventHandler<WindowEvent> confirmCloseEventHandler = event -> {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Confirm exit");
        a.setContentText("Any unsaved experimental data will be irretrievably lost.");

        Optional<ButtonType> closeResponse = a.showAndWait();
        if (!ButtonType.OK.equals(closeResponse.get())) {
            event.consume();
        }
    };

    public ExperimentManager getManager() {
        return manager;
    }
}