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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Logic to update the Hotel/company information (top right)
 */
public class UpdateHotel implements Updatable {
    private FXController UIController;

    public void update(GameState gameState, FXController UIController) {
        this.UIController = UIController;
        List<Company> cCompany = gameState.getCurrentBoard().getCharteredCompanies();
        List<Company> uCompany = gameState.getCurrentBoard().getUncharteredCompanies();
        List<Player> playerList = gameState.getPlayerList();
        List<Company> allCompanies = new ArrayList<>();
        setHotelName(playerList);
        for (Company company : cCompany) {
            allCompanies.add(company);
        }
        for (Company company : uCompany) {
            allCompanies.add(company);
        }
        addDataToHotelInformation(allCompanies, playerList);
    }

    /**
     * Simply populates the name headers for the table of stocks.
     */
    private void setHotelName(List<Player> playerList) {
        UIController.getHotelNameObserList().clear();
        //UIController.getHotelNameObserList().add("Available");
        for (Player player : playerList) {
            UIController.getHotelNameObserList().add(player.getName());
        }
        UIController.getHotelNameObserList().add("Size");
        UIController.getHotelNameObserList().add("Price");
        UIController.getHotelNameListView().setItems(UIController.getHotelNameObserList());
    }

    /**
     * Chooses what list to add the company data into based on the company name. (I'm sorry for the switch)
     *
     * @param companyList full list of all the comanies on the board
     * @param playerList  All the current players for # of stocks owned
     */
    private void addDataToHotelInformation(List<Company> companyList, List<Player> playerList) {
        for (Company company : companyList) {
            ListView<Integer> curCompanyListView = new ListView<>();
            ObservableList<Integer> curCompanyObserList = FXCollections.observableArrayList();
            switch (company.getCompanyName()) {
                case "Worldwide" -> {
                    curCompanyObserList = UIController.getWStockObserListView();
                    curCompanyListView = UIController.getWStockListView();
                }
                case "Sackson" -> {
                    curCompanyObserList = UIController.getSStockObserListView();
                    curCompanyListView = UIController.getSStockListView();
                }
                case "Festival" -> {
                    curCompanyObserList = UIController.getFStockObserListView();
                    curCompanyListView = UIController.getFStockListView();
                }
                case "Imperial" -> {
                    curCompanyObserList = UIController.getIStockObserListView();
                    curCompanyListView = UIController.getIStockListView();
                }
                case "American" -> {
                    curCompanyObserList = UIController.getAStockObserListView();
                    curCompanyListView = UIController.getAStockListView();
                }
                case "Continental" -> {
                    curCompanyObserList = UIController.getCStockObserListView();
                    curCompanyListView = UIController.getCStockListView();
                }
                case "Tower" -> {
                    curCompanyObserList = UIController.getTStockObserListView();
                    curCompanyListView = UIController.getTStockListView();
                }
            }
            curCompanyObserList.clear();
            //curCompanyObserList.add(0); // Available stocks left
            addPlayerStocksToHotel(playerList, curCompanyListView, curCompanyObserList, company.getCompanyName());
            curCompanyObserList.add(company.getNumTiles());
            curCompanyObserList.add(company.setNewStockPrice());
            curCompanyListView.setItems(curCompanyObserList);
        }
    }

    /**
     * Calculates the number of stocks the players has for a certain company
     *
     * @param playerList players in the game
     * @param listView   what list to see on the UI
     * @param obserList  what list to add the data to
     * @param company    what company are we checking the player stocks for.
     */
    private void addPlayerStocksToHotel(List<Player> playerList, ListView<Integer> listView, ObservableList<Integer> obserList, String company) {
        for (Player player : playerList) {
            int curNumberOfStocks = 0;
            List<Stock> curPlayerStockList = player.getStockList();
            if (curPlayerStockList != null) {
                for (Stock stock : curPlayerStockList) {
                    if (company.equals(stock.getParentCompany().getCompanyName())) {
                        curNumberOfStocks++;
                    }
                }
            }
            obserList.add(curNumberOfStocks);
            listView.setItems(obserList);
        }
    }
}