/**
 * @author Team 404
 * @version v0.0.1
 */
package ProjectAcquire;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Game class that can get the turns, start, load, or end a game
 */
public class Game {
   private @Getter @Setter GameState currentGameState;

    /**
     * Default constructor
     */
    Game(){}


    /**
     * Turn method that will determine which players turn it is
     * @return
     */
    public GameState getTurn(){
        return currentGameState;
    }

    /**
     * Load game method that accepts a gson file to get the game state of a specific game
     * @param game
     */
    public void loadGame(String game)throws IOException{

        //this.currentGameState = (gamestate that we pull from a file)
    }

    /**
     * Start game method that will begin a new game that isn't already saved
     */
    public GameState start() throws IOException {
        Company defaultCompany = new Company("emptyCo", 0, false, false);

        /*
        precheck: UI initializes with a starting screen. Player chooses either to exit, start, or load. if load, then call the loadGame() function instead.

        App starts the UI with start(Stage stage) (Not sure if that can be changed), but from there selecting new game can start this e.g: Game.start()
       when the player hits the start button in the UI, the UI calls this method, which does the following:
         */

        //1. queries for how many players there are, (UI), then adds x amount of players to a playerList.
        //This is just a static creation of players for now, will be more dynamic in the future.
        List<Tile> playerTileList = new ArrayList<>();
        LinkedList<Player> playerList = new LinkedList<Player>();
        Player player1 = new Player("Player 1", playerTileList, 3000);
        Player player2 = new Player("Player 2", playerTileList, 3000);
        Player player3 = new Player("Player 3", playerTileList, 3000);

        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);


        //2. creates 2d? list of tiles.
        List<Tile> freeTileList = new ArrayList<>(); //List of the uncharted tiles that no players have. Better variable name ideas?
        for (int r = 0; r < 9; r++){
            for(int c = 0; c < 12; c++) {
                Tile curTile = new Tile(defaultCompany, new int[]{r, c}); //SCREWED WITH BY ALEX TO MAKE COMPILE
                freeTileList.add(curTile);
            }
        }

        //3. creates our list of chartered and unchartered companies (the unchartered list can be empty)
        List<Company> charteredList = new ArrayList<>();
        List<Company> uncharteredList = new ArrayList<>();
        for (int i = 0; i < 7; i++){
            //ALEX NOTE: This will initialize every company with a stock price of 100. We should instead set the companies
            //statically 1 by 1 here with custom stock prices and names later on.
            Company curCompany = new Company("Company " + (Integer.toString(i)), 100, true, false);
            charteredList.add(curCompany);
        }


        //4. initialize a board using 1-4 as our parameters (using getInstance())
        Board board = Board.getInstance(freeTileList, uncharteredList, charteredList, playerList);

        //6. initialize our GameState using 4, 1 as our parameters.
        //ALEX NOTE: it seems that a GameState object holds exactly the same data as the Board object, since Board has the playerList just a thought.
        GameState gameState = new GameState(board, playerList);


        //7. call setCurrentGame() of Game using 6 as our parameter
        setCurrentGameState(gameState);


        //if loadGame was called, then simply: setCurrentGame(loadGame())

        //now we pass this to JavaFX (javafx calls this whole method)
        //Game.start() is invoked upon the player hitting "new game". So FXController call Game.start()
        return gameState;
    }

    /**
     * Run a game that is already saved from the gson file gathered from loadGame, (runs a game continuousely given starting data)
     */
    public void runGame() throws IOException {

        //while the game has not ended
        while(!currentGameState.isOver()){
            //game plays
            currentGameState.playTurn();
        }
        //currentGameState.update();
    }

    /**
     * Once a player ends the game on their turn the game will end using this method and determine the winner
     */
    public void endGame(){ }//We should probably move this to GameState.

}
