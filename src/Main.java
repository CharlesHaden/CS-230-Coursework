/**
 *
 *
 * @author Nim Man
 * @author HyderAlhashimi
 * @author Laurence Burns-Mill
 * @author Matthew Clarke
 */

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

public class Main extends Application {
    // Constants for the main window
    private static final int MAIN_WINDOW_WIDTH = 720;
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
        newProf.setOnAction(e -> window.setScene(createProfile));
        loadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.setScene(loadGameScreen);
                //MainMenu.loadBoard();
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


        MainMenu.curGenPlayers(4);
        MainMenu.loadPresetBoard(1);

        //INGAME SCREEN

        Image imageDecline = new Image("pause.png");
        Button pauseButton = new Button();
        ImageView imageDeclineView = new ImageView(imageDecline);
        imageDeclineView.setFitHeight(20);
        imageDeclineView.setFitWidth(20);
        pauseButton.setGraphic(imageDeclineView);
        pauseButton.setPrefSize(100,20);
        pauseButton.setOnAction(e -> window.setScene(pauseMenu));

        Label insertPrompt = new Label("Draw a tile");
        insertPrompt.setFont(new Font(10));
        insertPrompt.setLayoutX(540);
        insertPrompt.setLayoutY(370);
        insertPrompt.setPrefWidth(400);
        Label availableAction = new Label("Available Actions:");
        availableAction.setFont(new Font(20));
        availableAction.setLayoutX(530);
        availableAction.setLayoutY(440);
        availableAction.setPrefWidth(400);

        Label playerTurnLabel = new Label("Player's turn");
        playerTurnLabel.setFont(new Font(30));
        playerTurnLabel.setLayoutX(520);
        playerTurnLabel.setLayoutY(100);
        playerTurnLabel.setPrefWidth(400);

        Button drawTile = new Button("Draw Tile");
        drawTile.setPrefSize(90, 20);
        drawTile.setLayoutX(520);
        drawTile.setLayoutY(200);

        Button playAction = new Button("Play Action");
        playAction.setPrefSize(90, 20);
        playAction.setLayoutX(610);
        playAction.setLayoutY(200);
        playAction.setDisable(true);
        Button movePlayerButton = new Button("Move player");
        movePlayerButton.setPrefSize(90, 20);
        movePlayerButton.setLayoutX(610);
        movePlayerButton.setLayoutY(400);
        movePlayerButton.setDisable(true);
        Button insertTileButton = new Button("Insert Tile");
        insertTileButton.setPrefSize(90, 20);
        insertTileButton.setLayoutX(520);
        insertTileButton.setLayoutY(400);
        insertTileButton.setDisable(true);
        Button storeActionButton = new Button("Store Action");
        storeActionButton.setPrefSize(90, 20);
        storeActionButton.setLayoutX(565);
        storeActionButton.setLayoutY(374);
        storeActionButton.setDisable(true);

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
        rotateLeft.setLayoutX(520);
        rotateLeft.setLayoutY(300);
        rotateLeft.setGraphic(arrowLview);

        Image arrowRight = new Image("anticlockwisearrow.png");
        Button rotateRight = new Button();
        ImageView arrowRview = new ImageView(arrowRight);
        arrowRview.setFitHeight(20);
        arrowRview.setFitWidth(20);
        rotateRight.setPrefSize(20, 20);
        rotateRight.setLayoutX(660);
        rotateRight.setLayoutY(300);
        rotateRight.setGraphic(arrowRview);

        Group game = new Group();
        Group board = new Group();
        Group player = new Group();
        //////
        updateBoard(board);
        updatePlayer(player);
        //////

