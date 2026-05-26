package linstezh.logic;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

@DatabaseTable(tableName = "sections")
public class Section {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField (columnName = "type", canBeNull = false)
    private SectionTypes type;

    @DatabaseField (columnName = "position", canBeNull = false)
    private int position;

    @DatabaseField(columnName = "name", canBeNull = false)
    private String name;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<ExperimentItem> items;

    public Section(){}

    public Section(SectionTypes type, int position, String name){
        this.type = type;
        this.position = position;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public ForeignCollection<ExperimentItem> getItems() {
        return items;
    }

    public List<Item> getItemsAsList() {
        return new ArrayList<>(items);
    }

    public void setItems(ForeignCollection<ExperimentItem> items) {
        this.items = items;
    }
}
