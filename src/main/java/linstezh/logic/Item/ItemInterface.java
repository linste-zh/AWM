package linstezh.logic.Item;

import linstezh.logic.Section.Section;

public interface ItemInterface {
        int getID();
        Section getSection();
        ItemTypes getType();
        int getPosition();
        String getDisplayText();
        String getAffectiveValue();
}
