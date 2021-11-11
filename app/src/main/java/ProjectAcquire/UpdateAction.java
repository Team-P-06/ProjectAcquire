package ProjectAcquire;

import javafx.scene.control.Button;

import java.util.List;

public class UpdateAction implements Updatable{

    private FXController UIController;

    /**
     * Updates the list of companies that you can buy stocks from.
     * @param gameState the current gamestate to put data into the UI.
     */
    public void update(GameState gameState, FXController UIController){
        this.UIController = UIController;
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
