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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class Main extends Application {
    // Constants for the main window
    TableView<Profile> leaderBoardTable;
    TextField presetBoardInput;
    ObservableList<Profile> leaderBoardList;
    private static final int MAIN_WINDOW_WIDTH = 720;
    private static final int MAIN_WINDOW_HEIGHT = 650;
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

        TextField saveGameTextField = new TextField();
        saveGameTextField.setPromptText("Enter save name:");
        saveGameTextField.setLayoutX(230);
        saveGameTextField.setLayoutY(MAIN_WINDOW_HEIGHT/2);
        pause.getChildren().add(saveGameTextField);

        Button saveAndExitButton = new Button("Save and Exit");
        saveAndExitButton.setLayoutX(350);
        saveAndExitButton.setLayoutY(300);
        saveAndExitButton.setPrefSize(100,20);
        saveAndExitButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                System.out.println(saveGameTextField.getText());
                window.setScene(mainMenu);
                MainMenu.saveBoard(saveGameTextField.getText()); 
                //ADD STRING PARAMETER -> load game
            }
        });
        pause.getChildren().add(saveAndExitButton);




        Group sideGame = new Group();
        Group board = new Group();
        Group player = new Group();
        Group mainGame = new Group();
        //////


        //////
        mainGame.getChildren().add(board);
        mainGame.getChildren().add(player);
        mainGame.getChildren().add(sideGame);

        inGameScreen = new Scene(mainGame, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);


        //LEADERBOARD

        Pane leaderboard = new Pane();
        leaderboardScreen = new Scene(leaderboard, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
        MainMenu.loadProfile();
        System.out.println(MainMenu.getAllProfiles().get(0).getName());
        generateLeaderBoard();
        leaderBoardTable = new TableView();
        leaderBoardTable.setItems(getProfile());
        Button exitTableButton = new Button("Return to Main Menu");
        exitTableButton.setOnAction(e -> window.setScene(mainMenu));
        Button presetBoardInput = new Button("Current Preset Board: " + (LeaderBoard.getPresetBoard() + 1));
        presetBoardInput.setOnAction(e -> presetBoardInputClicked(presetBoardInput));
        presetBoardInput.setMinWidth(200);

        presetBoardInput.setLayoutX(140);
        presetBoardInput.setLayoutY(0);

        updateTable();

        leaderBoardTable.setLayoutX(0);
        leaderBoardTable.setLayoutY(30);

        leaderboard.getChildren().add(leaderBoardTable);
        leaderboard.getChildren().add(presetBoardInput);
        leaderboard.getChildren().add(exitTableButton);

        TableColumn<Profile, String> nameColumn = new TableColumn<>("Profile Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        TableColumn<Profile, int[]> winsColumn = new TableColumn<>("Wins");
        winsColumn.setMinWidth(200);
        winsColumn.setCellValueFactory(new PropertyValueFactory<>("Wins"));

        TableColumn<Profile, int[]> lossesColumn = new TableColumn<>("Losses");
        lossesColumn.setMinWidth(200);
        lossesColumn.setCellValueFactory(new PropertyValueFactory<>("Losses"));
        leaderBoardTable.getColumns().addAll(nameColumn, winsColumn, lossesColumn);


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
        Text chooseCarText = new Text(100, 350, "Choose car for Player 1");
        chooseCarText.setFont(new Font(20));
        gameSetup.getChildren().add(chooseCarText);


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
        presetBoard1.setDisable(true);
        presetBoard2.setDisable(true);
        presetBoard3.setDisable(true);
        gameSetup.getChildren().add(presetBoard3);

        presetBoard1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectBoardText.setText("Preset board 1 selected");
                MainMenu.loadPresetBoard(1);
                Game.setPlayers(MainMenu.getCurGamePlayers());
                for (int i =  0; i < Game.getPlayers().size(); i++) {
                    Game.getPlayers().get(i).setPlayerNum(i + 1);
                }
            }

        });
        presetBoard2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectBoardText.setText("Preset board 2 selected");
                MainMenu.loadPresetBoard(2);
                Game.setPlayers(MainMenu.getCurGamePlayers());
                for (int i =  0; i < Game.getPlayers().size(); i++) {
                    Game.getPlayers().get(i).setPlayerNum(i + 1);
                }
            }

        });
        presetBoard3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectBoardText.setText("Preset board 3 selected");
                MainMenu.loadPresetBoard(3);
                Game.setPlayers(MainMenu.getCurGamePlayers());
                for (int i =  0; i < Game.getPlayers().size(); i++) {
                    Game.getPlayers().get(i).setPlayerNum(i + 1);
                }
            }

        });

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
                presetBoard1.setDisable(false);
                presetBoard2.setDisable(false);
                presetBoard3.setDisable(false);
                for (int count = 1; count < 3; count++) {
                    Image carImage = new Image("player" + count + ".png");
                    ImageView chooseCarView = new ImageView(carImage);
                    chooseCarView.setFitHeight(50);
                    chooseCarView.setFitWidth(50);
                    Button carButton = new Button();
                    carButton.setLayoutX((count*100));
                    carButton.setLayoutY(370);
                    carButton.setPrefSize(80,80);
                    carButton.setGraphic(chooseCarView);
                    gameSetup.getChildren().add(carButton);
                }
                MainMenu.curGenPlayers(2);
            }
        });
        threePlayer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                noplayersText.setText("3 players selected");
                presetBoard1.setDisable(false);
                presetBoard2.setDisable(false);
                presetBoard3.setDisable(false);
                for (int count = 1; count < 4; count++) {
                    Image carImage = new Image("player" + count + ".png");
                    ImageView chooseCarView = new ImageView(carImage);
                    chooseCarView.setFitHeight(50);
                    chooseCarView.setFitWidth(50);
                    Button carButton = new Button();
                    carButton.setLayoutX((count*100));
                    carButton.setLayoutY(370);
                    carButton.setPrefSize(80,80);
                    carButton.setGraphic(chooseCarView);
                    gameSetup.getChildren().add(carButton);

                }
                MainMenu.curGenPlayers(3);
            }
        });

        fourPlayer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                noplayersText.setText("4 players selected");
                presetBoard1.setDisable(false);
                presetBoard2.setDisable(false);
                presetBoard3.setDisable(false);
                for (int count = 1; count < 5; count++) {
                    Image carImage = new Image("player"+ count+".png");
                    ImageView chooseCarView = new ImageView(carImage);
                    chooseCarView.setFitHeight(50);
                    chooseCarView.setFitWidth(50);
                    Button carButton = new Button();
                    carButton.setLayoutX((count*100));
                    carButton.setLayoutY(370);
                    carButton.setPrefSize(80,80);
                    carButton.setGraphic(chooseCarView);
                    gameSetup.getChildren().add(carButton);
                }
                MainMenu.curGenPlayers(4);
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
                // WHILE BOARD NOT LOADED, DO NOT CHANGE SCREEN
                window.setScene(inGameScreen);
                updateBoard(board);
                updatePlayer(player);
                updateSideGame(sideGame);
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
                createProfLabel.setText(textfield.getText() + " successfully created");
                //System.out.println(allprofiles.size().getName);
                //MainMenu.saveProfile();
            }
        });
        profilePane.getChildren().add(profileConfirm);

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
                loadGameLabel.setText(loadGameText.getText() + " loaded successfully");
                selectBoardText.setText("Saved board loaded");
            }
        });
        loadGame.getChildren().add(loadGameButton);

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
                deleteProfLabel.setText(deleteName.getText() + " successfully deleted");
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

    public void updatePlayer(Group player) {
        if (player != null) {
            player.getChildren().clear();
        }

        for (int i = 0; i < Game.getPlayers().size(); i++) {
            Player curPlayer = Game.getPlayers().get(i);
            String playerNo = "player";
            Image playerCarImage = new Image(playerNo + (i + 1) + ".png");
            ImageView playerCarView = new ImageView(playerCarImage);
            playerCarView.setFitWidth(40);
            playerCarView.setFitHeight(40);
            int[] position = curPlayer.getPlayerPosition();
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
                    Board.insertTile(Board.getWidth() - 1, finalG, true);
                    updateBoard(board);
                }
            });
            insertButton2.setDisable(!Board.checkInsert(Board.getWidth() - 1, finalG, true));
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

    public void presetBoardInputClicked(Button presetBoardInput) {
        if (LeaderBoard.getPresetBoard() == MainMenu.getNumOfPresetBoards() - 1) {
            LeaderBoard.setPresetBoard(0);
        } else {
            LeaderBoard.setPresetBoard(LeaderBoard.getPresetBoard()+1);
        }
        updateTable();
        presetBoardInput.setText("Current Preset Board: " + (LeaderBoard.getPresetBoard() + 1));
    }

    public ObservableList<Profile> getProfile(){
        leaderBoardList = FXCollections.observableArrayList(LeaderBoard.getProfileList());
        return(leaderBoardList);
    }

    public void updateTable() {
        leaderBoardTable.refresh();
    }

    public void generateLeaderBoard() {
        MainMenu.loadPresetBoard(1);
        new LeaderBoard(MainMenu.getAllProfiles(), 1);
    }

    public void updateSideGame(Group sideGame) {
        //INGAME SCREEN

        inGameScreen.setOnMouseClicked(new EventHandler<MouseEvent>() {
            FloorTile tileClicked;
            public void handle(MouseEvent event) {
                for (int i = 0; i < Board.getHeight(); i++) {
                    for (int j = 0; j < Board.getWidth(); j++) {
                        try {
                            tileClicked = Board.getTile((int) ((event.getSceneX() / 40)) - 1,
                                    ((int) ((event.getSceneY()) / 40) - 2));
                            System.out.println(tileClicked.getFloorTileType());
                        } catch (Exception e) {
                            tileClicked = null;
                        }
                    }
                }
            }
        });

        if (sideGame != null) {
            sideGame.getChildren().clear();
        }


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

        Label playerTurnLabel = new Label("Player " + Game.getCurPlayer().getPlayerNum() + "'s turn");
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

        ArrayList<ActionTile> curHand = Game.getCurPlayer().getPlayerHand();
        if (!curHand.isEmpty()) {
            for (int i = 0; i < curHand.size(); i++) {
                String curAction = curHand.get(i).getActionTileType();
                switch (curAction) {
                    case "Fire":
                        Image fireImage = new Image("fire.png");
                        Button fireButton = new Button();
                        ImageView fireButtonView = new ImageView(fireImage);
                        fireButtonView.setFitHeight(50);
                        fireButtonView.setFitWidth(50);
                        fireButton.setPrefSize(50, 50);
                        fireButton.setLayoutX(80 + (i * 80));
                        fireButton.setLayoutY(550);
                        fireButton.setGraphic(fireButtonView);
                        System.out.println("fire");
                        sideGame.getChildren().add(fireButton);
                        break;

                    case "Ice":
                        Image iceImage = new Image("ice.png");
                        Button iceButton = new Button();
                        ImageView iceButtonView = new ImageView(iceImage);
                        iceButtonView.setFitHeight(50);
                        iceButtonView.setFitWidth(50);
                        iceButton.setPrefSize(50, 50);
                        iceButton.setLayoutX(80 + (i * 80));
                        iceButton.setLayoutY(550);
                        iceButton.setGraphic(iceButtonView);
                        System.out.println("ice");
                        sideGame.getChildren().add(iceButton);
                        break;

                    case "Double Move":
                        Image doubleImage = new Image("doublemove.png");
                        Button doubleButton = new Button();
                        ImageView doubleButtonView = new ImageView(doubleImage);
                        doubleButtonView.setFitHeight(50);
                        doubleButtonView.setFitWidth(50);
                        doubleButton.setPrefSize(50, 50);
                        doubleButton.setLayoutX(80 + (i * 80));
                        doubleButton.setLayoutY(550);
                        doubleButton.setGraphic(doubleButtonView);
                        System.out.println("double");
                        sideGame.getChildren().add(doubleButton);
                        break;

                    case "Backtrack":
                        Image backImage = new Image("backtrack.png");
                        Button backButton = new Button();
                        ImageView backButtonView = new ImageView(backImage);
                        backButtonView.setFitHeight(50);
                        backButtonView.setFitWidth(50);
                        backButton.setPrefSize(50, 50);
                        backButton.setLayoutX(80 + (i * 80));
                        backButton.setLayoutY(550);
                        backButton.setGraphic(backButtonView);
                        sideGame.getChildren().add(backButton);
                        System.out.println("back");
                        break;

                        default:
                            System.out.println("didnt work");
                }
            }
        }

        drawTile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drawTile.setDisable(true);
                String drawnTile = Game.getCurPlayer().drawTile();
                ImageView imageview = new ImageView();
                if (drawnTile.equals("Floor")) {
                    imageview.setImage(new Image(Game.getCurPlayer().getTempFloorTile()
                            .getFloorTileType().toLowerCase() + ".png"));
                    insertPrompt.setText("Insert tile into board");
                    insertTileButton.setDisable(false);
                    rotateLeft.setDisable(false);
                    rotateRight.setDisable(false);

                } else  {
                    imageview.setImage(new Image(Game.getCurPlayer().getTempActionTile()
                            .getActionTileType().toLowerCase().replaceAll(" ", "") + ".png"));
                    insertTileButton.setDisable(true);
                    storeActionButton.setDisable(false);
                    rotateLeft.setDisable(true);
                    rotateRight.setDisable(true);
                }

                imageview.setX(574);
                imageview.setY(283);
                imageview.setFitHeight(70);
                imageview.setFitWidth(70);
                imageview.setPreserveRatio(true);
                sideGame.getChildren().add(imageview);

                rotateRight.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        imageview.setRotate(imageview.getRotate() - 90);
                        FloorTile tempFloorTile = Game.getCurPlayer().getTempFloorTile();
                        tempFloorTile.setOrientation((tempFloorTile.getOrientation() + 1) % 4);
                    }
                });
                rotateLeft.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        imageview.setRotate(imageview.getRotate() + 90);
                        FloorTile tempFloorTile = Game.getCurPlayer().getTempFloorTile();
                        tempFloorTile.setOrientation((tempFloorTile.getOrientation() - 1) % 4);
                    }
                });
                movePlayerButton.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event) {
                        Button moveLeftButton = new Button("Left");
                        Button moveRightButton = new Button("Right");
                        Button moveUpButton = new Button("Up");
                        Button moveDownButton = new Button("Down");
                        moveLeftButton.setLayoutX(330);
                        moveLeftButton.setLayoutY(550);
                        moveLeftButton.setPrefSize(60, 20);
                        moveDownButton.setLayoutX(400);
                        moveDownButton.setLayoutY(550);
                        moveDownButton.setPrefSize(60, 20);
                        moveRightButton.setLayoutX(470);
                        moveRightButton.setLayoutY(550);
                        moveRightButton.setPrefSize(60, 20);
                        moveUpButton.setLayoutX(400);
                        moveUpButton.setLayoutY(520);
                        moveUpButton.setPrefSize(60, 20);

                        sideGame.getChildren().addAll(moveDownButton,moveLeftButton,moveRightButton,moveUpButton);

                    }
                });
                endTurnButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        sideGame.getChildren().remove(imageview);
                        drawTile.setDisable(false);
                        Game.newTurn();
                        updateSideGame(sideGame);
                    }
                });
                storeActionButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        sideGame.getChildren().remove(imageview);
                        storeActionButton.setDisable(true);

                        movePlayerButton.setDisable(false);
                        updateSideGame(sideGame);
                        //movePlayerButton.setOnAction(e -> Player.makeMove(curBoard,e));
                        // ADD TILE TO PLAYER HAND
                        // ADD IMAGE TO BOTTOM //
                    }
                });

            }
        });

        sideGame.getChildren().add(pauseButton);
        sideGame.getChildren().add(endTurnButton);
        sideGame.getChildren().add(drawTile);
        sideGame.getChildren().add(movePlayerButton);
        sideGame.getChildren().add(insertTileButton);
        sideGame.getChildren().add(storeActionButton);
        sideGame.getChildren().add(playAction);
        sideGame.getChildren().add(availableAction);
        sideGame.getChildren().add(playerTurnLabel);
        //game.getChildren().add(insertPrompt);
        sideGame.getChildren().add(rotateRight);
        sideGame.getChildren().add(rotateLeft);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

