package cisc;

import c.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

import javafx.scene.canvas.*;
import javafx.scene.image.Image;

import javafx.geometry.Insets;
import javafx.util.Duration;

import static java.lang.Math.random;

public class Battle extends Application {

    BorderPane pane_MainPane;
    Scene scene;
    GridPane bottom_statusBarGridPane = new GridPane();
    StackPane bottom_root = new StackPane(bottom_statusBarGridPane);


    StackPane middle_root = new StackPane();
    GridPane center_EnemyGridPane = new GridPane();
    StackPane center_root = new StackPane(center_EnemyGridPane, middle_root);

    // Sprite width and height
    private static final int SPRITE_W = 79;
    private static final int SPRITE_H = 123;

    // Status Bar width and height
    private static final int SB_W = 300; // 300 looks good // 210 is a good small size
    private static final int SB_H = 200;

    // For textArea
    private static final int HEIGHT = 200;
    private TextArea logTextArea;
    private VBox menu;
    VBox log;
    StackPane top_root = new StackPane();

    // From Top GUI removed to make the canvas from Center GUI functional
    Label commandDescriptionLabel;
    StackPane root;
    GridPane buttonsGridPane;
    Button logButton;
    Button battleMenuButton;

    // For testing
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        setScene(stage);

        // Create all PartyMember in battle_GUI()
        // And also set them with the appropriate GUI
        battle_GUI();

        StackPane stack = new StackPane();

        stack.getChildren().addAll( colorfulCircles(), bottom_root, top_root, center_root);

        pane_MainPane.setBottom(bottom_root);
        pane_MainPane.setTop(top_root);
        //pane_MainPane.setCenter(middle_root);
        pane_MainPane.setCenter(center_root);

        BorderPane.setMargin(bottom_root, new Insets(30));
        BorderPane.setMargin(top_root, new Insets(30));

        // Align the GridPane
        bottom_statusBarGridPane.setAlignment(Pos.BOTTOM_CENTER);
        bottom_statusBarGridPane.setHgap(10);

        center_EnemyGridPane.setAlignment(Pos.CENTER);
        center_EnemyGridPane.setHgap(40);

