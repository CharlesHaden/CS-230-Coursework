import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.text.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

/**
 *
 *
 * @author Nim Man
 * @author HyderAlhashimi
 * @author Laurence Burns-Mill
 * @author Matthew Clarke
 */

public class Main extends Application {
    // Constants for the main window
    private static final int MAIN_WINDOW_WIDTH = 700;
    private static final int MAIN_WINDOW_HEIGHT = 600;
    private static final String WINDOW_TITLE = "Fast and Curious!";
    Stage window;
    Scene mainMenu;
    Scene pauseMenu;
    Scene setup;
    Scene createProfile;
    Scene deleteProfile;
    Scene leaderboardScreen;
    Scene inGameScreen;
    Scene loadGameScreen;


    @Override
    public void start(Stage window) throws Exception {

        //MAIN MENU
        Pane root = new Pane();
        mainMenu = new Scene(root, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
        Text title = new Text(160, 100, WINDOW_TITLE);
        title.setFont(new Font(50));
        root.getChildren().add(title);

        //CREATE BUTTONS
        Button playButton = new Button("Play");
        Button loadButton = new Button("Load Game");
        Button newProf = new Button("New Profile");
        Button delProfButton = new Button("Delete Profile");
        Button leaderboardButton = new Button("Leaderboard");
        Button exitButton = new Button("Exit");


        playButton.setLayoutX(300);
        playButton.setLayoutY(240);
        playButton.setPrefSize(100,40);
        root.getChildren().add(playButton);

        loadButton.setLayoutX(300);
        loadButton.setLayoutY(290);
        loadButton.setPrefSize(100,20);
        root.getChildren().add(loadButton);

        newProf.setLayoutX(300);
        newProf.setLayoutY(320);
        newProf.setPrefSize(100,20);
        root.getChildren().add(newProf);

        delProfButton.setLayoutX(300);
        delProfButton.setLayoutY(350);
        delProfButton.setPrefSize(100,20);
        root.getChildren().add(delProfButton);
        delProfButton.setOnAction(e -> window.setScene(deleteProfile));


        leaderboardButton.setLayoutX(300);
        leaderboardButton.setLayoutY(380);
        leaderboardButton.setPrefSize(100,20);
        root.getChildren().add(leaderboardButton);


        exitButton.setLayoutX(300);
        exitButton.setLayoutY(410);
        exitButton.setPrefSize(100,20);
        root.getChildren().add(exitButton);

        //SET BUTTON ACTIONS
        playButton.setOnAction(e -> window.setScene(setup));
        //loadButton.setOnAction(e -> MainMenu.loadBoard());
        newProf.setOnAction(e -> window.setScene(createProfile));
        loadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.setScene(loadGameScreen);
                MainMenu.loadBoard();
            }
        });
        leaderboardButton.setOnAction(e -> window.setScene(leaderboardScreen));
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }

        });

        //MESSAGE OF THE DAY
        MessageOfTheDay newMessage = new MessageOfTheDay();
        Label messageOtD = new Label(newMessage.getMessage());
        messageOtD.setFont(new Font(15));
        messageOtD.setLayoutX(10);
        messageOtD.setLayoutY(500);
        messageOtD.setPrefWidth(700);
        messageOtD.setWrapText(true);
        root.getChildren().add(messageOtD);


        //INGAME SCREEN

        Image imageDecline = new Image("/pause.png");
        Button pauseButton = new Button();
        ImageView imageDeclineView = new ImageView(imageDecline);
        imageDeclineView.setFitHeight(20);
        imageDeclineView.setFitWidth(20);
        pauseButton.setGraphic(imageDeclineView);
        pauseButton.setPrefSize(100,20);
        pauseButton.setOnAction(e -> window.setScene(pauseMenu));

        Label insertPrompt = new Label("Insert tile into board");
        insertPrompt.setFont(new Font(10));
        insertPrompt.setLayoutX(540);
        insertPrompt.setLayoutY(370);
        insertPrompt.setPrefWidth(400);
        Label availableAction = new Label("Available Actions:");
        availableAction.setFont(new Font(20));
        availableAction.setLayoutX(500);
        availableAction.setLayoutY(420);
        availableAction.setPrefWidth(400);

        Label playerTurnLabel = new Label("Player's turn");
        playerTurnLabel.setFont(new Font(30));
        playerTurnLabel.setLayoutX(490);
        playerTurnLabel.setLayoutY(100);
        playerTurnLabel.setPrefWidth(400);

        Button drawTile = new Button("Draw Tile");
        drawTile.setPrefSize(90, 20);
        drawTile.setLayoutX(490);
        drawTile.setLayoutY(200);

        Button playAction = new Button("Play Action");
        playAction.setPrefSize(90, 20);
        playAction.setLayoutX(580);
        playAction.setLayoutY(200);
        playAction.setDisable(true);
        Button endTurnButton = new Button("End turn");
        endTurnButton.setPrefSize(90, 20);
        endTurnButton.setLayoutX(580);
        endTurnButton.setLayoutY(550);

        Image arrowLeft = new Image("clockwisearrow.png");
        Button rotateLeft = new Button();
        ImageView arrowLview = new ImageView(arrowLeft);
        arrowLview.setFitHeight(20);
        arrowLview.setFitWidth(20);
        rotateLeft.setPrefSize(20, 20);
        rotateLeft.setLayoutX(490);
        rotateLeft.setLayoutY(300);
        rotateLeft.setGraphic(arrowLview);

        Image arrowRight = new Image("anticlockwisearrow.png");
        Button rotateRight = new Button();
        ImageView arrowRview = new ImageView(arrowRight);
        arrowRview.setFitHeight(20);
        arrowRview.setFitWidth(20);
        rotateRight.setPrefSize(20, 20);
        rotateRight.setLayoutX(640);
        rotateRight.setLayoutY(300);
        rotateRight.setGraphic(arrowRview);
        rotateRight.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });


        //////

        MainMenu.loadPresetBoard(0);
        Group game = new Group();

        for (int i = 0; i < Board.getHeight(); i++ ) {
            for (int j = 0; j < Board.getWidth(); j++) {
                ImageView imageview = new ImageView();
                String tileType = "";
                if (Board.getTile(i, j).getFloorTileType().equals("Straight")) {
                    tileType += "straight";
                } else if (Board.getTile(i, j).getFloorTileType().equals("Corner")) {
                    tileType += "corner";
                } else if (Board.getTile(i, j).getFloorTileType().equals("Tshaped") ) {
                    tileType += "tshaped";
                } else {
                    tileType += "goal";
                }
                if (Board.getTile(i, j).getFixed()) {
                    System.out.println("it worked");
                    tileType = "fixed" + tileType;
                }
                if (Board.getTile(i, j).getOnFire()) {
                    tileType = "fire" + tileType;
                }
                if (Board.getTile(i, j).getIsFrozen()) {
                    tileType = "frozen" + tileType;
                }
                imageview.setImage(new Image("file:" + tileType + ".png"));
                if (Board.getTile(i, j).getOrientation() == 1) {
                    imageview.setRotate(imageview.getRotate() + 90);
                } else if (Board.getTile(i, j).getOrientation() == 2) {
                    imageview.setRotate(imageview.getRotate() + 180);
                } else if (Board.getTile(i, j).getOrientation() == 3) {
                    imageview.setRotate(imageview.getRotate() + 270);
                }

                imageview.setX(50+(i*40));
                imageview.setY(70+(j*40));
                imageview.setFitHeight(40);
                imageview.setFitWidth(40);
                imageview.setPreserveRatio(true);
                game.getChildren().add(imageview);
            }
        }

        inGameScreen = new Scene(game, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
        game.getChildren().add(pauseButton);
        game.getChildren().add(endTurnButton);
        game.getChildren().add(drawTile);
        game.getChildren().add(availableAction);
        game.getChildren().add(playAction);
        game.getChildren().add(playerTurnLabel);
        game.getChildren().add(rotateRight);
        game.getChildren().add(rotateLeft);
        drawTile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drawTile.setDisable(true);
                playAction.setDisable(false);
                game.getChildren().add(insertPrompt);
                Tile drawnTile = Board.getTileFromSilkBag();
                ImageView imageview = new ImageView();
                if (drawnTile.getTileType().equals("Floor")){
                    FloorTile drawnFloorTile = (FloorTile) drawnTile;
                    imageview.setImage(new Image(drawnFloorTile.getFloorTileType().toLowerCase() + ".png"));
                } else  {
                    ActionTile drawnFloorTile = (ActionTile) drawnTile;
                    imageview.setImage(new Image(drawnFloorTile.getActionTileType().toLowerCase()
                            .replaceAll(" ", "") + ".png"));
                }

                imageview.setX(549);
                imageview.setY(283);
                imageview.setFitHeight(70);
                imageview.setFitWidth(70);
                imageview.setPreserveRatio(true);
                game.getChildren().add(imageview);

            }
        });

        //PAUSE MENU

        Pane pause = new Pane();
        pauseMenu = new Scene(pause, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
        Text paused = new Text(230, 150, "Game Paused");
        paused.setFont(new Font(40));
        pause.getChildren().add(paused);

        Button resumeButton = new Button("Resume game");
        resumeButton.setOnAction(e -> window.setScene(inGameScreen));
        resumeButton.setLayoutX(300);
        resumeButton.setLayoutY(260);
        resumeButton.setPrefSize(100,20);
        pause.getChildren().add(resumeButton); //ADDED BUTTON TO TEST NAVIGATION

        Button saveGame = new Button("Save and Exit");
        //NEED TO ADD SAVE FUNCTION
        saveGame.setOnAction(e -> window.setScene(mainMenu));
        saveGame.setLayoutX(300);
        saveGame.setLayoutY(300);
        saveGame.setPrefSize(100,20);
        pause.getChildren().add(saveGame);

        //LEADERBOARD

        Pane leaderboard = new Pane();
        leaderboardScreen = new Scene(leaderboard, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
        Button mButton = new Button("Return to Main Menu");
        mButton.setOnAction(e -> window.setScene(mainMenu));
        leaderboard.getChildren().add(mButton); //ADDED BUTTON TO TEST NAVIGATION

        //LOAD GAME
        Pane loadGame = new Pane();
        loadGameScreen = new Scene(loadGame, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
        Button returnM = new Button("Return to Main Menu");
        returnM.setOnAction(e -> window.setScene(mainMenu));
        loadGame.getChildren().add(returnM); //ADDED BUTTON TO TEST NAVIGATION

        //SETUP/PROFILE

        Pane gameSetup = new Pane();
        setup = new Scene(gameSetup, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
        Text settings = new Text(280, 100, "Settings");
        settings.setFont(new Font(40));
        gameSetup.getChildren().add(settings);
        Text players = new Text(150, 200, "How many players are there?");
        players.setFont(new Font(30));
        gameSetup.getChildren().add(players);

        Button twoPlayer = new Button("2");
        twoPlayer.setLayoutX(100);
        twoPlayer.setLayoutY(260);
        twoPlayer.setPrefSize(100,20);
        gameSetup.getChildren().add(twoPlayer);
        Button threePlayer = new Button("3");
        threePlayer.setLayoutX(300);
        threePlayer.setLayoutY(260);
        threePlayer.setPrefSize(100,20);
        gameSetup.getChildren().add(threePlayer);
        Button fourPlayer = new Button("4");
        fourPlayer.setLayoutX(500);
        fourPlayer.setLayoutY(260);
        fourPlayer.setPrefSize(100,20);
        gameSetup.getChildren().add(fourPlayer);

        fourPlayer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (int x = 0; x < 4; x++) {
                    System.out.println(x);
                }
            }

        });

        Button startGame = new Button("Start game");
        startGame.setLayoutX(550);
        startGame.setLayoutY(550);
        startGame.setPrefSize(100,20);
        gameSetup.getChildren().add(startGame);
        startGame.setOnAction(e -> window.setScene(inGameScreen));

        Button backButton = new Button("Back to Main Menu");
        gameSetup.getChildren().add(backButton);
        backButton.setOnAction(e -> window.setScene(mainMenu));

        //CREATE PROFILE
        Pane profilePane = new Pane();
        createProfile = new Scene(profilePane, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
        Button returnButton = new Button("Return to Main Menu");
        returnButton.setOnAction(e -> window.setScene(mainMenu));
        profilePane.getChildren().add(returnButton);

        TextField enterName = new TextField();
        enterName.setPromptText("Enter your name:");
        enterName.setLayoutX(230);
        enterName.setLayoutY(MAIN_WINDOW_HEIGHT/2);
        profilePane.getChildren().add(enterName);

        Label createProfLabel = new Label("Create a new profile");
        createProfLabel.setFont(new Font(30));
        createProfLabel.setLayoutX(230);
        createProfLabel.setLayoutY(210);
        profilePane.getChildren().add(createProfLabel);

        Button profileConfirm = new Button("Confirm");
        profileConfirm.setLayoutX(430);
        profileConfirm.setLayoutY(MAIN_WINDOW_HEIGHT/2);
        profileConfirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainMenu.newProfile(enterName.getText());
                window.setScene(mainMenu);

            }

        });
        profilePane.getChildren().add(profileConfirm);

        //DELETE PROFILE
        Pane deletePane = new Pane();
        deleteProfile = new Scene(deletePane, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);

        Button mainMeButton = new Button("Return to Main Menu");
        mainMeButton.setOnAction(e -> window.setScene(mainMenu));
        deletePane.getChildren().add(mainMeButton);

        TextField deleteName = new TextField();
        deleteName.setPromptText("Enter your name:");
        deleteName.setLayoutX(230);
        deleteName.setLayoutY(MAIN_WINDOW_HEIGHT/2);
        deletePane.getChildren().add(deleteName);

        Label deleteProfLabel = new Label("Delete existing profile");
        deleteProfLabel.setFont(new Font(30));
        deleteProfLabel.setLayoutX(230);
        deleteProfLabel.setLayoutY(210);
        deletePane.getChildren().add(deleteProfLabel);

        Button deleteConfirm = new Button("Delete");
        deleteConfirm.setLayoutX(430);
        deleteConfirm.setLayoutY(MAIN_WINDOW_HEIGHT/2);
        deleteConfirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainMenu.deleteProfile(deleteName.getText());
                window.setScene(mainMenu);

            }

        });
        deletePane.getChildren().add(deleteConfirm);

        //DEFAULT STARTUP SETTINGS
        //ICON
        window.getIcons().add(new Image("logo.png"));
        window.setTitle(WINDOW_TITLE);
        window.setScene(mainMenu);
        window.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}

