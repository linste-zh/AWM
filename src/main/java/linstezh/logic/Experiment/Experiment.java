package linstezh.logic.Experiment;

import linstezh.logic.Section.SectionInterface;
import linstezh.logic.Section.SectionTypes;

import javax.lang.model.element.UnknownDirectiveException;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Experiment {
    private int id;
    private String name;
    private List<SectionInterface> sections = new ArrayList<>();
    private int timerValue;
    private String expDirPath;

    public Experiment(){}

    public Experiment(int id, String name){
        this.id = id;
        this.name = name;
        this.expDirPath = createExpDir();
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

    public int getTimerValue() {
        return timerValue;
    }

    public void setTimerValue(int timerValue) {
        this.timerValue = timerValue;
    }

    public List<SectionInterface> sortSections(){
        Collections.sort(sections);

        return(sections);
    }

    public List<SectionInterface> getExperimentSections(){
        return sections.stream()
                .filter(section -> section.getType() == SectionTypes.EXPERIMENT)
                .collect(Collectors.toList());
    }

    public String getExpDirPath(){
        if (expDirPath == null){
            expDirPath = createExpDir();
        }

        return expDirPath;
    }

    private String createExpDir(){
        String basePath = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "AWM_Experiments"; //todo: not generalisable, find proper approach!

        String directoryPath = basePath + File.separator + name;
        File directory = new File(directoryPath);

        System.out.println(directoryPath);

        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created successfully.");
            } else {
                throw new Error("Failed to create directory.");
            }
        } else {
            System.out.println("Directory already exists.");
        }

        return directoryPath;
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
