/**
 * @author Team 404
 * @version v0.0.1
 */
package ProjectAcquire;

import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.*;

/**
 * GameSate class that contains a games current status
 */
public class GameState implements Updatable{
    /**
     * Variables needed to maintain a gamestate
     */
    private Player currentPlayer;
    private Board currentBoard;
    private LinkedList<Player> playerList;
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


    //Commented out by Alex. I am deprecating these methods.

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
     * Recursive play method that is called when a player decides to play their turn
     */
    public void playTurn(){
        //sets our current player to be the first player of our list.
        //then removes from the front of the list, so that the second player should now be at the front of the list
        //Then adds the current player to the back of the list.
        currentPlayer = playerList.poll();
        playerList.addLast(currentPlayer);

        

    }
    /**
     * Set the new board to be a blank board
     * @param Board
     */
    public void setCurrentBoard(Board Board){
        this.currentBoard = Board;
    }


    /**
     * Currently doesn't update the player information. (but all the logic works and the data is passed)
     * It doesn't change anything for the current scene.
     * @throws IOException
     */
    @Override
    public void update() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GameBoard.fxml"));
        loader.load();
        FXController controller = loader.getController();
        controller.updatePlayerInfo(playerList);
    }
}