        inGameScreen = new Scene(game, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
        game.getChildren().add(pauseButton);
        game.getChildren().add(endTurnButton);
        game.getChildren().add(drawTile);
        game.getChildren().add(movePlayerButton);
        game.getChildren().add(insertTileButton);
        game.getChildren().add(storeActionButton);
        game.getChildren().add(playAction);
        game.getChildren().add(availableAction);
        game.getChildren().add(playerTurnLabel);
        //game.getChildren().add(insertPrompt);
        game.getChildren().add(rotateRight);
        game.getChildren().add(rotateLeft);
        game.getChildren().add(board);
        game.getChildren().add(player);
        drawTile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drawTile.setDisable(true);
                Tile drawnTile = Board.getTileFromSilkBag();
                ImageView imageview = new ImageView();
                if (drawnTile.getTileType().equals("Floor")){
                    FloorTile drawnFloorTile = (FloorTile) drawnTile;
                    imageview.setImage(new Image(drawnFloorTile.getFloorTileType().toLowerCase() + ".png"));
                    insertPrompt.setText("Insert tile into board");
                    insertTileButton.setDisable(false);
                    rotateLeft.setDisable(false);
                    rotateRight.setDisable(false);

                } else  {
                    ActionTile drawnActionTile = (ActionTile) drawnTile;
                    imageview.setImage(new Image(drawnActionTile.getActionTileType().toLowerCase().replaceAll(" ", "") + ".png"));
                    insertPrompt.setText("Store the action and Move player");
                    insertTileButton.setDisable(true);
                    storeActionButton.setDisable(false);
                    movePlayerButton.setDisable(false);
                    rotateLeft.setDisable(true);
                    rotateRight.setDisable(true);
                }

                imageview.setX(574);
                imageview.setY(283);
                imageview.setFitHeight(70);
                imageview.setFitWidth(70);
                imageview.setPreserveRatio(true);
                game.getChildren().add(imageview);

                rotateRight.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        imageview.setRotate(imageview.getRotate() - 90);

                    }
                });
                rotateLeft.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        imageview.setRotate(imageview.getRotate() + 90);

                    }
                });

                endTurnButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        game.getChildren().remove(imageview);
                        drawTile.setDisable(false);

                    }
                });
                storeActionButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        game.getChildren().remove(imageview);
                        storeActionButton.setDisable(true);
                        // ADD IMAGE TO BOTTOM //
                    }
                });

            }
        });

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

        TextField loadGameText = new TextField();
        loadGameText.setPromptText("Enter file name:");
        loadGameText.setLayoutX(230);
        loadGameText.setLayoutY(MAIN_WINDOW_HEIGHT/2);
        loadGame.getChildren().add(loadGameText);
        loadGame.getChildren().add(returnM);
        Label loadGameLabel = new Label("Load Game");
        loadGameLabel.setFont(new Font(30));
        loadGameLabel.setLayoutX(230);
        loadGameLabel.setLayoutY(210);
        loadGame.getChildren().add(loadGameLabel);

        Button loadGameButton = new Button("Load");
        loadGameButton.setLayoutX(450);
        loadGameButton.setLayoutY(MAIN_WINDOW_HEIGHT/2);
        loadGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainMenu.loadBoard(loadGameText.getText());
                System.out.println(Board.getTile(0, 0).getTileType());
            }
        });
        loadGame.getChildren().add(loadGameButton);

        //SETUP/PROFILE
        Pane gameSetup = new Pane();
        setup = new Scene(gameSetup, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
        Text settings = new Text(280, 100, "Settings");
        settings.setFont(new Font(40));
        gameSetup.getChildren().add(settings);
        Text noplayersText = new Text(100, 170, "Select number of players");
        noplayersText.setFont(new Font(20));
        gameSetup.getChildren().add(noplayersText);
        Text selectBoardText = new Text(100, 250, "Select preset Board");
        selectBoardText.setFont(new Font(20));
        gameSetup.getChildren().add(selectBoardText);

        Button twoPlayer = new Button("2");
        twoPlayer.setLayoutX(100);
        twoPlayer.setLayoutY(190);
        twoPlayer.setPrefSize(80,20);
        gameSetup.getChildren().add(twoPlayer);
        Button threePlayer = new Button("3");
        threePlayer.setLayoutX(190);
        threePlayer.setLayoutY(190);
        threePlayer.setPrefSize(80,20);
        gameSetup.getChildren().add(threePlayer);
        Button fourPlayer = new Button("4");
        fourPlayer.setLayoutX(280);
        fourPlayer.setLayoutY(190);
        fourPlayer.setPrefSize(80,20);
        gameSetup.getChildren().add(fourPlayer);
        twoPlayer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                noplayersText.setText("2 players selected");
            }
        });

        threePlayer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                noplayersText.setText("3 players selected");
            }
        });

        fourPlayer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                noplayersText.setText("4 players selected");
            }
        });

        Button presetBoard1 = new Button("1");
        presetBoard1.setLayoutX(100);
        presetBoard1.setLayoutY(270);
        presetBoard1.setPrefSize(80,20);
        gameSetup.getChildren().add(presetBoard1);
        Button presetBoard2 = new Button("2");
        presetBoard2.setLayoutX(190);
        presetBoard2.setLayoutY(270);
        presetBoard2.setPrefSize(80,20);
        gameSetup.getChildren().add(presetBoard2);
        Button presetBoard3 = new Button("3");
        presetBoard3.setLayoutX(280);
        presetBoard3.setLayoutY(270);
        presetBoard3.setPrefSize(80,20);
        gameSetup.getChildren().add(presetBoard3);

        presetBoard1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectBoardText.setText("Preset board 1 selected");
            }

        });
        presetBoard2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectBoardText.setText("Preset board 2 selected");
            }

        });
        presetBoard3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectBoardText.setText("Preset board 3 selected");
            }

        });

        Button startGame = new Button("Start game");
        startGame.setLayoutX(550);
        startGame.setLayoutY(550);
        startGame.setPrefSize(100,20);
        gameSetup.getChildren().add(startGame);
        startGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.setScene(inGameScreen);
                updateBoard(board);
            }
        });
        Button backButton = new Button("Back to Main Menu");
        gameSetup.getChildren().add(backButton);
        backButton.setOnAction(e -> window.setScene(mainMenu));

        //CREATE PROFILE
        Pane profilePane = new Pane();
        createProfile = new Scene(profilePane, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
        Button returnButton = new Button("Return to Main Menu");
        returnButton.setOnAction(e -> window.setScene(mainMenu));
        profilePane.getChildren().add(returnButton);

        TextField textfield = new TextField();
        textfield.setPromptText("Enter your name:");
        textfield.setLayoutX(230);
        textfield.setLayoutY(MAIN_WINDOW_HEIGHT/2);
        profilePane.getChildren().add(textfield);

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
                MainMenu.newProfile(textfield.getText());
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
        window.setScene(inGameScreen);
        window.show();
    }

    public void updatePlayer(Group player) {

        if (player != null) {
            player.getChildren().clear();
        }

        for (int i = 0; i < MainMenu.getCurGamePlayers().size(); i++) {
            System.out.println("Size" + MainMenu.getCurGamePlayers().size());
            System.out.println(i);
            Player curPlayer = MainMenu.getCurGamePlayers().get(i);
            String playerNo = "player";
            System.out.println(playerNo + (i + 1) + ".png");
            Image playerCarImage = new Image(playerNo + (i + 1) + ".png");
            ImageView playerCarView = new ImageView(playerCarImage);
            playerCarView.setFitWidth(40);
            playerCarView.setFitHeight(40);
            int[] position = curPlayer.getPlayerPosition();
            System.out.println(curPlayer.getPlayerPosition()[0]);
            System.out.println(position[0]);
            playerCarView.setX(50 + (position[0] * 40));
            playerCarView.setY(70 + (position[1] * 40));
            playerCarView.setRotate(90 * (Board.getTile(curPlayer.getPlayerPosition()[0],
                    curPlayer.getPlayerPosition()[1]).getOrientation()));
            player.getChildren().add(playerCarView);
        }
    }

    public void updateBoard(Group board){

        if (board != null) {
            board.getChildren().clear();
        }

        for (int g = 0; g < Board.getWidth(); g++) {
            Image insertArrowImage = new Image("insertionArrowVert.png");
            ImageView insertArrowView = new ImageView(insertArrowImage);
            insertArrowView.setRotate(insertArrowView.getRotate()-180);
            insertArrowView.setFitHeight(30);
            insertArrowView.setFitWidth(10);
            Button insertButton = new Button();
            insertButton.setLayoutX(57+(g*40));
            insertButton.setLayoutY(35);
            insertButton.setPrefSize(20,30);
            insertButton.setGraphic(insertArrowView);
            int finalG = g;
            insertButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Board.insertTile(finalG, 0, false);
                    updateBoard(board);
                }
            });
            insertButton.setDisable(!Board.checkInsert(g, 0, false));
            board.getChildren().add(insertButton);
            ImageView insertArrowView2 = new ImageView(insertArrowImage);
            insertArrowView2.setFitHeight(30);
            insertArrowView2.setFitWidth(10);
            Button insertButton2 = new Button();
            insertButton2.setLayoutX(57+(g*40));
            insertButton2.setLayoutY((Board.getHeight()*40)+70);
            insertButton2.setPrefSize(20,30);
            insertButton2.setGraphic(insertArrowView2);
            insertButton2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Board.insertTile(finalG, Board.getHeight() - 1, false);
                    updateBoard(board);
                }
            });
            insertButton2.setDisable(!Board.checkInsert(g, Board.getHeight() - 1, false));
            board.getChildren().add(insertButton2);

        }
        for (int g = 0; g < Board.getHeight(); g++) {
            Image insertArrowImage = new Image("insertionArrow.png");
            ImageView insertArrowView = new ImageView(insertArrowImage);
            insertArrowView.setRotate(insertArrowView.getRotate()+180);
            insertArrowView.setFitHeight(10);
            insertArrowView.setFitWidth(30);
            Button insertButton = new Button();
            insertButton.setLayoutX(10);
            insertButton.setLayoutY(78+(g*40));
            insertButton.setPrefSize(30,20);
            insertButton.setGraphic(insertArrowView);
            int finalG = g;
            insertButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Board.insertTile(0, finalG, true);
                    updateBoard(board);
                }
            });
            insertButton.setDisable(!Board.checkInsert(0, finalG, true));
            board.getChildren().add(insertButton);
            ImageView insertArrowView2 = new ImageView(insertArrowImage);
            insertArrowView2.setFitHeight(10);
            insertArrowView2.setFitWidth(30);
            Button insertButton2 = new Button();
            insertButton2.setLayoutX((Board.getWidth()*40)+49);
            insertButton2.setLayoutY(78+(g*40));
            insertButton2.setPrefSize(30,20);
            insertButton2.setGraphic(insertArrowView2);
            insertButton2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Board.insertTile(Board.getHeight() - 1, finalG, true);
                    updateBoard(board);
                }
            });
            insertButton2.setDisable(!Board.checkInsert(Board.getHeight() - 1, finalG, true));
            board.getChildren().add(insertButton2);

        }

        for (int i = 0; i < Board.getWidth(); i++ ) {
            for (int j = 0; j < Board.getHeight(); j++) {
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
                    tileType = "fixed" + tileType;
                }
                if (Board.getTile(i, j).getOnFire()) {
                    tileType = "fire" + tileType;
                }
                if (Board.getTile(i, j).getIsFrozen()) {
                    tileType = "frozen" + tileType;
                }
                imageview.setImage(new Image(tileType + ".png"));
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
                board.getChildren().add(imageview);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

