/**
 * @author Show Pratoomratana, Team 404 Project Acquire
 * @version v0.0.1
 */

package ProjectAcquire;

public class Stock {
    private Company parentCompany;


    Stock(Company parentCompany){
        this.parentCompany = parentCompany;
    }

    /**
     *
     * @return the stock's parent company
     */
    public Company getCompany(){
        return  parentCompany;
    }

    /**
     *
     * @return the price of the stocks based on it's company's worth
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
}
