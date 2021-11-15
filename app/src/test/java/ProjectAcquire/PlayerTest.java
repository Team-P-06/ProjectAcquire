package ProjectAcquire;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    //Helper methods should be deprecated adn replaced once our gamestate
    //initializer method works properly.

    /**
     * Need helper class for building a player.
     */


    /**
     * Tests the player available tile method that makes sure the method correctly indicates when
     * a player has an available tile to play
     */
    @Test void test_player_availableTile() {

       List<Tile> helperTileList = TestHelper.helperMethod_tileList_company1_3_coord_A1_A3();
        Player classUnderTest = new Player("TEST",helperTileList,1000);
        assertTrue(classUnderTest.availableTile(), "app should have available tile method");
    }

    /**
     * Test the draw tile method to make sure the method correctly indicates when a player
     * draws a tile
     */
    @Test void test_player_drawTile() {


        Player classUnderTest = TestHelper.helperMethod_custom_Player("TEST");
        assertTrue(classUnderTest.availableTile(), "app should have available tile method");
    }

    /**
     * Tests the to string method to make sure we can correctly parse to player attributes to a string format
     */
    @Test
    void test_ToString(){
        Player classUnderTest = TestHelper.helperMethod_custom_Player("Test P1");
        assertEquals(classUnderTest.getName(), classUnderTest.toString());
    }

    /**
     * Tests the add tile method by adding a tile to a player list and comparing it to the same tile list that they had
     * before it was added
     */
    @Test
    void test_addTile(){
        Tile testTile = new Tile();
        Player classUnderTest = TestHelper.helperMethod_custom_Player("Test P1");
        Player classUnderTest2 = TestHelper.helperMethod_custom_Player("Test P2");
        classUnderTest.addTile(testTile);
        assertNotEquals(classUnderTest2.getTileList(), classUnderTest.getTileList());
    }

    /**
     * Tests the place tile method by having a player place a tile and having it removed from their tile list
     * @throws IOException
     */
    @Test
    void test_placeTile() throws IOException {
        Tile testTile = new Tile();
        Player classUnderTest = TestHelper.helperMethod_custom_Player("Test P1");
        Player classUnderTest2 = TestHelper.helperMethod_custom_Player("Test P2");
        classUnderTest.placeTile(testTile);
        assertNotEquals(classUnderTest2.getTileList(),classUnderTest.getTileList());
    }

/*
    @Test
    void test_buyStock(){
        Company testCompany = new Company();
        Stock testStock = new Stock(testCompany);
        Player testPlayer1 = new Player();
        List<Stock> testList = testPlayer1.getStockList();
        testPlayer1.buyStock(testStock);
        assertTrue(testPlayer1.getStockList() != testList);
    }
*/
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

    /**
     * Tests that the hash code function actually hashes the objects inside of the player class
     */
    @Test
    void test_hashCode(){
        Player classUnderTest = TestHelper.helperMethod_custom_Player("Test P1");
        assertNotNull(classUnderTest.hashCode());
    }

    /**
     * Test that tests the discard tile method by adding a tile and then discarding it and comparing it
     * to the list that it had before the test tile was added and then discarded
     */
    @Test
    void test_discardTile(){
        Player classUnderTest = TestHelper.helperMethod_custom_Player("Test P1");
        List<Tile> testList = classUnderTest.getTileList();
        Tile testTile = new Tile();
        classUnderTest.getTileList().add(testTile);
        classUnderTest.discardTile(testTile);
        assertEquals(classUnderTest.getTileList(), testList);
    }
}
