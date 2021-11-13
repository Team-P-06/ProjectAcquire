/**
 * @author Team 404
 * @version v0.0.1
 */
package ProjectAcquire;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.LinkedList;

/**
 * GameSate class that contains a games current status
 */
public class GameState{
    /**
     * Variables needed to maintain a gamestate
     */
    private @Getter Player currentPlayer;
    private @Getter Board currentBoard;
    private @Getter @Setter LinkedList<Player> playerList;
    private boolean isOver = false;


    /**
     * Default constructor
     */
    public GameState(){}

    /**
     * Creates a GameSate for a game to be passed
     * @param currentBoard
     * @param playerList
     */
    public GameState(Board currentBoard, LinkedList<Player> playerList){
        this.currentPlayer = playerList.get(0);
        this.currentBoard = currentBoard;
        this.playerList = playerList;
      // this.currentPlayer = playerList.peekFirst();
    }

    //Getters

    /**
     *
     * @return true if the game is over
     */
    boolean isOver(){
        return this.isOver;
    }

    //Setters

    /**
     * Sets whether the game is done or not
     * @param isOver whether game is over or not
     */
    void setOver(boolean isOver){
        this.isOver = isOver;
    }


    //Commented out by Alex. I am deprecating these methods, but I will temporarily make a nexTurn() method


    /**
     * For Show from Alex: This method may break your UI logic, feel free to edit.
     * @return the player that is at the top of the list but is not currentPlayer
     */
    public Player nextTurn(){

        return playerList.peekFirst();

    }

//    /**
//     * Determines which player has the next turn available in the gamestate
//     * @return
//     */
//    public Player nextTurn(){
//        if (firstPlayer == playerList.get(1)){
//            return firstPlayer;
//        }
//        else{
//            return secondPlayer;
//        }
//    }
//
//    /**
//     * If a player has a turn let the player play their turn
//     * @param player
//     * @return
//     */
//    public boolean hasTurn(Player player){
//        if(player == playerList.get(0)){
//            return true;
//        }
//        else {
//            return false;
//        }
//    }

    /**
     * If a player has a tile in their hand they can lay on the board let the player place the tile
     * @return
     */
    public boolean hasTileToPlay(Player playerTilePlace){
        if(playerTilePlace.getTileList() != null){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * recursively called play method that is called when a player decides to play their turn
     */
    public void playTurn(){

        //This section checks if we have loaded a game or if we are just starting.
        //sets our current player to be the first player of our list.
        //then removes from the front of the list, so that the second player should now be at the front of the list
        //Then adds the current player to the back of the list.
        if (currentPlayer == null){
            setUpInitialTurn(); //if our game has just started, we need to initialize it.
        }
        currentPlayer = playerList.poll();
        playerList.addLast(currentPlayer);


        //Looks at the current player, and then runs that players turn

        //1. Deals cards if less than 6 cards are in the player's hand

        while (currentPlayer.getTileList().size()<6){
            currentPlayer.drawTile();
        }
        //2.a Lets the player flip a tile in their hand, removes that tile from the player's hand.
             //Interrupt call that lets the player choose which tile in hand to flip
             //Ie. Tile playerChosenTile = Interrupt() where interrupt makes the tileList clickable on screen, then returns the tile
             //referenced by the UI tile list position clicked.
             //playerChosenTile.flip()
             //currentPlayer.discardTile()

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
            2.a This invokes player.playTile(Tile).
                -- which calls checkAction()
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
            [End Turn button send somthing back to the while loop telling it to stop]
            }

        4. NextTurn()
            4.a gamestate.update()

        MERGE:
        1. Merge will change the data for tiles, businesses etc.

        for each player in playerList{
        3. while(currentPlayer.numberOfStockInDefunctCompany > 0){
            -- call gameState.mergeOptions(Company defunctCompany, Player currentPlayer)
                  -- populates list with sell, keep or trade for current defunct business
                  -- player click an option and a dialoge box pops up asking for a number
                  -- Player.checkValidInput(int). return true or false. If false change label to invalid input.. [This will need to check the player stock list and keep adding to an int to see if the player has enough stocks]
                  -- if true call Player.sell(int, Company), keep(int, Company) etc.}
                    -- Updates the data for player money, stocks etc and buesiness information.
            }
        }
        4. go to step 3.

        MAIN DRAWBACK: is that we would have to pass around the current gameState a lot
         */
    }

    /**
     * Deals initial cards to players, and... what else does this method need to do for playTurn to be able to run?
     */
    private void setUpInitialTurn(){

        //dealing cards
        //1. I think that we should maybe have a dealt tile list and undealt tilelist that are complements of each other
        // where their union is equivalent to the board tile list. Then we can keep track of tiles dealt

    }


    /**
     * Currently doesn't update the player information. (but all the logic works and the data is passed)
     * It doesn't change anything for the current scene.
=======
     * Updates the UI with the current GameState data.
     * This currently creates a new window, so every update makes a new window.
     * This is really gross but works for now while we implement other more important things.
>>>>>>> feature/UILogic
     * @throws IOException
     */
    //@Override
    public void update() throws IOException {
        Update update = new Update();
        update.update(this);
    }

    public void Merge(Company defunctCompany) throws  IOException{
        Update update = new Update();
        update.mergeUI(this, defunctCompany);
    }
}

