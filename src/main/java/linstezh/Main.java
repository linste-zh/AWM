package linstezh;

import javafx.application.Application;
import javafx.stage.Stage;
import linstezh.database.DatabaseManager;
import linstezh.logic.ExperimentSection;
import linstezh.visualisation.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private static List<ExperimentSection> experimentSections = new ArrayList<>();

    public static void main(String[] args) {
        try{
            DatabaseManager db = DatabaseManager.getInstance();
            db.initTables();
            experimentSections = db.sections().getAll();
            launch(args);

            db.close();
            System.exit(1);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        WindowManager wm = new WindowManager(experimentSections.get(0));
        wm.display(primaryStage);
    }
}