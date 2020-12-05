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



public class Controller {
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
}