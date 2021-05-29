package edu.sdccd.cisc191.c.GUI;

import edu.sdccd.cisc191.c.PartyMember;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class PartyDisplay {

    // Status Bar width and height
    private static final int SB_W = 300; // 300 looks good // 210 is a good small size
    private static final int SB_H = 200;

    public static void bottomGUI(GridPane pane, PartyMember partyMember, int charPosition) {
        String textStyle = "-fx-control-inner-background:#000000; " +
                           "-fx-font: bold 15pt Consolas; " +
                           "-fx-highlight-fill: #ffffff; " +
                           "-fx-highlight-text-fill: #000000; " +
                           "-fx-text-fill: #ffffff; ";

        String textSubStyle = "-fx-control-inner-background:#000000; " +
                             "-fx-font: bold 15pt Consolas; " +
                             "-fx-highlight-fill: #ffffff; " +
                             "-fx-highlight-text-fill: #000000; " +
                             "-fx-text-fill: #606060; ";

        Sprite sprite = new Sprite();

        // Create all the labels that will be displayed with the status bar
        Label nameLabel  = new Label( partyMember.getName());
        Label levelLabel = new Label("Level: " + partyMember.getLevel());
        Label hpLabel    = new Label("HP: "    + partyMember.getCurrHP() +
                " / "     + partyMember.getMaxHP());

        // TODO: Add more labels
        //       EXP label, Job label, MP label, etc.
        // Set style for all the labels
        nameLabel.setStyle(textStyle);
        hpLabel.setStyle(textStyle);
        levelLabel.setStyle(textSubStyle);

        // Place all the labels in a VBox
        VBox labels = new VBox( nameLabel, hpLabel , levelLabel);

        // Create BorderPane where everything will be placed
        BorderPane statusBar = new BorderPane();

        // Place everything in the statusBar
        statusBar.setCenter(sprite.getSprite(partyMember.getCurrJob()));
        statusBar.setRight(labels);
        statusBar.setStyle(
                "-fx-background-color:rgb(0,0,0);" +
                "-fx-border-width:2px;" +
                "-fx-border-color:#ffffff;"
        );

        // Align the labels and set them so that there is some space between them and the margin
        labels.setAlignment(Pos.TOP_LEFT);
        labels.setPadding(new Insets(10));

        // Create Rectangle that will determine statusBar size
        Rectangle r = new Rectangle();
        r.setWidth(SB_W);
        r.setHeight(SB_H);

        // Stack Rectangle and statusBar
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(r, statusBar);

        //Space each Party Member's UI from each other
        pane.setHgap(10);

        // Align the GUI to the bottom center of the screen
        pane.setAlignment(Pos.BOTTOM_CENTER);

        // Place stackPane in GridPane
        pane.add(stackPane, charPosition, 0);

    }

    public void showPartyMembers(ArrayList<PartyMember> members) {
        System.out.println("**Current Party:  ***************");
        int i = 0;
        for(PartyMember member: members) {
            System.out.println("////////////////////////////// #" + ++i);
            System.out.println("Name : " + member.getName()    + "\n" +
                    "Level: " + member.getLevel()   + "\n" +
                    "JobID: " + member.getCurrJob() + "\n" +
                    " - - - - - - - - - - - - - - - - ");
            System.out.printf( "Atk: %-7d", member.getAtkStat());
            System.out.printf( "Def: %-7d", member.getDefStat());
            System.out.printf( "Spd: %-7d", member.getSpdStat());
            System.out.println();
            System.out.printf( "HP : %-7d", member.getMaxHP()  );
            System.out.printf( "Exp: %-7d", member.getExp()    );
            System.out.println();
        }
        System.out.println("*********************************");
    }

}
