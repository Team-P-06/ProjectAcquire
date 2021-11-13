package ProjectAcquire;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;

/**
 * Controller for actions of FXMLUI.
 * All functions and variables that are used communicating with MainMenu.fxml need a @FXML proceeding them.
 */
public class FXController {
    /**
     * MainMenu UI logic and variables start here
     */
    /**
     * Buttons, pane and lables for MainMenu
     */
    @FXML private Button newGameButton, loadGameMenuButton, exitGameButton, loadGame1Button, loadGame2Button, loadGame3Button;
    @FXML private Pane loadGameSelectionPane;
    @FXML private Label loadGameLabel, player;

    /**
     * Main stage for the application
     */
    @Getter public final Stage mainStage = new Stage();

    /**
     * Creates and shows the Main menu where you can select to start, load, or exit.
     * @throws IOException
     */
    public void showMainMenu() throws IOException {
        FXMLLoader mainMenuLoader = new FXMLLoader(getClass().getResource("/MainMenu.fxml"));
        mainMenuLoader.setController(this);
        mainStage.setScene(new Scene(mainMenuLoader.load()));
        mainStage.show();
    }

    /**
     * Start the Board game menu where you can actually play the game.
     * @param loader the loaded scene that you'd like to start (game screen)
     * @throws IOException
     */
    public void showBoardMenu(FXMLLoader loader) throws IOException {
        mainStage.hide();
        mainStage.setScene(new Scene(loader.load()));
        mainStage.show();
    }

    /**
     * fetches the main game menu screen.
     * @return
     */
    public FXMLLoader getGameBoardLoader() {
        FXMLLoader boardMenuLoader = new FXMLLoader(getClass().getResource("/GameBoard.fxml"));
        boardMenuLoader.setController(this);
        return boardMenuLoader;
    }

    /**
     * Button actions to begin a new game.
     */
    @FXML
    private void newGame() throws IOException {
        Update update = new Update();
        Game newGame = new Game();

        showBoardMenu(getGameBoardLoader());

        GameState gameState = newGame.start();
        Company com = new Company();
        update.update(gameState);
    }

    /**
     * enables the menu for the user to select a game slot to load, this does not actually load the game yet
     */
    @FXML
    private void loadGameMenu() {
        loadGameSelectionPane.setVisible(true);
    }

    /**
     * Loads the game given a specific game load to load from.
     * Doesn't really load a game yet.
     * @param event
     */
    @FXML
    private void loadGame(ActionEvent event) {
        Object source = event.getSource();
        String file = null;
        if (loadGame1Button.equals(source)) {
            loadGame1Button.setText("G1 loaded");
            //file = "/Savefiles/Savefile1";
        } else if (loadGame2Button.equals(source)) {
            loadGame2Button.setText("G2 loaded");
            //file = "/Savefiles/Savefile1";
        } else if (loadGame3Button.equals(source)) {
            loadGame3Button.setText("G3 loaded");
            //file = "/Savefiles/Savefile1";
        }
        /*Update update = new Update();
        Game newGame = new Game();

        showBoardMenu(getGameBoardLoader());
        GameState curGameState = (gameState load from file);
        update.update(curGameState);*/
    }

    /**
     * Function corresponding
     */
    @FXML private void saveGame(){
        //Save the game
    }
    

    /**
     * Exits the application
     */
    @FXML
    private void exitGame() {
        System.exit(0);
    }

    /**
     * **********************************************************************************************************
     * Game board UI logic and variables start here
     * Miscellaneous labels and buttons on the UI,
     */
    @FXML private Label playersLabel, playerMoneyLabel, playerStocksLabel, playersNetWorth;
    @Getter @FXML private Label hotelsLabel, hotelNameLabel, cHotelLabel, sHotelLabel, fHotelLabel, iHotelLabel, aHotelLabel, wHotelLabel, tHotelLabel, actionLabel, stocksPurchasedLabel, TurnLabel, currentPlayerTurnLabel;

    /**
     * Grid for the board to place all the tiles on it.
     */
    @FXML @Getter public GridPane tileGrid;

    /**
     * Lists for the available companies that a player can buy stocks from
     */
    @Getter @FXML public ListView<Button> actionChoiceList;
    @Getter ObservableList<Button> actionChoiceObserList = FXCollections.observableArrayList();
    @Getter @FXML private Button SaveGameButton;
    @Getter @FXML private Pane mergePane;
    @Getter @FXML private Spinner<Integer> mergeSpinner;
    @Getter @FXML private TextField mergeTextField;
    @Getter @FXML private Label invalidInputLabel;

    /**
     * Lists for player information, both ListView and Observable
     */
    @Getter @FXML private ListView<String> playerNameList, playerTilesList;
    @Getter @FXML private ListView<Integer> playerMoneyList, playerNetList;

    @Getter ObservableList<String> playerNameObserList = FXCollections.observableArrayList();
    @Getter ObservableList<String> playerTilesObserList = FXCollections.observableArrayList();
    @Getter ObservableList<Integer> playerMoneyObserList = FXCollections.observableArrayList();
    @Getter ObservableList<Integer> playerNetObserList = FXCollections.observableArrayList();

    /**
     * Buttons and lists for hotel information
     */
    @Getter @FXML private ListView<String> hotelNameListView;
    @Getter ObservableList<String> hotelNameObserList = FXCollections.observableArrayList();
    @FXML private Button endTurnButton;

    /**
     * List of integers along with it's observableList for all seven companies.
     */
    @Getter @FXML private ListView<Integer> cStockListView, sStockListView, fStockListView, iStockListView, aStockListView, wStockListView, tStockListView;
    @Getter ObservableList<Integer> cStockObserListView = FXCollections.observableArrayList();
    @Getter ObservableList<Integer> sStockObserListView = FXCollections.observableArrayList();
    @Getter ObservableList<Integer> fStockObserListView = FXCollections.observableArrayList();
    @Getter ObservableList<Integer> iStockObserListView = FXCollections.observableArrayList();
    @Getter ObservableList<Integer> aStockObserListView = FXCollections.observableArrayList();
    @Getter ObservableList<Integer> wStockObserListView = FXCollections.observableArrayList();
    @Getter ObservableList<Integer> tStockObserListView = FXCollections.observableArrayList();

}