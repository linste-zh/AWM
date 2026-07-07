package linstezh.logic;

import java.util.List;

public interface SectionInterface {
    int getID();
    int getExperimentID();
    SectionTypes getType();
    int getPosition();
    String getName();
    List<ItemInterface> getItems();
}
