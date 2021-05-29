package edu.sdccd.cisc191.c.GUI;


// Class imports due to being in a GUI Package
import edu.sdccd.cisc191.c.*;


// Javafx imports
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application{

    // MAIN ********************************
    public static void main(String[] args) {
        launch(args);
    }
    // *************************************

    // Window width and height
    private final double window_W = 1500;
    private final double window_H = 850;

    // Scenes ///////////////////////
    //public Scene scene; // 940, 733
    // Title scene
    private final BorderPane pane_Title = new BorderPane();
    private final Scene scene_Title = new Scene(pane_Title, window_W, window_H);
    // Credits scene
    private final BorderPane pane_Credits = new BorderPane();
    private final Scene scene_Credits = new Scene(pane_Credits, window_W, window_H);
    // Party Creator scene
    private final BorderPane pane_PartyCreator = new BorderPane();
    private final Scene scene_PartyCreator = new Scene(pane_PartyCreator, window_W, window_H);
    // Battle scene
    private final BorderPane pane_Battle = new BorderPane();
    private final Scene scene_Battle = new Scene(pane_Battle, window_W, window_H);
    /////////////////////////////////

    // BackGround colors
    private final String black    = "-fx-background-color: #000000";
    private final String darkGrey = "-fx-background-color: #444444";
    private final String darkRed  = "-fx-background-color: #150000";

    // Text Styles
    private final String textStyle = "-fx-control-inner-background:#000000; " +
                       "-fx-font: bold 35pt Consolas; " +
                       "-fx-highlight-fill: #ffffff; " +
                       "-fx-highlight-text-fill: #000000; " +
                       "-fx-text-fill: #ffffff; ";

    private final String textLogStyle = "-fx-control-inner-background:#000000; " +
                       "-fx-font: bold 15pt Consolas; " +
                       "-fx-highlight-fill: #ffffff; " +
                       "-fx-highlight-text-fill: #000000; " +
                       "-fx-text-fill: #ffffff; ";

    private final String buttonStyleWhite = "-fx-font: bold 15pt Consolas; " +
                       "-fx-text-fill: #ffffff; ";

    private final String buttonStyleBlack = "-fx-font: bold 15pt Consolas; " +
                       "-fx-text-fill: #000000; ";

    GridPane bottomGrid = new GridPane();
    GridPane centerGrid = new GridPane();
    BorderPane topPane = new BorderPane();

    // Used to iterate through things
    int i;

    // Sprite width and height
    final int SPRITE_W = 79;
    final int SPRITE_H = 123;

    boolean disableCanvasMon = true;

    // For textArea
    private static final int HEIGHT = 200;
    private TextArea logTextArea;
    VBox log;
    StackPane top_root = new StackPane();

    // From gui_BattleTop removed to make the canvas from gui_BattleTopCenter functional
    Label commandDescriptionLabel;
    StackPane root;
    GridPane buttonsGridPane;
    Button logButton;
    Button battleMenuButton;

    // Enemy Party // // // // // //
    private ArrayList<Enemy> partyMonsters = new ArrayList<Enemy>();
    PartyDisplay partyMonsterDisplay = new PartyDisplay();

    // Player Party // // // // // //
    private ArrayList<PartyMember> partyMembers = new ArrayList<PartyMember>();
    PartyDisplay partyDisplay = new PartyDisplay();
    // Player Inventory
    Inventory inventory = new Inventory();

    // Start ***********************************************************************************************************
    @Override
    public void start(Stage stage) {
        System.out.println(" - IN START - ");
        // Set title screen on stage
        setScene(stage, scene_Title, pane_Title,"Title Screen", darkGrey);
        gui_titleScene(stage);

    }

    public void setScene (Stage stage, Scene scene, BorderPane pane, String stageTitle, String colorBG) {

        // Set scene style
        pane.setStyle(colorBG);

        // Set the size for the scene
        stage.setMinWidth(window_W);
        stage.setMinHeight(window_H);

        // Set the scene that will be displayed
        stage.setScene(scene);

        // Set the stages title
        stage.setTitle(stageTitle);

        stage.show();

    } // End of setScene()

    public void gui_titleScene(Stage stage) {
        System.out.println(" - IN TITLE SCENE - ");
        // Create Label that says "RPG"
        Label titleLabel = new Label("RPG");
        titleLabel.setStyle(textStyle);

        // Create buttons for the scene
        Button startButton = new Button("START");
        Button creditsButton = new Button("CREDITS");
        Button quitButton = new Button("QUIT");

        // Give the buttons functionality
        startButton.setOnAction( e -> {
            setScene(stage, scene_PartyCreator, pane_PartyCreator,"Party Creator Screen", darkGrey);
            gui_partyCreator(stage);
        });

        creditsButton.setOnAction( e -> {
            setScene(stage, scene_Credits, pane_Credits,"Credits Screen", darkGrey);
            gui_creditsScene(stage);
        });

        quitButton.setOnAction( e -> Platform.exit());

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
        pane_Title.setCenter(grid);
    }

    public void gui_creditsScene(Stage stage) {
        System.out.println(" - IN CREDITS SCENE - ");
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

        // Give the button functionality
        titleButton.setOnAction( e -> {
            setScene(stage, scene_Title, pane_Title,"Title Screen", darkGrey);
            gui_titleScene(stage);
        });

        // Place everything in a GridPane
        GridPane grid = new GridPane();
        grid.add(creditsArea, 0, 0);
        grid.add(titleButton, 0, 1);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);

        // Place the GridPane in a BorderPane
        pane_Credits.setCenter(grid);
    }

    public void gui_partyCreator(Stage stage) {
        System.out.println(" - IN PARTY CREATOR - ");
        GridPane grid = new GridPane();
        BorderPane pane_CharCreatorPane = new BorderPane();
        AtomicInteger id = new AtomicInteger(20);

        grid.setAlignment(Pos.CENTER);
        bottomGrid.setPadding(new Insets(30));

        pane_PartyCreator.setCenter(grid);
        pane_PartyCreator.setBottom(bottomGrid);

        Label text = new Label("Name: "    );
        Label job  = new Label("Select Job");
        Label title = new Label("Create Party Members");
        TextField nameInput = new TextField();
        RadioButton jobMage   = new RadioButton("Mage"  );
        RadioButton jobRanged = new RadioButton("Ranged");
        RadioButton jobKnight = new RadioButton("Knight");

        // Set text style
        text.setStyle(buttonStyleWhite);
        job.setStyle(buttonStyleWhite);
        jobMage.setStyle(buttonStyleWhite);
        jobRanged.setStyle(buttonStyleWhite);
        jobKnight.setStyle(buttonStyleWhite);
        title.setStyle(buttonStyleWhite);

        ToggleGroup jobGroup  = new ToggleGroup();
        jobMage.  setToggleGroup(jobGroup);
        jobRanged.setToggleGroup(jobGroup);
        jobKnight.setToggleGroup(jobGroup);

        Sprite sprite = new Sprite();
        VBox radioButtons = new VBox();
        radioButtons.setSpacing(5);
        radioButtons.setAlignment(Pos.TOP_LEFT);
        radioButtons.setPadding(new Insets(15));
        radioButtons.getChildren().addAll(job,
                jobMage,
                jobRanged,
                jobKnight);

        HBox nameLabel = new HBox();
        nameLabel.setAlignment(Pos.TOP_LEFT);
        nameLabel.getChildren().addAll(text, nameInput);
        HBox.setHgrow(nameInput, Priority.ALWAYS);
        nameInput.setMaxWidth(Double.POSITIVE_INFINITY);

        VBox titleVBox = new VBox();
        titleVBox.getChildren().addAll(title, nameLabel);
        titleVBox.setAlignment(Pos.CENTER);
        titleVBox.setSpacing(10);

        pane_CharCreatorPane.setCenter(sprite.getSprite(id.get()));
        pane_CharCreatorPane.setTop(titleVBox);
        pane_CharCreatorPane.setRight(radioButtons);

        pane_CharCreatorPane.setPrefSize(250,250);

        grid.setPadding(new Insets(15,15,15,15));
        grid.setVgap(5);
        grid.setHgap(5);

        grid.add(pane_CharCreatorPane, 0, 0);

        jobMage.setOnAction( e -> {
            id.set(4);
            pane_CharCreatorPane.setCenter(sprite.getSprite(id.get()));
        });

        jobKnight.setOnAction( e -> {
            id.set(0);
            pane_CharCreatorPane.setCenter(sprite.getSprite(id.get()));
        });

        jobRanged.setOnAction( e -> {
            id.set(2);
            pane_CharCreatorPane.setCenter(sprite.getSprite(id.get()));
        });

        Button confirm = new Button("Add To Party");
        confirm.setStyle(buttonStyleBlack);

        Button startGame = new Button("Start!");
        startGame.setPrefWidth(100);
        startGame.setDisable(true);
        startGame.setStyle(buttonStyleBlack);

        // Set button functionality
        startGame.setOnAction( e -> {
            setScene(stage, scene_Battle, pane_Battle,"Battle Screen", black);
            gui_BattleScene(stage);

        });

        VBox buttons = new VBox(confirm, startGame);
        buttons.setSpacing(10);
        confirm.setMaxWidth(Double.POSITIVE_INFINITY);
        VBox.setMargin(startGame, new Insets(10,10,10,75));
        pane_CharCreatorPane.setBottom(buttons);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Create Party Member
        Alert creatorError = new Alert(Alert.AlertType.INFORMATION);
        confirm.setOnAction( e -> {

            if (nameInput.getText().isBlank() && nameInput.getText().trim().isEmpty()) {
                System.out.println("Please enter a name for your Party Member.");
                creatorError.setContentText("Please enter a name for your Party Member.");
                creatorError.setHeaderText("Name is blank");
                creatorError.show();

            } else if (nameInput.getText().length() > 11) {
                System.out.println("Please use less than 11 characters for your name.");
                creatorError.setContentText("Please use less than 11 characters for the name.\n" + "");
                creatorError.setHeaderText("Characters used: " + nameInput.getText().length());
                creatorError.show();

            } else if (id.get() > 6) {
                System.out.println("Please choose a Job for your Party Member.");
                creatorError.setContentText("Please choose a Job for your Party Member.\n");
                creatorError.setHeaderText("No Job selected");
                creatorError.show();

            } else {
                // Create partyMember and set base stats
                PartyMember partyMember = new PartyMember(nameInput.getText());
                partyMember.setCurrJob(id.get());
                partyMember.setLevel(1);
                partyMember.setMaxHP(10);
                partyMember.setCurrHP(10);
                partyMember.setAtkStat(10);

                partyMembers.add(i, partyMember);
                //party.setPartyMember(partyMember);

                PartyDisplay.bottomGUI(bottomGrid, partyMember, i);
                partyDisplay.showPartyMembers(partyMembers);

                i++;

                startGame.setDisable(false);
            }
        });
    }

    public void gui_BattleScene(Stage stage) {
        System.out.println(" - IN BATTLE - ");

        // Display Log and Commands
        gui_BattleTop();
        gui_BattleLeft();

        // Display Party Members
        bottomGrid.setPadding(new Insets(30));
        pane_Battle.setBottom(bottomGrid);

        // Display Party
        for (i = 0; partyMembers.size() > i; ++i) {
            System.out.println(i);
            PartyDisplay.bottomGUI(bottomGrid, partyMembers.get(i), i);

        }
        partyDisplay.showPartyMembers(partyMembers);


        // Display Enemies
        Enemy enemy  = new Enemy("Mon 1");
        Enemy enemy2 = new Enemy("Mon 2");
        Enemy enemy3 = new Enemy("Mon 3");
        Enemy enemy4 = new Enemy("Mon 4");

        partyMonsters.add(0, enemy);
        partyMonsters.add(1, enemy2);
        partyMonsters.add(2, enemy3);
        partyMonsters.add(3, enemy4);

        gui_BattleCenter(0, 0, partyMonsters.get(0));
        gui_BattleCenter(1, 1, partyMonsters.get(1));
        gui_BattleCenter(2, 2, partyMonsters.get(2));
        gui_BattleCenter(3, 3, partyMonsters.get(3));



        //Fight fight = new Fight(partyMembers, partyMonsters);

    }

    public void gui_BattleTop() {
        pane_Battle.setTop(top_root);
        pane_Battle.setCenter(centerGrid);
        BorderPane.setMargin(top_root, new Insets(30));
        centerGrid.setAlignment(Pos.CENTER);
        centerGrid.setHgap(40);

        // Text area
        logTextArea = new TextArea("LOG");
        logTextArea.setEditable(false);
        logTextArea.setWrapText(true);
        //logTextArea.setDisable(true);
        logTextArea.setPrefHeight(HEIGHT - 10);

        // Set logTextArea's style
        logTextArea.setStyle(textLogStyle);


        root = new StackPane();

        // Areas color, size and border
        StackPane hPane = new StackPane();
        hPane.setPrefHeight(HEIGHT);
        hPane.setStyle(black);

        log = new VBox(logTextArea);
        log.setStyle("-fx-border-width:2px;" +
                "-fx-border-color:#ffffff;");
        StackPane.setMargin(log, new Insets(3));

        // Add hPane and log to the StackPane
        root.getChildren().addAll(hPane, log);


        ////////////////////////////////////////////////////////////////////////////////////////////

        // Create label that will tell you what to do when you select a command
        commandDescriptionLabel = new Label("");
        commandDescriptionLabel.setStyle(textLogStyle);

        // Create text label that will describe what the menu option does
        Label menuDescriptionLabel = new Label("");
        menuDescriptionLabel.setStyle(textLogStyle);

        // Create standard menu buttons
        Button attack_Button = new Button("Attack");
        attack_Button.setPrefWidth(200);
        attack_Button.setPrefHeight(50);
        // Set text that will appear on mouse hover
        attack_Button.setOnMouseEntered( e -> menuDescriptionLabel.setText("Attack the enemy"));
        attack_Button.setOnMouseExited(  e -> menuDescriptionLabel.setText(""));

        Button cast_Button = new Button("Cast");
        cast_Button.setPrefWidth(200);
        cast_Button.setPrefHeight(50);
        // Set text that will appear on mouse hover
        cast_Button.setOnMouseEntered( e -> menuDescriptionLabel.setText("Cast a spell"));
        cast_Button.setOnMouseExited(  e -> menuDescriptionLabel.setText(""));

        Button run_Button = new Button("Run");
        run_Button.setPrefWidth(200);
        run_Button.setPrefHeight(50);
        // Set text that will appear on mouse hover
        run_Button.setOnMouseEntered( e -> menuDescriptionLabel.setText("Run from battle"));
        run_Button.setOnMouseExited(  e -> menuDescriptionLabel.setText(""));

        Button item_Button = new Button("Item");
        item_Button.setPrefWidth(200);
        item_Button.setPrefHeight(50);
        // Set text that will appear on mouse hover
        item_Button.setOnMouseEntered( e -> menuDescriptionLabel.setText("Use an item"));
        item_Button.setOnMouseExited(  e -> menuDescriptionLabel.setText(""));


        // Create a GridPane and place all the buttons on it
        buttonsGridPane = new GridPane();

        buttonsGridPane.add(attack_Button, 0, 0);
        buttonsGridPane.add(cast_Button  , 1, 0);
        buttonsGridPane.add(item_Button  , 0, 1);
        buttonsGridPane.add(run_Button   , 1 ,1);
        buttonsGridPane.add(menuDescriptionLabel, 0, 2);

        // Adjust the GridPane's elements Hgap, Vgap and the alignment
        buttonsGridPane.setHgap(15);
        buttonsGridPane.setVgap(15);
        buttonsGridPane.setAlignment(Pos.CENTER);

        buttonsGridPane.setStyle("-fx-border-width:3px;" +
                "-fx-border-color:#ffffff;");

        StackPane.setMargin(buttonsGridPane, new Insets(3));

        // TODO: Could make an abstract MenuButtons class so that they all contain these
        //       basic presets and still allow me to make unique classes from them;
        //       Such as the ItemButton class, AttackButton class, etc.
        //       These would all extend the abstract MenuButtons class

        // Item Button functionality //////////////////////////////////////////////////////////////  ITEM  /////////////

        // Create a TabPane and also creat 4 Tabs
        TabPane itemTabPane = new TabPane();
        Tab battleItemsTab = new Tab("Items");
        Tab healingItemsTab = new Tab("Medicine");
        Tab equipmentTab = new Tab("Equipment");
        Tab keyItemsTab = new Tab("Key Items");

        // Prevent the Tabs from being closed by the user
        battleItemsTab.setClosable(false);
        itemTabPane.setTabMinWidth(100);
        itemTabPane.setStyle("-fx-border-width:3px;" +
                             "-fx-border-color:#ffffff;");

        healingItemsTab.setClosable(false);
        equipmentTab.setClosable(false);
        keyItemsTab.setClosable(false);

        // Set the Tabs in the TabPane
        itemTabPane.getTabs().addAll(battleItemsTab, healingItemsTab, equipmentTab, keyItemsTab);
        StackPane.setMargin(itemTabPane, new Insets(3));

        item_Button.setOnAction( e -> {
            root.getChildren().remove(buttonsGridPane);
            root.getChildren().add(itemTabPane);
        });

        // TODO: Move to a new class or method ----------------------------------------------- + + +
        // Create list of items
        /*
         * Taken from:
         * https://docs.oracle.com/javafx/2/ui_controls/list-view.htm
         */



        AtkBuffItem aasa = new AtkBuffItem("Test itme", 1,1,1,1);

        ListView<String> itemListView = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList(aasa.getName());

        itemListView.setItems(items);
        //StackPane.setMargin(itemListView, new Insets(3));

        // Set itemListView's max size so that it doesn't poke out of the stackPane
        itemListView.setMaxSize(200, 159);

        // Create a Label that will describe what each item does
        Label itemDescriptionLabel = new Label("Item Description");
        itemDescriptionLabel.setStyle(textLogStyle);

        // Place everything in an HBox
        HBox itemAndItemLabelHBox = new HBox(itemListView, itemDescriptionLabel);

        // Adjust the HBox's margin so that itemDescriptionLabel is not aligned with the border
        HBox.setMargin(itemDescriptionLabel, new Insets(15));

        battleItemsTab.setContent(itemAndItemLabelHBox);

        // Attack Button functionality ////////////////////////////////////////////////////////////  ATTACK  ///////////
        attack_Button.setOnAction( e -> {
            root.getChildren().remove(buttonsGridPane);
            root.getChildren().add(commandDescriptionLabel);
            commandDescriptionLabel.setText("Click on the Monster you want to Attack");
            disableCanvasMon = false;

        });

        /////////////////////////////////////////////////////////////////////////////////////
        logButton = new Button("See Log");
        battleMenuButton = new Button("Choose a Command");
        BorderPane.setMargin(battleMenuButton, new Insets(15));
        BorderPane.setMargin(logButton, new Insets(15));
        BorderPane.setAlignment(battleMenuButton, Pos.CENTER);
        BorderPane.setAlignment(logButton, Pos.CENTER);

        // Menu to log buttons
        battleMenuButton.setOnAction( e -> {
            root.getChildren().remove(log);
            root.getChildren().add(buttonsGridPane); //.add(menu);


            // Set the buttons that should appear
            //middle_root.getChildren().removeAll(battleMenuButton);
            topPane.setBottom(logButton);

            //pane_MainPane.setCenter(logButton);
        });

        logButton.setOnAction( e -> {
            disableCanvasMon = true;
            root.getChildren().remove(buttonsGridPane); //.remove(menu);

            root.getChildren().remove(commandDescriptionLabel);
            // TODO: REMOVE THIS .remove(itemTabPane);
            root.getChildren().remove(itemTabPane);

            //root.getChildren().clear();
            root.getChildren().add(log);

            // Set the buttons that should appear
            //middle_root.getChildren().removeAll(logButton);
            topPane.setBottom(battleMenuButton);
            //pane_MainPane.setCenter(battleMenuButton);
        });

        // RUN ---------------------------------------------------------------------------
        run_Button.setOnAction(e -> {
            root.getChildren().remove(buttonsGridPane); // .remove(menu);
            root.getChildren().add(log);

            //middle_root.getChildren().removeAll(logButton);
            topPane.setBottom(battleMenuButton);

            //pane_MainPane.setCenter(battleMenuButton);

            logTextArea.appendText("\n You ran away...");
        });

        topPane.setCenter(root);
        top_root.getChildren().add(topPane);
        topPane.setBottom(battleMenuButton);
    } // End of battle_TopGUI()

    public void gui_BattleCenter(int charPosition, int charSpriteID, Enemy enemyMon) {
        Canvas canvasMon = new Canvas(SPRITE_W,SPRITE_H);
        canvasMon.toFront();

        Image sprite = new Image("cards.png");
        GraphicsContext g = canvasMon.getGraphicsContext2D();

        // Determine the sprite to use from the sprite sheet
        g.drawImage(sprite, (79 * charSpriteID), 123, 79, 123,
                0, 0, 79, 123);

        Label nameLabel = new Label("");
        nameLabel.setStyle(textLogStyle);

        // Display the monsters name when mouse hovers over the sprite
        canvasMon.setOnMouseEntered( e -> nameLabel.setText(enemyMon.getName()));
        canvasMon.setOnMouseExited( e -> nameLabel.setText(""));

        canvasMon.setOnMouseClicked( e -> {
            if (!disableCanvasMon) {
                logTextArea.appendText("\n Attacked " + enemyMon.getName());
                commandDescriptionLabel.setText("");
                root.getChildren().remove(commandDescriptionLabel);

                root.getChildren().remove(buttonsGridPane);
                root.getChildren().add(log);

                topPane.setBottom(battleMenuButton);
                disableCanvasMon = true;
            }
        });

        // Place everything in a VBox
        VBox enemySpriteAndNameVBox = new VBox(nameLabel, canvasMon);

        // Place the VBox in a GridPane
        centerGrid.add(enemySpriteAndNameVBox, charPosition, 0);

    }

    public void gui_BattleLeft() {
        Button chest = new Button("CHEST");

        pane_Battle.setLeft(chest);

        chest.setOnAction( e -> {
            inventory.obtainedBigChest();
            System.out.println();
            inventory.showInventory();
        });

    }

}