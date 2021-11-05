/**
 *  @author Team 404
 *  @version v0.0.1
 */
package ProjectAcquire;

import org.junit.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class JsonTest {
    /**
     * Helper class to build a test game to see if it will properly write a json file
     * @return testGame
     */
    GameState testJson(){
        Player PlayerTest1 = new Player();
        Player PlayerTest2 = new Player();
        Board testBoard = new Board();
        List<Player> testList = null;
        testList.add(PlayerTest1);
        testList.add(PlayerTest2);
        GameState testGame = new GameState(PlayerTest1, PlayerTest2, testBoard, testList);
        return testGame;
    }

    @Test void test_Json_File(){
        IOManager testSaveGame = new IOManager();
        assertNull(testSaveGame.saveGame(testJson()));
    }
}
