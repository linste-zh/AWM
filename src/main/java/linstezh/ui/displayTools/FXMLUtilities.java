package linstezh.ui.displayTools;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

public class FXMLUtilities {
    public static void applyProperties(Node node){
        Object h = node.getProperties().get("heightPercent");
        Object mh = node.getProperties().get("maxHeightPercent");
        Object w = node.getProperties().get("widthPercent");
        Object fp = node.getProperties().get("fillParent");

        if (h != null && node.getParent() != null) {
            setHeightPerc(node, h.toString());
        }

        if (mh != null && node.getParent() != null) {
            setMaxHeightPerc(node, mh.toString());
        }

        if (w != null && node.getParent() != null) {
            setWidthPerc(node, w.toString());
        }

        if (fp != null) {
            fillParent(node, fp.toString());
        }

        if (node instanceof Parent) {
            Parent parent = (Parent) node;
            for (Node child :  parent.getChildrenUnmodifiable()) {
                applyProperties(child);
            }
        }
    }

    public static void fillParent(Node node, String value){
        boolean fill = Boolean.parseBoolean(value);

        if(fill){
            AnchorPane.setTopAnchor(node, 0.0);
            AnchorPane.setBottomAnchor(node, 0.0);
            AnchorPane.setLeftAnchor(node, 0.0);
            AnchorPane.setRightAnchor(node, 0.0);
        }
    }

    public static void setHeightPerc(Node node, String value){
        try{
            double height = Double.parseDouble(value);

            Region reg = (Region) node;
            Region parent = (Region) node.getParent();

            reg.prefHeightProperty().bind(parent.heightProperty().multiply(height));
        } catch (Error e) {
            e.printStackTrace();
        }
    }

    public static void setMaxHeightPerc(Node node, String value){
        try{
            double height = Double.parseDouble(value);

            Region reg = (Region) node;
            Region parent = (Region) node.getParent();

            reg.maxHeightProperty().bind(parent.heightProperty().multiply(height));
        } catch (Error e) {
            e.printStackTrace();
        }
    }

    public static void setWidthPerc(Node node, String value){
        try{
            double width = Double.parseDouble(value);

            Region reg = (Region) node;
            Region parent = (Region) node.getParent();

            reg.prefWidthProperty().bind(parent.widthProperty().multiply(width));
        } catch (Error e) {
            e.printStackTrace();
        }
    }

}
