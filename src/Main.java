
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.*;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


 
public class Main extends Application {
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
      mainMenu = new Scene(root, 500, 550);
      Text t = new Text(80, 100, "Fast and Curious");
      t.setFont(new Font(50));
      root.getChildren().add(t);    

      //CREATE BUTTONS
       Button playButton = new Button("Play");
       Button leaderboardButton = new Button("Leaderboard");
       Button exitButton = new Button("Exit");

       playButton.setLayoutX(200);
       playButton.setLayoutY(260);
       playButton.setPrefSize(100,20);
       root.getChildren().add(playButton);

       leaderboardButton.setLayoutX(200);
       leaderboardButton.setLayoutY(300);
       leaderboardButton.setPrefSize(100,20);
       root.getChildren().add(leaderboardButton);

       exitButton.setLayoutX(200);
       exitButton.setLayoutY(340);
       exitButton.setPrefSize(100,20);
       root.getChildren().add(exitButton);
       
       
       //SET BUTTON ACTIONS
       playButton.setOnAction(e -> window.setScene(inGameScreen));
       leaderboardButton.setOnAction(e -> window.setScene(leaderboardScreen));
       exitButton.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
             System.exit(0);
            }
      });

      //INGAME SCREEN
      
      Pane game = new Pane();
      inGameScreen = new Scene(game, 500, 550);
      Button newButton = new Button("Back to Main Menu");
      newButton.setOnAction(e -> window.setScene(mainMenu));
      game.getChildren().add(newButton); //ADDED BUTTON TO TEST NAVIGATION 
      
      //PAUSE MENU
      
      //LEADERBOARD
      
      Pane leaderboard = new Pane();
      leaderboardScreen = new Scene(leaderboard, 500, 550);
      Button newButton2 = new Button("Return to Main Menu");
      newButton2.setOnAction(e -> window.setScene(mainMenu));
      leaderboard.getChildren().add(newButton2); //ADDED BUTTON TO TEST NAVIGATION 

      //SETUP/PROFILE

      


      //DEFAULT STARTUP SETTINGS

      
      //ICON
      window.getIcons().add(new Image("cars.jpg"));

      window.setTitle("Fast and Curious!");
      window.setScene(mainMenu);
      window.show();
   }
   
   public static void main(String[] args) {
        launch(args);
    }
}