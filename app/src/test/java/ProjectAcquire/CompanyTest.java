/**
 *  @author Team 404
 *  @version v0.0.1
 */
package ProjectAcquire;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CompanyTest {
    /**
     * Test that checks if the toString method in company actually makes a string with the company attributes
     */
    @Test
    void toString_Test(){
        Company testCompany = new Company("testCom", 200, false, false);
        assertNotNull(testCompany.toString());
    }

    /**
     * Test that checks the instance in company ledger class to make sure that the get instance method is actually
     * returning a new company ledger object whenever the instance is null
     */
    @Test
    void test_CompanyLedger(){
        CompanyLedger test = new CompanyLedger();
        assertNotNull(test.getInstance());
    }

    @Test void test_hashCode(){
        Company helperComp = TestHelper.helperMethod_Company("DEFAULT");
        assertNotEquals(0, helperComp.hashCode());
    }
}
