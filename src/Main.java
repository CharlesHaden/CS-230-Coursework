/** 
 * 
 * 
 * @author Nim Man
*/
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.text.*;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;


 
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
       Button leaderboardButton = new Button("Leaderboard");
       Button exitButton = new Button("Exit");

       playButton.setLayoutX(300);
       playButton.setLayoutY(260);
       playButton.setPrefSize(100,20);
       root.getChildren().add(playButton);

       leaderboardButton.setLayoutX(300);
       leaderboardButton.setLayoutY(300);
       leaderboardButton.setPrefSize(100,20);
       root.getChildren().add(leaderboardButton);

       exitButton.setLayoutX(300);
       exitButton.setLayoutY(340);
       exitButton.setPrefSize(100,20);
       root.getChildren().add(exitButton);
       
       
       //SET BUTTON ACTIONS
       playButton.setOnAction(e -> window.setScene(setup));
       leaderboardButton.setOnAction(e -> window.setScene(leaderboardScreen));
       exitButton.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
             System.exit(0);
            }
      });

      //INGAME SCREEN

      Image imageDecline = new Image("pause.png");
      Button pauseButton = new Button();
      ImageView imageDeclineView = new ImageView(imageDecline);
      imageDeclineView.setFitHeight(20);
      imageDeclineView.setFitWidth(20);
      pauseButton.setGraphic(imageDeclineView);
      pauseButton.setPrefSize(100,20);
      pauseButton.setOnAction(e -> window.setScene(pauseMenu));
      

      Image straightTile = new Image("straight.PNG");
      Image cornerTile = new Image("corner.PNG");
      Image tShapedTile = new Image("tshaped.PNG");
      Image goalTile = new Image("goal.PNG");

      FloorTile[][] curboard = Board.getTiles();
      Group game = new Group();
      for (int i = 0; i < Board.getHeight() ; i++ ) {
         for (int j = 0; i < Board.getWidth(); j++) {
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
            
            imageview.setX(10+(i*10));
            imageview.setY(10+(j*10));
            imageview.setFitHeight(10); 
            imageview.setFitWidth(10); 
            imageview.setPreserveRatio(true);
            game.getChildren().add(imageview);
            
         }
      }
   
      
      
      inGameScreen = new Scene(game, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
      game.getChildren().add(pauseButton);


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
      Button newButton2 = new Button("Return to Main Menu");
      newButton2.setOnAction(e -> window.setScene(mainMenu));
      leaderboard.getChildren().add(newButton2); //ADDED BUTTON TO TEST NAVIGATION 

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

   
   
   public static void main(String[] args) {
        launch(args);
    }
}

