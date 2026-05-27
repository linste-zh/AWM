package linstezh.logic;

public interface Item {
    int getId();
    ExperimentSection getSection();
    ItemTypes getType();
    int getPosition();
    String getDisplayText();
}
