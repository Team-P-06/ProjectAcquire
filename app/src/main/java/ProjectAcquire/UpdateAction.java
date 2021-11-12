package ProjectAcquire;

import javafx.scene.control.Button;

import java.util.List;

/**
 * Updates the actions available in the UI (bottom right)
 * This class is currently a WIP.
 */
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

}
