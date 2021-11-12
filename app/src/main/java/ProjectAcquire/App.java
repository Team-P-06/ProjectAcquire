/**
 * @author Team 404
 * @version v0.0.1
 */
package ProjectAcquire;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main App class
 */
public class App extends Application {

    static FXController controller;
    /**
     * Starts the JavaFX UI. Visuals are found in MainMenu.fxml in the resources folder. Logic is in FXController.java.
     * @param stage default stage for application
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        FXController mainMenu = new FXController();
        mainMenu.showMainMenu();
    }

    /**
     * Main method to start the application
     * @param args
     */
    public static void main(String[] args) throws IOException {
        System.out.println("App works");

        //Start UI
        launch();

        //Initialize Game
//       Game game = new Game();
        //plays the game
       // game.runGame();


    }

}
