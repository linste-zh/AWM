package linstezh.logic;

import linstezh.database.dbObjects.ExperimentDBO;

import java.util.List;

public class Experiment {
    private int id;
    private String name;
    private List<SectionInterface> sections;

    public Experiment(int id, String name, List<SectionInterface> sections){
        this.id = id;
        this.name = name;
        this.sections = sections;
    }

    public Experiment(ExperimentDBO experiment){
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

    public List<SectionInterface> getSections() {
        return sections;
    }

    public void setSections(List<SectionInterface> sections) {
        this.sections = sections;
    }
}
