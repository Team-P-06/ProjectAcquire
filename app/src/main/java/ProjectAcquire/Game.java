/**
 * @author Team 404
 * @version v0.0.1
 */
package ProjectAcquire;

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
    public void start(){

        //initializes our game
       GameState gameState = new GameState(); //depreciated, leaving in while we implement the following:



        /*
        precheck: UI initializes with a starting screen. Player chooses either to exit, start, or load. if load, then call the loadGame() function.

        If start, then do the following:
         */

        //1. queries for how many players there are, (UI), then adds x amount of players to a playerList

        //2. creates our 1... 2d? list of tiles.

        //3. creates our list of chartered and unchartered companies (the unchartered list can be empty)

        //4. set a current player

        //5. initialize a board using 1-4 as our parameters

        //6. initialize our GameState using 4, playerList.next(), 5, and 1 as our parameters

        //7. call setCurrentGame() using 6 as our parameter


        //initializes board, sets the GameState Board to be that board.
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
