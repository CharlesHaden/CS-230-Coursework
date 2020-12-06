/** 
 * 
 * 
 * @author Nim Man
 * @author HyderAlhashimi
 * @author Laurence
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

 
public class Main extends Application {
   // Constants for the main window
	private static final int MAIN_WINDOW_WIDTH = 700;
	private static final int MAIN_WINDOW_HEIGHT = 600;
	private static final String WINDOW_TITLE = "Fast and Curious!";
	Stage window;
	Scene mainMenu;
	Scene pauseMenu;
	Scene setup;
	Scene leaderboardScreen;
	Scene inGameScreen;
	Scene loadGameScreen;
	
	TableView<Profile> leaderBoardTable;
	TextField presetBoardInput;
	ObservableList<Profile> leaderBoardList;
	
    @Override
    public void start(Stage window) throws Exception {

    	//MAIN MENU
    	//MainMenu gameMenu = new MainMenu();

     	Pane root = new Pane(); 
     	mainMenu = new Scene(root, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
     	Text title = new Text(160, 100, WINDOW_TITLE);
     	title.setFont(new Font(50));
     	root.getChildren().add(title);    

     	//CREATE BUTTONS
     	Button playButton = new Button("Play");
     	Button loadButton = new Button("Load game");
     	Button leaderboardButton = new Button("Leaderboard");
     	Button exitButton = new Button("Exit");
       

     	playButton.setLayoutX(300);
     	playButton.setLayoutY(260);
     	playButton.setPrefSize(100,20);
     	root.getChildren().add(playButton);

     	loadButton.setLayoutX(300);
     	loadButton.setLayoutY(300);
     	loadButton.setPrefSize(100,20);
     	root.getChildren().add(loadButton);


     	leaderboardButton.setLayoutX(300);
     	leaderboardButton.setLayoutY(340);
     	leaderboardButton.setPrefSize(100,20);
     	root.getChildren().add(leaderboardButton);


     	exitButton.setLayoutX(300);
     	exitButton.setLayoutY(380);
     	exitButton.setPrefSize(100,20);
     	root.getChildren().add(exitButton);

     	//SET BUTTON ACTIONS
     	playButton.setOnAction(e -> window.setScene(setup));
     	//loadButton.setOnAction(e -> MainMenu.loadBoard());
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

     	Image imageDecline = new Image("pause.png");
     	Button pauseButton = new Button();
     	ImageView imageDeclineView = new ImageView(imageDecline);
     	imageDeclineView.setFitHeight(20);
     	imageDeclineView.setFitWidth(20);
     	pauseButton.setGraphic(imageDeclineView);
     	pauseButton.setPrefSize(100,20);
     	pauseButton.setOnAction(e -> window.setScene(pauseMenu));

     	Label insertPrompt = new Label("Insert tile into board");
     	insertPrompt.setFont(new Font(10));
     	insertPrompt.setLayoutX(510);
     	insertPrompt.setLayoutY(370);
     	insertPrompt.setPrefWidth(400);
     	Label availableAction = new Label("Available Actions:");
     	availableAction.setFont(new Font(15));
     	availableAction.setLayoutX(510);
     	availableAction.setLayoutY(420);
     	availableAction.setPrefWidth(400);


     	Label playerTurnLabel = new Label("Player's turn");
     	playerTurnLabel.setFont(new Font(30));
     	playerTurnLabel.setLayoutX(480);
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
      	endTurnButton.setDisable(true);

      	Button rotateRight = new Button("R");
      	rotateRight.setPrefSize(20, 20);
      	rotateRight.setLayoutX(640);
      	rotateRight.setLayoutY(300);
      	Button rotateLeft = new Button("L");
      	rotateLeft.setPrefSize(20, 20);
      	rotateLeft.setLayoutX(490);
      	rotateLeft.setLayoutY(300);
      

      	Image straightTile = new Image("straight.PNG");
      	Image cornerTile = new Image("corner.PNG");
      	Image tShapedTile = new Image("tshaped.PNG");
      	Image goalTile = new Image("goal.PNG");

      	//FloorTile[][] curboard = MainMenu.loadPresetBoard(1);
      	FloorTile[][] curboard = new FloorTile[10][10]; 
      	for (int i = 0; i < 10; i++) {  
      		for (int j = 0; j < 10; j++) {
      			FloorTile curTile = new CornerTile(3);
      			curboard[i][j] = (curTile);
      		}
      	}

     	//FloorTile[][] curboard = Board.getTiles();
     	Group game = new Group();
     	for (int i = 0; i < 10 ; i++ ) {
     		for (int j = 0; j < 10; j++) {
        	   ImageView imageview = new ImageView();
        	   if (curboard[i][j].getFloorTileType() == "Straight") {
        		   imageview.setImage(straightTile);
        	   } else if (curboard[i][j].getFloorTileType() == "Corner") {
        		   imageview.setImage(cornerTile);
        	   } else if (curboard[i][j].getFloorTileType() == "Tshaped") {
        		   imageview.setImage(tShapedTile);
        	   } else {
               imageview.setImage(goalTile);
        	   }
        	   if (curboard[i][j].getOrientation() == 1) {
        		   imageview.setRotate(imageview.getRotate() + 90);
        	   } else if (curboard[i][j].getOrientation() == 2) {
        		   imageview.setRotate(imageview.getRotate() + 180);
        	   } else if (curboard[i][j].getOrientation() == 3) {
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
    	
    	MainMenu.loadProfile();
    	generateLeaderBoard(); 
    	
    	leaderBoardTable = new TableView();
    	leaderBoardTable.setItems(getProfile());
    	
    	leaderboardScreen = new Scene(leaderboard, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
    	Button exitTableButton = new Button("Return to Main Menu");
    	exitTableButton.setOnAction(e -> window.setScene(mainMenu));
    	
    	Button presetBoardInput = new Button("Current Preset Board: " + LeaderBoard.getPresetBoard());
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
    	
    	//LOAD GAME 
    	Pane loadGame = new Pane();
    	loadGameScreen = new Scene(loadGame, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
    	Button returnM = new Button("Return to Main Menu");
    	returnM.setOnAction(e -> window.setScene(mainMenu));
    	loadGame.getChildren().add(returnM); //ADDED BUTTON TO TEST NAVIGATION 

    	//SETUP/PROFILE

    	Pane profileSetup = new Pane();
    	setup = new Scene(profileSetup, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
    	Text settings = new Text(280, 100, "Settings");
    	settings.setFont(new Font(40));
    	profileSetup.getChildren().add(settings);
    	Text players = new Text(150, 200, "How many players are there?");
    	players.setFont(new Font(30));
    	profileSetup.getChildren().add(players);
      

     	Button twoPlayer = new Button("2");
     	twoPlayer.setLayoutX(100);
     	twoPlayer.setLayoutY(260);
     	twoPlayer.setPrefSize(100,20);
     	profileSetup.getChildren().add(twoPlayer); 
     	Button threePlayer = new Button("3");
     	threePlayer.setLayoutX(300);
     	threePlayer.setLayoutY(260);
     	threePlayer.setPrefSize(100,20);
     	profileSetup.getChildren().add(threePlayer); 
     	Button fourPlayer = new Button("4");
     	fourPlayer.setLayoutX(500);
     	fourPlayer.setLayoutY(260);
     	fourPlayer.setPrefSize(100,20);
     	profileSetup.getChildren().add(fourPlayer); 

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
     	profileSetup.getChildren().add(startGame); 
     	startGame.setOnAction(e -> window.setScene(inGameScreen));

     	Button backButton = new Button("Back to Main Menu");
     	backButton.setPrefSize(100,20);
     	profileSetup.getChildren().add(backButton); 
     	backButton.setOnAction(e -> window.setScene(mainMenu));

     	//DEFAULT STARTUP SETTINGS
     	//ICON
     	window.getIcons().add(new Image("cars.jpg"));
     	window.setTitle(WINDOW_TITLE);
     	window.setScene(mainMenu);
     	window.show();
    }
    
    public void presetBoardInputClicked(Button presetBoardInput) {
    	if (LeaderBoard.getPresetBoard() == 3) {
    		LeaderBoard.setPresetBoard(0);
    	} else {
    		LeaderBoard.setPresetBoard(LeaderBoard.getPresetBoard()+1);
    	}
    	updateTable();
    	presetBoardInput.setText("Current Preset Board: " + LeaderBoard.getPresetBoard());;

    }
    
    public ObservableList<Profile> getProfile(){
    	leaderBoardList = FXCollections.observableArrayList(LeaderBoard.getProfileList());
    	return(leaderBoardList);
    }
   
    public void updateTable() {
    	leaderBoardTable.refresh();
    }
    
    public void generateLeaderBoard() {
    	MainMenu.loadPresetBoard(0);;
    	new LeaderBoard(MainMenu.getAllProfiles(), 0);
    }
    
   
    public static void main(String[] args) {
    	launch(args);
    }
}
}

