package ProjectAcquire;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import lombok.Getter;
import org.checkerframework.checker.guieffect.qual.UI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Update implements Updatable{

    @Getter final FXController UIController = new FXController();
    static final UpdateBoard boardUpdater = new UpdateBoard();
    static final UpdateHotel hotelUpdater = new UpdateHotel();
    static final UpdatePlayer playerUpdater = new UpdatePlayer();
    static final UpdateAction actionUpdater = new UpdateAction();

    /**
     * Main update logic that branches out and updates different parts of the UI(Player data, Board, Company stocks, and stock options
     */
    public void update(GameState gameState) throws IOException {
        UIController.getMainStage().hide();
        UIController.showBoardMenu(UIController.getGameBoardLoader());
        boardUpdater.update(gameState);
        hotelUpdater.update(gameState);
        updatePlayerInfo(gameState.getPlayerList(), gameState.nextTurn());
        updateChoiceInfo(gameState);
    }

    /*/**
     * Creates 108 buttons and assigns them to a place on the grid with colors and name.
     * If our tiles have a name/int from 0 - 107 associated with them you can find the respective col/row by such
     * row = n/12 (round down)
     * col = n % 12
     * The list of tiles will need to pull from the tile pool and players pool to properly color and associate them with a company.
     */
    /*private void updateBoard(List<Player> playerList, List<Tile> tilesNotInPlayerHand, Player currentPlayer) {

        for (Tile tile: tilesNotInPlayerHand) {
            Button currentButton = setButtonProperties(tile.getCompany().getCompanyName());
            currentButton.setText(getTileCoord(tile));
            UIController.getTileGrid().add(currentButton, calculateCol(tile.getCoord()), calculateRow(tile.getCoord()));
        }

        for(Player player: playerList)
            if(player.equals(currentPlayer)) {
                makeCurrentPlayerTiles(player.getTileList(), player); }
            else {
                makeOtherPlayerTile(player.getTileList()); }
    }

    private void makeCurrentPlayerTiles(List<Tile> tileList, Player player){
        for(Tile tile: tileList) {
            Button currentButton = new Button();
            currentButton.setStyle("-fx-border-color: red");
            currentButton.setStyle(getTileCoord(tile));
            currentButton.setOnAction(action -> { System.out.println("YASSSS");/*player.placeTile(tile)*/;});
        /*}
    }

    private void makeOtherPlayerTile(List<Tile> tileList){
        for(Tile tile: tileList) {
            Button currentButton = new Button();
            currentButton.setStyle("-fx-background-color: 000000");
            currentButton.setText(getTileCoord(tile));
            UIController.getTileGrid().add(currentButton, calculateCol(tile.getCoord()), calculateRow(tile.getCoord()));
        }
    }

    private Button setButtonProperties(String tileCom){
        Button currentButton = colorButton(tileCom);
        currentButton.setMinSize(40, 45);
        UIController.getTileGrid().setHalignment(currentButton, HPos.CENTER);
        return currentButton;
    }

    private Button colorButton(String companyName){
        Button button = new Button();
        switch(companyName) {
            case "Worldwide" -> button.setStyle("-fx-background-color: purple");
            case "Sackson" -> button.setStyle("-fx-background-color: orange");
            case "Festival" -> button.setStyle("-fx-background-color: green");
            case "Imperial" -> button.setStyle("-fx-background-color: yellow");
            case "American" -> button.setStyle("-fx-background-color: blue");
            case "Continental" -> button.setStyle("-fx-background-color: red");
            case "Tower" -> button.setStyle("-fx-background-color: grey");
            default -> button.setStyle("-fx-background-color: 000000");
        }
        return button;
    }*/

    /*/**
     * Gets the row if we count from 0 - 107 and the tiles start from the top left
     * Int division always rounds towards zero
     * @param n number of the tile
     * @return row
     */
    /*private int calculateRow(int n){
        return n/12;
    }*/

    /*/**
     * remainder of tile / 12 is the column
     * @param n  number of the tile
     * @return column
     */
    /*private int calculateCol(int n){
        return n%12;
    }*/

    /*/**
     * Extracts the string names of the tiles in the list of tiles
     * @param tile the tile you'd like to get the name of.
     * @return A coordinate name of the unique tile.
     */
    /*public String getTileCoord(Tile tile) {
        String tileString;
        int remainder;
        int mod = (tile.getCoord() % 12) + 1;
        switch (remainder = (tile.getCoord() / 12)) {
            case 0 -> tileString = ("A" + mod);
            case 1 -> tileString = ("B" + mod);
            case 2 -> tileString = ("C" + mod);
            case 3 -> tileString = ("D" + mod);
            case 4 -> tileString = ("E" + mod);
            case 5 -> tileString = ("F" + mod);
            case 6 -> tileString = ("G" + mod);
            case 7 -> tileString = ("H" + mod);
            case 8 -> tileString = ("I" + mod);
            default -> tileString = ("0" + mod);
        }
        return tileString;
    }*/


    /*/**
     * Updates the information about the players, e.g. money, net worth, and tile hand
     * Tile hand with swap every turn to represent the current players hand.
     * TileList needs to loop to extract the tile coordinates and put them into the string list
     */

    /*private void updatePlayerInfo(LinkedList<Player> playerList, Player currentPlayer) {
        UIController.getPlayerNameObserList().clear();
        UIController.getPlayerMoneyObserList().clear();
        UIController.getPlayerNetObserList().clear();
        UIController.getPlayerTilesObserList().clear();

        for (Player currentPlayerData : playerList) {
            UIController.getPlayerNameObserList().add(currentPlayerData.getName());
            UIController.getPlayerMoneyObserList().add(currentPlayerData.getMoney());
            UIController.getPlayerNetObserList().add(calculateNet(currentPlayerData));
        }

        for (Tile tile: currentPlayer.getTileList()) {
            UIController.getPlayerTilesObserList().add(getTileCoord(tile));
        }
        UIController.getPlayerNameList().setItems(UIController.getPlayerNameObserList());
        UIController.getPlayerMoneyList().setItems(UIController.getPlayerMoneyObserList());
        UIController.getPlayerNetList().setItems(UIController.getPlayerNetObserList());
        UIController.getPlayerTilesList().setItems(UIController.getPlayerTilesObserList());
    }*/

    /*/**
     * Calulates the net value of the player by the stock prices they hold plus current money on hand.
     * @param  player who's net worth would you like to calculate
     * @return  int value for cash worth
     */
    /*private int calculateNet(Player player){
        List<Stock> playerStockList = player.getStockList();
        int playerNet = 0;
        if(playerStockList == null){
            playerNet = player.getMoney();
        }
        else {
            for (Stock stock : playerStockList) {
                playerNet += stock.getStockPriceFromCompany();
            }
            playerNet += player.getMoney();
        }
        return playerNet;
    }*/

    /*/**
     * Simply populates the name headers for the table of stocks.
     * THIS ONLY NEEDS TO BE CALLED ONCE
     */
    /*private void setHotelName(List<Player> playerList) {
        UIController.getHotelNameObserList().clear();
        UIController.getHotelNameObserList().add("Available");
        for(Player player: playerList){
            UIController.getHotelNameObserList().add(player.getName());
        }
        UIController.getHotelNameObserList().add("Size");
        UIController.getHotelNameObserList().add("Price");
        UIController.getHotelNameListView().setItems(UIController.getHotelNameObserList());
    }*/

    /*/**
     * Parent function to set all the information for the companies in 7 lists.
     * @param cCompany charted companies
     * @param uCompany uncharted companies
     * @param playerList list of current players
     */
    /*private void updateHotelInformation(List<Company> cCompany, List<Company> uCompany, List<Player> playerList){
        List<Company> allCompanies = new ArrayList<>();
        for(Company company: cCompany){
            allCompanies.add(company);
        }
        for(Company company: uCompany){
            allCompanies.add(company);
        }
        addDataToHotelInformation(allCompanies, playerList);
    }*/

    /*/**
     * Chooses what list to add the company data into based on the company name. (I'm sorry for the switch)
     * @param companyList full list of all the comanies on the board
     * @param playerList All the current players for # of stocks owned
     */
    /*private void addDataToHotelInformation(List<Company> companyList, List<Player> playerList){
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
        }*/

    /*/**
     * Calculates the number of stocks the players has for a certin company
     * @param playerList players in the game
     * @param listView what list to see on the UI
     * @param obserList what list to add the data to
     * @param company what company are we checking the player stocks for.
     */
    /*private void addPlayerStocksToHotel(List<Player> playerList, ListView<Integer> listView, ObservableList<Integer> obserList, String company){
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
        }*/

    /**
     * Updates the list of companies that you can buy stocks from.
     * @param gameState the current gamestate to put data into the UI.
     */
    private void updateChoiceInfo(GameState gameState) {
        UIController.getAvailableStocksObserList().clear();
        List<Company> companyList = gameState.getCurrentBoard().getCharteredCompanies();
        for (Company curCompany : companyList) {
            Button currentCompanyButton = makeCompanyButton(curCompany, gameState.nextTurn());
            UIController.getAvailableStocksObserList().add(currentCompanyButton);
        }
        UIController.getAvailableStocksList().setItems(UIController.getAvailableStocksObserList());
    }

    /**
     * Makes a transparent button and puts it in the list
     * @param company the company that the button is for
     * @return returns a button so it can be put into the observable list
     */
    private Button makeCompanyButton(Company company, Player currentPlayer){
        Button companyButton = new Button(company.getCompanyName());
        companyButton.setStyle("-fx-background-color: FFFFFF");
        //setButtonAction(companyButton, currentPlayer);
        return companyButton;
    }

    /**
     * Logic for the button to purchase a stock for that specific company.
     * Will make this once we have stock.buyStock() is fleshed out
     * @param button
     */
    // I need to get the current player and relate the name of the button they cicked with the real company
    // Then call current player.buyStock(); and that call in turn eventually calls gamestate.update() again.
    /*private void setButtonAction(Button button, Player currentPlayer){
        button.setOnAction( -> {
            currentPlayer.buyStock();
        });
    }*/
    /**
     * Updates the current states of all the companies stocks.
     * need stocks for variables of Available, P1, P2, size, and price for each company.
     */
    private void updateStockInfo() {
    }

    /*public void populateTest() throws IOException {
        List<Tile> tileList = new ArrayList<>();
        List<Company> unchartedCompanies = new ArrayList<>();
        List<Company> charteredCompanies = new ArrayList<>();
        LinkedList<Player> playerList = new LinkedList<>();
        List<Tile> p1TileList = new ArrayList<>();
        List<Tile> p2TileList = new ArrayList<>();

        Player p1 = new Player("p1", p1TileList, 400);
        Player p2 = new Player("p2", p2TileList, 800);
        playerList.add(p1);
        playerList.add(p2);

        Company defaultCom = new Company("default", 500, false, false);
        Company worldwideCom = new Company("Worldwide", 800, false, false);
        Company sacksonCom = new Company("Sackson", 4200, false, false);
        Company festivalCom = new Company("Festival", 1200, false, false);
        Company imperialCom = new Company("Imperial", 980, false, false);
        Company americanCom = new Company("American", 5222, false, false);
        Company continentalCom = new Company("Continental", 4563, false, false);
        Company towerCom = new Company("Tower", 0, false, false);

        for (int i = 0; i < 6; i++){
            Tile tile = new Tile(defaultCom, 1);
            p1TileList.add(tile);
            p2TileList.add(tile);
        }

        charteredCompanies.add(defaultCom);
        charteredCompanies.add(worldwideCom);
        charteredCompanies.add(sacksonCom);
        charteredCompanies.add(festivalCom);
        charteredCompanies.add(imperialCom);
        charteredCompanies.add(americanCom);
        charteredCompanies.add(continentalCom);
        charteredCompanies.add(towerCom);

        for (int i = 0; i <= 107; i++){
            Tile defaultTile = new Tile(defaultCom, i);
            tileList.add(defaultTile);
            i++;
            Tile worldWideTile = new Tile(worldwideCom, i);
            tileList.add(worldWideTile);
            i++;
            Tile sacksonTile = new Tile(sacksonCom, i);
            tileList.add(sacksonTile);
            i++;
            Tile festivalTile = new Tile(festivalCom, i);
            tileList.add(festivalTile);
            i++;
            Tile imperialTile = new Tile(imperialCom, i);
            tileList.add(imperialTile);
            i++;
            Tile americanTile = new Tile(americanCom, i);
            tileList.add(americanTile);
            i++;
            Tile continentalTile = new Tile(continentalCom, i);
            tileList.add(continentalTile);
            i++;
            Tile towerTile = new Tile(towerCom, i);
            tileList.add(towerTile);
            i++;
        }
        Board board = new Board(tileList, unchartedCompanies, charteredCompanies, playerList);
        GameState game = new GameState(board, playerList);

        game.update();
    }*/

}
