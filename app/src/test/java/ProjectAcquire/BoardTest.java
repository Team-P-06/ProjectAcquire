package ProjectAcquire;

import org.junit.After;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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

    @Test void test_board_company_after_charter() {

        Board classUnderTest = TestHelper.helperMethod_custom_board();

        Company boardCompanyToCharter = classUnderTest.getUncharteredCompanies().get(0);

        classUnderTest.charter(boardCompanyToCharter);

        assertTrue(boardCompanyToCharter.isChartered(), "Charter method did not work properly");
        TestHelper.helperMethod_tearDownBoard();

    }

    @Test void test_board_company_after_uncharter() {

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
        classUnderTest.updateBoard();
        assertTrue(1==1);

    }

    /**
     * Tests the checkPermanent method. Real implementation will cause this test to fail.
     */
    @Test void test_checkPermanent(){

        Board classUnderTest = TestHelper.helperMethod_custom_board();
        assertFalse(classUnderTest.checkPermanent("A1"));
    }



    }