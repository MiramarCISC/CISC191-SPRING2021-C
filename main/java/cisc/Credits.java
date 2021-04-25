package cisc;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.scene.canvas.*;
import javafx.scene.image.Image;

import javafx.geometry.Insets;

import java.awt.*;

import javafx.scene.paint.Color;


public class Credits extends Application{

    BorderPane pane_CreditsPane;
    Scene scene;

    Title t = new Title();

    public void start(Stage stage) {
        setScene(stage);

        // Create text that will be displayed in the credits
        String credits = "CREDITS\n\n" +

                         "Spring 2021, CISC 191\n" +
                         "Professor: Andrew Huang\n\n" +

                         "Group C:\n" +
                         "An Truong\n" +
                         "Andrés Cadena-Perales\n" +
                         "Kenton Reiss\n" +
                         "Mohamed Muse\n" +
                         "Terry Tran\n\n";

        Label creditsArea = new Label(credits);

        // Set the font, color and size for the text
        creditsArea.setStyle("-fx-control-inner-background:#000000; " +
                "-fx-font: bold 25pt Consolas; " +
                "-fx-highlight-fill: #ffffff; " +
                "-fx-highlight-text-fill: #000000; " +
                "-fx-text-fill: #ffffff; ");

        // Create a button to go back to the title screen
        Button titleButton = new Button("Back");
        titleButton.setPrefSize(80,20);
        titleButton.setOnAction( e -> t.start(stage));

        // Place everything in a GridPane
        GridPane grid = new GridPane();
        grid.add(creditsArea, 0, 0);
        grid.add(titleButton, 0, 1);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);

        // Place the GridPane in a BorderPane
        // Might be possible to remove the BorderPane entirely
        pane_CreditsPane.setCenter(grid);

    } // End of Start()

    public void setScene (Stage stage) {

        pane_CreditsPane = new BorderPane();

        // Set scene style
        pane_CreditsPane.setStyle("-fx-background-color: #000000");

        scene = new Scene(pane_CreditsPane, stage.getWidth() - 16, stage.getHeight() - 38);

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
