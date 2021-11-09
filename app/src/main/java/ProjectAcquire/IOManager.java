/**
 *  @author Team 404
 *  @version v0.0.1
 */
package ProjectAcquire;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

public class IOManager {
    /**
     * Default constructor
     */
    public void IOManager(){}
    /**
     * Save a current game being played with its current game state being converted to a json file
     * @param saveThisGame
     */
    public String saveGame(GameState saveThisGame){
        Gson gson = new Gson();
        GameState savedGameState = saveThisGame;
        String jsonFile = gson.toJson(savedGameState);
        return jsonFile;
    }

    /**
     * Load game methods that will handle all of the loading of a saved json file
     * @param file
     * @return
     * @throws IOException
     */
    public GameState loadGame(String file) throws IOException {
        BufferedReader readFile = null;
        Game thisGame = new Game();
        /**
         * Try to read a json file that was created from the save game method
         * which will then be converted from a string to a gamestate object within
         * the gamestate class
         */
        try {
            readFile = new BufferedReader(new FileReader(file));
            Gson gson = new GsonBuilder().create();
            GameState savedGameState = gson.fromJson(readFile, GameState.class);
            if (savedGameState != null) {
                thisGame.setCurrentGame(savedGameState);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally{
            if(readFile != null){
                readFile.close();
            }
        }
        return thisGame.getCurrentGame();
    }
}
