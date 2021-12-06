/**
 * MIT License
 *
 * Copyright (c) 2021 404
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * @author Team 404
 * @version v1.0.0
 */

package ProjectAcquire;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Getter;
import java.io.IOException;
import java.util.List;

/**
 * Controller for actions of FXML.
 * All functions and variables that are used communicating with MainMenu.fxml need a @FXML proceeding them.
 */
@Generated
public class FXController {
    /**
     * Buttons, pane and labels for MainMenu
     */
    @FXML private Button newGameButton, loadGameMenuButton, exitGameButton;
    @FXML private Label loadGameLabel, player;

    /**
     * Main stage for the application
     */
    @Getter public final Stage mainStage = new Stage();

    /**
     * Creates and shows the Main menu where you can select to start, load, or exit.
     * @throws IOException
     */
    @Generated
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
        Platform.setImplicitExit(false);
        mainStage.hide();
        mainStage.setScene(new Scene(loader.load()));
        mainStage.show();
    }

    /**
     * fetches the main game menu screen.
     * @return the FXMLLoader for loading a game scene
     */
    public FXMLLoader getGameBoardLoader() {
        FXMLLoader boardMenuLoader = new FXMLLoader(getClass().getResource("/GameBoard.fxml"));
        boardMenuLoader.setController(this);
        return boardMenuLoader;
    }

    /**
     * Button actions to begin a new game.
     * @param numOfPlayers the number of players
     */
    private void newGame(int numOfPlayers) throws Exception { //ALEX NOTE: This will act as our main method as written.
        Update update = new Update();
        Game newGame = Game.getInstance();

        showBoardMenu(getGameBoardLoader());

        GameState gameState = newGame.start(numOfPlayers);
        update.nextTurnUI(gameState);
    }

    /**
     * Shows the textbox for the number of players to play the game
     */
    @FXML
    private void showPlayerNumber(){
        playerChoicePane.setVisible(true);
        startGameButton.setOnAction(a -> {
            try {
                numberOfPlayersChoice(NumberOfPlayersTextField.getText());
            } catch (Exception e) {e.printStackTrace();}
        });
    }

    /**
     * Validates the player's choice of number of players is valid
     * @param playerInput the player's input
     * @throws Exception
     */
    private void numberOfPlayersChoice(String playerInput) throws Exception {
        if (playerInput.equals("")) { newGame(2); }

        else if (!checkInput(playerInput)){
            playerCountErrorLabel.setText("Invalid input");
        } else if (Integer.parseInt(playerInput) > 10){
            playerCountErrorLabel.setText("Too many player, only up to 10 people can play");
        } else if(Integer.parseInt(playerInput) < 2){
            playerCountErrorLabel.setText("Not enough player, at least 2 people need to play");
        }
        else{
           newGame(Integer.parseInt(playerInput));
        }
    }

    /**
     * Checks to make sure the players input is a integer
     * @param playerInput The players input from the text box
     * @return boolean if the input is an int or not
     */
    private boolean checkInput(String playerInput){
        if (playerInput.equals("")) {return true;}
        return playerInput.matches("[0-9]+");
    }

    /**
     * Loads the game given a game to load from.
     * This effectively mimics game.newGame, except all the data is from the loaded json file.
     */
    @FXML
    private void loadGame() throws Exception {
        Update update = new Update();
        IOManager ioManager = new IOManager();
        Game newGame = Game.getInstance();

        showBoardMenu(getGameBoardLoader());
        GameState loadedGame = ioManager.loadGame("./src/main/resources/SavedGames/SavedGame.txt");

        Board board = Board.getInstance(loadedGame.getCurrentBoard().getTileList(), loadedGame.getCurrentBoard().getUncharteredCompanies(),
                loadedGame.getCurrentBoard().getCharteredCompanies(), loadedGame.getCurrentBoard().getPlayerList());

        GameState gameState =  GameState.getInstance(board, loadedGame.getPlayerList());
        newGame.loadGame(loadedGame);
        update.nextTurnUI(gameState);
    }

    /**
     * Exits the application
     */
    @FXML
    private void exitGame() {
        System.exit(0);
    }

    /**
     * Shows the end game screen upon a player clicking "end game" when the conditions are met
     * @throws IOException
     */
    @FXML
    public void endGame() throws IOException {
        GameState gameState = GameState.getInstance();
        FXMLLoader endGameLoader = new FXMLLoader(getClass().getResource("/EndGame.fxml"));
        endGameLoader.setController(this);
        mainStage.setScene(new Scene(endGameLoader.load()));
        setWinner(gameState);
    }

    /**
     * Calculates the winner based on their net worth
     * @param gameState the current game's gamestate
     */
    @FXML
    private void setWinner(GameState gameState){
        UpdatePlayer netCalculator = new UpdatePlayer();

        List<Player> playerList = gameState.getPlayerList();
        String playerResult = "";
        Player winner = playerList.get(0);
        for(Player player : playerList){
            int playerNet = netCalculator.calculateNet(player);
            playerResult = (playerResult + player.getName() + "    $" + playerNet + "\n");
            if (netCalculator.calculateNet(winner) < playerNet){ winner = player; }
            playerResultLabel.setText(playerResult);
            winnerLabel.setText("Winner: " + winner.getName());
        }
    }


    /**
     * **********************************************************************************************************
     * All the FXML labels/buttons and such are found here
     */
    @FXML private Label playersLabel, playerMoneyLabel, playerStocksLabel, playersNetWorth;
    @Getter @FXML private Label hotelsLabel, hotelNameLabel, cHotelLabel, sHotelLabel, fHotelLabel, iHotelLabel, aHotelLabel, wHotelLabel, tHotelLabel, actionLabel, stocksPurchasedLabel, TurnLabel, currentPlayerTurnLabel;

    /**
     * Main menu player choice UI items
     */
    @FXML Label numberOfPlayersLabel;
    @Getter @FXML Label playerCountErrorLabel;
    @Getter @FXML Button startGameButton;
    @Getter @FXML TextField NumberOfPlayersTextField;
    @FXML Pane playerChoicePane;

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
    @Getter @FXML private Button endTurnButton;

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

    /**
     * Labels, pane and TextFields for buying stocks
     */
    @FXML @Getter private TextArea wBuyTextArea;
    @FXML @Getter private TextArea cBuyTextArea;
    @FXML @Getter private TextArea sBuyTextArea;
    @FXML @Getter private TextArea fBuyTextArea;
    @FXML @Getter private TextArea iBuyTextArea;
    @FXML @Getter private TextArea aBuyTextArea;
    @FXML @Getter private TextArea tBuyTextArea;

    @FXML @Getter private Pane wBuyPane;
    @FXML @Getter private Pane cBuyPane;
    @FXML @Getter private Pane sBuyPane;
    @FXML @Getter private Pane fBuyPane;
    @FXML @Getter private Pane iBuyPane;
    @FXML @Getter private Pane aBuyPane;
    @FXML @Getter private Pane tBuyPane;

    /**
     * Labels, pane and TextFields for merging stocks options
     *
     */
    @FXML @Getter private Label mergeSellLabel, mergeTradeLabel, mergeKeepLabel;
    @FXML @Getter private TextArea mergeSellTextArea;
    @FXML @Getter private TextArea mergeTradeTextArea;
    @FXML @Getter private TextArea mergeKeepTextArea;
    @FXML @Getter private Pane mergeOptionPane;

    /**
     * End game labels and panes
     */
    @Getter @FXML Label winnerLabel, playerResultLabel, resultLabel;
    @Getter @FXML Button exitGameEndLabel, homeGameEndLabel;
    @Getter @FXML private Button endGameButton;
}