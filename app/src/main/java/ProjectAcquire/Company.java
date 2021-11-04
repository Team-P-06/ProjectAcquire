//Company.java
/** @author Alex Diviney, Team 404, Project Aquire
 * @version v0.0.1
 */


package ProjectAcquire;

public class Company {

   private String companyName;
   private int tilesOnBoard;
   private int stockPrice;
   private boolean chartered;
   private boolean isPermanent;


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

    /**
     * @return the name of the Company
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @return the number of tiles on the board
     */
    public int getTilesOnBoard() {
        return tilesOnBoard;
    }

    /**
     * @return the current stock price
     */
    public int getStockPrice() {
        return stockPrice;
    }

    /**
     * @return whether the company is currently chartered or not
     */
    public boolean isChartered() {
        return chartered;
    }

    /**
     *
     * @return whether the company is permanent or not
     */
    public boolean isPermanent() {
        return isPermanent;
    }

    /** integer is passed in and the stockPrice is set to that integer
     *
     * @param stockPrice an int to set the stock price to
     */
    public void setStockPrice(int stockPrice) {
        this.stockPrice = stockPrice;
    }

    /** integer is passed in and the tilesOnBoard is set to that integer
     *
     * @param tilesOnBoard number of tiles
     */
    public void setTilesOnBoard(int tilesOnBoard) {
        this.tilesOnBoard = tilesOnBoard;
    }

    /** boolean is passed in and chartered is set to that boolean
     *
     * @param chartered whether company is chartered or not
     */
    public void setChartered(boolean chartered) {
        this.chartered = chartered;
    }

    /** IMPORTANT: this setter is private right now, but I don't remember why
     * boolean is passed in and permanent is set to that boolean.
     *
     * @param permanent whether the company is permanent or not
     */
    private void setPermanent(boolean permanent) {
        this.isPermanent = permanent;
    }



}
