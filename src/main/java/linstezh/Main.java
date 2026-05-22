package linstezh;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import linstezh.visualisation.ExpItemAdapter;
import linstezh.visualisation.ExperimentItemScreen;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ExpItemAdapter exampleItem = new ExpItemAdapter("Test text", false);
        Region newItem = new ExperimentItemScreen(exampleItem).createContent();

        Scene scene = new Scene(newItem, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}