/**
 *
 */
package ProjectAcquire;

/**
 * Game class that can get the turns, start, load, or end a game
 */
public class Game {

    private GameState GameState = new GameState();

    /**
     * Load game method that accepts a gson file to get the game state of a specific game
     * @param game
     */
    public void loadGame(String game){ }

    /**
     * Turn method that will determine which players turn it is
     * @return
     */
    public GameState getTurn(){
        return GameState;
    }

    /**
     * Start game method that will begin a new game that isn't already saved
     */
    public void startGame(){ }

    /**
     * Run a game that is already saved from the gson file gathered from loadGame
     * @param currentGame
     */
    public void runGame(GameState currentGame){ }

    /**
     * Once a player ends the game on their turn the game will end using this method and determine the winner
     */
    public void endGame(){ }
}
