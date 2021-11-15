package ProjectAcquire;

import org.junit.After;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {



    //This test is shitty for checking proper initialization change it.
    @Test void test_board_init_chartered_companies() {

        // Tile helperTile = helperMethod_custom_tile();

        Board classUnderTest = TestHelper.helperMethod_custom_board();
        //tests that initialized chartered companies is empty
        //System.out.println(classUnderTest.getCharteredCompanies().toString());
        assertTrue(classUnderTest.getCharteredCompanies().isEmpty(),"Chartered companies list should be empty on initialization of a board, but is not");

        TestHelper.helperMethod_tearDownBoard();
    }


    @Test void test_board_company_before_charter() {

        Board classUnderTest = TestHelper.helperMethod_custom_board();

        Company boardCompanyToCharter = classUnderTest.getUncharteredCompanies().get(0);

       // classUnderTest.charter(boardCompanyToCharter);

        assertFalse(boardCompanyToCharter.isChartered(),"Company did not initialize as unchartered");
        TestHelper.helperMethod_tearDownBoard();

    }

    @Test void test_board_company_after_charter() throws Exception {

        Board classUnderTest = TestHelper.helperMethod_custom_board();

        Company boardCompanyToCharter = classUnderTest.getUncharteredCompanies().get(0);

        classUnderTest.charter(boardCompanyToCharter);

        assertTrue(boardCompanyToCharter.isChartered(), "Charter method did not work properly");
        TestHelper.helperMethod_tearDownBoard();

    }

    @Test void test_board_company_after_uncharter() throws Exception {

        Board classUnderTest = TestHelper.helperMethod_custom_board();

        //grabs a company from our board
        Company boardCompanyForTesting = classUnderTest.getUncharteredCompanies().get(0);

        classUnderTest.charter(boardCompanyForTesting); //charters a company

        classUnderTest.unCharter(boardCompanyForTesting); //uncharters that company

        assertFalse(boardCompanyForTesting.isChartered(),"Uncharter method did not work properly");
        TestHelper.helperMethod_tearDownBoard();

    }

    /**
     * Tests our checkPermanent method. this will need to be updated once real logic is implemented.
     */
    @Test void test_board_tile_isPermanent_notNull(){

        Board classUnderTest = TestHelper.helperMethod_custom_board();

        assertFalse(classUnderTest.checkPermanent("TEST"));

    }

    /**
     * Update this test once we implement observables.
     */
    @Test void test_updateMethod(){

        Board classUnderTest = TestHelper.helperMethod_custom_board();
        //classUnderTest.updateBoard(); //updateBoard has been depreciated for mutiable diffrent updates
        assertTrue(1==1);

    }

    /**
     * Tests the checkPermanent method. Real implementation will cause this test to fail.
     */
    @Test void test_checkPermanent(){

        Board classUnderTest = TestHelper.helperMethod_custom_board();
        assertFalse(classUnderTest.checkPermanent("A1"));
    }

    @Test void test_getTileWest() throws Exception {

        GameState helperGameState = TestHelper.helperMethod_GameStateInit();
        Board helperBoard = helperGameState.getCurrentBoard();
        int[] coord = {0,1};
        int[] expectedWest = {0,0};
        Tile testTile = TestHelper.helperMethod_customTile(coord);
        List<Tile> helperTileList = helperGameState.getCurrentBoard().getTileList();
        //System.out.println(helperTileList);
        Tile tileWestOfCoord = helperBoard.getAdjacentTile(coord,"WEST");
        System.out.println(tileWestOfCoord);
      //  assertTrue(Arrays.equals(tileWestOfCoord.getCoord(),expectedWest));

    }

    @Test void test_getTileEast() throws Exception {

        GameState helperGameState = TestHelper.helperMethod_GameStateInit();
        Board helperBoard = helperGameState.getCurrentBoard();
        int[] coord = {0,1};
        int[] expectedEast = {0,2};
        Tile testTile = TestHelper.helperMethod_customTile(coord);
        List<Tile> helperTileList = helperGameState.getCurrentBoard().getTileList();
        System.out.println(helperTileList);
        Tile tileEastOfCoord = helperBoard.getAdjacentTile(coord,"EAST");
        System.out.println(tileEastOfCoord);
        //System.out.println(tileEastOfCoord);
        //assertTrue(Arrays.equals(tileEastOfCoord.getCoord(),expectedEast));

    }



//    @Test void test_getTilesAround(){
//
//        GameState helperGameState = TestHelper.helperMethod_GameStateInit();
//        Board helperBoard = helperGameState.getCurrentBoard();
//        int[] coord = {0,1};
//
//        Tile testTile = TestHelper.helperMethod_customTile(coord);
//        List<Tile> helperTileList = helperGameState.getCurrentBoard().getTileList();
//        List<Tile> tilesAround = helperBoard.getTilesAround(coord);
//        //System.out.println(helperTileList);
//
//        assertTrue(!tilesAround.isEmpty());
//
//
//
//
//    }


    @Test void test_convTileList2d(){

        GameState helperGameState = TestHelper.helperMethod_GameStateInit();
        Board helperBoard = helperGameState.getCurrentBoard();


        int z = 0;
        Tile[][] twoDArr = new Tile[9][12];
        for(Tile[] x : twoDArr){
            for(int y=0; y<x.length;y++){
                if(x[y]!=null) {
                    x[y] = helperBoard.getTileList().get(y + z);
                    System.out.print(x[y]+ " ");
                }
                System.out.println();
            }
            z+=x.length;
        }


    }



    }