package linstezh.logic;

import linstezh.database.dbObjects.SectionDBO;

import java.util.List;

public class Section implements SectionInterface{
    private int id;

    private Experiment experiment;

    private SectionTypes type;

    private int position;

    private String name;

    private List<Item> items;

    public Section(Experiment experiment, SectionTypes type, int position, String name){
        this.experiment = experiment;
        this.type = type;
        this.position = position;
        this.name = name;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public Experiment getExperiment() {
        return experiment;
    }

    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    public int getExperimentID(){
        //return experiment.getID();
        return 0; //placeholder
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
