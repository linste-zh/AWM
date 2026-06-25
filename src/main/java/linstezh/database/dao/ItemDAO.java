package linstezh.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import linstezh.database.dbObjects.ItemDBO;

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

    public List<ItemDBO> getAllOfSectionID(int sectionID){
        ArrayList<ItemDBO> allExperimentItems = new ArrayList<>();

        for (ItemDBO item : itemDao) {
            if(item.getSectionID() == sectionID) {
                allExperimentItems.add(item);
            }
        }

        return allExperimentItems;
    }

    public List<ItemDBO> getAll(){
        ArrayList<ItemDBO> allExperimentItems = new ArrayList<>();

        for (ItemDBO item : itemDao) {
            allExperimentItems.add(item);
        }

        return allExperimentItems;
    }
}