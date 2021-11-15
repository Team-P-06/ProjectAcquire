package ProjectAcquire;

import javafx.scene.control.Button;

import java.io.IOException;
import java.util.List;

/**
 * Updates the actions available in the UI (bottom right)
 * This class is currently a WIP.
 */
public class UpdateAction{

    private FXController UIController;

    /**
     * Updates the list of companies that you can buy stocks from. if merge is true, update via merge method with different choice UI.
     * The huge list of parameters is really gross, but I unfortunately don't have time to implement a better specific update method.
     * The booleans are pretty much mutually exclusive.
     * @param gameState The current Gamestate
     * @param UIController The FXController to update the UI
     * @param charter weather or not the player placed a tile and needs to choose a buisness to charter.
     * @param merge weather or not the a merge is happening to display that specific UI for it.
     * @param defunctCompany If a merge is happening what buisness is going defunct. Null if merge == false
     * @param mergeChoice weather or not during a merge 2 companies have the same amount of tiles.
     * @param listOfEqualCompanies the list of companies that have an equal amount of tiles. Null if mergeChoice == false.
     */
    public void update(GameState gameState, FXController UIController, boolean charter,
                       boolean merge, Company defunctCompany, boolean mergeChoice, List<Company> listOfEqualCompanies){
        this.UIController = UIController;
        UIController.getActionChoiceObserList().clear();
        UIController.getMergePane().setVisible(false);
        UIController.getActionLabel().setText("");
        if(!merge) { //If there is no merge. AKA fresh new currentPlayer to play a tile
            List<Company> companyList = gameState.getCurrentBoard().getCharteredCompanies();
            UIController.getActionLabel().setText("buy stocks");

            UIController.getEndTurnButton().setVisible(true);
            UIController.getEndTurnButton().setOnAction(action -> { // Sets an exit button to stop selling stocks
                try{ gameState.setNextTurn(); }
                catch (IOException e) { e.printStackTrace(); }
            });

            for (Company curCompany : companyList) {
                Button currentCompanyButton = makeBuyStocksList(curCompany, gameState.nextTurn());
                UIController.getActionChoiceObserList().add(currentCompanyButton);
            }
        }
        else if(charter){showCharterMenu(gameState, gameState.getCurrentBoard().getCharteredCompanies());} //Charter a new company
        else if (mergeChoice){ displayMergeChoice(gameState, listOfEqualCompanies); } //Display merge options during a equal merge
        else{ updateMergeInfo(defunctCompany, gameState.getCurrentPlayer()); } //Sell, trade, keep stock menu for merging.
        UIController.getActionChoiceList().setItems(UIController.getActionChoiceObserList());
    }

    /**
     * Makes a transparent button and puts it in the list
     * @param company the company that the button is for
     * @return returns a button so it can be put into the observable list
     */
    private Button makeBuyStocksList(Company company, Player currentPlayer){
        Button companyButton = new Button(company.getCompanyName());
        companyButton.setStyle("-fx-background-color: FFFFFF");
        setButtonAction(companyButton, currentPlayer, company);
        UIController.getActionLabel().setText("Buy Stocks");
        return companyButton;
    }

    /**
     * Logic for the button to purchase a stock for that specific company.
     * Will make this once we have stock.buyStock() is fleshed out
     * @param button
     */
    private void setButtonAction(Button button, Player currentPlayer, Company company){
        button.setOnAction(a -> {
            Stock stock = new Stock(company);
            currentPlayer.buyStock(stock);
        });
    }
    /**
     * Updates the current states of all the companies stocks.
     * need stocks for variables of Available, P1, P2, size, and price for each company.
     */
    private void updateMergeInfo(Company company, Player curPlayer) {
        UIController.getActionChoiceObserList().clear();
        UIController.getMergePane().setVisible(true);
        int numberOfStocks = countStocks(company, curPlayer);
        UIController.getActionLabel().setText("Merge: " + company.getCompanyName() +
                " is defunct you have " + numberOfStocks + " in the company");

        Button sell = new Button();
        sell.setText("Sell");
        sell.setStyle("-fx-background-color: ffffff; -fx-border-color: black");
        sell.setOnAction(a -> validSell(company, UIController.getMergeTextField().getText(), curPlayer));

        Button trade = new Button();
        trade.setText("Trade");
        trade.setStyle("-fx-background-color: ffffff; -fx-border-color: black");
        trade.setOnAction(a -> validTrade(company, UIController.getMergeTextField().getText(), curPlayer));

        Button keep = new Button();
        keep.setStyle("-fx-background-color: ffffff; -fx-border-color: black");
        keep.setText("Keep");
        keep.setOnAction(a -> validKeep(company, UIController.getMergeTextField().getText(), curPlayer));

        UIController.getActionChoiceObserList().add(trade);
        UIController.getActionChoiceObserList().add(sell);
        UIController.getActionChoiceObserList().add(keep);
        UIController.getActionChoiceList().setItems(UIController.getActionChoiceObserList());
        }

