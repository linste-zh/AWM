package linstezh.database.mapper;

import linstezh.database.dbo.ExperimentItemDBO;
import linstezh.database.dbo.ItemDBO;
import linstezh.database.dbo.TrialItemDBO;
import linstezh.logic.Item.ExperimentItem;
import linstezh.logic.Item.Item;
import linstezh.logic.Item.TrialItem;


public class TrialItemMapper {
    public static TrialItemDBO toDBO(TrialItem item, ItemDBO baseItem){
        TrialItemDBO dbo = new TrialItemDBO();

        dbo.setID(item.getID());
        dbo.setBaseItemID(baseItem);
        dbo.setMemoryChunk(item.getMemoryChunk());
        dbo.setCorrectEvaluation(item.getCorrectEvaluation());
        dbo.setEvalExplanation(item.getEvalExplanation());

        return dbo;
    }

    public static TrialItem fromDBO(TrialItemDBO itemDBO, Item baseItem){
        TrialItem item = new TrialItem(baseItem);

        item.setID(itemDBO.getID());
        item.setMemoryChunk(itemDBO.getMemoryChunk());
        item.setCorrectEvaluation(itemDBO.getCorrectEvaluation());
        item.setEvalExplanation(itemDBO.getEvalExplanation());

        return item;
    }
}
