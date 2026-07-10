package linstezh.database.mapper;

import linstezh.database.dbo.ItemDBO;
import linstezh.logic.Item.Item;
import linstezh.logic.Item.ItemInterface;
import linstezh.logic.Section.Section;

public class ItemMapper {
    public static ItemDBO toDBO(ItemInterface item){
        ItemDBO dbo = new ItemDBO();

        dbo.setID(item.getID());
        dbo.setSectionID(SectionMapper.toDBO(item.getSection()));
        dbo.setType((item.getType()));
        dbo.setPosition(item.getPosition());
        dbo.setDisplayText(item.getDisplayText());
        dbo.setAffectiveValue(item.getAffectiveValue());

        return dbo;
    }

    public static Item fromDBO(ItemDBO itemDBO, Section section){
        Item item = new Item();

        item.setID(itemDBO.getID());
        item.setSection(section);
        item.setType((itemDBO.getType()));
        item.setPosition(itemDBO.getPosition());
        item.setDisplayText(itemDBO.getDisplayText());
        item.setAffectiveValue(itemDBO.getAffectiveValue());

        return item;
    }
}
