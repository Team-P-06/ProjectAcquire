package ProjectAcquire;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TileTest {

    /**
     * Tests that the name of a custom initialized tile is set correctly
     */
    @Test void test_tile_init() {

        // Tile helperTile = helperMethod_custom_tile();

       int[] testTileCoord = {0,1};
        Tile classUnderTest = TestHelper.helperMethod_custom_tile_via_coord_and_companyName(testTileCoord,"TEST");
        assertTrue(classUnderTest.getCompany().getCompanyName().equals("TEST"), "Initialized Tile should have a name of TEST");
    }

    /**
     * Tests that a Tile is initially not flipped
     */
    @Test void test_tile_isFlipped_on_initialize() {
        Company helperComp = new Company();
        int[] coord = {0,1};

        // Tile helperTile = helperMethod_custom_tile();

        Tile classUnderTest = TestHelper.helperMethod_custom_tile_via_coord_and_companyName(coord,"TEST");
        assertFalse(classUnderTest.isFlipped());
    }

    /**
     * Tests that a tile is flipped once setFlipped is called
     */
    @Test void test_tile_isFlipped_on_setFlipped() {
        int[] coord = {0,1};

        Tile classUnderTest = TestHelper.helperMethod_custom_tile_via_coord_and_companyName(coord,"TEST");
        classUnderTest.setFlipped(true);
        assertTrue(classUnderTest.isFlipped());
    }

    /**
     * Tests getCoord()
     */
    @Test void test_tile_getCoord() {

        int[] coord = {0,1};

        Tile classUnderTest = TestHelper.helperMethod_custom_tile_via_coord_and_companyName(coord,"TEST");
        classUnderTest.setFlipped(true);
        assertEquals(coord[0], classUnderTest.getCoord()[0]);
    }

    /**
     * Tests getCompany() and makes sure that the company name is proper
     */
    @Test void test_tile_getCompany() {
        int[] coord = {0,1};

        Tile classUnderTest = TestHelper.helperMethod_custom_tile_via_coord_and_companyName(coord,"TEST");
        classUnderTest.setFlipped(true);
        assertTrue(classUnderTest.getCompany().getCompanyName().equals("TEST"));
    }

    //Tests that the name of the company is changed after setCompany is called
    @Test void test_tile_setCompany() {
        int[] coord = {0,1};

        Tile classUnderTest = TestHelper.helperMethod_custom_tile_via_coord_and_companyName(coord,"NAME_BEFORE");

        Company testerCompany = TestHelper.helperMethod_Company("NAME_AFTER");
        classUnderTest.setCompany(testerCompany);

        assertTrue(classUnderTest.getCompany().getCompanyName().equals("NAME_AFTER"));

    }

    /**
     * Tests isDealt() method make sure a tile is dealt inside the gamestate
     */
    @Test
    void test_isDealt(){
        Tile testTile = new Tile();
        assertFalse(testTile.isDealt());
    }

    /**
     * Tests that the isDealable() method returns the right boolean while calling is dealt and flipped inside of the tile class
     */
    /*@Test
    void test_isDealable(){
        Tile testTile = new Tile();
        assertTrue(testTile.isDealable());
    }*/

    /**
     * Tests that the method isFlipped() correctly checks to see if a tile is flipped
     */
    @Test
    void test_isFlipped(){
        Tile testTile = new Tile();
        assertFalse(testTile.isFlipped());
    }

    /**
     * Tests the toString method to make sure that the tile coordinates are getting turned into a string
     */
    @Test
    void test_toString(){
        Tile testTile = new Tile();
        assertNotNull(testTile.toString());
    }

    /**
     * Tests the coordToString() method to make sure the tile coords are properly getting converted to a string
     */
    @Test
    void test_TileCoordToString(){
        Tile testTile = new Tile();
        assertNotNull(testTile.tileCoordToString());
    }

}
