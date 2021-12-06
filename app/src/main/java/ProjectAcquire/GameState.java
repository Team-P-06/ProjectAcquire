/**
 * GameState.java
 *
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
 * @version v1.1.0
 */

package ProjectAcquire;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

/**
 * GameSate class that contains a games current status
 */
public class GameState {
    /**
     * Variables needed to maintain a gamestate
     */
    @Getter private Player currentPlayer;
    @Getter private Board currentBoard;
    @Getter @Setter private  LinkedList<Player> playerList;
    private boolean isOver;
    private static GameState instance;

    /**
     * Default constructor
     */
    private GameState() {

    }

    /**
     * Creates a GameSate for a game to be passed
     *
     * @param currentBoard the board class that instantiated all our objects
     * @param playerList the list of players in our game
     */
    public GameState(Board currentBoard, LinkedList<Player> playerList) {
        this.currentPlayer = playerList.get(0);
        this.currentBoard = currentBoard;
        this.playerList = playerList;
    }

    /**
     * Default get instance for singleton
     * @return the single instance of GameState
     */
    @Generated
    public static GameState getInstance(){

        if(instance == null){
            instance = new GameState();
        }
        return instance;
    }

    /**
     * Gets the single instance of GameState
     * @param currentBoard The current Board
     * @param playerList The current list of players
     * @return A GameState instance
     */
    public static GameState getInstance(Board currentBoard,LinkedList<Player> playerList){

        if(instance == null){
            instance = new GameState(currentBoard,playerList);
        }
        return instance;
    }

    /**
     * @return true if the game is over
     */
    boolean isOver() {
        return this.isOver;
    }

    /**
     * Sets whether the game is done or not
     * @param isOver whether game is over or not
     */
    void setOver(boolean isOver) {
        this.isOver = isOver;
    }

    /**
     * Sets the next players turn
     * @return the player that is at the top of the list but is not currentPlayer
     */
    public Player nextTurn() {
        return playerList.peekFirst();
    }

