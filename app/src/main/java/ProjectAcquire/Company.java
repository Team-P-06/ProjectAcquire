/**
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
 * @version v1.0.0
 */


package ProjectAcquire;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Company {

     private String companyName;
     private List<Tile> tilesOnBoard;
     private int numTiles;
     private int stockPrice;
     private boolean chartered;
     private boolean isPermanent;


    /**
     * Default constructor
     */
    public Company(){
        this.companyName = "DEFAULT";
        this.stockPrice = 100;
        this.chartered = false;
        this.isPermanent = false;
        this.numTiles = 0;
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
        this.numTiles = 0;
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

    /**
     * Calculates the stock price of the companies based on how many tiles they hold
     * @return
     */
    public int calculateStockPrice(){
        int stockPrice = 0;
        switch (numTiles){
            case 0 -> stockPrice = 0;
            case 1 -> stockPrice = 0;
            case 2 -> stockPrice = 200;
            case 3 -> stockPrice = 300;
            case 4 -> stockPrice = 400;
            case 5 -> stockPrice = 500;
        }
        if (numTiles >=6 && numTiles <= 10){ stockPrice = 700;}
        else if (numTiles >=11 && numTiles <= 20){ stockPrice = 800;}
        else if (numTiles >=21 && numTiles <= 30){ stockPrice = 900;}
        else if (numTiles >=31 && numTiles <= 40){ stockPrice = 800;}
        else if (numTiles >= 32) {stockPrice =  1000; }

        if (companyName.equals("Worldwide") || companyName.equals("Sackson")){
            stockPrice +=0;
        }
        else if (companyName.equals("Festival") || companyName.equals("Imperial") || companyName.equals("American")){
            stockPrice += 100;
        }
        else if (companyName.equals("Continental") || companyName.equals("Tower")){
            stockPrice += 200;
        }
        else if (companyName.equals("DEFAULT")) { stockPrice = 0; }
        return stockPrice;
    }

    /**
     * Majority payout is simply the stock price times 10
     * @return the payout to the majority holder
     */
    public int getMajorityPayout(){
        return calculateStockPrice() * 10;
    }

    /**
     * Minority payout is half of the majority payout.
     * @return the payout to all players who are not the majority holder.
     */
    public int getMinorityPayout(){
        return getMajorityPayout() / 2;
    }

    /**
     * Sets the new stock price for a given company
     */
    public int setNewStockPrice(){
        return stockPrice = calculateStockPrice();
    }
}