    /**
     * Checks if the entry is valid, if so call sell the stocks. Same for validTrade and validKeep
     * @param company where the stocks parent are
     * @param userValue what value the user supplied
     * @param player the current player who is selling the stocks
     */
        private void validSell(Company company, String userValue, Player player){
            if (validSubmission(company, player)){
                player.sellStock(company, Integer.parseInt(userValue));
                UIController.getInvalidInputLabel().setVisible(false);
            }
        }

        private void validTrade(Company company, String userValue, Player player){
            if (validSubmission(company, player)){
                player.tradeStock(company, Integer.parseInt(userValue));
                UIController.getInvalidInputLabel().setVisible(false);
            }
        }

        private void validKeep(Company company, String userValue, Player player){
            if (validSubmission(company, player)){
                player.keepStock(company, Integer.parseInt(userValue));
                UIController.getInvalidInputLabel().setVisible(false);
            }
        }

    /**
     * If the input is not valid or the player doesn't have enough stock, tell the user and let them try again.
     * @return boolean true - valid input, false - not valid
     */
    private boolean validSubmission(Company company, Player player){
            if (!enoughStocks(UIController.getMergeTextField().getText(), countStocks(company, player))) {
                UIController.getInvalidInputLabel().setVisible(true);
                updateMergeInfo(company, player);
                //return false;
            }
            return true;
        }

    /**
     * Counts the number of stock in the player stock list to see if the input is valid
     * @param company the company the stocks are coming from
     * @param curPlayer the play who's stocks we are checking
     * @return the numer of stocks the player has for that company.
     */
    private int countStocks (Company company, Player curPlayer){
            int numberOfStocks = 0;
            if(curPlayer.getStockList() != null) {
            List<Stock> fullStockList = curPlayer.getStockList();
            for (Stock stock : fullStockList) {
                if (stock.getParentCompany() == company) {
                    numberOfStocks++;
                }
            }
        }
            return numberOfStocks;
        }

    /**
     * Checks if the user has enough stock to trade/sell etc. Also checks if the user placed a integer.
     * @param userValueOfStocks value supplied by the user
     * @param totalStocks the real number of total stocks that the player has.
     * @return boolean of wheather or not the transaction is valid.
     */
        private boolean enoughStocks (String userValueOfStocks, int totalStocks){
            if (!userValueOfStocks.matches("[0-9]*")) { return false; }
            else return Integer.parseInt(userValueOfStocks) <= totalStocks;
        }

    private void showCharterMenu(GameState gameState, List<Company> charteredComs){
        UIController.getActionLabel().setText("Choose a company to charter");
        for (Company com : charteredComs){
                Button choiceButton = new Button();
                choiceButton.setText(com.getCompanyName());
                choiceButton.setStyle("-fx-background-color: ffffff; -fx-border-color: black");
                choiceButton.setOnAction(a -> {gameState.getCurrentBoard().charter(com);});
                UIController.getActionChoiceObserList().add(choiceButton);
        }
    }

    private void displayMergeChoice(GameState gameState, List<Company> companyChoiceList){
            UIController.getActionLabel().setText("Choose a company you'd like to keep");
            for (Company com : companyChoiceList){
                Button choiceButton = new Button();
                choiceButton.setText(com.getCompanyName());
                choiceButton.setStyle("-fx-background-color: ffffff; -fx-border-color: black");
                choiceButton.setOnAction(a -> {gameState.getCurrentBoard().merge(com);}); //sets action to charter the choice company
                UIController.getActionChoiceObserList().add(choiceButton);
        }
    }
}