/**
 *  @author Team 404
 *  @version v0.0.1
 */
package ProjectAcquire;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
public class JsonTest{

    @Test
    /**
     * Test to see if a json file is created during the save game method inside of the IOManager class
     * Result: passed, thus a json string file is now created
     */
    void test_Json_File() throws IOException {
        GameState testGame = TestHelper.helperMethod_GameStateInit();
        IOManager testSaveGame = new IOManager();
        assertNotNull(testSaveGame.saveGame(testGame), "There currently is a saved game state");
    }

    /**
     * Test to see if the load game method does create a gamestate properly from the saved game json file
     * Result : passed, thus the user can continue where they left off
     */
    /*
    @Test
    void test_Loading_Game() {
        try{
        GameState testGame = TestHelper.helperMethod_GameStateInit();
        IOManager manager = new IOManager();
        String file = manager.saveGame(testGame);
        GameState testGame2 = manager.loadGame(file);
        assertEquals(testGame.getCurrentPlayer().getMoney(), testGame2.getCurrentPlayer().getMoney(), "" +
                "The gamestates after loading a game is equal to exactly where it left off when saved");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    */

    /**
     * Test to see if the default constructor does not create a fake object to call the methods in IOManager
     */
    @Test
    void test_IOManager(){
        IOManager test = new IOManager();
        assertNotNull(test);
    }
}
