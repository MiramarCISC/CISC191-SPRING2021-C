package cisc;

import c.Bow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.scene.canvas.*;
import javafx.scene.image.Image;

import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

    //TODO: I keep copying everything here into every class
    //      so it may be possible to make an abstract class
    //      that already contains this.

public class Main extends Application{

    BorderPane pane_MainPane = new BorderPane();
    Scene scene = new Scene(pane_MainPane, 1500, 850); // 940, 733

    Title t = new Title();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        setScene(stage);
        t.start(stage);
    }

    public void setScene (Stage stage) {
        // Set the size for the scene
        stage.setMinWidth(940);
        stage.setMinHeight(850);

        // Set the scene that will be displayed
        stage.setScene(scene);

        // Set the stages title
        stage.setTitle("RPG");

        // Set scene style
        pane_MainPane.setStyle("-fx-background-color: #444444");

        stage.show();

    } // End of setScene()

}
