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

import javafx.scene.control.Button;
import org.checkerframework.checker.guieffect.qual.UI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Updates the actions available in the UI (bottom right)
 * This class is currently a WIP.
 */
public class UpdateAction{

    private FXController UIController;
    private GameState gameState;
    private Update update = new Update();

    /**
     * Updates the list of companies that you can buy stocks from. if merge is true, update via merge method with different choice UI.
     * The huge list of parameters is really gross, but I unfortunately don't have time to implement a better specific update method.
     * The booleans are pretty much mutually exclusive.
     * @param gameState The current Gamestate
     * @param UIController The FXController to update the UI
     * @param charter weather or not the player placed a tile and needs to choose a buisness to charter.
     * @param merge weather or not the a merge is happening to display that specific UI for it.
     * @param defunctCompanies If a merge is happening what buisness is going defunct. Null if merge == false
     * @param mergeChoice weather or not during a merge 2 companies have the same amount of tiles.
     * @param listOfEqualCompanies the list of companies that have an equal amount of tiles. Null if mergeChoice == false.
     */
    public void update(GameState gameState, FXController UIController, boolean charter, Company winnerCompany,
                       List<Company> defunctCompanies, List<Company> listOfEqualCompanies) {
        this.UIController = UIController;
        this.gameState = gameState;
        UIController.getActionChoiceObserList().clear();
        UIController.getMergePane().setVisible(false);
        UIController.getActionChoiceList().setVisible(false); // Hiding the list for chartering companies, merge choice, and trade/sell/keep
        UIController.getActionLabel().setText("");
        clearBuyTextBox(); // Hiding buy stocks options

        if(charter){showCharterMenu(gameState, gameState.getCurrentBoard().getUncharteredCompanies());} //Charter a new company

        else if(winnerCompany == null || defunctCompanies == null) { //If there is no merge. buy stocks
            List<Company> charteredCompanyList = gameState.getCurrentBoard().getCharteredCompanies();
            UIController.getActionLabel().setText("Buy up to 3 stocks");
            UIController.getEndTurnButton().setVisible(true);

            showBuyOptions(charteredCompanyList);
            // Change to confirm button for ending the turn
            UIController.getEndTurnButton().setOnAction(action -> { // Sets an button to confirm the stocks to buy
                try{ purchaseStocks(charteredCompanyList, gameState.getCurrentPlayer()); }
                catch (Exception e){e.printStackTrace();}
            });
        }

        else if (listOfEqualCompanies != null){ displayMergeChoice(gameState, listOfEqualCompanies); } //Display merge options during an equal merge
        else{
            try { //Sell, trade, keep stock menu for merging.
                showMergeInfo(winnerCompany, defunctCompanies, gameState.getCurrentBoard().getPlayerList());
            } catch (Exception e){ e.printStackTrace(); } }
        UIController.getActionChoiceList().setItems(UIController.getActionChoiceObserList());
    }

    /**
     * Shows the buy options for all that are in the chartered List
     * @param charteredCo
     */
    private void showBuyOptions(List<Company> charteredCo){
        for (Company company : charteredCo){
            switch(company.getCompanyName()){
                case "Worldwide" -> UIController.getWBuyPane().setVisible(true);
                case "Sackson" -> UIController.getSBuyPane().setVisible(true);
                case "Festival" -> UIController.getFBuyPane().setVisible(true);
                case "Imperial" -> UIController.getIBuyPane().setVisible(true);
                case "American" -> UIController.getABuyPane().setVisible(true);
                case "Continental" -> UIController.getCBuyPane().setVisible(true);
                case "Tower" -> UIController.getTBuyPane().setVisible(true);
            }
        }
    }

    /**
     * Hides all the company buy options and confirm button, this is after the buy turn is over.
     */
    private void hideBuyOptions(){
        UIController.getInvalidInputLabel().setVisible(false);
        UIController.getWBuyPane().setVisible(false);
        UIController.getSBuyPane().setVisible(false);
        UIController.getFBuyPane().setVisible(false);
        UIController.getIBuyPane().setVisible(false);
        UIController.getABuyPane().setVisible(false);
        UIController.getCBuyPane().setVisible(false);
        UIController.getTBuyPane().setVisible(false);
    }

    private void clearBuyTextBox(){
        UIController.getFBuyTextArea().clear();
        UIController.getWBuyTextArea().clear();
        UIController.getCBuyTextArea().clear();
        UIController.getSBuyTextArea().clear();
        UIController.getIBuyTextArea().clear();
        UIController.getABuyTextArea().clear();
        UIController.getTBuyTextArea().clear();
    }

