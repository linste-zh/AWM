package linstezh.executionManagers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import linstezh.logic.ActiveExperiment.ParticipantEvalResponse;
import linstezh.logic.ActiveExperiment.ParticipantMemResponse;
import linstezh.logic.Section.SectionInterface;
import linstezh.output.resultCSV.CsvAggregatedRowGenerator;
import linstezh.ui.controllers.ExperimentFeedbackController;
import linstezh.ui.controllers.RootController;

import java.io.IOException;
import java.util.List;

public class TrialSectionManager extends ExpSectionManager {
    private final SectionInterface trialSection;
    private boolean feedbackOutstanding = true;

    public TrialSectionManager(SectionInterface trialSection, ExperimentManager manager, RootController rootController, Stage primaryStage){
        super(trialSection, manager, rootController, primaryStage);
        this.trialSection = trialSection;
    }

    public void loadNextScene() {
        resetTimer();

        if(nextItem < items.size()) {
            loadNextItem();
        }else if(super.recallOutstanding){
            loadRecallScreen();
        }else if(feedbackOutstanding){
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

    public void restartSection(){
        nextItem = 0;
        recallOutstanding = true;
        feedbackOutstanding = true;
        loadNextScene();
    }

}
