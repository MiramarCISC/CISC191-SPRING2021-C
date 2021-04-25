package cisc;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.application.Platform;

import javafx.scene.canvas.*;
import javafx.scene.image.Image;

import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.awt.*;

import javafx.scene.paint.Color;

public class Title extends Application {

    BorderPane pane_TitlePane;
    Scene scene;

    public void start(Stage stage) {
        setScene(stage);

        // Create Label that says "RPG"
        Label titleLabel = new Label("RPG");
        titleLabel.setStyle("-fx-control-inner-background:#000000; " +
                "-fx-font: bold 35pt Consolas; " +
                "-fx-highlight-fill: #ffffff; " +
                "-fx-highlight-text-fill: #000000; " +
                "-fx-text-fill: #ffffff; ");

        // Create buttons for the scene
        Button startButton = new Button("START");
        Button creditsButton = new Button("CREDITS");
        Button quitButton = new Button("QUIT");

        // Resize the buttons
        startButton.setPrefSize(80, 20);
        creditsButton.setPrefSize(80,20);
        quitButton.setPrefSize(80, 20);

        // Place Label and Buttons on a grid
        GridPane grid  = new GridPane();
        grid.add(titleLabel   , 0, 0);
        grid.add(startButton  , 0, 1);
        grid.add(creditsButton, 0, 2);
        grid.add(quitButton   , 0, 3);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);

        // Place the grid on a BorderPane
        pane_TitlePane.setCenter(grid);

        // Create and instance of the Battle class and the Credits class
        Battle b = new Battle();
        Credits c = new Credits();

        // Give the buttons functionality
        startButton.setOnAction( e -> b.start(stage));
        creditsButton.setOnAction( e -> c.start(stage));
        quitButton.setOnAction( e -> Platform.exit());

    } //End of start()


    // TODO: Move setScene to abstract class

    public void setScene (Stage stage) {

        pane_TitlePane = new BorderPane();

        // Set scene style
        pane_TitlePane.setStyle("-fx-background-color: #000000");

        scene = new Scene(pane_TitlePane, stage.getWidth() - 16, stage.getHeight() - 38);

        // Set the size for the scene
        stage.setMinWidth(940);
        stage.setMinHeight(733);

        // Set the scene that will be displayed
        stage.setScene(scene);

        // Set the stages title
        stage.setTitle("RPG");


        stage.show();

    } // End of setScene()

}
