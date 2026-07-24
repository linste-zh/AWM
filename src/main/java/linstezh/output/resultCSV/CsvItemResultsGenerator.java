package linstezh.output.resultCSV;

import linstezh.executionManagers.ExperimentManager;
import linstezh.logic.ActiveExperiment.ParticipantEvalResponse;
import linstezh.logic.ActiveExperiment.ParticipantMemResponse;
import linstezh.logic.Item.ExperimentItem;
import linstezh.logic.Item.ItemInterface;
import linstezh.logic.Item.ItemTypes;
import linstezh.logic.Section.SectionInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CsvItemResultsGenerator implements CsvResultsGenerator{
    public List<String[]> generate(ExperimentManager manager) {
        List<String[]> csvRows = new ArrayList<>();
        csvRows.add(CsvRowGenerator.generateHeaders());
        for (SectionInterface section : manager.getSections()) {
            for (ItemInterface item : section.getItems()) {
                if (item.getType() == ItemTypes.EXPERIMENT) {
                    ExperimentItem expItem = (ExperimentItem) item;
                    csvRows.add(CsvRowGenerator.generateRow(
                            manager.getExperimentTitle(),
                            manager.getParticipantName(),
                            new Date(),
                            section.getName(),
                            expItem,
                            manager.matchEvalResponse(expItem),
                            manager.matchMemResponse(expItem)
                    ));
                } else {
                    csvRows.add(CsvRowGenerator.generateRow(
                            manager.getExperimentTitle(),
                            manager.getParticipantName(),
                            new Date(),
                            section.getName(),
                            item
                    ));
                }
            }
        }
        return csvRows;
    }

}
