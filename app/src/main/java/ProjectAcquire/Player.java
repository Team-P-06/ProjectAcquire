package ProjectAcquire;

import java.util.List;

/**
 * Class to manage the players. This holds their stats and actions
 */
public class Player {
    String name;
    int money;
    List<Stock> stockList;
    List<Tile> tileList;

    /**
     *
     * @param name name of the player
     * @param tileHand the 6 tiles the player holds at any given time
     * @param money how much mony they have
     */
    Player(String name, List<Tile> tileHand, Integer money){
        this.name = name;
        this.money = money;
        this.tileList = tileHand;
    }

    /**
     *
     * @return Checks if the current tile on the board is occupied.
     */
    public boolean availableTile(String coord){
        return true;
    }

    /**
     * Places a tile on the board
     */
    public void placeTile(Tile tile){

    }

    /**
     * Player chooses to buy 1 - 3 stocks
     * @param stock what company the stocks are coming from
     */
    public void buyStock(Stock stock){

    }

    /**
     * Player cheese to buy 1 - 3 stocks
     * @param stock what company the stocks come from
     */
    public void sellStock(Stock stock){

    }

    /**
     * Keep the stocks from a company after a merge
     * @param amount number of stocks you'd like to keep. 0 - number of stocks the player owns
     */
    public void keepStock(int amount){

    }

    /**
     * Trades stocks from a defunct company to the larger company
     * @param amount numer of stocks to trade. 0 - number of stocks the player owns.
     */
    public void tradeStock(int amount){

    }

}