        middle_root.setAlignment(Pos.TOP_CENTER);

    } // End of Start()

    // Set the scene and background
    public void setScene (Stage stage) {

        pane_MainPane = new BorderPane();

        // Set scene style
        pane_MainPane.setStyle("-fx-background-color: #444444");

        scene = new Scene(pane_MainPane, stage.getWidth() - 16, stage.getHeight() - 38);

        // Set the size for the scene
        stage.setMinWidth(940);
        stage.setMinHeight(733);

        // Set the scene that will be displayed
        stage.setScene(scene);

        // Set the stages title
        stage.setTitle("RPG");

        stage.show();

    } // End of setScene()

    // Non colorful colorful circles
    public Rectangle colorfulCircles() {
        /*
         * Taken from:
         * https://docs.oracle.com/javafx/2/get_started/animation.htm#BABDAFAF
         */

        // Add Graphics
        Group shapes = new Group();

        // Create circles
        for (int i = 0; i < 30; i++) {
            Circle circle = new Circle(150, Color.web("white", 0.05));

            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.web("white", 0.16));
            circle.setStrokeWidth(4);

            shapes.getChildren().addAll(circle);
        }

        // Add a colorful rectangle
        Rectangle colors = new Rectangle(scene.getWidth(), scene.getHeight(),
                new LinearGradient(0f, 1f, 1f, 0f,
                        true, CycleMethod.NO_CYCLE, new Stop[]{
                        new Stop(0   , Color.web("#f8bd55")),
                        new Stop(0.14, Color.web("#c0fe56")),
                        new Stop(0.28, Color.web("#5dfbc1")),
                        new Stop(0.43, Color.web("#64c2f8")),
                        new Stop(0.57, Color.web("#be4af7")),
                        new Stop(0.71, Color.web("#ed5fc2")),
                        new Stop(0.85, Color.web("#ef504c")),
                        new Stop(1   , Color.web("#f2660f")),}));
        colors.widthProperty().bind(scene.widthProperty());
        colors.heightProperty().bind(scene.heightProperty());

        // Apply a Blend Mode
        Group blendModeGroup =
                new Group(
                        new Group(
                                new Rectangle(
                                        scene.getWidth() * 3, scene.getHeight() * 3, Color.BLACK),
                                shapes),
                        colors);
        colors.setBlendMode(BlendMode.OVERLAY);
        pane_MainPane.getChildren().add(blendModeGroup);

        // Add Visual Effect
        shapes.setEffect(new BoxBlur(10, 10, 3));

        // Add Animation
        Timeline timeline = new Timeline();
        for (Node circle: shapes.getChildren()) {
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, // Set start position at 0
                            new KeyValue(circle.translateXProperty(), random() * 2000), //800
                            new KeyValue(circle.translateYProperty(), random() * 1200)  //600
                    ),
                    new KeyFrame(new Duration(80000), // Set end position at 40s
                            new KeyValue(circle.translateXProperty(), random() * 2000),
                            new KeyValue(circle.translateYProperty(), random() * 1200)
                    )
            );
        }
        // Play 40s of animation
        //pane_MainPane.setCenter(colors);
        timeline.play();

        return colors;
    }

    public void battle_GUI() {

        // Top GUI
        battle_TopGUI();

        // Center GUI
        RegMon aa = new RegMon();
        aa.setTypeName("Mon 1");

        RegMon bb = new RegMon();
        bb.setTypeName("Mon 2");

        RegMon cc = new RegMon();
        cc.setTypeName("Mon 3");

        RegMon dd = new RegMon();
        dd.setTypeName("Mon 4");

        battle_CenterGUI(0, 0, aa);
        battle_CenterGUI(1, 1, bb);
        battle_CenterGUI(2, 2, cc);
        battle_CenterGUI(3, 3, dd);

        // Bottom GUI
        PartyMember aaa = new PartyMember();
        aaa.setName("aaaa");
        aaa.setMaxHP(50);
        aaa.setCurrHP(20);
        aaa.setAtkStat(10);
        aaa.setDefStat(10);

        PartyMember bbb = new PartyMember();
        bbb.setName("bbbb");
        bbb.setMaxHP(50);
        bbb.setCurrHP(20);
        bbb.setAtkStat(10);
        bbb.setDefStat(10);

        PartyMember ccc = new PartyMember();
        ccc.setName("cccc");
        ccc.setMaxHP(50);
        ccc.setCurrHP(20);
        ccc.setAtkStat(10);
        ccc.setDefStat(10);

        PartyMember ddd = new PartyMember();
        ddd.setName("dddd");
        ddd.setMaxHP(50);
        ddd.setCurrHP(20);
        ddd.setAtkStat(10);
        ddd.setDefStat(10);

        battle_BottomGUI(0, aaa);
        battle_BottomGUI(1, bbb);
        battle_BottomGUI(2, ccc);
        battle_BottomGUI(3, ddd);
    }

    // charSpriteID 0 - 13
    public void battle_CenterGUI(int charPosition, int charSpriteID, RegMon enemyMon) {

        // Create a canvas to insert player sprite
        Canvas canvas = new Canvas(SPRITE_W,SPRITE_H);
        Image sprite = new Image("cards.png");
        GraphicsContext g = canvas.getGraphicsContext2D();

        // Determine the sprite to use from the sprite sheet
        g.drawImage(sprite, (79 * charSpriteID), 123, 79, 123,
                                          0, 0, 79, 123);

        Label nameLabel = new Label("");
        nameLabel.setStyle(
                "-fx-control-inner-background:#000000; " +
                        "-fx-font: 15pt Consolas; " +
                        "-fx-highlight-fill: #ffffff; " +
                        "-fx-highlight-text-fill: #000000; " +
                        "-fx-text-fill: #ffffff; "
        );

        /*
        Button enemySelectionButton = new Button(" Name Button ");
        enemySelectionButton.setPrefWidth(SPRITE_W);
        enemySelectionButton.setPrefHeight(SPRITE_H + 20);
        */

        // Display the monsters name when mouse hovers over the sprite
        canvas.setOnMouseEntered( e -> nameLabel.setText(enemyMon.getTypeName()));
        canvas.setOnMouseExited( e -> nameLabel.setText(""));

        canvas.setOnMouseClicked( e -> {
            logTextArea.appendText("\n Attacked " + enemyMon.getTypeName());
            center_EnemyGridPane.toBack();
            commandDescriptionLabel.setText("");
            root.getChildren().remove(commandDescriptionLabel);

            root.getChildren().remove(buttonsGridPane); //.remove(menu);
            root.getChildren().add(log);

            // Set the buttons that should appear
            middle_root.getChildren().removeAll(logButton);
            middle_root.getChildren().add(battleMenuButton);


        });


        // Place everything in a VBox
        VBox enemySpriteAndNameVBox = new VBox(nameLabel, canvas);

        // Place the VBox in a GridPane
        center_EnemyGridPane.add(enemySpriteAndNameVBox, charPosition, 0);

        //center_EnemyGridPane.add(enemySelectionButton,   charPosition, 0);

    }

    public void battle_TopGUI() {

        // Text area
        logTextArea = new TextArea("LOG");
        logTextArea.setEditable(false);
        logTextArea.setWrapText(true);
        //logTextArea.setDisable(true);
        logTextArea.setPrefHeight(HEIGHT - 10);

        // Set logTextArea's style
        logTextArea.setStyle(
                "-fx-control-inner-background:#000000; " +
                        "-fx-font: 15pt Consolas; " +
                        "-fx-highlight-fill: #ffffff; " +
                        "-fx-highlight-text-fill: #000000; " +
                        "-fx-text-fill: #ffffff; "
        );

        root = new StackPane();

        // Areas color, size and border
        StackPane hPane = new StackPane();
        hPane.setPrefHeight(HEIGHT);
        hPane.setStyle("-fx-background-color:rgb(0,0,0)");

        log = new VBox(logTextArea);
        log.setStyle("-fx-border-width:2px;" +
                "-fx-border-color:#ffffff;");
        StackPane.setMargin(log, new Insets(3));

        // Add hPane and log to the StackPane
        root.getChildren().addAll(hPane, log);


        ////////////////////////////////////////////////////////////////////////////////////////////

        // Create label that will tell you what to do when you select a command
        commandDescriptionLabel = new Label("");
        commandDescriptionLabel.setStyle(
                "-fx-control-inner-background:#000000; " +
                        "-fx-font: 15pt Consolas; " +
                        "-fx-highlight-fill: #ffffff; " +
                        "-fx-highlight-text-fill: #000000; " +
                        "-fx-text-fill: #ffffff; "
        );

        // Create text label that will describe what the menu option does
        Label menuDescriptionLabel = new Label("");
        menuDescriptionLabel.setStyle(
                        "-fx-control-inner-background:#000000; " +
                        "-fx-font: 15pt Consolas; " +
                        "-fx-highlight-fill: #ffffff; " +
                        "-fx-highlight-text-fill: #000000; " +
                        "-fx-text-fill: #ffffff; "
        );

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
        //       These would all extends the abstract MenuButtons class

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
        ListView<String> itemListView = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList(
                "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7",
                "Item 8", "Item 9", "Item 10", "Item 11", "Item 12", "tem 13", "Item 14");

        itemListView.setItems(items);
        //StackPane.setMargin(itemListView, new Insets(3));

        // Set itemListView's max size so that it doesn't poke out of the stackPane
        itemListView.setMaxSize(200, 159);

        // Create a Label that will describe what each item does
        Label itemDescriptionLabel = new Label("Item Description");
        itemDescriptionLabel.setStyle(
                "-fx-control-inner-background:#000000; " +
                        "-fx-font: 15pt Consolas; " +
                        "-fx-highlight-fill: #ffffff; " +
                        "-fx-highlight-text-fill: #000000; " +
                        "-fx-text-fill: #ffffff; "
        );

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
            center_EnemyGridPane.toFront();
        });

