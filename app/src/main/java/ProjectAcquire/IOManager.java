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
        //writeFile(jsonFile);
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
                thisGame.setCurrentGameState(savedGameState);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally{
            if(readFile != null){
                readFile.close();
            }
        }
        return thisGame.getCurrentGameState();
    }

    /**
     * Write the current gamestate object that is saved into a json string to our resource folder in order to be called
     * later when they try to load the game back up again
     * @param game the current game that is being saved into a gamestate json string
     */
    public void writeFile(String game){
        /**
         * try to create a new file writer that will the to the resource folder with the name of saved game.txt for now
         */
        //for now it will only save one game. When the user chooses to save a game and decides what slot they want
        //to save to that will then change the saved game file name depending on the save slot
        try{
            FileWriter file = new FileWriter("C:\\Users\\Tyler Kelley\\Desktop\\CS_2263\\Acquire\\ProjectAcquire\\" +
                    "app\\src\\main\\resources\\SavedGames\\SavedGame.txt");
            file.write(game);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
