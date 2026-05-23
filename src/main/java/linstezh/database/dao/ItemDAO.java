package linstezh.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import linstezh.database.mapper.ItemMapper;

public class ItemDAO {
    private Dao<ItemMapper, Integer> itemDao;

    public ItemDAO(ConnectionSource connectionSource) throws Exception {
        // Create DAO for User using DaoManager
        itemDao = DaoManager.createDao(connectionSource, ItemMapper.class);
    }

    public void createItem(String sectionID, int position, String displayText, String memoryChunk, String affectiveValue, boolean correctEvaluation) throws Exception {
        ItemMapper item = new ItemMapper(sectionID, position, displayText, memoryChunk, affectiveValue, correctEvaluation);
        itemDao.create(item); // Inserts the user into the "users" table
        System.out.println("Created user: " + item);
    }

}
