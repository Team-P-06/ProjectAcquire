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
    private Player nextPlayer;
    private Board currentBoard;
    private List<Player> playerList;
    private boolean isOver = false;


    /**
     * Default constructor
     */
    public GameState(){}
    /**
     * Create a new game with the starting player, board, and company attributes
     */
    //Is this constructor still needed? Since all this is all done in Game.start() already. - Show
     /*public void GameState(){
        /**
         * Add a new player 1 and save player 1 to the current gamestate
         */
       //Player newCurrentPlayer = new Player();
       //setCurrentPlayer(newCurrentPlayer);
        /**
         * Add a new player 2 and save player 2 to the current gamestate
         */
        //Player newNextPlayer = new Player();
        //setNextPlayer(newNextPlayer);
        /**
         * Create a new blank board and save that as the current board until a turn is played
         */
        //Board blankBoard = new Board();
       // setCurrentBoard(blankBoard);
        /**
         * Make sure the player list is empty and then add a current player(1) and next player(2)
         */
        //playerList.clear();
        //playerList.add(currentPlayer);
        //playerList.add(nextPlayer);
    //}


    /**
     * Creates a GameSate for a game to be passed
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

    /**
     * Setter methods that will set up the new game
     * @param Player
     */
    public void setCurrentPlayer(Player Player){
        this.currentPlayer = Player;
    }

    /**
     * Set the second player status
     * @param Player
     */
    public void setNextPlayer(Player Player){
        this.nextPlayer = Player;
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

