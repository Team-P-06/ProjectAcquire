package ProjectAcquire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

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
    public final Stage mainStage = new Stage();

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
        mainStage.hide();
        FXMLLoader boardMenuLoader = new FXMLLoader(getClass().getResource("/GameBoard.fxml"));
        boardMenuLoader.setController(this);
        return boardMenuLoader;
    }

    /**
     * Button actions to begin a new game.
     */
    @FXML
    private void newGame() throws IOException {
        showBoardMenu(getGameBoardLoader());
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
        System.exit(0);
    }

    /**
     * **********************************************************************************************************
     * Game board UI logic and variables start here
     * Miscellaneous labels and buttons on the UI,
     */
    @FXML private Label playersLabel, playerMoneyLabel, playerStocksLabel, playersNetWorth;
    @FXML private Label hotelsLabel, hotelNameLabel, cHotelLabel, sHotelLabel, fHotelLabel, iHotelLabel, aHotelLabel, wHotelLabel, tHotelLabel, avaliableStocksLabel, stocksPurchasedLabel;
    @FXML private Button populateTestButton;
    /**
     * Main update logic that branches out and updates different parts of the UI(Player data, Board, Company stocks, and stock options
     */
    public void updateAll(GameState gameState) throws IOException {
        mainStage.hide();
        showBoardMenu(getGameBoardLoader());
        updateBoard();
        setHotelInformation();
        updatePlayerInfo(gameState.getPlayerList(), gameState.nextTurn());
        updateChoiceInfo(gameState);
    }


    /**
     * Grid for the board to place all the tiles on it.
     */
    @FXML private GridPane tileGrid;
    /**
     * Creates 108 buttons and assigns them to a place on the grid with colors and name.
     * If our tiles have a name/int from 0 - 107 associated with them you can find the respective col/row by such
     * row = n/12 (round down)
     * col = n % 12
     * The list of tiles will need to pull from the tile pool and players pool to properly color and associate them with a company.
     */
    private void updateBoard() {
        List<Tile> tilesOnBoard = new ArrayList<>();
        tilesOnBoard = createTestTile();
        for (Tile tile: tilesOnBoard) {
            Button currentButton = new Button();
            currentButton.setMinSize(40, 45);
            currentButton.setText(getTileCoord(tile));
            currentButton.setStyle("-fx-background-color: 000000");
            GridPane.setHalignment(currentButton, HPos.CENTER);
            tileGrid.add(currentButton, calculateCol(tile.getCoord()), calculateRow(tile.getCoord()));

        }
    }

    /**
     * List of test tiles to fill the board.
     * In the final build the tiles will be build when the game starts and that data will then be sent to FXController.
     * NOT A FUNCTION IN THE FINAL BUILD
     * @return a list of tiles.
     */
    private List<Tile> createTestTile(){
        List<Tile> listOfTiles = new ArrayList<>();
        Company defaultCompany = new Company();
        for (int i = 0; i <= 107; i++){
            Tile currentTile = new Tile(defaultCompany, i);
            listOfTiles.add(currentTile);
        }
        return listOfTiles;
    }

    /**
     * Gets the row if we count from 0 - 107 and the tiles start from the top left
     * Int division always rounds towards zero
     * @param n number of the tile
     * @return row
     */
    private int calculateRow(int n){
        return n/12;
    }

    /**
     * remainder of tile / 12 is the column
     * @param n  number of the tile
     * @return column
     */
    private int calculateCol(int n){
        return n%12;
    }

    /**
     * Extracts the string names of the tiles in the list of tiles
     * @param tile the tile you'd like to get the name of.
     * @return A coordinate name of the unique tile.
     */
    public String getTileCoord(Tile tile) {
        String tileString;
        int remainder;
        int mod = (tile.getCoord() % 12) + 1;
        switch (remainder = (tile.getCoord() / 12)) {
            case 0 -> tileString = ("A" + mod);
            case 1 -> tileString = ("B" + mod);
            case 2 -> tileString = ("C" + mod);
            case 3 -> tileString = ("D" + mod);
            case 4 -> tileString = ("E" + mod);
            case 5 -> tileString = ("F" + mod);
            case 6 -> tileString = ("G" + mod);
            case 7 -> tileString = ("H" + mod);
            case 8 -> tileString = ("I" + mod);
            default -> tileString = ("0" + mod);
        }
        return tileString;
    }



//        Player p1 = new Player("player 1", pTilesList, 400);
//        Player p2 = new Player("player 2", pTilesList, 4000);
//        LinkedList<Player> pList = new LinkedList<Player>();
//        pList.add(p1);
//        pList.add(p2);


    /**
     * Lists for the .
     */
    @FXML private ListView<String> stocksPurchasedList;
    @FXML private ListView<Button> availableStocksList;
    ObservableList<Button> availableStocksObserList = FXCollections.observableArrayList();





