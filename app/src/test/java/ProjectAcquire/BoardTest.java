package ProjectAcquire;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {


    Board helperMethod_tile(){
        Board genericBoard = new Board();

        return genericBoard;
    }
    Board helperMethod_custom_board(){
        Tile tile = new Tile();
        List<Tile> helperTileList = new ArrayList<Tile>();
        helperTileList.add(tile);
        helperTileList.add(tile);

        //Chartered companies should start empty
        List<Company> helperCharteredCompList = new ArrayList<Company>();

        //uncharterd company list should have companies in it on start.
        List<Company> helperUncharteredCompanyList = new ArrayList<Company>();

        Company helperComp = new Company();
        Company helperComp2 = new Company();




        List<Player> helperPlayerList = new ArrayList<Player>();
        Player player = new Player();
        helperPlayerList.add(player);

        String coord = "A1";



        Board customBoard = Board.getInstance(helperTileList,helperUncharteredCompanyList,helperCharteredCompList,helperPlayerList);

        return customBoard;
    }


    //This test is shitty for checking proper initialization change it.
    @Test void test_board_init_chartered_companies() {

        // Tile helperTile = helperMethod_custom_tile();

        Board classUnderTest = helperMethod_custom_board();
        //tests that initialized chartered companies is empty
        assertTrue(classUnderTest.getCharteredCompanies().isEmpty(),"Chartered companies list should be empty on initialization of a board, but is not");
    }



}