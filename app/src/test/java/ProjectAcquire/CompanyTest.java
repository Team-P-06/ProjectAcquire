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
}