    /**
     * Buys the requested number of stocks and validates that the player has enough money and buys no more than 3 stock.
     * @param charteredCo the list of all chartered Companies
     * @param player the player buying the stocks
     */
    private void purchaseStocks(List<Company> charteredCo, Player player) throws IOException {
        UIController.getInvalidInputLabel().setVisible(false); // Hides error text
        for(Company co : charteredCo){
            if (!validString(getBuyString(co))){ // If any string is invalid
                invalidInputError();
            }
        }

        int totalNumOfStock = 0;
        int totalPriceOfStock = 0;
        for(Company co : charteredCo){ // Gets the total stocks and total price
            String curUserString = getBuyString(co);
            if (!curUserString.equals("")){
                totalNumOfStock += Integer.parseInt(curUserString);
                totalPriceOfStock += (co.calculateStockPrice() * Integer.parseInt(curUserString));
            }
        }

        if (totalNumOfStock > 3){ // Checks if the number of stocks purchased exceed 3
            UIController.getInvalidInputLabel().setVisible(true);
            UIController.getInvalidInputLabel().setText("You can only buy 3 or less stocks");
        }

        else if(totalPriceOfStock > player.getMoney()){ // Checks if the total price exceeds the player's money
            UIController.getInvalidInputLabel().setVisible(true);
            UIController.getInvalidInputLabel().setText("Not enough money");
        }
        else { // Valid input, buy stocks and rotate turn
            for (Company co : charteredCo) { // Buys all the stocks specified by the player
                if (getBuyString(co) != "") {
                    Integer numOfStocksToBuy = Integer.parseInt(getBuyString(co));
                    for (int i = 0; i < numOfStocksToBuy; i++) {
                        player.buyStock(co);
                    }
                }
            }
            hideBuyOptions();
            clearBuyTextBox();
            gameState.setNextTurn();
        }
    }

    private void invalidInputError(){
        UIController.getInvalidInputLabel().setVisible(true);
        UIController.getInvalidInputLabel().setText("Invalid input");
    }

    private String getBuyString(Company companyAssociatedWithString){
        String userValue = "";
            switch (companyAssociatedWithString.getCompanyName()){
                case "Worldwide" -> userValue = UIController.getWBuyTextArea().getText();
                case "Sackson" -> userValue = UIController.getSBuyTextArea().getText();
                case "Festival" -> userValue = UIController.getFBuyTextArea().getText();
                case "Imperial" -> userValue = UIController.getIBuyTextArea().getText();
                case "American" -> userValue = UIController.getABuyTextArea().getText();
                case "Continental" -> userValue = UIController.getCBuyTextArea().getText();
                case "Tower" -> userValue = UIController.getTBuyTextArea().getText();
                default -> userValue = "";
            }
        return userValue;
    }

    /**
     * Checks if the input by the user is value (e.g not a integer)
     * @return boolean true - valid input, false - not valid
     */
    private boolean validString(String userValue){
        if (userValue.equals("")) {return true;}
         return userValue.matches("[0-9]+");
        }


    /**
     * Updates the current states of all the companies stocks.
     * requires the winning company, list of defunct companies and current list of players.
     */
    private void showMergeInfo(Company winnerCo, List<Company> defunctCos, List<Player> playerList) throws IOException {
        UIController.getMergeOptionPane().setVisible(true); // Show the action options
        UIController.getEndTurnButton().setVisible(true);
        UIController.getActionLabel().setText(playerList.get(0) + ": " + defunctCos.get(0).getCompanyName() + " is going under, get rid of your stocks");

        UIController.getEndTurnButton().setOnAction(action -> { // Sets an button to confirm the stocks to sell/trade/keep.
            try{ checkActionForMerge( winnerCo, defunctCos, playerList); }
            catch (Exception e){ e.printStackTrace();}
        });
     }

    /**
     * This continuously removes players from the player list until there is none left.
     * When there are no more players remove the current defunct company from the list.
     * When both lists are empty then all players have gotten rid of their stocks for each defunct company.
     * @param winnerCo The company who won the merge
     * @param defunctCompanies The companies who are bing consumed by the winner company
     * @param players the current list of players.
     * @throws IOException
     */
        private void checkActionForMerge(Company winnerCo, List<Company> defunctCompanies, List<Player> players) throws IOException {
            List<Player> playerListCopy = new ArrayList<>(players); // Create copies of list for this "sub turn"
            List<Company> companyListCopy = new ArrayList<>(defunctCompanies);
            if (validateMergeOptions(companyListCopy.get(0), playerListCopy.get(0))){
                UIController.getInvalidInputLabel().setText(""); // Clear any labels after a success
                sellTradeKeepStocks(playerListCopy.get(0), winnerCo, companyListCopy.get(0)); // Deals with the current players stocks

                playerListCopy.remove(0);
                if(playerListCopy.size() > 0){
                    showMergeInfo(winnerCo, companyListCopy, playerListCopy); // Let the next player remove stocks
                }
                else if (playerListCopy.size() == 0 && defunctCompanies.size() > 1){ // rotate the defunct company
                    companyListCopy.remove(0);
                    showMergeInfo(winnerCo, companyListCopy, gameState.getPlayerList());
                }
                else{//if (defunctCompanies == null && players == null){ // When both lists are empty the merge turn ends.
                    gameState.setNextTurn();
                }
            }
        }

