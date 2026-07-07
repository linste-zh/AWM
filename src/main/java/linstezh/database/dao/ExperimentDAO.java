package linstezh.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import linstezh.database.dbo.ExperimentDBO;

import java.sql.SQLException;
import java.util.List;

public class ExperimentDAO {
    private final Dao<ExperimentDBO, Integer> experimentDao;
    private ConnectionSource src;

    public ExperimentDAO(ConnectionSource src) throws Exception {
        this.src = src;
        experimentDao = DaoManager.createDao(src, ExperimentDBO.class);
    }

    public ExperimentDBO getByID(int id) throws SQLException {
        List<ExperimentDBO> allWithID = experimentDao.query(
                experimentDao.queryBuilder()
                        .where().eq("id", id)
                        .prepare()
        );

        if(allWithID.size() > 1){
            throw new SQLException("ID not unique");
        }else if(allWithID.isEmpty()){
            throw new SQLException("ID not found");
        }else{
            return allWithID.getFirst();
        }
    }
}
