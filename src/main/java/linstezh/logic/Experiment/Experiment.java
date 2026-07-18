package linstezh.logic.Experiment;

import linstezh.logic.Section.SectionInterface;
import linstezh.logic.Section.SectionTypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Experiment {
    private int id;
    private String name;
    private List<SectionInterface> sections = new ArrayList<>();

    public Experiment(){}

    public Experiment(int id, String name){
        this.id = id;
        this.name = name;
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

    public void addSection(SectionInterface section){
        this.sections.add(section);
    }

    public List<SectionInterface> getExperimentSections(){
        return sections.stream()
                .filter(section -> section.getType() == SectionTypes.EXPERIMENT)
                .collect(Collectors.toList());
    }

    @Override
    public String toString(){
        StringBuilder string = new StringBuilder();
        string.append(this.name + "(" + this.id + ")\n");
        string.append("*".repeat(10) + "\n");
        for(SectionInterface section : this.sections){
            string.append(section + "\n");
            string.append("-".repeat(10) + "\n");
        }

        return string.toString();
    }
}
