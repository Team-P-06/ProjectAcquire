package ProjectAcquire;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class TestHelper {

//methods must be static since this is basically just moving helper methods from individual tests to one file



    static Company helperMethod_Company(String companyName){

        Company helperComp = new Company(companyName,100,false,false);

        return helperComp;

    }

    static List<Company> helperMethod_CompanyList(){
        List<Company> helperCompanyList = new ArrayList<Company>(); //initialize list

        //initializes helper companies
        Company helperCompany1 = helperMethod_Company("COMPANY1");
        Company helperCompany2 = helperMethod_Company("COMPANY2");
        Company helperCompany3 = helperMethod_Company("COMPANY3");

        //adds companies to list
        helperCompanyList.add(helperCompany1);
        helperCompanyList.add(helperCompany2);
        helperCompanyList.add(helperCompany3);

        return helperCompanyList;
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
    static Tile helperMethod_custom_tile_via_coord_and_companyName(int[] coord, String companyName){

        Company helperComp = helperMethod_Company(companyName);

        Tile customTile = new Tile(helperComp, coord);

        return customTile;
    }



    static List<Tile> helperMethod_tileList_company1_3_coord_A1_A3(){
        ArrayList<Tile> testTileList = new ArrayList<Tile>();
        int[] coord1 = {0,1};
        int[] coord2 = {0,2};
        int[] coord3 = {0,3};

        Tile tile = helperMethod_custom_tile_via_coord_and_companyName(coord1,"TEST1");
        Tile tile2 = helperMethod_custom_tile_via_coord_and_companyName(coord2,"TEST2");
        Tile tile3 = helperMethod_custom_tile_via_coord_and_companyName(coord3,"TEST3");

        testTileList.add(tile);
        testTileList.add(tile2);
        testTileList.add(tile3);

        return testTileList;
    }


    /**
     * Creates a Player object with a custom name, a Custom Tile List, and 1000 dollars
     * @param playerName The name of the player to create
     * @return A Player
     */
    static Player helperMethod_custom_Player(String playerName){
        //name, list of tiles, money
        List<Tile> customTileList = helperMethod_tileList_company1_3_coord_A1_A3(); //calls Custom Tile list
        Player helperPlayer = new Player(playerName,customTileList,1000);
        return helperPlayer;
    }

    static Stock helperMethod_customStock(String nameOfCompany){

        Company helperComp = TestHelper.helperMethod_Company(nameOfCompany);
        Stock classUnderTest = new Stock(helperComp);

        return classUnderTest;

    }

    /**
     *
     * @return a Board instance for testing
     */
    @Before
    static Board helperMethod_custom_board(){
        //Tile tile = helperMethod_custom_tile_via_coord_and_companyName("A1","TEST");

        List<Tile> helperTileList = helperMethod_tileList_company1_3_coord_A1_A3();

        //Chartered companies should start empty
        List<Company> helperCharteredCompList = new ArrayList<Company>();

        //uncharterd company list should have companies in it on start.
        List<Company> helperUncharteredCompanyList = helperMethod_CompanyList();

        List<Player> helperPlayerList = new ArrayList<Player>();
        Player player = new Player();
        helperPlayerList.add(player);

        Board customBoard = Board.getInstance(helperTileList,helperUncharteredCompanyList,helperCharteredCompList,helperPlayerList);
        //System.out.println(customBoard.toString());
        return customBoard;
    }

    @After
    static void helperMethod_tearDownBoard(){
        Board oldBoard = helperMethod_custom_board();
        oldBoard.setNull();
    }


    static GameState helperMethod_GameStateInit(){


        /*
        precheck: UI initializes with a starting screen. Player chooses either to exit, start, or load. if load, then call the loadGame() function.
        App starts the UI with start(Stage stage) (Not sure if that can be changed), but from there selecting new game can start this e.g: Game.start()
        Load game can call Game.loadGame() instead - show
        If start, then do the following:
         */



        //1. queries for how many players there are, (UI), then adds x amount of players to a playerList.
        //This is just a static creation of players for now, will be more dynamic in the future.
        List<Tile> test_playerTileList = new ArrayList<>();
        LinkedList<Player> test_playerList = new LinkedList<>();
        Player test_player1 = TestHelper.helperMethod_custom_Player("P1");
        Player test_player2 = TestHelper.helperMethod_custom_Player("P2");
        Player test_player3 = TestHelper.helperMethod_custom_Player("P3");

        test_playerList.add(test_player1);
        test_playerList.add(test_player2);
        test_playerList.add(test_player3);


        //2. list of tiles.
        List<Tile> test_boardTileList = TestHelper.helperMethod_tileList_company1_3_coord_A1_A3();

        //Grab companies from tiles.
        Company testTileList_company1 = test_boardTileList.get(0).getCompany();
        Company testTileList_company2 = test_boardTileList.get(1).getCompany();
        Company testTileList_company3 = test_boardTileList.get(2).getCompany();

        //3. creates our list of chartered and unchartered companies, add tile companies to them.
        List<Company> test_charteredList = new ArrayList<Company>();
        List<Company> test_uncharteredList = new ArrayList<Company>();
        test_uncharteredList.add(testTileList_company1);
        test_uncharteredList.add(testTileList_company2);
        test_uncharteredList.add(testTileList_company3);

        //4. set a current player
        // Isn't the current/next player set when we create the gameState?
        // We would already need a GameState object to call GameState.setCurrentPlayer - Show

        //5. initialize a board using 1-4 as our parameters
        Board board = Board.getInstance(test_playerTileList, test_uncharteredList, test_charteredList, test_playerList);

        //6. initialize our GameState using board and our playerlist as parameters
        // This makes p1 current player and p2 next
        GameState gameState = new GameState(board, test_playerList);

        //7. call setCurrentGame() using 6 as our parameter
        //setCurrentGameState(gameState);
        return gameState;
    }



}