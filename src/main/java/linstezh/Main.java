package linstezh;

import linstezh.database.DatabaseManager;
import linstezh.logic.Experiment;
import linstezh.logic.ExperimentManager;

public class Main{
    public static void main(String[] args) throws Exception {
        DatabaseManager db = DatabaseManager.getInstance();

        db.initTables();
        for(Experiment exp : db.getAllExperiments()){
            db.loadExperiment(exp);
            System.out.println(exp);
        }

        ExperimentManager em = new ExperimentManager();
        //em.launchExperiment(args);


    }

    public static void finish() throws Exception {
        DatabaseManager.getInstance().close();
        System.exit(1);
    }
}