package linstezh.logic;

public interface ItemInterface {
        int getID();
        Section getSection();
        ItemTypes getType();
        int getPosition();
        String getDisplayText();
        String getAffectiveValue();
}
