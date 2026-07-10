package linstezh.database.mapper;

import linstezh.database.dbo.ExperimentItemDBO;
import linstezh.database.dbo.ItemDBO;
import linstezh.logic.Item.ExperimentItem;
import linstezh.logic.Item.Item;


public class ExperimentItemMapper {
    public static ExperimentItemDBO toDBO(ExperimentItem item, ItemDBO baseItem){
        ExperimentItemDBO dbo = new ExperimentItemDBO();

        dbo.setID(item.getID());
        dbo.setBaseItemID(baseItem);
        dbo.setMemoryChunk(item.getMemoryChunk());
        dbo.setCorrectEvaluation(item.getCorrectEvaluation());

        return dbo;
    }

    public static ExperimentItem fromDBO(ExperimentItemDBO itemDBO, Item baseItem){
        ExperimentItem item = new ExperimentItem(baseItem);

        item.setID(itemDBO.getID());
        item.setMemoryChunk(itemDBO.getMemoryChunk());
        item.setCorrectEvaluation(itemDBO.getCorrectEvaluation());

        return item;
    }
}
