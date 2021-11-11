/**
 * @author Team 404
 * @version v0.0.1
 */


package ProjectAcquire;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Company {

    private String companyName;
    @Setter private int tilesOnBoard;
    @Setter private int stockPrice;
    @Setter private boolean chartered;
    @Setter private boolean isPermanent;


    /**
     * Default constructor
     */
    public Company(){
        this.companyName = "TEST";
        this.stockPrice = 100;
        this.chartered = false;
        this.isPermanent = false;
        this.tilesOnBoard = 0;

    }

    /**
     * Class constructor
     * @param cn name of the company
     * @param sp default stock price of company
     * @param chart default charter state of company
     * @param perm whether the company is permanent.
     */
    public Company(String cn, int sp, boolean chart, boolean perm){
        this.companyName = cn;
        this.stockPrice = sp;
        this.chartered = chart;
        this.isPermanent = perm;
        this.tilesOnBoard = 0;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyName='" + companyName + '\'' +
                ", tilesOnBoard=" + tilesOnBoard +
                ", stockPrice=" + stockPrice +
                ", chartered=" + chartered +
                ", isPermanent=" + isPermanent +
                '}';
    }
}
