package linstezh.output.resultCSV;

import linstezh.executionManagers.ExperimentManager;
import linstezh.logic.ActiveExperiment.ParticipantEvalResponse;
import linstezh.logic.ActiveExperiment.ParticipantMemResponse;
import linstezh.logic.Section.SectionInterface;

import java.util.List;

public interface CsvResultsGenerator {
    List<String[]> generate(ExperimentManager manager);
}
