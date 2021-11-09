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
    private Player firstPlayer;
    private Player secondPlayer;
    private Board currentBoard;
    private List<Player> playerList;
    private boolean isOver = false;


    /**
     * Default constructor
     */
    public GameState(){}

    /**
     * Creates a GameSate for a game to be passed
     * @param firstPlayer
     * @param secondPlayer
     * @param currentBoard
     * @param playerList
     */
    public GameState(Player firstPlayer, Player secondPlayer, Board currentBoard, List<Player> playerList){
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
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
        if (firstPlayer == playerList.get(1)){
            return firstPlayer;
        }
        else{
            return secondPlayer;
        }
    }

    /**
     * If a player has a turn let the player play their turn
     * @param Player
     * @return
     */
    public boolean hasTurn(Player Player){
        if(Player == playerList.get(0)){
            return true;
        }
        else {
            return false;
        }
    }

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
        if(hasTurn(firstPlayer) == true){
            //code that can be done inside of playing the turn
            //switching the player order so every time a player finishes their turn they
            //get moved to the back of the "list"
            playerList.set(1,firstPlayer);
            playerList.set(0, secondPlayer);
        }
        if(hasTurn(secondPlayer) == true){
            //code that can be done inside of playing the turn
            //switching the player order so every time a player finishes their turn they
            //get moved to the back of the "list"
            playerList.set(1, secondPlayer);
            playerList.set(0,firstPlayer);
        }
    }
    /**
     * Set the new board to be a blank board
     * @param Board
     */
    public void setCurrentBoard(Board Board){
        this.currentBoard = Board;
    }

    /**
     * Getters for the players that are contained in the current player list
     * @return
     */
    public Player getFirstPlayer(){
        return  firstPlayer;
    }
    public Player getSecondPlayer(){
        return secondPlayer;
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