/*
        attack_Button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                menuDescriptionLabel.setText("TEXT");
            }
        });

        attack_Button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                menuDescriptionLabel.setText("");
            }
        });
*/


        // TODO: Make this less redundant --------------------------------------------------- + + +
        // OLD CODE vvv
/*
        //create standard menu buttons
        Button attack_Button = new Button("Attack");
        attack_Button.setPrefWidth(70);
        Button cast_Button = new Button("Cast");
        cast_Button.setPrefWidth(70);
        Button run_Button = new Button("Run");
        run_Button.setPrefWidth(70);
        Button item_Button = new Button("Item");
        item_Button.setPrefWidth(70);

        // format the menu button for the scene
        menu = new VBox(attack_Button, cast_Button, item_Button, run_Button);
        menu.setPadding(new Insets(10));
        menu.setSpacing(10);
        menu.setStyle("-fx-border-width:3px;" +
                      "-fx-border-color:#ffffff;");
        StackPane.setMargin(menu, new Insets(3));
*/
        /////////////////////////////////////////////////////////////////////////////////////

        logButton = new Button("Back to log");
        battleMenuButton = new Button("Change to battle menu");

        // Menu to log buttons
        battleMenuButton.setOnAction( e -> {
            root.getChildren().remove(log);
            root.getChildren().add(buttonsGridPane); //.add(menu);

            // Set the buttons that should appear
            middle_root.getChildren().removeAll(battleMenuButton);
            middle_root.getChildren().add(logButton);

            //pane_MainPane.setCenter(logButton);
        });

        logButton.setOnAction( e -> {
            root.getChildren().remove(buttonsGridPane); //.remove(menu);

            // TODO: REMOVE THIS .remove(itemTabPane);
            root.getChildren().remove(itemTabPane);

            //root.getChildren().clear();
            root.getChildren().add(log);

            // Set the buttons that should appear
            middle_root.getChildren().removeAll(logButton);
            middle_root.getChildren().add(battleMenuButton);
            //pane_MainPane.setCenter(battleMenuButton);
        });

