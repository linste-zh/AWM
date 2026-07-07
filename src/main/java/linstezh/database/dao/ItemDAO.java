package linstezh.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import linstezh.database.dbo.ItemDBO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    private final Dao<ItemDBO, Integer> itemDao;
    ConnectionSource src;

    public ItemDAO(ConnectionSource src) throws Exception {
        this.src = src;
        itemDao = DaoManager.createDao(src, ItemDBO.class);
    }

    public void create(ItemDBO item) throws Exception {
        itemDao.create(item);
        System.out.println("Created item: " + item);
    }

    public List<ItemDBO> getBySectionId(int sectionID) throws SQLException {
        return itemDao.query(
                itemDao.queryBuilder()
                        .where().eq("sectionID", sectionID)
                        .prepare()
        );
    }

    public List<ItemDBO> getAll(){
        ArrayList<ItemDBO> allExperimentItems = new ArrayList<>();

        for (ItemDBO item : itemDao) {
            allExperimentItems.add(item);
        }

        return allExperimentItems;
    }
}