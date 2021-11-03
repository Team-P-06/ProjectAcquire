/**
 *
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
    Player currentPlayer;
    Board currentBoard;
    List<Player> playerList;

    public void newGameState(){
        GameState currentGameState = new GameState();
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
