package linstezh.executionManagers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import linstezh.logic.ActiveExperiment.ParticipantEvalResponse;
import linstezh.logic.ActiveExperiment.ParticipantMemResponse;
import linstezh.logic.Item.ExperimentItem;
import linstezh.logic.Item.ItemInterface;
import linstezh.logic.Item.ItemTypes;
import linstezh.logic.Section.SectionInterface;
import linstezh.output.resultCSV.CsvAggregatedRowGenerator;
import linstezh.ui.adapters.ExpItemAdapter;
import linstezh.ui.adapters.ImageDistractorItemAdapter;
import linstezh.ui.adapters.TextDistractorItemAdapter;
import linstezh.ui.controllers.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class TrialSectionManager extends ExpSectionManager {
    private final SectionInterface trialSection;
    private boolean feedbackOutstanding = true;

    public TrialSectionManager(SectionInterface trialSection, ExperimentManager manager, RootController rootController, Stage primaryStage){
        super(trialSection, manager, rootController, primaryStage);
        this.trialSection = trialSection;
    }

    public void loadNextScene() {
        if(nextItem < items.size()) {
            loadNextItem();
        }else if(super.recallOutstanding){
            loadRecallScreen();
        }else if(feedbackOutstanding){
            System.out.println("entered feedback stage");

            List<ParticipantEvalResponse> evalResponses = manager.getEvalResponsesOfSection(trialSection);
            List<ParticipantMemResponse> memResponses = manager.getMemResponsesOfSection(trialSection);
            int evalScore = CsvAggregatedRowGenerator.calculateEvaluationsScore(evalResponses);
            int maxEvalScore = trialSection.maxEvalScore();
            int memScore = CsvAggregatedRowGenerator.calculateMemoryScore(memResponses);
            int maxMemScore = trialSection.maxMemoryScore();

            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/linstezh/ui/screens/ExperimentFeedbackScreen.fxml"));
                Parent content = loader.load();
                ExperimentFeedbackController controller = loader.getController();
                controller.init(evalScore, maxEvalScore, memScore, maxMemScore, this);
                rootController.setContent(content);

                feedbackOutstanding = false;
            }catch(IOException e){
                System.out.println("exception hit");
                concludeSection(); //todo: meaningful catch!
            }

        }else{
            concludeSection();
        }
    }

}
