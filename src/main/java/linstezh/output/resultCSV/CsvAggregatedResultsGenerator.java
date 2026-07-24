package linstezh.output.resultCSV;

import linstezh.executionManagers.ExperimentManager;
import linstezh.logic.Section.SectionInterface;

import java.util.ArrayList;
import java.util.List;

public class CsvAggregatedResultsGenerator implements CsvResultsGenerator{
    public List<String[]> generate(ExperimentManager manager) {
        List<String[]> csvRows = new ArrayList<>();
        csvRows.add(CsvAggregatedRowGenerator.generateHeaders());
        for (SectionInterface section : manager.getSections()) {
            csvRows.add(CsvAggregatedRowGenerator.generateRow(
                    manager.getExperimentTitle(),
                    manager.getParticipantName(),
                    section,
                    manager.getEvalResponsesOfSection(section),
                    manager.getMemResponsesOfSection(section))
            );
        }
        return csvRows;
    }

}
