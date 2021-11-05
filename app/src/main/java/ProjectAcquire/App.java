/**
 * @author Team 404
 * @version v0.0.1
 */
package ProjectAcquire;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main App class
 */
public class App extends Application {

    /**
     * Starts the JavaFX UI. Visuals are found in MainMenu.fxml in the resources folder. Logic is in FXController.java.
     * @param stage default stage for application
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/MainMenu.fxml"));

        Scene mainScene = new Scene(root);

        stage.setScene(mainScene);
        stage.show();
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
      //  Game game = new Game();
        //game.start();

        //plays the game
       // game.runGame();

    }

}
