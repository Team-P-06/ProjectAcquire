package ProjectAcquire;

public class Stock {
    Company parentCompany;


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
        /*parent = getCompany();
        return parent.getStockPrice();*/
    }

    /**
     * ???
     * @param player
     * @param company
     * @return
     */
    public int maxStockBuy(Player player, Company company){

    }
}
