package linstezh.database.dbo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import linstezh.logic.Experiment.Experiment;

@DatabaseTable(tableName = "experiments")
public class ExperimentDBO implements DBO {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "name", canBeNull = false)
    private String name;

    @DatabaseField(columnName = "timerValue", defaultValue = "5000")
    private int timerValue;

    public ExperimentDBO(){}

    public ExperimentDBO(int id, String name, int timerValue){
        this.id = id;
        this.name = name;
        this.timerValue = timerValue;
    }

    public ExperimentDBO(Experiment experiment){
        this.id = experiment.getID();
        this.name = experiment.getName();
        this.timerValue = experiment.getTimerValue();
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

    public int getTimerValue() {
        return timerValue;
    }

    public void setTimerValue(int timerValue) {
        this.timerValue = timerValue;
    }
}
