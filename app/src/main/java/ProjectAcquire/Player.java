/**
 * Player.java
 *
 * MIT License
 *
 * Copyright (c) 2021 404
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * @author Team 404
 * @version v1.1.0
 */

package ProjectAcquire;

import java.io.IOException;
import java.util.*;

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
     * Player constructor
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
     */
    public void placeTile(Tile tile) throws IOException {
        try {
            tile.setFlipped(true);
            tileList.remove(tile);
            tile.setDealt(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Player chooses to buy 1 - 3 stocks
     * @param company what company the stocks are coming from
     */
    public void buyStock(Company company){
        Stock stock = new Stock(company);
        stockList.add(stock);
        setMoney(getMoney() - stock.getParentCompany().calculateStockPrice());
    }

    /**
     * Player chooses to sell 1 - 3 stocks
     * Update action already calculated if they have enough stocks to sell.
     * @param defunctCo stocks from the company they would like to sell.
     * @param numberOfStocks the number of stocks they are selling
     */
    @Generated //Have to actually test this inside of the game UI
    public void sellStock(Company defunctCo, int numberOfStocks){
        Iterator<Stock> stockIter = stockList.iterator();
        while (stockIter.hasNext()){
            Stock curStock = stockIter.next();

            if (curStock.getParentCompany() == defunctCo && numberOfStocks > 0) {
                stockIter.remove();
                setMoney(getMoney() + curStock.getParentCompany().calculateStockPrice());
                numberOfStocks--;
            }
        }
    }

    /**
     * Keep the stocks from a company after a merge
     * Update action already calculated if they have enough stocks to keep
     * No action is needed, since the price of a stock is dynamically calculated with company.calculateStockPrice()
     * @param defunctCo stocks from the company they would like to keep.
     * @param numberOfStocks the number of stocks they are keep
     */
    @Generated //No code to test
    public void keepStock(Company defunctCo, int numberOfStocks){
        // No editing of the player stocks is needed.
    }

    /**
     * Trades stocks from a defunct company to the larger company
     * @param winnerCompany the company that won the merge
     * @param defunctCompany stocks from the company they would like to sell.
     * @param numberOfStocks the number of stocks they are selling
     */
    @Generated //Have to actually test this inside of the game UI
    public void tradeStock(Company winnerCompany, Company defunctCompany, int numberOfStocks){
        for (int i = 0; i < numberOfStocks; i++){ // Give the player the winning stock
            Stock newStock = new Stock(winnerCompany);
            stockList.add(newStock);
        }

        Iterator<Stock> stockIter = stockList.iterator();
        while (stockIter.hasNext()){ // remove the defunct company stocks
            Stock curStock = stockIter.next();
            if(numberOfStocks > 0 && curStock.getParentCompany() == defunctCompany) {
                stockIter.remove();
                numberOfStocks--;
            }
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

    /**
     * Counts the number of stock in the player stock list to see if the input is valid
     * @param company the company the stocks are coming from
     * @return the number of stocks the player has for that company.
     */
    public int countStocks (Company company){
        int numberOfStocks = 0;
        if(stockList != null) {
            List<Stock> fullStockList = stockList;
            for (Stock stock : fullStockList) {
                if (stock.getParentCompany().equals(company)) {
                    numberOfStocks++;
                }
            }
        }
        return numberOfStocks;
    }

    /**
     * Take in an object.class and gets all of the object attributes we need for our board
     * @param o
     * @return Boolean
     */
    @Override @Generated //Tested inside of the UI because no assertion tests are applicable to fully test
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return money == player.money && Objects.equals(name, player.name) && Objects.equals(stockList, player.stockList) && Objects.equals(tileList, player.tileList);
    }

    /**
     * Create a hash code for all of our instance variables inside of this class
     * @return int hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, money, stockList, tileList);
    }

    /**
     * Creates a string for all of our instance variables insdie of this class
     * @return string
     */
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

    /**
     * Checks to see if a tile is available to play
     * @return tile
     */
    public boolean availableTile(){
        return true;
    }
}

