/**
 *  @author Team 404
 *  @version v0.0.1
 */
package ProjectAcquire;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

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
        writeFile(jsonFile);
        return jsonFile;
    }

    /**
     * Load game methods that will handle all of the loading of a saved json file
     * @param file Json file the contains the saved game objects
     * @return The gamestate that was saved
     */
    public GameState loadGame(String file) throws IOException {
        Gson converter = new Gson();
        //readString with path.of(file) is causing the path exceptions io error when running gradle test not sure what to do here -Tyler
        //String jsonString = Files.readString(Path.of(file)); // Added by Show, this resolved a loading error(Expected BEGIN_OBJECT but was STRING)
        GameState savedGame = converter.fromJson(file, GameState.class);
        return savedGame;
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
        try{
            FileWriter file = new FileWriter("./src/main/resources/SavedGames/SavedGame.txt");
            file.write(game);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
