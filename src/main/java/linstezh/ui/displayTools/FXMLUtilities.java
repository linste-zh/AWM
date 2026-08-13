package linstezh.ui.displayTools;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class FXMLUtilities {
    public static void fillAllParents(Parent parent){
        for (Node node : parent.getChildrenUnmodifiable()) {
            if ("fillParent".equals(node.getUserData())) {
                fillParent(node);
            }
            if (node instanceof Parent) {
                fillAllParents((Parent) node);
            }
        }
    }

    public static void fillParent(Node node){
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
    }
}
