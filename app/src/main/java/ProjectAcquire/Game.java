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
        //Company defaultCompany = new Company("emptyCo", 0, false, false);

        /*
        precheck: UI initializes with a starting screen. Player chooses either to exit, start, or load. if load, then call the loadGame() function instead.

        App starts the UI with start(Stage stage) (Not sure if that can be changed), but from there selecting new game can start this e.g: Game.start()
       when the player hits the start button in the UI, the UI calls this method, which does the following:
         */

        //1. queries for how many players there are, (UI), then adds x amount of players to a playerList.
        //This is just a static creation of players for now, will be more dynamic in the future.
        //List<Tile> playerTileList = new ArrayList<>();
        LinkedList<Player> playerList = new LinkedList<Player>();
        int numOfPlayers = 3;
        for (int i = 0; i < numOfPlayers; i++) {
            List<Tile> newPlayerTileList = new ArrayList<>();
            Player newPlayer = new Player("Player " + (i+1), newPlayerTileList, 3000);
            playerList.add(newPlayer);
        }

        //3. creates our list of chartered and unchartered companies (the unchartered list can be empty)
        List<Company> charteredList = new ArrayList<>();
        List<Company> uncharteredList = new ArrayList<>();
        Company worldwideCo =  new Company("Worldwide", 200, false, false);
        uncharteredList.add(worldwideCo);
        Company SacksonCo =  new Company("Sackson", 200, false, false);
        uncharteredList.add(SacksonCo);
        Company festivalCo =  new Company("Festival", 300, false, false);
        uncharteredList.add(festivalCo);
        Company imperialCo =  new Company("Imperial", 300, false, false);
        uncharteredList.add(imperialCo);
        Company americanCo =  new Company("American", 300, false, false);
        uncharteredList.add(americanCo);
        Company continentalCo =  new Company("Continental", 400, false, false);
        uncharteredList.add(continentalCo);
        Company towerCo =  new Company("Tower", 400, false, false);
        uncharteredList.add(towerCo);
        Company defaultCo =  new Company("DEFAULT", 0, false, false); // for placed tiles but no company
        uncharteredList.add(defaultCo);


        //2. creates 2d? list of tiles.
        List<Tile> freeTileList = new ArrayList<>();
        for (int r = 0; r < 9; r++){
            for(int c = 0; c < 12; c++) {
                Tile curTile = new Tile(defaultCo, new int[]{r, c});
                freeTileList.add(curTile);
            }
        }


        //4. initialize a board using 1-4 as our parameters (using getInstance())
        Board board = Board.getInstance(freeTileList, uncharteredList, charteredList, playerList);

        //6. initialize our GameState using 4, 1 as our parameters.
        //ALEX NOTE: it seems that a GameState object holds exactly the same data as the Board object, since Board has the playerList just a thought.
        GameState gameState = new GameState(board, playerList);


        //7. call setCurrentGame() of Game using 6 as our parameter
        setCurrentGameState(gameState);
        //gameState.playTurnNoUI();
        gameState.playTurn();

        //if loadGame was called, then simply: setCurrentGame(loadGame())

        //now we pass this to JavaFX (javafx calls this whole method)
        //Game.start() is invoked upon the player hitting "new game". So FXController call Game.start()

        System.out.println("Game.start() was finished");
        return gameState;
    }

    /**
     * Run a game that is already saved from the gson file gathered from loadGame, (runs a game continuousely given starting data)
     */
    public void runGame() throws IOException {

        //while the game has not ended
//        while(!currentGameState.isOver()){
//            //game plays
//            currentGameState.playTurnNoUI();
//        }
        for(int x=0;x<9;x++) {
            currentGameState.playTurn();
            //currentGameState.playTurnNoUI();
            System.out.println("Current Player is: " + currentGameState.getCurrentPlayer());
        }
        System.out.println("Game.runGame() was finished");

    }


    /**
     * Once a player ends the game on their turn the game will end using this method and determine the winner
     */
    public void endGame(){ }//We should probably move this to GameState.

}
