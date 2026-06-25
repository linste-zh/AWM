package linstezh.database.dbObjects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import linstezh.logic.*;

@DatabaseTable(tableName = "sections")
public class SectionDBO {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField (canBeNull = false)
    private int experimentID;

    @DatabaseField (columnName = "type", canBeNull = false)
    private SectionTypes type;

    @DatabaseField (columnName = "position", canBeNull = false)
    private int position;

    @DatabaseField(columnName = "name", canBeNull = false)
    private String name;

    public SectionDBO(){}

    public SectionDBO(int experimentID, SectionTypes type, int position, String name){
        this.type = type;
        this.experimentID = experimentID;
        this.position = position;
        this.name = name;
    }

    public SectionDBO(SectionInterface section){
        this.id = section.getID();
        this.type = section.getType();
        this.experimentID = section.getExperimentID();
        this.position = section.getPosition();
        this.name = section.getName();
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getExperimentID() {
        return experimentID;
    }

    public void setExperimentID(int experimentID) {
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
