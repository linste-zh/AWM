package linstezh.database.mapper;

import linstezh.database.dbo.SectionDBO;
import linstezh.logic.Experiment.Experiment;
import linstezh.logic.Section.Section;

/*
    Note: Items need to be transformed and added to DB/extracted from DB separately
 */
public class SectionMapper {
    public static SectionDBO toDBO(Section section){
        SectionDBO dbo = new SectionDBO();

        dbo.setID(section.getID());
        dbo.setExperimentID(ExperimentMapper.toDBO(section.getExperiment()));
        dbo.setPosition(section.getPosition());
        dbo.setType(section.getType());
        dbo.setName(section.getName());

        return dbo;
    }

    public static Section fromDBO(SectionDBO sectionDBO, Experiment experiment) {
        Section sect = new Section();

        sect.setID(sectionDBO.getID());
        sect.setExperiment(experiment);
        sect.setPosition(sectionDBO.getPosition());
        sect.setType(sectionDBO.getType());
        sect.setName(sectionDBO.getName());

        return sect;
    }
}