    /**
     * Simply returns the list of inputs from the user
     * @return list of inputs from the sell/trade/keep boxes in the UI
     */
    private List<String> getUserInputs(){
            List<String> usrInput  = new ArrayList<>();
            String sellTextAreaString = UIController.getMergeSellTextArea().getText();
            usrInput.add(sellTextAreaString);
            String tradeTextAreaString = UIController.getMergeTradeTextArea().getText();
            usrInput.add(tradeTextAreaString);
            String keepTextAreaString = UIController.getMergeKeepTextArea().getText();
            usrInput.add(keepTextAreaString);
            return usrInput;
        }

    /**
     * Checks to make sure the user's input is valid, e.g no non-integers, and they have enough stocks
     * @param curDefunctCompanies The company the stocks are coming from.
     * @param curPlayer Who is getting rid of stocks
     * @return returns weather or not the input is valid.
     */
    private boolean validateMergeOptions(Company curDefunctCompanies, Player curPlayer){
            List<String> userInputs = getUserInputs();
            int totalStocks = 0;

            for (String curInput : userInputs ) { // Make sure all the text boxes have a valid integer in them
                if (!curInput.equals("") && validString(curInput)) {
                    totalStocks += Integer.parseInt(curInput);
                }
                else if (!curInput.equals("") && !validString(curInput)){
                    invalidInputError();
                    return false;}
            }

            if (totalStocks > countStocks(curDefunctCompanies, curPlayer)){ // Check if the user has enough stocks to get rid of
                UIController.getInvalidInputLabel().setVisible(true);
                UIController.getInvalidInputLabel().setText("Not enough stocks");
                return false;
            }
            else{
                return true;
            }
        }

    /**
     * Sells and trades stocks from player input. All remaining stocks are kept
     * @param player Who's getting rid of stocks
     * @param defunctCompany What company are the stocks coming form
     */
    private void sellTradeKeepStocks(Player player, Company winnerCompany, Company defunctCompany){
            List<String> userInput = getUserInputs();
            int totalStocksPlayerIsRemoving = 0;
            int totalPlayerStocks = countStocks(defunctCompany, player);

            if (userInput.get(0) != "") {  // Sell stock
                player.sellStock(defunctCompany , Integer.parseInt(userInput.get(0)));
                totalStocksPlayerIsRemoving += Integer.parseInt(userInput.get(0));
            }

            if (userInput.get(1) != "") { // Trade stocks
                player.tradeStock(winnerCompany, defunctCompany, Integer.parseInt(userInput.get(1)));
                totalStocksPlayerIsRemoving += Integer.parseInt(userInput.get(1));
            }

            // Keep the remaining stocks
            player.keepStock(defunctCompany, totalPlayerStocks - totalStocksPlayerIsRemoving);
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


    private void showCharterMenu(GameState gameState, List<Company> unCharteredComs){
        UIController.getActionLabel().setText("Choose a company to charter");
        UIController.getActionChoiceList().setVisible(true);
        for (Company com : unCharteredComs){
                Button choiceButton = new Button();
                choiceButton.setText(com.getCompanyName());
                choiceButton.setStyle("-fx-background-color: ffffff; -fx-border-color: black");
                choiceButton.setOnAction(a -> {
                    try {
                        gameState.addToACompany(com);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                if(com.getCompanyName() != "DEFAULT") {
                    UIController.getActionChoiceObserList().add(choiceButton);
                }
        }
    }

    private void displayMergeChoice(GameState gameState, List<Company> companyChoiceList){
        UIController.getActionLabel().setText("Choose a company you'd like to keep");
        UIController.getActionChoiceList().setVisible(true);
        for (Company com : companyChoiceList){
            Button choiceButton = new Button();
            choiceButton.setText(com.getCompanyName());
            choiceButton.setStyle("-fx-background-color: ffffff; -fx-border-color: black");
            choiceButton.setOnAction(a -> {System.out.println("You chose" + com.getCompanyName() + "to win");});/*gameState.getCurrentBoard().merge(com);});*/ //sets action to charter the choice company
            UIController.getActionChoiceObserList().add(choiceButton);
        }
    }
}