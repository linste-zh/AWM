package linstezh;

import linstezh.database.DatabaseManager;
import linstezh.database.dao.ItemDAO;
import linstezh.logic.ExperimentItem;

public class Main {
    public static void main(String[] args) {
        try{
            DatabaseManager db = new DatabaseManager();
            db.initTables();
            ItemDAO itemDAO = new ItemDAO(db);
            ExperimentItem experimentItem = new ExperimentItem("1", 1, "Example Text", "Text", "happy", true);
            itemDAO.create(experimentItem);
            System.out.print(itemDAO.getAll());
            db.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}