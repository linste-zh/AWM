package linstezh.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import linstezh.logic.Section;

import java.util.ArrayList;
import java.util.List;

public class SectionDAO {
    private final Dao<Section, Integer> sectionDao;
    ConnectionSource src;

    public SectionDAO(ConnectionSource src) throws Exception {
        this.src = src;
        sectionDao = DaoManager.createDao(src, Section.class);
    }

    public void create(Section section) throws Exception {
        sectionDao.create(section);
        System.out.println("Created item: " + section);
    }

    public List<Section> getAll(){
        ArrayList<Section> allSections = new ArrayList<>();

        for (Section section : sectionDao) {
            allSections.add(section);
        }

        return allSections;
    }

}
