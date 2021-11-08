package ProjectAcquire;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StockTest {

    @Test
    void test_stock_init() {

      Stock classUnderTest = TestHelper.helperMethod_customStock("TEST");

        assertTrue(classUnderTest.getParentCompany().getCompanyName().equals("TEST")," the parent company is NOT correctly initialized");
    }

    @Test
    void test_stock_getStockPriceFromComp() {

        Stock classUnderTest = TestHelper.helperMethod_customStock("TEST");

        assertTrue(classUnderTest.getParentCompany().getStockPrice()==100,"Stock price did not match what it should have");
    }
    @Test
    void test_stock_dummy_maxStockBuy() {

        Stock classUnderTest = TestHelper.helperMethod_customStock("TEST");
        Player testPlayer = TestHelper.helperMethod_custom_Player("P1");
        Company testCompany = TestHelper.helperMethod_Company("Comp");

        assertTrue(classUnderTest.maxStockBuy(testPlayer,testCompany)==0,"Stock price did not match what it should have");
    }

}
