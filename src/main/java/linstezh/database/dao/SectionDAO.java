package linstezh.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import linstezh.logic.ExperimentSection;

import java.util.ArrayList;
import java.util.List;

public class SectionDAO {
    private final Dao<ExperimentSection, Integer> sectionDao;
    ConnectionSource src;

    public SectionDAO(ConnectionSource src) throws Exception {
        this.src = src;
        sectionDao = DaoManager.createDao(src, ExperimentSection.class);
    }

    public void create(ExperimentSection experimentSection) throws Exception {
        sectionDao.create(experimentSection);
        System.out.println("Created item: " + experimentSection);
    }

    public List<ExperimentSection> getAll(){
        ArrayList<ExperimentSection> allExperimentSections = new ArrayList<>();

        for (ExperimentSection experimentSection : sectionDao) {
            allExperimentSections.add(experimentSection);
        }

        return allExperimentSections;
    }

}
