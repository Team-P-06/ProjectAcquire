package ProjectAcquire;
import org.junit.After;
import org.junit.Before;
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
    static Tile helperMethod_custom_tile_via_coord_and_companyName(String coord, String companyName){

        Company helperComp = helperMethod_Company(companyName);

        Tile customTile = new Tile(helperComp, coord);

        return customTile;
    }



    static List<Tile> helperMethod_tileList_company1_3_coord_A1_A3(){
        ArrayList<Tile> testTileList = new ArrayList<Tile>();
        Tile tile = helperMethod_custom_tile_via_coord_and_companyName("A1","TEST1");
        Tile tile2 = helperMethod_custom_tile_via_coord_and_companyName("A2","TEST2");
        Tile tile3 = helperMethod_custom_tile_via_coord_and_companyName("A3","TEST3");

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
    static void helperMethod_tearDown(){
        Board oldBoard = helperMethod_custom_board();
        oldBoard.setNull();
    }

}
