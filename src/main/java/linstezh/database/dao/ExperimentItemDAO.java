package linstezh.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import linstezh.database.dbObjects.ExperimentItemDBO;

public class ExperimentItemDAO {
    private final Dao<ExperimentItemDBO, Integer> experimentItemDao;
    private ConnectionSource src;

    public ExperimentItemDAO(ConnectionSource src) throws Exception {
        this.src = src;
        experimentItemDao = DaoManager.createDao(src, ExperimentItemDBO.class);
    }
}
