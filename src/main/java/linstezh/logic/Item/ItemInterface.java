package linstezh.logic.Item;

import linstezh.logic.Section.Section;

import java.util.Date;

public interface ItemInterface {
        int getID();
        Section getSection();
        ItemTypes getType();
        int getPosition();
        String getDisplayText();
        String getAffectiveValue();
        Date getDisplayDate();
        void setDisplayDate(Date date);
}
