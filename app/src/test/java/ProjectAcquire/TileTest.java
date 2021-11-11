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
        classUnderTest.setFlipped();
        assertTrue(classUnderTest.isFlipped());
    }

    /**
     * Tests getCoord()
     */
    @Test void test_tile_getCoord() {

        int[] coord = {0,1};

        Tile classUnderTest = TestHelper.helperMethod_custom_tile_via_coord_and_companyName(coord,"TEST");
        classUnderTest.setFlipped();
        assertEquals(coord[0], classUnderTest.getCoord()[0]);
    }

    /**
     * Tests getCompany() and makes sure that the company name is proper
     */
    @Test void test_tile_getCompany() {
        int[] coord = {0,1};

        Tile classUnderTest = TestHelper.helperMethod_custom_tile_via_coord_and_companyName(coord,"TEST");
        classUnderTest.setFlipped();
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

}
