/**
 *  @author Team 404
 *  @version v0.0.1
 */
package ProjectAcquire;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
public class JsonTest{

    @Test
    /**
     * Test to see if a json file is created during the save game method inside of the IOManager class
     * Result: passed, thus a json string file is now created
     */
    void test_Json_File(){
        GameState testGame = TestHelper.helperMethod_GameStateInit();
        IOManager testSaveGame = new IOManager();
        assertNotNull(testSaveGame.saveGame(testGame), "There currently is a saved game state");
    }
    @Test
    void test_Writing_File() throws IOException {
        GameState testGame = TestHelper.helperMethod_GameStateInit();
        IOManager testSaveGame = new IOManager();
        testSaveGame.saveGame(testGame);
        assertEquals(testGame, testSaveGame.loadGame(testSaveGame.getTestFile()));
        // true : assertEquals(testSaveGame.getTestFile(), testSaveGame.saveGame(testGame));

    }
}
