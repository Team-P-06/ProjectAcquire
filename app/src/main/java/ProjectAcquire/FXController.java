package ProjectAcquire;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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

    /**
     * Grid for the board to place all the tiles on it.
     */
<<<<<<< HEAD
    @FXML @Getter public GridPane tileGrid;

    /**
     * Lists for the list of avaliable stocks to purchase.
=======
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
            tileGrid.add(currentButton, calculateCol(tile.getCoord()[1]), calculateRow(tile.getCoord()[0])); //Screwed with by Alex

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
            Tile currentTile = new Tile(defaultCompany, new int[]{i,0}); //Screwed with by alex to make compile
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
    public String getTileCoord(Tile tile) {    //Screwed with by Alex to make compile. BREAKS BOARD UI, PLEASE FIX SHOW :(
        String tileString;
        int remainder;
        int mod = (tile.getCoord()[0] % 12) + 1;
        switch (remainder = (tile.getCoord()[0] / 12)) {
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
>>>>>>> 0b1ce7602575a420b26cbe0cbdc507705e3127f1
     */
    @FXML private ListView<String> stocksPurchasedList;
    @Getter @FXML private ListView<Button> availableStocksList;
    @Getter ObservableList<Button> availableStocksObserList = FXCollections.observableArrayList();


    /**
     * Lists for player information, both ListView and Observable
     * Net and Tiles may be lists of objects in the future, but just strings for testing purposes now
     */
    @Getter @FXML private ListView<String> playerNameList, playerTilesList;
    @Getter @FXML private ListView<Integer> playerMoneyList, playerNetList;

    @Getter ObservableList<String> playerNameObserList = FXCollections.observableArrayList();
    @Getter ObservableList<String> playerTilesObserList = FXCollections.observableArrayList();
    @Getter ObservableList<Integer> playerMoneyObserList = FXCollections.observableArrayList();
    @Getter ObservableList<Integer> playerNetObserList = FXCollections.observableArrayList();

    /**
     * Buttons and lists for the selection area (where you buy stocks)
     */
    @Getter @FXML private ListView<String> hotelNameListView;
    @Getter ObservableList<String> hotelNameObserList = FXCollections.observableArrayList();
    @FXML private Button endTurnSubmitButton;

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

     /*@FXML private Button populateButton;
        Player p1 = new Player("player 1", pTilesList, 400);
        Player p2 = new Player("player 2", pTilesList, 4000);
        Player p3 = new Player("player 3", pTilesList, 8000);

        //Alex NOTE: This pList is a duplicate of line 233
        LinkedList<Player> pList = new LinkedList<>();
        pList.add(p1);
        pList.add(p2);
        pList.add(p3);

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

        //ALEX NOTE: We should be able to
//        Game game = new Game();
//        GameState gameStateFX = game.start();

        gameState.update();




    }*.

    /*public void testPopulateButtonAction() throws IOException {
        Update update = new Update();
        update.populateTest();
    }*/
}