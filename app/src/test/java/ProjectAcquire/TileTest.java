package ProjectAcquire;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TileTest {


    Tile helperMethod_tile(){
        Tile genericTile = new Tile();

        return genericTile;
    }
    Tile helperMethod_custom_tile(){

        Company helperComp = new Company();
        String coord = "A1";

        Tile customTile = new Tile(helperComp, coord);

        return customTile;
    }

    @Test void test_tile_init() {

        // Tile helperTile = helperMethod_custom_tile();

        Tile classUnderTest = helperMethod_custom_tile();
        assertTrue(classUnderTest.getCompany().getCompanyName().equals("TEST"), "app should have available tile method");
    }



}
