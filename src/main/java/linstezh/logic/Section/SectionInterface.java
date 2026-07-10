package linstezh.logic.Section;

import linstezh.logic.Item.ItemInterface;

import java.util.List;

public interface SectionInterface {
    int getID();
    int getExperimentID();
    SectionTypes getType();
    int getPosition();
    String getName();
    List<ItemInterface> getItems();
}
