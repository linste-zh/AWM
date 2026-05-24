package linstezh;

import javafx.application.Application;
import javafx.stage.Stage;
import linstezh.database.DatabaseManager;
import linstezh.database.dao.ItemDAO;
import linstezh.logic.ExperimentItem;
import linstezh.visualisation.WindowManager;

import java.util.ArrayList;
import java.util.List;

import static javafx.application.Application.launch;

public class Main extends Application {
    private static List<ExperimentItem> items = new ArrayList<>();

    public static void main(String[] args) {
        try{
            DatabaseManager db = new DatabaseManager();
            db.initTables();
            ItemDAO itemDAO = new ItemDAO(db);
            //ExperimentItem experimentItem = new ExperimentItem("1", 1, "Example Text", "Text", "happy", true);
            //itemDAO.create(experimentItem);
            items = itemDAO.getAll();
            launch(args);

            db.close();
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