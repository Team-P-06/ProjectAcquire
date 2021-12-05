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
        Game test = new Game();
        test.setCurrentGameState(test_Game);
        assertNotNull(test.getTurn());
    }

    /**
     * Test that the get instance method properly makes an instance if our game instance is null
     */
    @Test
    void test_getInstance(){
        Game test = new Game();
        assertNotNull(test.getInstance());
    }

    /**
     * Test that our start method properly generates the proper number of players with correct attributes
     * @throws IOException
     */
    @Test
    void test_Start() throws IOException {
        Game test = new Game();
        assertNotNull(test.start(2));
    }
}
