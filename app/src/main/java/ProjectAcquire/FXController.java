package ProjectAcquire;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

/**
 * Controller for actions of FXMLUI.
 * All functions and variables that are used communicating with MainMenu.fxml need a @FXML proceeding them.
 */
public class FXController {
    @FXML private Button newGameButton;
    @FXML private Button loadGameMenuButton;
    @FXML private Button exitGameButton;

    @FXML private Pane loadGameSelectionPane;
    @FXML private Label loadGameLabel;
    @FXML private Button loadGame1Button;
    @FXML private Button loadGame2Button;
    @FXML private Button loadGame3Button;


    public void initialize(){}

    /**
     * Starting a new fresh game
     * @param event The NewGame button is pressed.
     */
    @FXML
    private void newGame(ActionEvent event){
        newGameButton.setText("Game started");
    }

    /**
     * enables the menu for the user to select a game slot to load, this does not acutally load the game
     * @param event on "load game" click
     */
    @FXML
    private void loadGameMenu(ActionEvent event) {
        loadGameSelectionPane.setVisible(true);
    }

    /**
     * Loads the game given a specific game load to load from
     * @param event
     */
    @FXML
    private void loadGame(ActionEvent event) {
        Object source = event.getSource();
        if (loadGame1Button.equals(source)) {
            loadGame1Button.setText("G1 loaded");
        } else if (loadGame2Button.equals(source)) {
            loadGame2Button.setText("G2 loaded");
        } else if (loadGame3Button.equals(source)) {
            loadGame3Button.setText("G3 loaded");
        }
    }


    /**
     * Exits the application
     * @param event on "Exit Game" button click
     */
    @FXML
    private void exitGame(ActionEvent event) {
        exitGameButton.setText("Exiting");
        System.exit(0);
    }
}
