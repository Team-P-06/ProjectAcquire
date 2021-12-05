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
    public GameState() {
    }

    /**
     * Creates a GameSate for a game to be passed
     *
     * @param currentBoard
     * @param playerList
     */
    private GameState(Board currentBoard, LinkedList<Player> playerList) {
        this.currentPlayer = playerList.get(0);
        this.currentBoard = currentBoard;
        this.playerList = playerList;
    }

    //default getInstance
    public static GameState getInstance(){

        if(instance == null){
            instance = new GameState();
        }
        return instance;
    }

    /**
     *
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

    //Getters

    /**
     * @return true if the game is over
     */
    boolean isOver() {
        return this.isOver;
    }

    //Setters

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
    public void playTurn() throws IllegalArgumentException, IOException {
        try {
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



            } catch (Exception e) {
                e.printStackTrace();
        }
    }

    /**
     * Counts the number of tiles still able to be dealt to players
     * @return Numer of tiles left
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
     */
    @Generated
    public void setNextTurn() throws IOException {
        Player playerWhosTurnJustEnded = playerList.removeFirst();
        playerList.addLast(playerWhosTurnJustEnded);
        currentPlayer = playerList.peekFirst();
        Update update = new Update();
        playTurn();
        update.nextTurnUI(this);
    }

    /**
     * While we are waiting on the final UI hooks, we can use playerInputs as our interrupts
     */
    @Generated //not tested because the same method is tested above just without the counter
    public void playTurnNoUI() throws Exception {

        try {
            if (currentPlayer == null) {
                setUpInitialTurn(); //if our game has just started, we need to initialize it.
            }
            //Looks at the current player, and then runs that players turn
            //1. Deals cards if less than 6 cards are in the player's hand
//            for(int x =0;x<6;x++){
//                currentBoard.dealTile(currentPlayer);
//                System.out.println(currentPlayer.getTileList().size());
//    }
            int counter = 0;
                while (currentPlayer.getTileList().size() < 6 ) {
                    currentBoard.dealTile(currentPlayer);
                    counter++;
                    System.out.println(currentPlayer.getTileList().size());
                }
                
        } catch (Exception e) {
            e.printStackTrace();
        }
        //ALEX NOTE: Tried using scanners but something something you have to set up event handlers. So I hardcoded
        //a list of predestined player actions to test things instead.
        int[] preDestinedActions = {1,1};
        //Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        //System.out.println("Choose a tile from your hand. (1,2,3,4,5,6)");
       //String userInput = scanner.nextLine();  // Read user input
       // int inputInt = Integer.parseInt(userInput);
      //  Tile tileChosen = currentPlayer.getTileList().get(inputInt-1);

        Tile tileChosen = currentPlayer.getTileList().get(preDestinedActions[0]); //grabs the player's leftmost tile.
        //prints out the tile chosen. This tile will NOT show up in the UI at the moment, because this tile is going to be played
        //before the user even sees the UI (or a frame after).
        System.out.println(tileChosen);
        currentPlayer.placeTile(tileChosen);

        int actionType = currentBoard.checkForActionInitiation(tileChosen); //will return 0 if no action and 1 if we need to charter
        System.out.println("ACTION TYPE is: "+ actionType);
        //our player needs to choose a company to charter.
        if (actionType == 1){
            System.out.println("CHOOSE A COMPANY TO CHARTER FROM THE FOLLOWING LIST:");
            System.out.println("Continental (1), Tower (2)");
            Company companyChosen = currentBoard.getUncharteredCompanies().get(preDestinedActions[1]); //preDest chooses Continental
            //we have flipped our tile and chosen a company for it, so now we can assign it a company
            //and our charterLogic algorithm will deal with what happens next. (the consequences of this chartering)
            currentBoard.charter(companyChosen);
        }
        //A tile was placed but didn't cause a charter.
        if (actionType == 0){
            System.out.println("The chosen player tile is flipped is: " + tileChosen.isFlipped()); //Nothing in the UI to indicate that a tile is flipped currently
        }

        //sets our next player
        playerList.addLast(playerList.poll());
        currentPlayer = nextTurn();
        System.out.println("GameState.playTurnNoUI() was finished");

    }


    /**
     * Deals initial cards to players, and... what else does this method need to do for playTurn to be able to run?
     */
    public void setUpInitialTurn() {
        currentPlayer = playerList.peekFirst();
    }

    /**
     * Essentially the "playTile" method, since the interrupts need a gamestate it works better here.
     * This is called when you click a tile on the board.
     * Decides 1 of 4 actions based on how the tiles should be merged/created
     * @param tile tile that the player placed on the board.
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

    private void checkPermanent(){
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

    /**
     * Update for after the player places a tile. Populates the action area with charted businesses.
     * Should be called after a playTile() and the board data is set.
     * @throws IOException
     */
    @Generated
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

    public boolean getisOver(){
        return isOver;
    }
}