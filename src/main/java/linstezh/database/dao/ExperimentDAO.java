package linstezh.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import linstezh.database.dbo.ExperimentDBO;
import linstezh.exceptions.databaseIdException;

import java.sql.SQLException;
import java.util.List;

public class ExperimentDAO implements DAO{
    private final Dao<ExperimentDBO, Integer> experimentDao;
    private ConnectionSource src;

    public ExperimentDAO(ConnectionSource src) throws Exception {
        this.src = src;
        experimentDao = DaoManager.createDao(src, ExperimentDBO.class);
    }

    public ExperimentDBO getByID(int id) throws databaseIdException, SQLException {
        ExperimentDBO experiment = experimentDao.queryForId(id);

        if(experiment == null){
            throw new databaseIdException("ID not found");
        }
        return experiment;
    }

    public List<ExperimentDBO> getAll() throws SQLException {
        return experimentDao.queryForAll();
    }

    public ExperimentDBO create(ExperimentDBO experiment) throws Exception {
        experimentDao.create(experiment);
        return experiment;
    }

    public ExperimentDBO update(ExperimentDBO experiment) throws SQLException {
        experimentDao.update(experiment);
        return experiment;
    }

    public ExperimentDBO delete(ExperimentDBO experiment) throws SQLException{
        experimentDao.delete(experiment);
        return experiment;
    }
}
