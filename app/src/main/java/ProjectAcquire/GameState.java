/**
 * @author Team 404
 * @version v0.0.1
 */
package ProjectAcquire;

import java.io.IOException;
import java.util.*;

import lombok.AccessLevel;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

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
       // this.currentPlayer = playerList.get(0);
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
        //sets our current player to be the first player of our list.
        //then removes from the front of the list, so that the second player should now be at the front of the list
        //Then adds the current player to the back of the list.
        if (currentPlayer == null){
            setUpInitialTurn(); //if our game has just started, we need to initialize it.
        }
        currentPlayer = playerList.poll();
        playerList.addLast(currentPlayer);





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
        //FXController controller = new FXController();
        //controller.updateAll(this);
    }
}

