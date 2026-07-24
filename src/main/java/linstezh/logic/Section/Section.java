package linstezh.logic.Section;

import linstezh.logic.Experiment.Experiment;
import linstezh.logic.Item.ItemInterface;
import linstezh.logic.Item.ItemTypes;

import java.util.ArrayList;
import java.util.List;

public class Section implements SectionInterface{
    private int id;

    private Experiment experiment;

    private SectionTypes type;

    private int position;

    private String name;

    private List<ItemInterface> items = new ArrayList<>();

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

    public void addItem(ItemInterface item){
        this.items.add(item);
    }

    public long maxEvalScore() {
        return items.stream().filter(item -> item.getType() == ItemTypes.EXPERIMENT).count();
    }

    public long maxMemoryScore() {
        return items.stream().filter(item -> item.getType() == ItemTypes.EXPERIMENT).count() * 2;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        string.append(this.position + ". " + this.name + "(" + this.id + ")\n");
        for(ItemInterface item : this.items) {
            string.append("\t" + item + "\n");
        }


        return string.toString();
    }
}
