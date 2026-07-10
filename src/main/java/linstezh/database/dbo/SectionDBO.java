package linstezh.database.dbo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import linstezh.logic.SectionTypes;

@DatabaseTable(tableName = "sections")
public class SectionDBO implements DBO{
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField (canBeNull = false, foreign = true)
    private ExperimentDBO experimentID;

    @DatabaseField (columnName = "type", canBeNull = false)
    private SectionTypes type;

    @DatabaseField (columnName = "position", canBeNull = false)
    private int position;

    @DatabaseField(columnName = "name", canBeNull = false)
    private String name;

    public SectionDBO(){}

    public SectionDBO(ExperimentDBO experimentID, SectionTypes type, int position, String name){
        this.type = type;
        this.experimentID = experimentID;
        this.position = position;
        this.name = name;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public ExperimentDBO getExperimentID() {
        return experimentID;
    }

    public void setExperimentID(ExperimentDBO experimentID) {
        this.experimentID = experimentID;
    }

    public SectionTypes getType() {
        return type;
    }

    public void setType(SectionTypes type) {
        this.type = type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
