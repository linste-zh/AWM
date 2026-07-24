package linstezh.output.resultCSV;

import linstezh.executionManagers.ExperimentManager;
import linstezh.logic.Item.ExperimentItem;
import linstezh.logic.Item.ItemInterface;
import linstezh.logic.Item.ItemTypes;
import linstezh.logic.Section.SectionInterface;

import java.util.ArrayList;
import java.util.List;

public class CsvItemResultsGenerator implements CsvResultsGenerator{
    public List<String[]> generate(ExperimentManager manager) {
        List<String[]> csvRows = new ArrayList<>();
        csvRows.add(CsvItemRowGenerator.generateHeaders());
        for (SectionInterface section : manager.getSections()) {
            for (ItemInterface item : section.getItems()) {
                if (item.getType() == ItemTypes.EXPERIMENT) {
                    ExperimentItem expItem = (ExperimentItem) item;
                    csvRows.add(CsvItemRowGenerator.generateRow(
                            manager.getExperimentTitle(),
                            manager.getParticipantName(),
                            section.getName(),
                            expItem,
                            manager.matchEvalResponse(expItem),
                            manager.matchMemResponse(expItem)
                    ));
                } else {
                    csvRows.add(CsvItemRowGenerator.generateRow(
                            manager.getExperimentTitle(),
                            manager.getParticipantName(),
                            section.getName(),
                            item
                    ));
                }
            }
        }
        return csvRows;
    }

}
