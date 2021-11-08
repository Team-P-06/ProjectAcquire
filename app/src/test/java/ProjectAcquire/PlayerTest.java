package ProjectAcquire;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    //Helper methods should be deprecated adn replaced once our gamestate
    //initializer method works properly.

    /**
     * Need helper class for building a player.
     */



    @Test void test_player_availableTile() {

       List<Tile> helperTileList = TestHelper.helperMethod_tileList();

        Player classUnderTest = new Player("TEST",helperTileList,1000);
        assertTrue(classUnderTest.availableTile("TEST"), "app should have available tile method");
    }




}
