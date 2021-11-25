package ProjectAcquire;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
   class CompanyLedger{
    private static CompanyLedger instance;
    private Tile charterTile;
    private Company charterComp;
    public static CompanyLedger getInstance(){
        if(instance==null){
            instance = new CompanyLedger();
        }
        return instance;
    }

}
