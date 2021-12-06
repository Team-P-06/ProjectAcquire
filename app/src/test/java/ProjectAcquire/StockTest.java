/**
 *  @author Team 404
 *  @version v0.0.1
 */
package ProjectAcquire;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StockTest {

    /**
     * Test the stock initializer method to make sure that it properly generates a stock object with specific attributes
     */
    @Test
    void test_stock_init() {
      Stock classUnderTest = TestHelper.helperMethod_customStock("TEST");

        assertNotNull(classUnderTest," the parent company is NOT correctly initialized");
    }

    /**
     * Test that get stock price from company properly gets the stock price that is listed under a specific company
     */
    @Test
    void test_stock_getStockPriceFromComp() {

        Stock classUnderTest = TestHelper.helperMethod_customStock("TEST");

        assertTrue(classUnderTest.getStockPriceFromCompany()==0,"Stock price did not match what it should have");
    }

    /**
     * Test that the max stock buy method properly gets a max stock buy so when the stocks of a company isn't available
     * anymore it will be zero (no chance at buying since it's maxed out)
     */
    @Test
    void test_stock_dummy_maxStockBuy() {

        Stock classUnderTest = TestHelper.helperMethod_customStock("TEST");
        Player testPlayer = TestHelper.helperMethod_custom_Player("P1");
        Company testCompany = TestHelper.helperMethod_Company("Comp");

        assertTrue(classUnderTest.maxStockBuy(testPlayer,testCompany)==0,"Stock price did not match what it should have");
    }

    /**
     * Test that the to string method properly generates a string for us with the proper conatined attributes inside of
     * the class
     */
    @Test
    void test_stock_toString() {
        Company testCompany = TestHelper.helperMethod_Company("TEST");
        Stock classUnderTest = new Stock(testCompany);

        assertTrue(classUnderTest.toString().equals("TEST")," the parent company is NOT correctly initialized");
    }

    /**
     * Test that calculates the stock price of a company that has company tiles on the board. Since there is company tiles
     * the calculate price method should make the price of a stock not the same as when it has more or less tiles on
     * a board
     */
    @Test void test_stock_price_with_variable_company_tiles(){
        Company testCompany = TestHelper.helperMethod_Company("Continental");
        testCompany.setNumTiles(15);
        assertEquals(1000, testCompany.calculateStockPrice(), "The stock calculation is not correct");
    }

    /**
     * Test that calculates to majority payout to players when merges happen
     */
    @Test void test_MajorityPayout(){
        Company testCompany = TestHelper.helperMethod_Company("Imperial");
        testCompany.setNumTiles(22);
        assertEquals(10000 , testCompany.getMajorityPayout(), "The majority payout is incorrect");
    }

    /**
     * Test that calculates the minor payout to players when merges happen
     */
    @Test void test_getMinoryPayout(){
        Company testCompany = TestHelper.helperMethod_Company("Worldwide");
        testCompany.setNumTiles(5);
        assertEquals(2500 , testCompany.getMinorityPayout(), "The minority payout is incorrect");
    }

    /**
     * Test that the stock price is properly calculates when only 1 tiles of a company is on the board. This dffers from
     * the above calculate methods since it will be the default stock price with one tile on board
     */
    @Test void test_stock_price_with_1_tile(){
        Company testCompany = TestHelper.helperMethod_Company("Sackson");
        testCompany.setNumTiles(1);
        assertEquals(0 , testCompany.calculateStockPrice(), "The company with 1 tile has a stock price");
    }

    /**
     * Test that computes the default company price of a stock
     */
    @Test void test_default_company_price(){
        Company testCompany = TestHelper.helperMethod_Company("DEFAULT");
        testCompany.setNumTiles(90);
        assertEquals(0 , testCompany.calculateStockPrice(), "The default company has a stock price");
    }

}
