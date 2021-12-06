/**
 * IOManager.java
 *
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
 * @version v1.1.0
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
<<<<<<< HEAD
     * Load game methods that will handle all of the loading of a saved json file
=======
     * Load game methods that will handle all the loading of a saved json file
     * @param file Json file the contains the saved game objects
>>>>>>> 834af487e80773b743b20cc672d6cb9921686740
     * @return The gamestate that was saved
     */
    public GameState loadGame(String file) throws FileNotFoundException{
        Gson converter = new Gson();

        BufferedReader jsonString = new BufferedReader(new FileReader(file));
        GameState savedGame = converter.fromJson(jsonString, GameState.class);

        return savedGame;
    }

    /**
     * Write the current gamestate object that is saved into a json string to our resource folder in order to be called
     * later when they try to load the game back up again
     * @param game the current game that is being saved into a gamestate json string
     */

}
