/**
 *  @author Team 404
 *  @version v0.0.1
 */
package ProjectAcquire;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    /**
     * Tests that the get turn method properly gets the current player inside of our player list. In our prject the player
     * who is in the front of the player list is the current player
     */
    @Test
    void test_getTurn(){
        GameState test_Game = TestHelper.helperMethod_GameStateInit();
        Game test = Game.getInstance();
        test.setCurrentGameState(test_Game);
        assertNotNull(test.getTurn());
        TestHelper.helperMethod_tearDownGame();
    }

    /**
     * Test that the get instance method properly makes an instance if our game instance is null
     */
    @Test
    void test_getInstance(){
        Game test = Game.getInstance();
        assertNotNull(test);
        TestHelper.helperMethod_tearDownGame();

    }

    @Test
    void test_Start() {
        Game test = Game.getInstance();
        assertNull(test.start(3));
        TestHelper.helperMethod_tearDownGame();

    }

    /**
     * Test that our start method properly generates the proper number of players with correct attributes
     * @throws IOException
     */
    @Test
    void test_loadGame(){
        Game test = Game.getInstance();
        GameState testGameState = TestHelper.helperMethod_GameStateInit();
        test.loadGame(testGameState);
        assertNotNull(Game.getInstance().getCurrentGameState());
        TestHelper.helperMethod_tearDownGame();
    }
}
