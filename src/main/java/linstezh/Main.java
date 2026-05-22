package linstezh;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import linstezh.visualisation.ExperimentItemScreen;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(new ExperimentItemScreen().createContent(), 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}