/*
        // Menu's Attack, Spell, Run, Item button functionality
        // ATTACK --------------------------------------------------------------------------
        Button attack_1 = new Button("Attack 1");
        Button attack_2 = new Button("Attack 2");
        Button attack_3 = new Button("Attack 3");
        Button attack_4 = new Button("Attack 4");

        // back button
        Button attackReturn_Button = new Button("Back");

        // TODO: Make a function to remove menu's children so there isn't so much repeated code
        attackReturn_Button.setOnAction( e -> {
            menu.getChildren().removeAll(attack_1, attack_2, attack_3, attack_4, attackReturn_Button);
            menu.getChildren().addAll(attack_Button, cast_Button, item_Button, run_Button);
        });

        // Menu's Attack implementation
        attack_Button.setOnAction( e -> {
            menu.getChildren().removeAll(attack_Button, cast_Button, item_Button, run_Button);
            menu.getChildren().addAll(attack_1, attack_2, attack_3, attack_4, attackReturn_Button);
        });

        // SPELL --------------------------------------------------------------------------
        Button spell_1 = new Button("Spell 1");
        Button spell_2 = new Button("Spell 2");
        Button spell_3 = new Button("Spell 3");
        Button spell_4 = new Button("Spell 4");

        // back button
        Button spellReturn_Button = new Button("Back");

        // TODO: Make a function to remove menu's children so there isn't so much repeated code
        spellReturn_Button.setOnAction( e -> {
            menu.getChildren().removeAll(spell_1, spell_2, spell_3, spell_4, spellReturn_Button);
            menu.getChildren().addAll(attack_Button, cast_Button, item_Button, run_Button);
        });

        // Menu's Spell implementation
        cast_Button.setOnAction( e -> {
            menu.getChildren().removeAll(attack_Button, cast_Button, item_Button, run_Button);
            menu.getChildren().addAll(spell_1, spell_2, spell_3, spell_4, spellReturn_Button);
        });

        // ITEM --------------------------------------------------------------------------
        Button itemReturn_Button = new Button("Back");
        itemReturn_Button.setOnAction( e -> {
            menu.getChildren().removeAll(itemReturn_Button);
            menu.getChildren().addAll(attack_Button, cast_Button, item_Button, run_Button);
        });

        item_Button.setOnAction( e-> {
            menu.getChildren().removeAll(attack_Button, cast_Button, item_Button, run_Button);
            menu.getChildren().addAll(itemReturn_Button);
        });


 */
        // RUN ---------------------------------------------------------------------------
        run_Button.setOnAction(e -> {
            root.getChildren().remove(buttonsGridPane); // .remove(menu);
            root.getChildren().add(log);

            middle_root.getChildren().removeAll(logButton);
            middle_root.getChildren().add(battleMenuButton);

            //pane_MainPane.setCenter(battleMenuButton);

            logTextArea.appendText("\n You ran away...");
        });


        top_root.getChildren().add(root);
        middle_root.getChildren().add(battleMenuButton);


    } // End of battle_TopGUI()

    public void battle_BottomGUI(int charPosition, PartyMember partyMember) {

        // Create a canvas to insert player sprite
        Canvas canvas = new Canvas(SPRITE_W,SPRITE_H);
        Image sprite = new Image("cards.png");
        GraphicsContext g = canvas.getGraphicsContext2D();

        // Determine the sprite to use from the sprite sheet
        g.drawImage(sprite, (79 * charPosition), 0, 79, 123,
                                          0, 0, 79, 123);

        // Create all the labels that will be displayed with the status bar
        Label nameLabel = new Label("Name: " + partyMember.getName());
        Label hpLabel = new Label("HP: " + partyMember.getCurrHP() +
                                     " / " + partyMember.getMaxHP());

        // TODO: Add more labels
        //       EXP label, Job label, MP label, etc.
        // Set style for all the labels
        nameLabel.setStyle("-fx-control-inner-background:#000000; " +
                "-fx-font: bold 15pt Consolas; " +
                "-fx-highlight-fill: #ffffff; " +
                "-fx-highlight-text-fill: #000000; " +
                "-fx-text-fill: #ffffff; ");

        hpLabel.setStyle("-fx-control-inner-background:#000000; " +
                "-fx-font: bold 15pt Consolas; " +
                "-fx-highlight-fill: #ffffff; " +
                "-fx-highlight-text-fill: #000000; " +
                "-fx-text-fill: #ffffff; ");

        // Place all the labels in a VBox
        VBox labels = new VBox( nameLabel, hpLabel );

        // Create BorderPane where everything will be placed
        BorderPane statusBar = new BorderPane();

        // Place everything in the statusBar
        statusBar.setCenter(canvas);
        statusBar.setRight(labels);
        statusBar.setStyle("-fx-background-color:rgb(0,0,0);" +
                           "-fx-border-width:2px;" +
                           "-fx-border-color:#ffffff;");

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

        // Place stackPane in bottom_statusBarGridPane
        bottom_statusBarGridPane.add(stackPane, charPosition, 0);

    }

} // End of class Battle