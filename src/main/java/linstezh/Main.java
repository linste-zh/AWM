package linstezh;

import javafx.application.Application;
import javafx.stage.Stage;
import linstezh.database.DatabaseManager;
import linstezh.logic.ExperimentItem;
import linstezh.visualisation.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private static List<ExperimentItem> items = new ArrayList<>();

    public static void main(String[] args) {
        try{
            DatabaseManager db = new DatabaseManager();
            db.initTables();
            //ExperimentItem experimentItem = new ExperimentItem("1", 1, "Example Text", "Text", "happy", true);
            //itemDAO.create(experimentItem);
            items = db.items().getAll();
            launch(args);

            db.close();
            System.exit(1);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        WindowManager wm = new WindowManager(items);
        wm.display(primaryStage);
    }
}