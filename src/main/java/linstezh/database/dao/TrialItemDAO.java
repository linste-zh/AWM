package linstezh.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import linstezh.database.dbo.TrialItemDBO;
import linstezh.exceptions.databaseIdException;

import java.sql.SQLException;
import java.util.List;

public class TrialItemDAO implements DAO{
    private final Dao<TrialItemDBO, Integer> trialItemDao;
    private ConnectionSource src;

    public TrialItemDAO(ConnectionSource src) throws Exception {
        this.src = src;
        trialItemDao = DaoManager.createDao(src, TrialItemDBO.class);
    }

    public TrialItemDBO getByID(int id) throws databaseIdException, SQLException {
        TrialItemDBO trialItem = trialItemDao.queryForId(id);

        if(trialItem == null){
            throw new databaseIdException("ID not found");
        }
        return trialItem;
    }

    public TrialItemDBO getByItemID(int itemID) throws SQLException {
        List<TrialItemDBO> allTrialItems = trialItemDao.queryForEq("baseItemID", itemID);
        if(allTrialItems.isEmpty()){
            throw new databaseIdException("BaseID not found");
        }else if(allTrialItems.size() > 1){
            throw new databaseIdException("BaseID not unique");
        }else{
            return allTrialItems.getFirst();
        }
    }

    public TrialItemDBO create(TrialItemDBO trialItem) throws Exception {
        trialItemDao.create(trialItem);
        return trialItem;
    }

    public TrialItemDBO update(TrialItemDBO trialItem) throws SQLException {
        trialItemDao.update(trialItem);
        return trialItem;
    }

    public TrialItemDBO delete(TrialItemDBO trialItem) throws SQLException{
        trialItemDao.delete(trialItem);
        return trialItem;
    }
}
