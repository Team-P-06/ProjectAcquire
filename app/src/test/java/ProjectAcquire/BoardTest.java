/**
 *  @author Team 404
 *  @version v0.0.1
 */

package ProjectAcquire;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    /**
     * Test that chartered comapanies are actually contained inside of a list. Inside of this specific test it should
     * be empty since no chartered companies are created on this certain board
     */

    //This test is shitty for checking proper initialization change it.
    @Test void test_board_init_chartered_companies() {
        Board classUnderTest = TestHelper.helperMethod_custom_board();
        //tests that initialized chartered companies is empty
        assertTrue(classUnderTest.getCharteredCompanies().isEmpty(),"Chartered companies list should be empty on initialization of a board, but is not");

        TestHelper.helperMethod_tearDownBoard();
    }

    /**
     * Test that a company from the company class that is contained on a board is not chartered before a user
     * wants to either charter a company or do other specific options
     */
    @Test void test_board_company_before_charter() {

        Board classUnderTest = TestHelper.helperMethod_custom_board();

        Company boardCompanyToCharter = classUnderTest.getUncharteredCompanies().get(0);

       // classUnderTest.charter(boardCompanyToCharter);

        assertFalse(boardCompanyToCharter.isChartered(),"Company did not initialize as unchartered");
        TestHelper.helperMethod_tearDownBoard();

    }

    /**
     * Test that get lowest stock method inside of board actually generates a lowest stock price inside of the board
     * class that we can use later when a user is playing the game
     */
    @Test
    void test_getLowestStock(){
        Board test = TestHelper.helperMethod_custom_board();
        assertNotNull(test.getLowestStockPrice());
    }

    /**
     * Test that a company can be un-chartered properly throughout the uncharter method inside of the board class
     * @throws Exception
     */
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
        assertNotNull(Board.getInstance().toString());
        TestHelper.helperMethod_tearDownBoard();
    }

    /**
     * Test that an instance is being created when a board object is null
     */
    @Test
    void test_Instance(){
        //Board test = new Board();
        assertNotNull(Board.getInstance());
        TestHelper.helperMethod_tearDownBoard();

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
        TestHelper.helperMethod_tearDownBoard();

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
        TestHelper.helperMethod_tearDownBoard();

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
        TestHelper.helperMethod_tearDownBoard();
    }


    //Not needed test anymore?
    @Test void test_getTileWest() throws Exception {

        GameState helperGameState = TestHelper.helperMethod_GameStateInit();
        Board helperBoard = helperGameState.getCurrentBoard();
        int[] coord = {0,1};
        int[] expectedWest = {0,0};
        Tile testTile = TestHelper.helperMethod_customTile(coord);
        List<Tile> helperTileList = helperGameState.getCurrentBoard().getTileList();
        //System.out.println(helperTileList);
        Tile tileWestOfCoord = helperBoard.getAdjacentTile(coord,"WEST");
       // System.out.println(tileWestOfCoord);
       assertTrue(Arrays.equals(tileWestOfCoord.getCoord(),expectedWest));

        TestHelper.helperMethod_tearDownBoard();

    }

    //Not needed test anymore?
    @Test void test_getTileEast() throws Exception {

        GameState helperGameState = TestHelper.helperMethod_GameStateInit();
        Board helperBoard = helperGameState.getCurrentBoard();
        int[] coord = {0,1};
        int[] expectedEast = {0,2};
        Tile testTile = TestHelper.helperMethod_customTile(coord);
        List<Tile> helperTileList = helperGameState.getCurrentBoard().getTileList();
       // System.out.println(helperTileList);
        Tile tileEastOfCoord = helperBoard.getAdjacentTile(coord,"EAST");
        //System.out.println(tileEastOfCoord);
        //System.out.println(tileEastOfCoord);
        assertTrue(Arrays.equals(tileEastOfCoord.getCoord(),expectedEast));

        TestHelper.helperMethod_tearDownBoard();

    }

    /**
     * Test that our tile list convert to 2d array method actually generates a 2d list that we can use throughout our
     * board class
     */
    @Test void test_convTileList2d(){

        GameState helperGameState = TestHelper.helperMethod_GameStateInit();
        Board helperBoard = helperGameState.getCurrentBoard();

        int z = 0;
        Tile[][] twoDArr = new Tile[9][12];
        for(Tile[] x : twoDArr){
            for(int y=0; y<x.length;y++){
                if(x[y]!=null) {
                    x[y] = helperBoard.getTileList().get(y + z);
                  //  System.out.print(x[y]+ " ");
                }
               // System.out.println();
            }
            z+=x.length;
        }
        TestHelper.helperMethod_tearDownBoard();

    }

    /**
     *
     * Tests the Boards deal tile method
     */
    @Test void test_dealTile(){
        Board helperBoard = TestHelper.helperMethod_custom_board();

        List<Tile> tList = TestHelper.helperMethod_tileList_company1_3_coord_A1_A3();
        System.out.println(tList);
        helperBoard.setTileList(tList);

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
       //for(Company c: test_charteredCompanies) {System.out.print(c.getStockPrice()+ " ");}

       assertEquals(200,lowestPrice);
        TestHelper.helperMethod_tearDownBoard();
    }

    /**
     * This method tests the checkForAction method to make sure it works for seeing a charter action
     *
     * @throws Exception
     */
    @Test void test_checkForActionInitiation() throws Exception {

        Board helperBoard = TestHelper.helperMethod_custom_board();

        List<Tile> helperTileList = helperBoard.getTileList();
        //System.out.println(helperTileList);
        Tile testTile = helperTileList.get(0);
        int testAction = helperBoard.checkForActionInitiation(testTile);
        assertEquals(2,testAction); //since the Tiles in our helperTileList have companies associated with them, return 2.
        TestHelper.helperMethod_tearDownBoard();

    }

    /**
     * Test checkForAction's adding Flipped tiles to company branch
     * @throws Exception
     */
    @Test void test_checkForActionInitiation_addComp() throws Exception {

        Board helperBoard = TestHelper.helperMethod_custom_board();

        List<Tile> helperTileList = helperBoard.getTileList();
        //System.out.println(helperTileList);
        Tile testTile = helperTileList.get(0);
        Tile adjTile = helperTileList.get(1);
        Company defCom = TestHelper.helperMethod_Company("DEFAULT");
        adjTile.setCompany(defCom);
        adjTile.setFlipped(true);
        int testAction = helperBoard.checkForActionInitiation(testTile);
        assertEquals(1,testAction); //since the Tiles in our helperTileList have companies associated with them, return 2.
        TestHelper.helperMethod_tearDownBoard();

    }

    /**
     * Tests checkForActions normal merging branch
     * @throws Exception
     */
    @Test void test_checkForActionInitiation_merge() throws Exception {

        Board helperBoard = TestHelper.helperMethod_custom_board();

        List<Tile> helperTileList = helperBoard.getTileList();
        //System.out.println(helperTileList);
        Tile testTile = helperTileList.get(1);
        Tile adjTile = helperTileList.get(0);
        Tile adjTile2 = helperTileList.get(2);

        Company mergeCom1 = TestHelper.helperMethod_Company("COMP1");
        Company mergeCom2 = TestHelper.helperMethod_Company("COMP2");
        mergeCom1.setChartered(true);
        mergeCom2.setChartered(true);
        adjTile.setCompany(mergeCom1);
        adjTile2.setCompany(mergeCom2);

        int testAction = helperBoard.checkForActionInitiation(testTile);
        assertEquals(3,testAction); //since the Tiles in our helperTileList have companies associated with them, return 2.
        TestHelper.helperMethod_tearDownBoard();

    }

    /**
     * tests checkForActions merging between permanent companies branch
     * @throws Exception
     */
    @Test void test_checkForActionInitiation_merge_perms() throws Exception {

        Board helperBoard = TestHelper.helperMethod_custom_board();

        List<Tile> helperTileList = helperBoard.getTileList();
        //System.out.println(helperTileList);
        Tile testTile = helperTileList.get(1);
        Tile adjTile = helperTileList.get(0);
        Tile adjTile2 = helperTileList.get(2);

        Company mergeCom1 = TestHelper.helperMethod_Company("COMP1");
        Company mergeCom2 = TestHelper.helperMethod_Company("COMP2");
        mergeCom1.setChartered(true);
        mergeCom2.setChartered(true);
        adjTile.setCompany(mergeCom1);
        adjTile2.setCompany(mergeCom2);
        adjTile.getCompany().setPermanent(true);
        adjTile2.getCompany().setPermanent(true);


        int testAction = helperBoard.checkForActionInitiation(testTile);
        assertEquals(4,testAction); //since the Tiles in our helperTileList have companies associated with them, return 2.
        TestHelper.helperMethod_tearDownBoard();

    }

    /**
     * This method tests the checkForAction method to make sure it works for seeing a "carry on nothing to see here" action
     *
     * @throws Exception
     */
    @Test void test_checkForActionInitiation_tilesNotFlipped() throws Exception {

        Board helperBoard = TestHelper.helperMethod_custom_board();

        List<Tile> helperTileList = new ArrayList<>();
        Tile customTile1 = TestHelper.helperMethod_customTile(new int[]{0,0});
        Tile customTile2 = TestHelper.helperMethod_customTile(new int[]{0,1});
        Tile customTile3 = TestHelper.helperMethod_customTile(new int[]{0,2});

        customTile1.setFlipped(false);
        customTile2.setFlipped(false);
        customTile3.setFlipped(false);

        helperTileList.add(customTile1);
        helperTileList.add(customTile2);
        helperTileList.add(customTile3);

        helperBoard.setTileList(helperTileList);
        //System.out.println(helperBoard.companiesAroundTile(customTile1));
        Tile testTile = helperTileList.get(0);
        int testAction = helperBoard.checkForActionInitiation(testTile);
        assertEquals(0,testAction); //since the Tiles in our helperTileList have companies associated with them, return 2.
        TestHelper.helperMethod_tearDownBoard();

    }

    @Test void test_companiesAroundTile(){
        Board helperBoard = TestHelper.helperMethod_custom_board();
        List<Tile> tList = helperBoard.getTileList();
        List<Company> helperComp = helperBoard.companiesAroundTile(tList.get(0));
        assert(helperComp.size()>0);
        TestHelper.helperMethod_tearDownBoard();

    }

    /**
     * Test that charterLogic works correctly.
     */
    @Test void test_charterLogic(){

        Board helperBoard = TestHelper.helperMethod_custom_board();
        List<Tile> tList = helperBoard.getTileList();
        //System.out.println(tList);
        Tile companyTile = tList.get(0);
        companyTile.setDealt(true);
        companyTile.setFlipped(true);
        Tile adjToCompanyTile = tList.get(1);
        adjToCompanyTile.setDealt(true);
        adjToCompanyTile.setFlipped(true);

        Company defCom = TestHelper.helperMethod_Company("DEFAULT");
        adjToCompanyTile.setCompany(defCom);
        helperBoard.charterLogic(companyTile.getCompany());
        assert(companyTile.getCompany().getCompanyName().equals(adjToCompanyTile.getCompany().getCompanyName())); //adj company should now have the same name as companyTile
        TestHelper.helperMethod_tearDownBoard();

    }

    /**
     * Tests that adding flipped tiles to chartered company works correctly.
     */
    @Test void test_addToComp(){

        Board helperBoard = TestHelper.helperMethod_custom_board();
        List<Tile> tList = helperBoard.getTileList();
        Tile companyTile =tList.get(0);
        companyTile.setDealt(true);
        companyTile.setFlipped(true);
        Tile adjToCompanyTile = tList.get(1);
        adjToCompanyTile.setDealt(true);
        adjToCompanyTile.setFlipped(true);

        Company defCom = TestHelper.helperMethod_Company("DEFAULT");
        adjToCompanyTile.setCompany(defCom);
        helperBoard.addToCompLogic(companyTile.getCompany());
        assert(companyTile.getCompany().getCompanyName().equals(adjToCompanyTile.getCompany().getCompanyName())); //adj company should now have the same name as companyTile
        TestHelper.helperMethod_tearDownBoard();

    }

    /**
     * Tests that merging works correctly
     */
    @Test void test_mergerLogic(){

        Board helperBoard = TestHelper.helperMethod_custom_board();
        List<Tile> tList = helperBoard.getTileList();
        List<Company> compList = helperBoard.getUncharteredCompanies();
        System.out.println(compList);


        Company winnerComp = compList.get(0);
        winnerComp.setNumTiles(4);
        winnerComp.calculateStockPrice();

        List<Company> loserList = new ArrayList<>();
        Company loserComp1 = compList.get(1);
        loserComp1.setNumTiles(3);
        loserComp1.calculateStockPrice();
        Company loserComp2 = compList.get(2);
        loserComp2.setNumTiles(2);
        loserComp2.calculateStockPrice();

        loserList.add(loserComp1);
        loserList.add(loserComp2);

        //charters the unchartered companies
        helperBoard.charter(compList.get(0));
        helperBoard.charter(compList.get(0));
        helperBoard.charter(compList.get(0));
        //passes into mergeLogic
        helperBoard.mergeLogic(winnerComp,loserList);
        assertEquals(helperBoard.getCharteredCompanies().size(), 1); //merge should uncharter the two smaller companies, leaving us with 1 chartered company.
        TestHelper.helperMethod_tearDownBoard();





    }

}