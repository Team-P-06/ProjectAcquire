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

       List<Tile> helperTileList = TestHelper.helperMethod_tileList_company1_3_coord_A1_A3();
        Player classUnderTest = new Player("TEST",helperTileList,1000);
        assertTrue(classUnderTest.availableTile(), "app should have available tile method");
    }


    @Test void test_player_drawTile() {


        Player classUnderTest = TestHelper.helperMethod_custom_Player("TEST");
        assertTrue(classUnderTest.availableTile(), "app should have available tile method");
    }

    @Test void buy_stocks_test(){
        List<Tile> helperTileList = TestHelper.helperMethod_tileList_company1_3_coord_A1_A3();
        Company testCo = TestHelper.helperMethod_Company("Sackson");

        testCo.setTilesOnBoard(15);
        testCo.setNewStockPrice();

        List<Stock> testStockList = new ArrayList<>();
        Stock test_stock = TestHelper.helperMethod_customStock("Sackson");
        Player testPlayer = new Player("P1", helperTileList, 1000);
        testPlayer.setStockList(testStockList);
        testPlayer.buyStock(test_stock);

        assertEquals(1000, testPlayer.getMoney());
    }

}
