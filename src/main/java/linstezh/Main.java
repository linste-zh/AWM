package linstezh;

import linstezh.database.DatabaseManager;

public class Main {
    public static void main(String[] args) {
        try{
            DatabaseManager db = new DatabaseManager();
            db.initTables();
            db.createItemInDB();
            db.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}