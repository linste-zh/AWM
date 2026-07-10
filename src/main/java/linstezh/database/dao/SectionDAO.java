package linstezh.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import linstezh.database.dbo.ExperimentDBO;
import linstezh.database.dbo.SectionDBO;
import linstezh.exceptions.databaseIdException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SectionDAO implements DAO{
    private final Dao<SectionDBO, Integer> sectionDao;
    private ConnectionSource src;

    public SectionDAO(ConnectionSource src) throws Exception {
        this.src = src;
        sectionDao = DaoManager.createDao(src, SectionDBO.class);
    }

    public SectionDBO getByID(int id) throws databaseIdException, SQLException {
        SectionDBO section = sectionDao.queryForId(id);

        if(section == null){
            throw new databaseIdException("ID not found");
        }
        return section;
    }

    public List<SectionDBO> getByExperimentID(int experimentID) throws SQLException {
        return sectionDao.queryForEq("experimentID", experimentID);
    }

    public SectionDBO create(SectionDBO section) throws Exception {
        sectionDao.create(section);
        return section;
    }

    public SectionDBO update(SectionDBO section) throws SQLException {
        sectionDao.update(section);
        return section;
    }
}
