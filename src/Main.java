import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
 
public class Main extends Application {
   Stage window;
   Scene mainMenu;
   Scene pauseMenu;
   Scene profile;
   Scene leaderboard;
   Scene inGameScreen;

    @Override
    public void start(Stage window) {

      //MAIN MENU
      Pane root = new Pane(); 
      Text t = new Text();
       t.setX(80);
       t.setY(100);
       t.setFont(new Font(50));
       t.setText("Fast and Curious");

      //CREATE BUTTONS
       Button playButton = new Button("Play");
       Button leaderboardButton = new Button("Leaderboard");
       Button exitButton = new Button("Exit");

       playButton.setLayoutX(200);
       playButton.setLayoutY(260);
       root.getChildren().add(playButton);
       leaderboardButton.setLayoutX(200);
       leaderboardButton.setLayoutY(300);
       root.getChildren().add(leaderboardButton);
       exitButton.setLayoutX(200);
       exitButton.setLayoutY(340);
       root.getChildren().add(exitButton);
       root.getChildren().add(t);
       
       mainMenu = new Scene(root, 500, 550);
       
       //SET BUTTON ACTIONS
       playButton.setOnAction(e -> window.setScene(inGameScreen));
       leaderboardButton.setOnAction(e -> window.setScene(inGameScreen));
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


      //PROFILE


      //DEFAULT STARTUP SETTINGS
      window.setTitle("Fast and Curious!");
      window.setScene(mainMenu);
      window.show();
   }
   public static void main(String[] args) {
        launch(args);
    }
}