package linstezh.logic.Item;

import linstezh.logic.Section.Section;
import linstezh.logic.Section.SectionInterface;

import java.util.Date;

public interface ItemInterface extends Comparable<ItemInterface>{
        int getID();
        Section getSection();
        ItemTypes getType();
        int getPosition();
        String getDisplayText();
        String getAffectiveValue();
        Date getDisplayDate();
        void setDisplayDate(Date date);
        int compareTo(ItemInterface other);
}
