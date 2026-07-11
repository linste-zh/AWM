package linstezh;

import javafx.application.Application;
import linstezh.database.DatabaseManager;
import linstezh.logic.Experiment.Experiment;

import java.util.List;

public class Main{
    public static void main(String[] args) throws Exception {
        DatabaseManager db = DatabaseManager.getInstance();

        db.initTables();
        List<Experiment> allExperiments = db.getAllExperiments();

        Experiment currentExperiment = db.loadExperiment(allExperiments.getFirst());
        System.out.println(currentExperiment);


        App.init(currentExperiment, db);
        Application.launch(App.class, args);
    }

    public static void finish(){
        try {
            DatabaseManager.getInstance().close();
        }catch(Exception e){
            e.printStackTrace();
        }
        System.exit(1);
    }
}