package linstezh.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import linstezh.database.dbObjects.ExperimentDBO;

public class ExperimentDAO {
    private final Dao<ExperimentDBO, Integer> experimentDao;
    private ConnectionSource src;

    public ExperimentDAO(ConnectionSource src) throws Exception {
        this.src = src;
        experimentDao = DaoManager.createDao(src, ExperimentDBO.class);
    }
}