    /**
     * If a player has a tile in their hand they can lay on the board let the player place the tile
     * @return true if the player can place a tile
     */
    public boolean hasTileToPlay(Player playerTilePlace) {
        if (playerTilePlace.getTileList() != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * recursively called play method that is called when a player decides to play their turn
     */
    public void playTurn() {

            if (currentPlayer == null) {
                setUpInitialTurn(); //if our game has just started, we need to set up the current player.
            }

            //Deals cards if less than 6 cards are in the player's hand
            while (currentPlayer.getTileList().size() < 6 && tilesLeft() > 0) {
                currentBoard.dealTile(currentPlayer);
            }

            //helper loop (extremely inefficient) that checks specifically for tiles that haven't been added to a company right
            for(Company comp: getCurrentBoard().getCharteredCompanies()){
                getCurrentBoard().addToCompLogic(comp); //make sure all chartered companies have adjacent flipped tiles added properly
            }

    }

    /**
     * Counts the number of tiles still able to be dealt to players
     * @return Number of tiles left to deal to players
     */
    private int tilesLeft(){
        int numOfTilesLeft = 108;
        for(Tile tile : currentBoard.getTileList()){
            if (tile.isDead() == true || tile.isFlipped() == true || tile.isDealt() == true){
                numOfTilesLeft--;
            }
        }
        return numOfTilesLeft;
    }

    /**
     * Sets the next player as the current and puts the player who just went to the back of the list.
     * This is called after stocks are sold.
     *
     * ALEX NOTE: We cannot test this due to UI elements, Ie. the update call.
     */

    @Generated
    public void setNextTurn() {
        Player playerWhosTurnJustEnded = playerList.removeFirst();
        playerList.addLast(playerWhosTurnJustEnded);
        currentPlayer = playerList.peekFirst();
        Update update = new Update();
        playTurn();
        update.nextTurnUI(this);

    }



    /**
     * Sets up the first player in the list to be the player to go first
     */
    public void setUpInitialTurn() {
        currentPlayer = playerList.peekFirst();
    }

    /**
     * Essentially the "playTile" method, since the interrupts need a gamestate it works better here.
     * This is called when you click a tile on the board.
     * Decides 1 of 4 actions based on how the tiles should be merged/created
     * @param tile tile that the player placed on the board.
     * @param player the player who played the tile
     * @throws IOException
     */
    @Generated
    public void getTileChoice(Tile tile, Player player) throws Exception {
        player.placeTile(tile);
        checkPermanent();

        int action = currentBoard.checkForActionInitiation(tile);

        if (action == 0) { // If not chartering then jump to buying stocks
            playTurn();
            buyStocksInterrupt();
        }
        else if (action == 1){ // If we place a tile next to another empty tile.
            playTurn();
            if(currentBoard.getUncharteredCompanies().size() > 1) { // If there are companies to charter (DEFAULT is always uncharted)
                CompanyLedger.getInstance().setCharterTile(tile);
                charterChoiceInterrupt(); // UI calls addToACompany -- > board.charter(playerChoice) --> buyInterrupt
            }
            else{ buyStocksInterrupt(); }
        }
        else if (action == 2){ // If we place a tile next to another company
            playTurn();
            Company adjComp = new Company();
            for(Company com: currentBoard.companiesAroundTile(tile)){
                if(!com.getCompanyName().equals("DEFAULT")){
                    adjComp = com;
                }
            }

            //This makes sure the reference to the company is correct when loading a game. Doesn't affect new games.
            for(Company com : currentBoard.getCharteredCompanies()){
                if (com.getCompanyName().equals(adjComp.getCompanyName())){
                    adjComp.setNumTiles(com.getNumTiles());
                    currentBoard.getCharteredCompanies().remove(com);
                    break;
                }
            }
            currentBoard.getCharteredCompanies().add(adjComp);
            buyStocksInterrupt();
        }
        else if( action == 3){ //If there is a merge action needed
            CompanyLedger.getInstance().setCharterTile(tile);
            List<Company> companiesAroundTile = currentBoard.companiesAroundTile(tile);
            mergeInterrupt(companiesAroundTile);
        }
        else if ( action == 4){ // A dead tile was set
            tile.setDead(true);
            tile.setFlipped(false);
            tile.setDealt(false);
            player.getTileList().remove(tile);
            playTurn();
            Update update = new Update();
            update.nextTurnUI(this);
        }
    }

    @Generated
    /**
     * Check to see if a company inside the players company list is permanent
     */
    public void checkPermanent(){
        for (Company com : currentBoard.getCharteredCompanies()) {
            if (com.getNumTiles() > 10){
                com.setPermanent(true);
            }
        }
    }

    /**
     * This is called by the UI after the player chose the company to charter.
     * This should call charter that adds that tile to the company next to it then start the buy phase.
     * @param company What company was chartered
     * @throws Exception
     */
    @Generated
    public void addToACompany(Company company) throws Exception {
        currentBoard.charter(company);
        buyStocksInterrupt();
    }

    /**
     * Lets the current player buy up to 3 stocks if they have enough money.
     * Then it sets up the next players turn.
     * @throws IOException
     */
    @Generated
    private void buyStocksInterrupt() throws IOException {
        if(currentPlayer.getMoney() > currentBoard.getLowestStockPrice()) {
            buyInterrupt();
        }
        else{
            setNextTurn();
        }
    }

    /**
     * Lets a player choose the winner company if there is an equal, merges the companies and starts the sell phase.
     * @param mergingCompanies the companies that are being merged
     * @throws IOException
     */
    @Generated
    public void mergeInterrupt(List<Company> mergingCompanies) throws IOException{
        Update update = new Update();
        update.mergeUI(this, mergingCompanies);
    }


    @Generated
    /**
     * Update for after the player places a tile. Populates the action area with charted businesses.
     * Should be called after a playTile() and the board data is set.
     * @throws IOException
     */
    public void buyInterrupt() throws IOException {
        Update update = new Update();
        update.buyUI(this);
    }

    /**
     * Shows the options for a player to choose what company they would like to charter
     * @throws IOException
     */
    @Generated
    public void charterChoiceInterrupt() throws IOException {
        Update update = new Update();
        update.charterChoiceUI(this); //passes in the tile that caused the charter
    }

    @Generated
    /**
     * Check to see if the game is over
     * @return true or false based on if the game is over
     */
    public boolean getisOver(){
        return isOver;
    }

    /**
     * Sets the instance of the gameState to null
     */
    @Generated
    public void setNull(){
        instance = null;
    }
}