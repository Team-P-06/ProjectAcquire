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

    @Test
    void test_getLowestStock(){
        Board test = TestHelper.helperMethod_custom_board();
        assertNotNull(test.getLowestStockPrice());
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
     * Update this test once we implement observables.
     */
    @Test void test_updateMethod(){

        Board classUnderTest = TestHelper.helperMethod_custom_board();
        //classUnderTest.updateBoard(); //updateBoard has been depreciated for mutiable diffrent updates
        assertTrue(1==1);

    }



    /**
     * Test to make sure that a string is properly generated within the board class
     */
    @Test
    void test_String(){
        Board test = new Board();
        assertNotNull(test.toString());
    }

    /**
     * Test that an instance is being created when a board object is null
     */
    @Test
    void test_Instance(){
        Board test = new Board();
        assertNotNull(test.getInstance());
    }

    /**
     * Test that we can properly get how many tiles a company has on the board
     */
    @Test
    void test_tilesOnBoard() {
        GameState test_Game = TestHelper.helperMethod_GameStateInit();
        Board helperBoard = test_Game.getCurrentBoard();
        Company test_Company = new Company();
        assertEquals(0,test_Company.getNumTiles());
    }

    /**
     * Test that we can get the company tiles on the board with the override method as the same one above but with
     * different options being available
     */
    @Test
    void test_companayTiles(){
        GameState test_Game = TestHelper.helperMethod_GameStateInit();
        Board helperBoard = test_Game.getCurrentBoard();
        Company test_Company = new Company();
        assertEquals(0,helperBoard.getCompanyNumberOfTiles(test_Company));
    }

    /**
     * Test that determmines if we are properly getting the tiles on board a company has. In this case our default company
     * has no tiles on board so it should return a null value
     */
    @Test
    void test_getTilesOnBoard(){
        GameState helperGameState = TestHelper.helperMethod_GameStateInit();
        Board helperBoard = helperGameState.getCurrentBoard();
        Company test = new Company();
        assertNull(helperBoard.getTilesOnBoard(test));
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

    /**
     *
     * Tests the Boards deal tile method
     */
    @Test void test_dealTile(){
        Board helperBoard = TestHelper.helperMethod_custom_board();

        List<Player> pList= helperBoard.getPlayerList();
        helperBoard.setCurrentPlayer(pList.get(0));
       // System.out.println(helperBoard.getCurrentPlayer());
        helperBoard.dealTile(helperBoard.getCurrentPlayer());

        Player testPlayer = pList.get(0);
        Tile testTile = testPlayer.getTileList().get(testPlayer.getTileList().size()-1);
        assertEquals(testTile.isDealt(),true);

        TestHelper.helperMethod_tearDownBoard();

    }


    /**
     * Tests getLowestStockPrice
     */
    @Test void test_getLowestStockPrice(){

        Board helperBoard = TestHelper.helperMethod_custom_board();
        List<Company> test_charteredCompanies = TestHelper.helperMethod_CompanyList(); //will make a list of 3 companies
        test_charteredCompanies.get(0).setNumTiles(2); //equates to a stockPrice of 200
        test_charteredCompanies.get(1).setNumTiles(3);//equates to a stockPrice of 300
        test_charteredCompanies.get(2).setNumTiles(4);//equates to a stockPrice of 400

        helperBoard.setCharteredCompanies(test_charteredCompanies); //overrides the chartered companies variable for test
       int lowestPrice = helperBoard.getLowestStockPrice(); //should be 200
       for(Company c: test_charteredCompanies) {System.out.print(c.getStockPrice()+ " ");}

       assertEquals(200,lowestPrice);
        TestHelper.helperMethod_tearDownBoard();
    }


}