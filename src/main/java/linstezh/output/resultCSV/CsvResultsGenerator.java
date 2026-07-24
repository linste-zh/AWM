package linstezh.output.resultCSV;

import linstezh.executionManagers.ExperimentManager;

import java.util.List;

public interface CsvResultsGenerator {
    List<String[]> generate(ExperimentManager manager);
}
