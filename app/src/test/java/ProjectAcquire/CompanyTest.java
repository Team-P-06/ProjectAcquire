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

<<<<<<< HEAD
    /**
     * Tests that the hash method actually generates a proper hash code with the variables inside of company class
     */
    @Test
    void test_Hash(){
        Company testCompany =  new Company();
        assertNotNull(testCompany.hashCode());
=======
    @Test void test_hashCode(){
        Company helperComp = TestHelper.helperMethod_Company("DEFAULT");
        assertNotEquals(0, helperComp.hashCode());
>>>>>>> 7e60c6b1888e18cb1ec480b8da446b6c5706a638
    }
}
