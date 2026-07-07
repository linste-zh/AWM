package linstezh.logic;

import java.util.List;

public class Section implements SectionInterface{
    private int id;

    private Experiment experiment;

    private SectionTypes type;

    private int position;

    private String name;

    private List<ItemInterface> items;

    public Section(){}

    public Section(int id, Experiment experiment, SectionTypes type, int position, String name){
        this.id = id;
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

    public List<ItemInterface> getItems() {
        return items;
    }

    public void setItems(List<ItemInterface> items) {
        this.items = items;
    }
}
