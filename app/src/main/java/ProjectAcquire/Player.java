/**
 * @author Team 404
 * @version v0.0.1
 */

package ProjectAcquire;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

/**
 * Class to manage the players. This holds their stats and actions
 */
public class Player{
    @Getter @Setter private String name;
    @Getter @Setter private int money;
    @Getter @Setter private List<Stock> stockList;
    @Getter @Setter private List<Tile> tileList;

    /**
     * Default constructor with dummy values
     */
    Player(){
        this.name = "player1";
        this.money = 400;
        List<Stock> stockList = new ArrayList<>();
        List<Tile> tileList = new ArrayList<>();
    }

    /**
     *
     * @param name name of the player
     * @param tileHand the 6 tiles the player holds at any given time
     * @param money how much money they have
     */
    Player(String name, List<Tile> tileHand, Integer money){
        this.name = name;
        this.money = money;
        this.tileList = tileHand;
    }

    /**
     * Getters for all the player variables
     * @return specified getter
     */
//    public String getName(){ return name; }
//    public int getMoney(){ return money; }
//    public List<Stock> getStockList(){ return stockList; }
//    public List<Tile> getTileList(){ return tileList; }

    /**
     * Setters for all the players values
     * @param money/name of player
     */
//    private void setMoney(int money){ this.money = money; }
//    private void setName(String name){ this.name = name; }

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

        stockList.add(stock);
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



    /**
     * ALEX NOTE: This Method Will not work as written, we should probably move it to Board and change the name to "dealTile".
     * This is because we are initializing arbitrary tiles here when we should be initializing all tiles in our Game inititalize() method.
     * DrawTile may be best as a passive action.
     *
     * creates a new unique tile and give it to the player who drew it.
     * @return a new unique tile
     */
    public Tile drawTile(){

   Tile drawnTile = new Tile(); //DEFAULT TILE
        tileList.add(drawnTile);
        return drawnTile;
    }

    /**
     * Removes a tile from the players hand
     */
    public void discardTile(Tile tile){
    tileList.remove(tile);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return money == player.money && Objects.equals(name, player.name) && Objects.equals(stockList, player.stockList) && Objects.equals(tileList, player.tileList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, money, stockList, tileList);
    }

    @Override
    public String toString() {

        String retString1 = name;

        String retString2 = "Player{" +
                "name='" + name + '\'' +
                ", money=" + money +
                ", stockList=" + stockList +
                ", tileList=" + tileList +
                '}';

        return retString1;
    }
}

