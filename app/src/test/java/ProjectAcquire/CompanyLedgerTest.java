package ProjectAcquire;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class CompanyLedgerTest {


    @Test
    void test_getInstance(){

        CompanyLedger test_ledger = CompanyLedger.getInstance();
        assertNotNull(test_ledger);
        CompanyLedger.setInstance(null);

    }


}