//        Board board = Board.getInstance();
//        GameState gameState = new GameState(board, pList);
//        gameState.update();
//        //getLoader();
//        updatePlayerInfo(pList);
//        setHotelInformation();


    /**
     * Lists for player information, both ListView and Observable
     * Net and Tiles may be lists of objects in the future, but just strings for testing purposes now
     */
    @FXML private ListView<String> playerNameList, playerTilesList;
    @FXML private ListView<Integer> playerMoneyList, playerNetList;

    ObservableList<String> playerNameObserList = FXCollections.observableArrayList();
    ObservableList<String> playerTilesObserList = FXCollections.observableArrayList();
    ObservableList<Integer> playerMoneyObserList = FXCollections.observableArrayList();
    ObservableList<Integer> playerNetObserList = FXCollections.observableArrayList();

    /**
     * Updates the information about the players, e.g. money, net worth, and tile hand
     * Tile hand with swap every turn to represent the current players hand.
     * TileList needs to loop to extract the tile coordinates and put them into the string list
     */

    private void updatePlayerInfo(LinkedList<Player> playerList, Player currentPlayer) {
        playerNameObserList.clear();
        playerMoneyObserList.clear();
        playerNetObserList.clear();
        playerTilesObserList.clear();

        for (Player currentPlayerData : playerList) {
            playerNameObserList.add(currentPlayerData.getName());
            playerMoneyObserList.add(currentPlayerData.getMoney());
            playerNetObserList.add(currentPlayerData.getMoney()); // WIll need a net calculation
        }

        for (Tile tile: currentPlayer.getTileList()) {
            playerTilesObserList.add(getTileCoord(tile));
        }
        playerNameList.setItems(playerNameObserList);
        playerMoneyList.setItems(playerMoneyObserList);
        playerNetList.setItems(playerNetObserList);
        playerTilesList.setItems(playerTilesObserList);

    }

    /**
     * Buttons and lists for the selection area (where you buy stocks)
     */
    @FXML private ListView<String> hotelNameListView;
    ObservableList<String> hotelNameObserList = FXCollections.observableArrayList();
    @FXML private Button endTurnSubmitButton;

    /**
     * Simply populates the name headers for the table of stocks.
     */
    private void setHotelInformation() {
        hotelNameObserList.add("Available");
        hotelNameObserList.add("Player 1");
        hotelNameObserList.add("Player 2");
        hotelNameObserList.add("Size");
        hotelNameObserList.add("Price");
        hotelNameListView.setItems(hotelNameObserList);
    }

    /**
     * Updates the list of companies that you can buy stocks from.
     * @param gameState the current gamestate to put data into the UI.
     */
    private void updateChoiceInfo(GameState gameState) {
        List<Company> companyList = gameState.getCurrentBoard().getCharteredCompanies();
        for (Company curCompany : companyList) {
            Button currentCompanyButton = makeCompanyButton(curCompany, gameState.nextTurn());
            availableStocksObserList.add(currentCompanyButton);
        }
        availableStocksList.setItems(availableStocksObserList);
    }

    /**
     * Makes a transparent button and puts it in the list
     * @param company the company that the button is for
     * @return returns a button so it can be put into the observable list
     */
    private Button makeCompanyButton(Company company, Player currentPlayer){
        Button companyButton = new Button(company.getCompanyName());
        companyButton.setStyle("-fx-background-color: FFFFFF");
        //setButtonAction(companyButton, currentPlayer);
        return companyButton;
    }

    /**
     * Logic for the button to purchase a stock for that specific company.
     * Will make this once we have stock.buyStock() is fleshed out
     * @param button
     */
    // I need to get the current player and relate the name of the button they cicked with the real company
    // Then call current player.buyStock(); and that call in turn eventually calls gamestate.update() again.
    /*private void setButtonAction(Button button, Player currentPlayer){
        button.setOnAction( -> {
            currentPlayer.buyStock();
        });
    }*/


    /**
     * List of integers along with it's observableList for all seven companies.
     */
    @FXML private ListView<Integer> cStockListView, sStockListView, fStockListView, iStockListView, aStockListView, wStockListView, tStockListView;
    ObservableList<Integer> cStockObserListView = FXCollections.observableArrayList();
    ObservableList<Integer> sStockObserListView = FXCollections.observableArrayList();
    ObservableList<Integer> fStockObserListView = FXCollections.observableArrayList();
    ObservableList<Integer> iStockObserListView = FXCollections.observableArrayList();
    ObservableList<Integer> aStockObserListView = FXCollections.observableArrayList();
    ObservableList<Integer> wStockObserListView = FXCollections.observableArrayList();
    ObservableList<Integer> tStockObserListView = FXCollections.observableArrayList();

    /**
     * Updates the current states of all the companies stocks.
     * need stocks for variables of Available, P1, P2, size, and price for each company.
     */
    private void updateStockInfo() {
    }

    /**
     * This just initializes the data to be fed to GameState() just so it can turn around and call UpdateAll().
     * This is purely for tests. In reality all this data from this function press will be directly from GameState.
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
        //Alex NOTE: This pList is a duplicate of line 233
        LinkedList<Player> pList = new LinkedList<>();
        pList.add(p1);
        pList.add(p2);

        List<Company> charteredCom = new ArrayList<>();
        Company com1 = new Company("Company 1", 100, true, false);
        Company com2 = new Company("Company 2", 400, true, false);
        Company com3 = new Company("Company 3", 900, true, false);
        charteredCom.add(com1);
        charteredCom.add(com2);
        charteredCom.add(com3);

        Board board = Board.getInstance();
        board.setCharteredCompanies(charteredCom);

        GameState gameState = new GameState(board, pList);

        gameState.update();
    }

}