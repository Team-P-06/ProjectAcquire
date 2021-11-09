package ProjectAcquire;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

}
