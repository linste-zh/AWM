package linstezh.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import linstezh.database.dbo.ItemDBO;
import linstezh.exceptions.databaseIdException;

import java.sql.SQLException;
import java.util.List;

public class ItemDAO implements DAO{
    private final Dao<ItemDBO, Integer> itemDao;
    ConnectionSource src;

    public ItemDAO(ConnectionSource src) throws Exception {
        this.src = src;
        itemDao = DaoManager.createDao(src, ItemDBO.class);
    }

    public ItemDBO getByID(int id) throws databaseIdException, SQLException {
        ItemDBO item = itemDao.queryForId(id);

        if(item == null){
            throw new databaseIdException("ID not found");
        }
        return item;
    }

    public List<ItemDBO> getBySectionID(int sectionID) throws SQLException {
        return itemDao.queryForEq("sectionID", sectionID);
    }

    public ItemDBO create(ItemDBO item) throws Exception {
        itemDao.create(item);
        return item;
    }

    public ItemDBO update(ItemDBO item) throws SQLException {
        itemDao.update(item);
        return item;
    }

    public ItemDBO delete(ItemDBO item) throws SQLException{
        itemDao.delete(item);
        return item;
    }
}