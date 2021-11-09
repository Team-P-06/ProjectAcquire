/**
 * @author Team 404
 * @version v0.0.1
 */


package ProjectAcquire;


import lombok.AccessLevel;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

public class Stock {


    /**
     * Getter and setter for parentCompany
     */

    @Generated @Getter @Setter private Company parentCompany;

    //Custom Constructor
    Stock(Company parentCompany){
        this.parentCompany = parentCompany;
    }


    /**
     *
     * @return the price of the stocks based on its company's worth
     */
    public int getStockPriceFromCompany(){

        return parentCompany.getStockPrice();
    }

    /**
     * ???
     * @param player
     * @param company
     * @return
     */
    public int maxStockBuy(Player player, Company company){
    return 0; //DEFAULT
    }

    /**
     *
     * @return a toString of the stock
     */
    @Override public String toString() {

        return  parentCompany.getCompanyName();
    }

}


