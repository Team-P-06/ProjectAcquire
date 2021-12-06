/**
 * Stock.java
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


import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

public class Stock {

    @Generated @Getter @Setter private Company parentCompany;

    /**
     * Custom constructor
     * @param parentCompany
     */
    Stock(Company parentCompany){
        this.parentCompany = parentCompany;
    }


    /**
     * Calculate the stock price of a company and then gets the stock price
     * @return the price of the stocks based on its company's worth
     */
    public int getStockPriceFromCompany(){

        return parentCompany.calculateStockPrice();
    }

    /**
     * Calculates the max stock buy a player can buy from with their available money
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