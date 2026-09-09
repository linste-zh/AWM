package linstezh.executionManagers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import linstezh.logic.Item.ExperimentItem;
import linstezh.logic.Item.ItemInterface;
import linstezh.logic.Item.ItemTypes;
import linstezh.logic.Section.SectionInterface;
import linstezh.ui.adapters.ExpItemAdapter;
import linstezh.ui.adapters.ImageDistractorItemAdapter;
import linstezh.ui.adapters.TextDistractorItemAdapter;
import linstezh.ui.controllers.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class TrialSectionManager extends ExpSectionManager {

    public TrialSectionManager(SectionInterface experimentSection, ExperimentManager manager, RootController rootController, Stage primaryStage){
        super(experimentSection, manager, rootController, primaryStage);
    }

}
