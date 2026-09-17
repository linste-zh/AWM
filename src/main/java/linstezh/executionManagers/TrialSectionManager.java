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
    private boolean feedbackOutstanding = true;

    public TrialSectionManager(SectionInterface trialSection, ExperimentManager manager, RootController rootController, Stage primaryStage){
        super(trialSection, manager, rootController, primaryStage);
    }

    public void loadNextScene() {
        resetTimer();

        if(nextItem < items.size()) {
            loadNextItem();
        }else if(super.recallOutstanding){
            loadRecallScreen();
        }else if(feedbackOutstanding){
            List<ParticipantEvalResponse> evalResponses = manager.getEvalResponsesOfSection(section);
            List<ParticipantMemResponse> memResponses = manager.getMemResponsesOfSection(section);
            int evalScore = CsvAggregatedRowGenerator.calculateEvaluationsScore(evalResponses);
            int maxEvalScore = section.maxEvalScore();
            int memScore = CsvAggregatedRowGenerator.calculateMemoryScore(memResponses);
            int maxMemScore = section.maxMemoryScore();

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
