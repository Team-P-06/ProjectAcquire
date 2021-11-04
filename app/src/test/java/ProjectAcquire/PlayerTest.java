package ProjectAcquire;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    /**
     * Need helper class for building a player.
     */


    List<Tile> helperMethod_tileList(){
        ArrayList<Tile> testTileList = new ArrayList<Tile>();
        Tile tile = new Tile();
        Tile tile2 = new Tile();
        Tile tile3 = new Tile();
        Tile tile4 = new Tile();
        Tile tile5 = new Tile();
        Tile tile6 = new Tile();

        testTileList.add(tile);
        testTileList.add(tile2);
        testTileList.add(tile3);
        testTileList.add(tile4);
        testTileList.add(tile5);
        testTileList.add(tile6);

        return testTileList;
    }

    @Test void test_player_availableTile() {

       List<Tile> helperTileList = helperMethod_tileList();

        Player classUnderTest = new Player("TEST",helperTileList,1000);
        assertTrue(classUnderTest.availableTile("TEST"), "app should have available tile method");
    }



}
