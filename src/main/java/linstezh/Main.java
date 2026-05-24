package linstezh;

import javafx.application.Application;
import javafx.stage.Stage;
import linstezh.database.DatabaseManager;
import linstezh.logic.Section;
import linstezh.visualisation.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private static List<Section> sections = new ArrayList<>();

    public static void main(String[] args) {
        try{
            DatabaseManager db = new DatabaseManager();
            db.initTables();
            sections = db.sections().getAll();
            launch(args);

            db.close();
            System.exit(1);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        WindowManager wm = new WindowManager(sections.get(0));
        wm.display(primaryStage);
    }
}