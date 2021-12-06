/**
 * MIT License
 *
 * Copyright (c) 2021 404
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * @author Team 404
 * @version v1.0.0
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
    @Generated
    public void IOManager(){}
    /**
     * Save a current game being played with its current game state being converted to a json file
     * @param saveThisGame the current gamestate to save
     */
    public String saveGame(GameState saveThisGame) throws IOException {
        Gson gson = new Gson();
        GameState savedGameState = saveThisGame;
        String jsonFile = gson.toJson(savedGameState);
        FileWriter file = new FileWriter("./src/main/resources/SavedGames/SavedGame.txt");
        file.write(jsonFile);
        file.flush();
        file.close();

        return jsonFile;
    }

    /**
     * Load game methods that will handle all the loading of a saved json file
     * @param file Json file the contains the saved game objects
     * @return The gamestate that was saved
     */
    public GameState loadGame(String file) throws IOException {
        Gson converter = new Gson();

        //readString with path.of(file) is causing the path exceptions io error when running gradle test not sure what to do here -Tyler
        //String jsonString = Files.readString(Path.of(file)); // Added by Show, this resolved a loading error(Expected BEGIN_OBJECT but was STRING)
        BufferedReader jsonString = new BufferedReader(new FileReader("./src/main/resources/SavedGames/SavedGame.txt"));
        GameState savedGame = converter.fromJson(jsonString, GameState.class);

        return savedGame;
    }

    /**
     * Write the current gamestate object that is saved into a json string to our resource folder in order to be called
     * later when they try to load the game back up again
     * @param game the current game that is being saved into a gamestate json string
     */

    //Not needed anymore?
    @Generated
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
