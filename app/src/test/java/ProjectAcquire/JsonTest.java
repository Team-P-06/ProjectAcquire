/**
 *  @author Team 404
 *  @version v0.0.1
 */
package ProjectAcquire;

import org.junit.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class JsonTest{
    /**
     * Helper class to build a test game to see if it will properly write a json file
     * @return testGame
     */
    @Before GameState testJson(){

        GameState testGame = TestHelper.helperMethod_GameStateInit();
        return testGame;
    }

    @Test public void test_Json_File(){
        IOManager testSaveGame = new IOManager();
        assertNotNull(testSaveGame.saveGame(testJson()));
    }
}
