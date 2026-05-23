package linstezh.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import linstezh.logic.ExperimentItem;

public class ItemDAO {
    private final Dao<ExperimentItem, Integer> itemDao;

    public ItemDAO(ConnectionSource connectionSource) throws Exception {
        // Create DAO for User using DaoManager
        itemDao = DaoManager.createDao(connectionSource, ExperimentItem.class);
    }

    public void createItem(String sectionID, int position, String displayText, String memoryChunk, String affectiveValue, boolean correctEvaluation) throws Exception {
        ExperimentItem item = new ExperimentItem(sectionID, position, displayText, memoryChunk, affectiveValue, correctEvaluation);
        itemDao.create(item); // Inserts the user into the "users" table
        System.out.println("Created user: " + item);
    }

}
