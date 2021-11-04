/**
 * @author Team 404
 * @version v0.0.1
 */
package ProjectAcquire;

import java.util.*;

/**
 * GameSate class that contains a games current status
 */
public class GameState {
    /**
     * Variables needed to maintain a gamestate
     */
    private Player currentPlayer;
    private Player nextPlayer;
    private Board currentBoard;
    private List<Player> playerList;
    private boolean isOver = false;

    public GameState(){

    }


    /**
     * Creates a new GameSate for a new game to be passed
     * @param currentPlayer
     * @param nextPlayer
     * @param currentBoard
     * @param playerList
     */
    public GameState(Player currentPlayer, Player nextPlayer, Board currentBoard, List<Player> playerList){
        this.currentPlayer = currentPlayer;
        this.nextPlayer = nextPlayer;
        this.currentBoard = currentBoard;
        this.playerList = playerList;
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

    /**
     * Determines which player has the next turn available in the gamestate
     * @return
     */
    public Player nextTurn(){
        return currentPlayer;
    }

    /**
     * If a player has a turn let the player play their turn
     * @param Player
     * @return
     */
    public boolean hasTurn(Player Player){
        return true;
    }

    /**
     * If a player has a tile in their hand they can lay on the board let the player place the tile
     * @param availableTile
     * @return
     */
    public boolean hasTileToPlay(Tile availableTile){
        return true;
    }

    /**
     * Recursive play method that is called when a player decides to play their turn
     */
    public void playTurn(){}
}
