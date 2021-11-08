package ProjectAcquire;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class TestHelper {

//methods must be static since this is basically just moving helper methods from individual tests to one file



    static Company helperMethod_Company(String companyName){

        Company helperComp = new Company(companyName,100,false,false);

        return helperComp;

    }

    static Tile helperMethod_tile(){
        Tile genericTile = new Tile();

        return genericTile;
    }

    /**
     *
     * @param coord Tile coordinate position as string
     * @param companyName Name of company Tile is attached to as string
     * @return a custom Tile
     */
    static Tile helperMethod_custom_tile_via_coord_and_companyName(String coord, String companyName){

        Company helperComp = helperMethod_Company(companyName);

        Tile customTile = new Tile(helperComp, coord);

        return customTile;
    }



    //Returns a list of tiles.
    static List<Tile> helperMethod_tileList(){
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

    static List<Tile> helperMethod_custom_tileList(){
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


    /**
     * Creates a Player object with a custom name, a Custom Tile List, and 1000 dollars
     * @param playerName The name of the player to create
     * @return A Player
     */
    static Player helperMethod_custom_Player(String playerName){
        //name, list of tiles, money
        List<Tile> customTileList = helperMethod_custom_tileList(); //calls Custom Tile list
        Player helperPlayer = new Player(playerName,customTileList,1000);
        return helperPlayer;
    }





}
