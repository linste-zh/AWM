package linstezh.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import linstezh.database.dbo.ExperimentItemDBO;
import linstezh.exceptions.databaseIdException;

import java.sql.SQLException;
import java.util.List;

public class ExperimentItemDAO implements DAO{
    private final Dao<ExperimentItemDBO, Integer> experimentItemDao;
    private ConnectionSource src;

    public ExperimentItemDAO(ConnectionSource src) throws Exception {
        this.src = src;
        experimentItemDao = DaoManager.createDao(src, ExperimentItemDBO.class);
    }

    public ExperimentItemDBO getByID(int id) throws databaseIdException, SQLException {
        ExperimentItemDBO expItem = experimentItemDao.queryForId(id);

        if(expItem == null){
            throw new databaseIdException("ID not found");
        }
        return expItem;
    }

    public ExperimentItemDBO getByItemID(int itemID) throws SQLException {
        List<ExperimentItemDBO> allExpItems = experimentItemDao.queryForEq("baseItemID", itemID);
        if(allExpItems.isEmpty()){
            throw new databaseIdException("BaseID not found");
        }else if(allExpItems.size() > 1){
            throw new databaseIdException("BaseID not unique");
        }else{
            return allExpItems.getFirst();
        }

    }

    public ExperimentItemDBO create(ExperimentItemDBO expItem) throws Exception {
        experimentItemDao.create(expItem);
        return expItem;
    }

    public ExperimentItemDBO update(ExperimentItemDBO expItem) throws SQLException {
        experimentItemDao.update(expItem);
        return expItem;
    }

    public ExperimentItemDBO delete(ExperimentItemDBO expItem) throws SQLException{
        experimentItemDao.delete(expItem);
        return expItem;
    }
}
