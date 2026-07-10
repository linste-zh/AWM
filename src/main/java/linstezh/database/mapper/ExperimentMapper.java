package linstezh.database.mapper;

import linstezh.database.dbo.ExperimentDBO;
import linstezh.logic.Experiment;

/*
    Note: Sections need to be transformed and added to DB/extracted from DB separately
 */
public class ExperimentMapper {
    public static ExperimentDBO toDBO(Experiment experiment){
        ExperimentDBO dbo = new ExperimentDBO();
        dbo.setID(experiment.getID());
        dbo.setName(experiment.getName());
        return dbo;
    }

    public static Experiment fromDBO(ExperimentDBO experimentDBO) {
        Experiment exp = new Experiment();

        exp.setID(experimentDBO.getID());
        exp.setName(experimentDBO.getName());

        return exp;
    }
}
