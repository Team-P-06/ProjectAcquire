package ProjectAcquire;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class UpdateHotel implements Updatable{
    private final Update UIController = Update.getUIController();

    public void update(GameState gameState){
        List<Company> cCompany = gameState.getgetCurrentBoard().getCharteredCompanies();
        List<Company> uCompany = getCurrentBoard().getUncharteredCompanies();
        List<Player> playerList = gameState.getPlayerList();
        List<Company> allCompanies = new ArrayList<>();
        setHotelName(playerList);
        for(Company company: cCompany){
            allCompanies.add(company);
        }
        for(Company company: uCompany){
            allCompanies.add(company);
        }
        addDataToHotelInformation(allCompanies, playerList);
    }

    /**
     * Simply populates the name headers for the table of stocks.
     * THIS ONLY NEEDS TO BE CALLED ONCE
     */
    private void setHotelName(List<Player> playerList) {
        UIController.getHotelNameObserList().clear();
        UIController.getHotelNameObserList().add("Available");
        for(Player player: playerList){
            UIController.getHotelNameObserList().add(player.getName());
        }
        UIController.getHotelNameObserList().add("Size");
        UIController.getHotelNameObserList().add("Price");
        UIController.getHotelNameListView().setItems(UIController.getHotelNameObserList());
    }

    /**
     * Chooses what list to add the company data into based on the company name. (I'm sorry for the switch)
     * @param companyList full list of all the comanies on the board
     * @param playerList All the current players for # of stocks owned
     */
    private void addDataToHotelInformation(List<Company> companyList, List<Player> playerList){
        for(Company company : companyList){
            ListView<Integer> curCompanyListView = new ListView<>();
            ObservableList<Integer> curCompanyObserList = FXCollections.observableArrayList();
            switch(company.getCompanyName()) {
                case "Worldwide" ->{
                    curCompanyObserList = UIController.getWStockObserListView();
                    curCompanyListView = UIController.getWStockListView();}
                case "Sackson" ->{
                    curCompanyObserList = UIController.getSStockObserListView();
                    curCompanyListView = UIController.getSStockListView();}
                case "Festival" ->{
                    curCompanyObserList = UIController.getFStockObserListView();
                    curCompanyListView = UIController.getFStockListView();}
                case "Imperial" ->{
                    curCompanyObserList = UIController.getIStockObserListView();
                    curCompanyListView = UIController.getIStockListView();}
                case "American" ->{
                    curCompanyObserList = UIController.getAStockObserListView();
                    curCompanyListView = UIController.getAStockListView();}
                case "Continental" ->{
                    curCompanyObserList = UIController.getCStockObserListView();
                    curCompanyListView = UIController.getCStockListView();}
                case "Tower" ->{
                    curCompanyObserList = UIController.getTStockObserListView();
                    curCompanyListView = UIController.getTStockListView();}
                //default -> curCompanyObserList = UIController.getStockObserListView();
            }
            curCompanyObserList.clear();
            curCompanyObserList.add(0); // Available stocks left
            addPlayerStocksToHotel(playerList, curCompanyListView, curCompanyObserList, company.getCompanyName());
            curCompanyObserList.add(company.getTilesOnBoard());
            curCompanyObserList.add(company.getStockPrice());
            curCompanyListView.setItems(curCompanyObserList);
        }
    }

    /**
     * Calculates the number of stocks the players has for a certin company
     * @param playerList players in the game
     * @param listView what list to see on the UI
     * @param obserList what list to add the data to
     * @param company what company are we checking the player stocks for.
     */
    private void addPlayerStocksToHotel(List<Player> playerList, ListView<Integer> listView, ObservableList<Integer> obserList, String company){
        for(Player player: playerList){
            int curNumberOfStocks = 0;
            List<Stock> curPlayerStockList = player.getStockList();
            if (curPlayerStockList != null){
                for(Stock stock: curPlayerStockList) {
                    if (company.equals(stock.getParentCompany().getCompanyName())) {
                        curNumberOfStocks++;
                    }
                }
            }
            obserList.add(curNumberOfStocks);
        }
    }
}
