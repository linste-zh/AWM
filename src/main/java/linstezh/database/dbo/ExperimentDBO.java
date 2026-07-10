package linstezh.database.dbo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import linstezh.database.dao.DAO;
import linstezh.logic.Experiment;

@DatabaseTable(tableName = "experiments")
public class ExperimentDBO implements DBO {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "name", canBeNull = false)
    private String name;

    public ExperimentDBO(){}

    public ExperimentDBO(int id, String name){
        this.id = id;
        this.name = name;
    }

    public ExperimentDBO(Experiment experiment){
        this.id = experiment.getID();
        this.name = experiment.getName();
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
