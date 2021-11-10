package ProjectAcquire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Controller for actions of FXMLUI.
 * All functions and variables that are used communicating with MainMenu.fxml need a @FXML proceeding them.
 */
public class FXController {
    /**
     * MainMenu UI logic and variables start here
     */
    /**
     * Components/variables for MainMenu
     */
    @FXML private Button newGameButton, loadGameMenuButton, exitGameButton;

    @FXML private Pane loadGameSelectionPane;
    @FXML private Label loadGameLabel;
    @FXML private Button loadGame1Button, loadGame2Button, loadGame3Button;

    @FXML private Label player;

    public void initialize(){}


    /**
     * Starting a new fresh game
     */
    @FXML
    private void newGame() throws IOException {
        gameMenu();
    }

    static FXController boardcontroller;
    /**
     * Start the in game menu UI.
     * @throws IOException
     */
    private void gameMenu() throws IOException {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GameBoard.fxml"));
        Parent root = loader.load();
        boardcontroller = (FXController) loader.getController();

        Scene boardScene = new Scene(root);

        stage.setScene(boardScene);
        stage.show();


    }

    /**
     * enables the menu for the user to select a game slot to load, this does not acutally load the game
     */
    @FXML
    private void loadGameMenu() {
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
     */
    @FXML
    private void exitGame() {
        exitGameButton.setText("Exiting");
        System.exit(0);
    }


    /**
     * **********************************************************************************************************
     * Game board UI logic and variables start here
     */
    /**
     * Components/variables for MainMenu
     */
    @FXML private Label playersLabel, playerMoneyLabel, playerStocksLabel, playersNetWorth;

    @FXML private Label hotelsLabel, hotelNameLabel, cHotelLabel, sHotelLabel, fHotelLabel, iHotelLabel, aHotelLabel, wHotelLabel, tHotelLabel, avaliableStocksLabel, stocksPurchasedLabel;
    /**
     * List of strings, All of these could be real objects in the future but strings work for now.
     */
    @FXML private ListView<String> hotelNameListView, availableStocksList, stocksPurchasedList;
    ObservableList<String> hotelNameObserList = FXCollections.observableArrayList();

    /**
     * List of integers along with it's observableList
     */
    @FXML private ListView<Integer> cStockListView, sStockListView, fStockListView, iStockListView, aStockListView, wStockListView, tStockListView;
    ObservableList<Integer> cStockObserListView, sStockObserListView, fStockObserListView, iStockObserListView, aStockObserListView, wStockObserListView, tStockObserListView
            = FXCollections.observableArrayList();

    /**
     * Lists for player information, both ListView and Observable
     */
    @FXML private ListView<String> playerNameList;
    @FXML private ListView<Integer> playerMoneyList;
    @FXML private ListView<Integer> playerNetList; // This and Tiles will be lists of objects in the future, but just strings for testing purposes now
    @FXML private ListView<String> playerTilesList;
    ObservableList<String> playerNameObserList = FXCollections.observableArrayList();
    ObservableList<Integer> playerMoneyObserList = FXCollections.observableArrayList();
    ObservableList<Integer> playerNetObserList = FXCollections.observableArrayList();
    ObservableList<String> playerTilesObserList = FXCollections.observableArrayList();

    /**
     * Various buttons on the GameBoard screen
     */
    @FXML private Button updatePlayerNamesButton, endTurnSubmitButton;

    /**
     * Updates the information(name, money, tile list) of players in the list
     * This is purely for tests. In reality all this data from the button press will be directly from GameState.
     * This function will not exist in the final build!
     */
    @FXML
    private void updatePlayers() throws IOException {
        Tile t1 = new Tile();
        Tile t2 = new Tile();
        List<Tile> pTilesList = new ArrayList<>();
        pTilesList.add(t1);
        pTilesList.add(t2);

        Player p1 = new Player("player 1", pTilesList, 400);
        Player p2 = new Player("player 2", pTilesList, 4000);
        LinkedList<Player> pList = new LinkedList<>();
        pList.add(p1);
        pList.add(p2);

        Board board = new Board();

        GameState gameState = new GameState(board, pList);
        gameState.update();
        //getLoader();
        updatePlayerInfo(pList);
        setHotelInformation();
    }

    /**
     * Takes a list of players and adds the data to the players list on the UI.
     * This is the update function to update player data.
     * @param playerList a list of players from GameState.
     */
    public void updatePlayerInfo(LinkedList<Player> playerList){
        playerNameObserList.clear();
        playerMoneyObserList.clear();
        playerNetObserList.clear();
        playerTilesObserList.clear();

        for (Player currentPlayer : playerList) {
            playerNameObserList.add(currentPlayer.getName());
            playerMoneyObserList.add(currentPlayer.getMoney());
            playerTilesObserList.addAll(getTileName(currentPlayer.getTileList()));
        }
        playerNameList.setItems(playerNameObserList);
        playerMoneyList.setItems(playerMoneyObserList);
        playerTilesList.setItems(playerTilesObserList);
    }

    /**
     * Might not need this in the future
     * Takes a list of Tiles and extracts its name(coordinates) into a list, so it can be put into a list easily
     * @param tileList a list of tiles
     * @return a list of tile coordinates
     */
    public List<String> getTileName(List<Tile> tileList){
        List<String> tileStringList = new ArrayList<>();
        for (Tile tile: tileList) {
            tileStringList.add(tile.getCoord());
        }
        return tileStringList;
    }

    private void setHotelInformation(){
        hotelNameObserList.add("Available");
        hotelNameObserList.add("Player 1");
        hotelNameObserList.add("Player 2");
        hotelNameObserList.add("Size");
        hotelNameObserList.add("Price");
        hotelNameListView.setItems(hotelNameObserList);
    }

}
