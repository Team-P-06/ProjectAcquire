/**
 * @author Team 404
 * @version v0.0.1
 */
package ProjectAcquire;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Game class that can get the turns, start, load, or end a game
 */
public class Game {
    GameState currentGameState;

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
    public void loadGame(String game){

        //if we have loadgame in IO manager that sets the currentgamestate variable and then call rungame(currentGameState)
        //we technically dont need a loadGame method here?
    }

    /**
     * Start game method that will begin a new game that isn't already saved
     */
    public void start() throws IOException {
        Company defaultCompany = new Company("emptyCo", 0, false, false);

        //initializes our game
       //GameState gameState = new GameState(); //depreciated, leaving in while we implement the following:

        /*
        precheck: UI initializes with a starting screen. Player chooses either to exit, start, or load. if load, then call the loadGame() function.
        App starts the UI with start(Stage stage) (Not sure if that can be changed), but from there selecting new game can start this e.g: Game.start()
        Load game can call Game.loadGame() instead - show
        If start, then do the following:
         */

        //1. queries for how many players there are, (UI), then adds x amount of players to a playerList.
        //This is just a static creation of players for now, will be more dynamic in the future.
        List<Tile> playerTileList = new ArrayList<>();
        List<Player> playerList = new ArrayList<>();
        Player player1 = new Player("Player 1", playerTileList, 3000);
        Player player2 = new Player("Player 2", playerTileList, 3000);
        playerList.add(player1);
        playerList.add(player2);

        //2. creates our 1... 2d? list of tiles.
        List<Tile> freeTileList = new ArrayList<>(); //List of the uncharted tiles that no players have. Better variable name ideas?
        for (int i = 0; i < 108; i++){
            Tile curTile = new Tile(defaultCompany, Integer.toString(i));
            freeTileList.add(curTile);
        }

        //3. creates our list of chartered and unchartered companies (the unchartered list can be empty)
        List<Company> chartedList = new ArrayList<>();
        List<Company> uncharteredList = new ArrayList<>();
        for (int i = 0; i < 7; i++){
            Company curCompany = new Company("Company " + (Integer.toString(i)), 0, true, false);
            chartedList.add(curCompany);
        }

        //4. set a current player
        // Isn't the current/next player set when we create the gameState?
        // We would already need a GameState object to call GameState.setCurrentPlayer - Show

        //5. initialize a board using 1-4 as our parameters
        Board board = new Board(freeTileList, uncharteredList, chartedList, playerList);

        //6. initialize our GameState using 4, playerList.next(), 5, and 1 as our parameters
        // This makes p1 current player and p2 next
        GameState gameState = new GameState(player1, player2, board, playerList);

        //7. call setCurrentGame() using 6 as our parameter
        setCurrentGame(gameState);


        //if loadGame was called, then simply: setCurrentGame(loadGame())
    }

    /**
     * Run a game that is already saved from the gson file gathered from loadGame, (runs a game continuousely given starting data)
     */
    public void runGame(){

        //while the game has not ended
        while(!currentGameState.isOver()){
            //game plays
            currentGameState.playTurn();
        }
    }

    /**
     * Once a player ends the game on their turn the game will end using this method and determine the winner
     */
    public void endGame(){ }//We should probably move this to GameState.

    /**
     *
     * @param gameState The gameState of our current game
     */
    public void setCurrentGame(GameState gameState){
        this.currentGameState = gameState;
    }
    public GameState getCurrentGame(){
        return currentGameState;
    }
}
