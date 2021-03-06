/**
 *  @author Team 404
 *  @version v0.0.1
 */
package ProjectAcquire;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {


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

    /**
     * Test that buy stocks correctly updates the player and stocks information
     */
    @Test void buy_stocks_test(){
        List<Tile> helperTileList = TestHelper.helperMethod_tileList_company1_3_coord_A1_A3();
        Company testCo = TestHelper.helperMethod_Company("Sackson");

        testCo.setNumTiles(15);
        testCo.setNewStockPrice();

        List<Stock> testStockList = new ArrayList<>();
        Player testPlayer = new Player("P1", helperTileList, 1000);
        testPlayer.setStockList(testStockList);
        testPlayer.buyStock(testCo);

        assertEquals(200, testPlayer.getMoney());
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

    /**
     * Test that the equals method properly takes in an object and compares it to other player objects inside of the class
     */
    @Test
    void test_Equals(){
        Player testPlayer = new Player();
        Object test = null;
        assertFalse(testPlayer.equals(test));
    }

    /**
     * Same test as above but instead the object ins't null
     */
    @Test
    void testEqualsTrue(){
        Player testPlayer = new Player();
        Object test = new Object();
        assertFalse(testPlayer.equals(test));
    }

    /**
     * Test that the stock list method is properly gathering a stock list from a player but in this case there
     * isn't any stocks bought in the company
     */
    @Test
    void test_stockList(){
        Player testPlayer = TestHelper.helperMethod_custom_Player("P1");
        Company testCo = TestHelper.helperMethod_Company("Test");
        assertEquals(0,testPlayer.countStocks(testCo));
    }

    /**
     * Same test as above but this time we actually have a stock bought inside of a players stock list to hit certain
     * branches inside of the method
     */
    @Test
    void test_stockList2(){
        GameState testGame = TestHelper.helperMethod_GameStateInit();
        LinkedList<Player> testList = testGame.getPlayerList();
        Player testPlayer = testList.get(0);
        Company testCom = TestHelper.helperMethod_Company("Test Company");
        Stock testStock = new Stock(testCom);
        List<Stock> testStockList = new LinkedList<>();
        testStockList.add(testStock);
        testPlayer.setStockList(testStockList);
        testStock.setParentCompany(testCom);

        assertEquals(1,testPlayer.countStocks(testCom));
    }
    @Test
    void test_countStocks(){

        Player testPlayer = TestHelper.helperMethod_custom_Player("P1");
        List<Stock> sList = new ArrayList<>();
        Stock testStock1 = TestHelper.helperMethod_customStock("COMPANY1");
        sList.add(testStock1);
        testPlayer.setStockList(sList);
        Company testCompany = sList.get(0).getParentCompany();
        assertEquals(1, testPlayer.countStocks(testCompany));

    }


}
