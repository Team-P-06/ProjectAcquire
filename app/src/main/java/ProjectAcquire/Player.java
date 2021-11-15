/**
 * @author Team 404
 * @version v0.0.1
 */

package ProjectAcquire;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

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
     * Places a tile on the board
     * UI calls this when clicking on a tile that's in the players hand, this then goes to Board.checkForActionInitiation().
     * @param tile the tile that was placed
     * @param gameState the current gamestate
     * depreciated, since we need both gamestate and board, this has been moved to GameState and called getTileChoice()
     */
    public void placeTile(Tile tile) throws IOException {

        try {
            //Sets the tile to be flipped.
            tile.setFlipped();

            //removes from the player's hand.
            tileList.remove(tile);
            // Board curBoard = Board.getInstance();

            //curBoard.checkForActionInitiation(tile, gameState);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Player chooses to buy 1 - 3 stocks
     * @param stock what company the stocks are coming from
     */
    public void buyStock(Stock stock){
        stockList.add(stock);
        setMoney(getMoney() - stock.getParentCompany().getStockPrice());
    }

    /**
     * Player chooses to sell 1 - 3 stocks
     * Update action already calculated if they have enough stocks to sell.
     * @param parentCompany stocks from the company they would like to sell.
     * @param numberOfStocks the number of stocks they are selling
     */
    public void sellStock(Company parentCompany, int numberOfStocks){
        for (Stock stock : stockList){
            if(numberOfStocks > 0) {
                if (stock.getParentCompany() == parentCompany) {
                    stockList.remove(stock);
                    setMoney(getMoney() + stock.getParentCompany().getStockPrice());
                    numberOfStocks--;
                }
            }
        }
    }

    /**
     * Keep the stocks from a company after a merge
     * Update action already calculated if they have enough stocks to keep
     * @param parentCompany stocks from the company they would like to keep.
     * @param numberOfStocks the number of stocks they are keep
     */
    public void keepStock(Company winnerCompany, int numberOfStocks){
        // Do nothing
    }

    /**
     * Trades stocks from a defunct company to the larger company
     * Currently doesn't remove stock from the defunct company.
     * @param parentCompany stocks from the company they would like to sell.
     * @param numberOfStocks the number of stocks they are selling
     */
    public void tradeStock(Company winnerCompany, int numberOfStocks){
        int stocksTraded = 0;
        for (int i = 0; i < numberOfStocks; i++){
            Stock newStock = new Stock(winnerCompany);
            stockList.add(newStock);
        }
    }


    /**
     * Removes a tile from the players hand
     */
    public void discardTile(Tile tile){
    tileList.remove(tile);
    }

    /**
     * Adds a tile to the player's hand
     *
     * @param tile Tile to add to the players hand
     */
    public void addTile(Tile tile){
        tileList.add(tile);
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
    public boolean availableTile(){
        return true;
    }
}

