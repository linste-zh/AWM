package linstezh.logic.Section;

import linstezh.logic.Item.ItemInterface;

import java.util.List;

public interface SectionInterface extends Comparable<SectionInterface>{
    int getID();
    int getExperimentID();
    SectionTypes getType();
    int getPosition();
    String getName();
    List<ItemInterface> getItems();
    int maxEvalScore();
    int maxMemoryScore();
    int compareTo(SectionInterface other);
}
