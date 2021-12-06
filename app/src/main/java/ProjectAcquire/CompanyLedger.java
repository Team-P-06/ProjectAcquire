//CompanyLedger.java



//ALEX NOTE: This is a really bad way to do things, this is kind of like a local database that only holds a couple fields
//I did it this way so that I could store a chartering tile outside of the class where it is instantiated.

package ProjectAcquire;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
   class CompanyLedger{
    @Setter
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
