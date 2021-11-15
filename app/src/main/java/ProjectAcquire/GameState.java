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
import org.checkerframework.checker.units.qual.C;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
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
    private boolean isOver = false;


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
    public GameState(Board currentBoard, LinkedList<Player> playerList) {
        this.currentPlayer = playerList.get(0);
        this.currentBoard = currentBoard;
        this.playerList = playerList;
        // this.currentPlayer = playerList.peekFirst();
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
        //This section checks if we have loaded a game or if we are just starting.
        //sets our current player to be the first player of our list.
        //then removes from the front of the list, so that the second player should now be at the front of the list
        //Then adds the current player to the back of the list.
        try {
            if (currentPlayer == null) {
                setUpInitialTurn(); //if our game has just started, we need to initialize it.
            }

            //Looks at the current player, and then runs that players turn
            //1. Deals cards if less than 6 cards are in the player's hand
            while (currentPlayer.getTileList().size() < 6) {
                currentBoard.dealTile(currentPlayer);
            }
            } catch (Exception e) {
                e.printStackTrace();
        }
    }

    /**
     * Sets the next player as the current and puts the player who just went to the back of the list.
     * This is called after stocks are sold.
     */
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
    private void setUpInitialTurn() {
        currentPlayer = playerList.peekFirst();
    }

    /**
     * Loops until the player has bought 3 stocks or can't afford any more (or hits "end turn").
     * Currently, only allows the player to buy 1 stock.
     * Then it sets up the next players turn.
     * @throws IOException
     */
    private void buyStocksTurn() throws IOException {
        if(currentPlayer.getMoney() > currentBoard.getLowestStockPrice()) {
            buyInterrupt();
        }
        setNextTurn();
    }

    /**
     * Essentially the "playTile" method, since the interrupts need a gamestate it works better here.
     * This is called when you click a tile on the board.
     * Decides 1 of 4 actions based on how the tiles should be merged/created
     * @param tile tile that the player placed on the board.
     * @throws IOException
     */
    public void getTileChoice(Tile tile) throws Exception {
        int action = currentBoard.checkForActionInitiation(tile);
        //System.out.println(action);
        if (action == 0) { // If not chartering then jump to buying stocks
            noCharter(tile);
        }
        else if (action == 1){ // If we place a tile next to another empty tile.
            charterChoiceInterrupt(); // UI calls board.charter(playerChoice)
        }
        else if (action == 2){ // If we place a tile next to another company
            // currentBoard.updateCompanyTiles(); //Currently don't have information about the company that's
                                                  // increasing, would have to be done inside the function.

            getCurrentBoard().charterLogic(tile.getCompany()); //add flipped tile to adjacent company.

            buyStocksTurn(); // after the size is increased, let players buy stocks
        }
        else if( action == 3){ //If there is a merge started by the tile
            if (currentBoard.checkEqualsMerge() != null){ //If there are equal companies in a merge
                List<Company> choicelist = currentBoard.checkEqualsMerge();
                mergeChoiceInterrupt(choicelist); // Player choice will then call merge(winnerCompany selected)
            }
            else { //No equal companies
                Company defunctCo = currentBoard.getDefunctCompany();
                currentBoard.merge(defunctCo); // Merge the companies
                mergeInterrupt(defunctCo); // Start the player selling/trading/keeping stocks turn
            }
        }
    }

    /**
     * If no company is chartered, set it to the default company and remove the tile form the players hand.
     * @param tile
     * @throws IOException
     */
    public void noCharter(Tile tile) throws IOException {
        List<Company> uc = currentBoard.getUncharteredCompanies();
        List<Company> cc = currentBoard.getCharteredCompanies();
        for (Company defaultCo : uc) {
            if (defaultCo.getCompanyName().equals("DEFAULT")) {
                currentPlayer.getTileList().remove(tile);
                tile.setFlipped();
                tile.setDealt(false);
                buyStocksTurn();
            }
        }
        for (Company defaultCo : cc){
            if (defaultCo.getCompanyName().equals("DEFAULT")){
                currentPlayer.getTileList().remove(tile);
                tile.setFlipped();
                tile.setDealt(false);
                buyStocksTurn();
            }
        }
    }


    /**
     * Update for when a merge occurs, creates the options for players to sell, trade, or keep stocks.
     * This should be called mutable times to update every time for the "sub turn" during a merge.
     * @param defunctCompany the company that is being destroyed from the merge.
     * @throws IOException
     */
    public void mergeInterrupt(Company defunctCompany) throws IOException{
        Update update = new Update();
        update.mergeUI(this, defunctCompany);
    }

    /**
     * Update for after the player places a tile. Populates the action area with charted businesses.
     * Should be called after a playTile() and the board data is set.
     * @throws IOException
     */
    public void buyInterrupt() throws IOException {
        Update update = new Update();
        update.buyUI(this);
    }

    public void charterChoiceInterrupt() throws IOException {
        Update update = new Update();
        update.charterChoiceUI(this);
    }

    public void mergeChoiceInterrupt(List<Company> equalCompanies) throws IOException {
        Update update = new Update();
        update.mergeChoiceUI(this, equalCompanies);
    }
}