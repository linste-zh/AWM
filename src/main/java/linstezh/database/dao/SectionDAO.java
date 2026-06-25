package linstezh.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import linstezh.database.dbObjects.SectionDBO;

import java.util.ArrayList;
import java.util.List;

public class SectionDAO {
    private final Dao<SectionDBO, Integer> sectionDao;
    private ConnectionSource src;

    public SectionDAO(ConnectionSource src) throws Exception {
        this.src = src;
        sectionDao = DaoManager.createDao(src, SectionDBO.class);
    }

    public void create(SectionDBO experimentSection) throws Exception {
        sectionDao.create(experimentSection);
        System.out.println("Created item: " + experimentSection);
    }

    public List<SectionDBO> getAll(){
        ArrayList<SectionDBO> allSections = new ArrayList<>();

        for (SectionDBO section : sectionDao) {
            allSections.add(section);
        }

        return allSections;
    }

}
