/**
 * @author Team 404
 * @version v0.0.1
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
     *
     * @param isOver whether game is over or not
     */
    void setOver(boolean isOver) {
        this.isOver = isOver;
    }


    //Commented out by Alex. I am deprecating these methods, but I will temporarily make a nexTurn() method


    /**
     * For Show from Alex: This method may break your UI logic, feel free to edit.
     *
     * @return the player that is at the top of the list but is not currentPlayer
     */
    public Player nextTurn() {

        return playerList.peekFirst();

    }
    /**
     * If a player has a tile in their hand they can lay on the board let the player place the tile
     *
     * @return
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
        //2.a Lets the player flip a tile in their hand, removes that tile from the player's hand.
        //Interrupt call that lets the player choose which tile in hand to flip
        //Ie. Tile playerChosenTile = Interrupt() where interrupt makes the tileList clickable on screen, then returns the tile
        //referenced by the UI tile list position clicked.
        //chosenTile.flip()
        //player.getTileList().remove(chosenTile)
        updateNewTurn();

        //2.b The player then gets to buy stock from any of the companies.
        //this is a UI Interrupt too.
        //While (currentPlayer.getMoney() > lowest corporation stock value and flag == false)
        //{ Stock playerStockBuy = UI_stock_interrupt()
        // where UI_stock_interrupt() shows the player the stocks that they can buy, and then returns a stock if they choose to buy, otherwise returns null
        // if(playerStockBuy == null){then flag = true;}  because our player chose to not buy any stocks
        //else{ currentPlayer.buyStock(playerStockBuy);} stock that player has chosen is bought, added to stockList.
        //if the player has not chosen to stop buying tiles, should loop. (this isn't great code, but it is functional)
        // }

        //2.c step 2.a will cause checkForAction to need to be called, then either a charter, merge, or no action happens
        //so after 2.b runs, we call BoardLogic.checkForAction(currentBoard, playerStockBuy.getCoord()).
        //checkForAction should, based on a coord, get the coord above it, below it, and to the left and right of it,
        //then decide based on a switch block whether to call merge, charter, or nothing and come back to this method.
        //2.d any action caused by 2.c should be dealt with in a different method (merge, charter, etc), not in here.
        //3. Turn ends.

        /* Show's alternate (but really similar) approach
        1. Set up/ load game board
            1.a gameState.Update();

        2. CurPlayer places a tile
            2.a  UI makes the player's tileList clickable.
            2.b  UI interrupt, Tile in the player's hand that player clicks is returned.
            2.c  Then we invoke player.playTile(chosenTile). where chosenTile is the tile from 2.b
                -- which calls checkActionType()
                --if checkActionType() == "MERGE", then call merge().
                --else if checkActionType() == "ADD_TO_CURRENT_COMP", call tileIsAdjacent()
                --else if checkActionType() == "CHARTER". UI INTERRUPT, get company from player choice call charter(playerChoice)
                --
                -- If merge goto --> MERGE
            2.b gameState.Update();

        3. while(buysLeft == 3; buysLeft > 0; buysLeft-- && Player.money > lowest stock price)  {
            3.a gameState.UpdateBuyStock()
                -- UI updates available stock buying list.
            3.b: Player chooses a stock to buy
            3.c: player.buyStocks() is called
                -- If they have enough money update information accordingly (player, hotel)
                -- If they don't call gameState.notEnoughMoney();
                    -- UI sets a label telling them they don't have enough cash and calls gameState.UpdateBuyStock() again.
            3.d: gamestate.update()
            [End Turn button send something back to the while loop telling it to stop]
            }

        4. NextTurn()
            4.a gamestate.update()

        MERGE:
        1. Merge will change the data for tiles, businesses etc.

        for each player in playerList{
        3. while(currentPlayer.numberOfStockInDefunctCompany > 0){
            -- call gameState.mergeOptions(Company defunctCompany, Player currentPlayer)
                  -- populates list with sell, keep or trade for current defunct business
                  -- player click an option and a dialogue box pops up asking for a number
                  -- Player.checkValidInput(int). return true or false. If false change label to invalid input.. [This will need to check the player stock list and keep adding to an int to see if the player has enough stocks]
                  -- if true call Player.sell(int, Company), keep(int, Company) etc.}
                    -- Updates the data for player money, stocks etc and business information.
            }
        }
        4. go to step 3.

        MAIN DRAWBACK: is that we would have to pass around the current gameState a lot
         */

        System.out.println("GameState.playTurn() was finished");
    }

    /**
     * While we are waiting on the final UI hooks, we can use playerInputs as our interrupts
     */
    public void playTurnNoUI() throws IllegalArgumentException, IOException {

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
        //dealing cards
        //1. I think that we should maybe have a dealt tile list and undealt tilelist that are complements of each other
        // where their union is equivalent to the board tile list. Then we can keep track of tiles dealt

    }

    public void getCompanyChoice(Company playerChoice){
        // Returns the company that the player would like to charter and invokes the rest of the charter().
    }

    public void getMergeChoice(Company playerChoice){
        // Returns the company that the player would like to keep and continues with merge.
    }

    /**
     * Essencially the "playTile" method, since the interrupts need a gamestate it works better here.
     * This is called when you click a tile on the board.
     * @param tile tile that the player placed on the board.
     * @throws IOException
     */
    public void getTileChoice(Tile tile) throws IOException {
        int action = currentBoard.checkForActionInitiation(tile);
        if (action == 0) { // If not chartering then jump to buying stocks
            noCharter(tile);
        }
        if (action == 2){
            charterChoiceInterrupt();
        }
    }

    public void noCharter(Tile tile) throws IOException {
        List<Company> uc = currentBoard.getUncharteredCompanies();
        List<Company> cc = currentBoard.getCharteredCompanies();

        for (Company defaultCo : uc) {
            if (defaultCo.getCompanyName().equals("Default")) {
                tile.setCompany(defaultCo);
                currentPlayer.getTileList().remove(tile);
                tile.setFlipped();
                tile.setDealt(false);
                buyInterrupt();
            }
        }
        for (Company defaultCo : cc){
            if (defaultCo.getCompanyName().equals("Default")){
                tile.setCompany(defaultCo);
                currentPlayer.getTileList().remove(tile);
                tile.setFlipped();
                tile.setDealt(false);
                buyInterrupt();
            }
        }
    }


    /**
     * Overall update for when a player would like to place a new tile and the player actions on the bottom right have no actions.
     * This currently creates a new window, so every update makes a new window.
     * This is really gross but works for now while we implement other more important things.
     * @throws IOException
     */
    public void updateNewTurn() throws IOException {
        Update update = new Update();
        update.nextTurnUI(this);
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

