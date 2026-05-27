package linstezh;


import linstezh.database.DatabaseManager;
import linstezh.logic.ExperimentManager;

public class Main{
    public static void main(String[] args) throws Exception {
        DatabaseManager.getInstance().initTables();

        ExperimentManager em = new ExperimentManager();
        em.launchExperiment(args);
    }

    public static void finish() throws Exception {
        DatabaseManager.getInstance().close();
        System.exit(1);
    }
}