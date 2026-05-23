package linstezh.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import linstezh.database.DatabaseManager;
import linstezh.logic.ExperimentItem;

import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    private final Dao<ExperimentItem, Integer> itemDao;

    public ItemDAO(DatabaseManager dbm) throws Exception {
        ConnectionSource src = dbm.getConnectionSource();
        itemDao = DaoManager.createDao(src, ExperimentItem.class);
    }

    public void create(ExperimentItem item) throws Exception {
        itemDao.create(item);
        System.out.println("Created item: " + item);
    }

    public List<ExperimentItem> getAll(){
        ArrayList<ExperimentItem> allExperimentItems = new ArrayList<>();

        for (ExperimentItem item : itemDao) {
            allExperimentItems.add(item);
        }

        return allExperimentItems;
    }

}
