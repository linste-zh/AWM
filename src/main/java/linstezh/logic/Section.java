package linstezh.logic;

import java.util.List;

public interface Section {
    int getId();
    SectionTypes getType();
    int getPosition();
    String getName();
    List<Item> getItemsAsList();
}
