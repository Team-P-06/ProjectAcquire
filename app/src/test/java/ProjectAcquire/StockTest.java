package ProjectAcquire;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StockTest {

    @Test
    void test_stock_init() {
      Stock classUnderTest = TestHelper.helperMethod_customStock("TEST");

        assertNotNull(classUnderTest," the parent company is NOT correctly initialized");
    }


    @Test
    void test_stock_getStockPriceFromComp() {

        Stock classUnderTest = TestHelper.helperMethod_customStock("TEST");

        assertTrue(classUnderTest.getStockPriceFromCompany()==100,"Stock price did not match what it should have");
    }
    @Test
    void test_stock_dummy_maxStockBuy() {

        Stock classUnderTest = TestHelper.helperMethod_customStock("TEST");
        Player testPlayer = TestHelper.helperMethod_custom_Player("P1");
        Company testCompany = TestHelper.helperMethod_Company("Comp");

        assertTrue(classUnderTest.maxStockBuy(testPlayer,testCompany)==0,"Stock price did not match what it should have");
    }


    @Test
    void test_stock_toString() {
        Company testCompany = TestHelper.helperMethod_Company("TEST");
        Stock classUnderTest = new Stock(testCompany);

        assertTrue(classUnderTest.toString().equals("TEST")," the parent company is NOT correctly initialized");
    }

    @Test void test_stock_price_with_variable_company_tiles(){
        Company testCompany = TestHelper.helperMethod_Company("Continental");
        testCompany.setTilesOnBoard(15);
        assertEquals(1000, testCompany.calculateStockPrice(), "The stock calculation is not correct");
    }

    @Test void test_MajorityPayout(){
        Company testCompany = TestHelper.helperMethod_Company("Imperial");
        testCompany.setTilesOnBoard(22);
        assertEquals(10000 , testCompany.getMajorityPayout(), "The majority payout is incorrect");
    }

    @Test void test_getMinoryPayout(){
        Company testCompany = TestHelper.helperMethod_Company("Worldwide");
        testCompany.setTilesOnBoard(5);
        assertEquals(2500 , testCompany.getMinoryPayout(), "The minority payout is incorrect");
    }

    @Test void test_stock_price_with_1_tile(){
        Company testCompany = TestHelper.helperMethod_Company("Sackson");
        testCompany.setTilesOnBoard(1);
        assertEquals(0 , testCompany.calculateStockPrice(), "The company with 1 tile has a stock price");
    }

    @Test void test_default_company_price(){
        Company testCompany = TestHelper.helperMethod_Company("DEFAULT");
        testCompany.setTilesOnBoard(90);
        assertEquals(0 , testCompany.calculateStockPrice(), "The default company has a stock price");
    }

}
