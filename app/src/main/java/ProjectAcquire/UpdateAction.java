/**
 * UpdateAction.java
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

import javafx.scene.control.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Updates the actions available in the UI (bottom right)
 * This class is currently a WIP.
 */
@Generated
public class UpdateAction{

    private FXController UIController;
    private GameState gameState;

    /**
     * Updates the list of companies that you can buy stocks from. if merge is true, update via merge method with different choice UI.
     * @param gameState The current Gamestate
     * @param UIController The FXController to update the UI
     * @param charter weather or not the player placed a tile and needs to choose a business to charter.
     * @param listOfMergingCompanies the list of companies that are being merged together. This is essentially a merge flag.
     */
    public void update(GameState gameState, FXController UIController, boolean charter, List<Company> listOfMergingCompanies) {
        this.UIController = UIController;
        this.gameState = gameState;
        UIController.getActionChoiceObserList().clear();
        UIController.getMergePane().setVisible(false);
        UIController.getActionChoiceList().setVisible(false); // Hiding the list for chartering companies, merge choice, and trade/sell/keep
        UIController.getActionLabel().setText("");
        clearBuyTextBox(); // Hiding buy stocks options

        if(charter){ // Charter a new company
            showCharterMenu(gameState.getCurrentBoard().getUncharteredCompanies());
        }

        else if(listOfMergingCompanies == null) { //If there is no merge. buy stocks
            List<Company> charteredCompanyList = gameState.getCurrentBoard().getCharteredCompanies();
            UIController.getActionLabel().setText("Buy up to 3 stocks");
            UIController.getEndTurnButton().setVisible(true);
            showBuyOptions(charteredCompanyList);

            // Enable confirm button for ending the turn
            UIController.getEndTurnButton().setOnAction(action -> { // Sets a button to confirm the stocks to buy
                try{ purchaseStocks(charteredCompanyList, gameState.getCurrentPlayer()); }
                catch (Exception e){e.printStackTrace();}
            });
        }
        else{ // Merge is happening, check if there is an equal merge then sell, trade, and keep stock.
            try {
                checkEqualCompanies(listOfMergingCompanies);
            } catch (Exception e){ e.printStackTrace(); } }
        UIController.getActionChoiceList().setItems(UIController.getActionChoiceObserList()); // Sets the buttons to the list of actions when chartering.
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

    /**
     * Clears the textboxes for selling stocks
     */
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
     * Clears the textboxes for the merge options.
     */
    private void clearMergeTextBox(){
        UIController.getMergeSellTextArea().clear();
        UIController.getMergeTradeTextArea().clear();
        UIController.getMergeKeepTextArea().clear();
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

    /**
     * Simply sets an error label
     */
    private void invalidInputError(){
        UIController.getInvalidInputLabel().setVisible(true);
        UIController.getInvalidInputLabel().setText("Invalid input");
    }

    /**
     * Gets the string from the user for a company when buying stocks
     * @param companyAssociatedWithString what company the player is buying from
     * @return the value of the user
     */
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
     * Shows UI information for the player to choose what to do with their stocks
     * @param winnerCo The company that won the merge
     * @param defunctCos The list of companies that are being consumed
     * @param playerList The list of players in the game.
     * @param fullDefunctList The original unedited list of defunct companies. (Used to actually merge the companies data)
     * @throws IOException
     */
    private void showMergeInfo(Company winnerCo, List<Company> defunctCos,
                               List<Player> playerList, List<Company> fullDefunctList) throws IOException {
        UIController.getMergeOptionPane().setVisible(true); // Show the action options
        UIController.getEndTurnButton().setVisible(true);
        UIController.getActionLabel().setText(playerList.get(0) + ": " + defunctCos.get(0).getCompanyName() + " is going under, get rid of your stocks (leftover stocks will be kept)");

        UIController.getEndTurnButton().setOnAction(action -> { // Sets an button to confirm the stocks to sell/trade/keep.
            try{ mergeTurnRotation( winnerCo, defunctCos, playerList, fullDefunctList); }
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
     * @param originalDefunctCompanies The unedited list of defunct Companies
     * @throws IOException
     */
        private void mergeTurnRotation(Company winnerCo, List<Company> defunctCompanies,
                                       List<Player> players, List<Company> originalDefunctCompanies) throws IOException {
            defunctCompanies.removeIf(com -> com.getCompanyName().equals("DEFAULT")); // Removes the default companies from the list
            List<Player> playerListCopy = new ArrayList<>(players); // Create copies of list for this "sub turn"
            List<Company> companyListCopy = new ArrayList<>(defunctCompanies);
            Player curPlayer = playerListCopy.get(0);
            Company curDefunctCompanies = companyListCopy.get(0);
            if (gameState.getPlayerList().size() == playerListCopy.size()){
                mergePayout(defunctCompanies.get(0)); // Give merge payouts once per company
            }

            if (validateMergeOptions(curDefunctCompanies, curPlayer)){ // If all the inputs are valid
                UIController.getInvalidInputLabel().setText(""); // Clear any labels after a success
                copyStocks(originalDefunctCompanies); // Makes sure stock references are correct before selling

                sellTradeKeepStocks(curPlayer, winnerCo, curDefunctCompanies);
                playerListCopy.remove(0);

                if(playerListCopy.size() > 0){
                    showMergeInfo(winnerCo, companyListCopy, playerListCopy, originalDefunctCompanies);
                }
                else if (playerListCopy.size() == 0 && defunctCompanies.size() > 1){ // rotate the defunct company and give payouts
                    companyListCopy.remove(0);
                    showMergeInfo(winnerCo, companyListCopy, gameState.getPlayerList(), originalDefunctCompanies);
                }
                else{ // When both lists are empty the merge turn ends and set the placed tile to be a part of the winner company.
                    for(Company defunctCoList : originalDefunctCompanies){
                        winnerCo.setNumTiles(winnerCo.getNumTiles() + defunctCoList.getNumTiles());
                        defunctCoList.setNumTiles(0);
                    }

                    // Makes sure to fix any references when in a loaded game
                    for(Company company : gameState.getCurrentBoard().getCharteredCompanies()) {
                        if (company.getCompanyName().equals(winnerCo.getCompanyName())) {
                            gameState.getCurrentBoard().getCharteredCompanies().remove(company);
                            break;
                        }
                    }

                    Tile charterTile = CompanyLedger.getInstance().getCharterTile();
                    charterTile.setCompany(winnerCo);
                    winnerCo.setNumTiles(winnerCo.getNumTiles() + 1); // Add the played tile to the total count of stocks

                    gameState.getCurrentBoard().getCharteredCompanies().add(winnerCo);
                    gameState.getCurrentBoard().mergeLogic(winnerCo, originalDefunctCompanies); // Merges the companies and updates the board.
                    CompanyLedger.getInstance().setCharterComp(winnerCo);
                    gameState.setNextTurn();
                }
            }
        }

    /**
     * This fixes an issues with references when loading a game.
     * Effectively makes sure all the stocks for each player corresponds to the correct company.
     * @param originalDefunctCompanies
     */
    private void copyStocks(List<Company> originalDefunctCompanies){
            for(Company defunctCompany : originalDefunctCompanies) {

                for (Player player : gameState.getPlayerList()) {
                    int defunctStocks = player.countStocks(defunctCompany);
                    Iterator<Stock> stockIter = player.getStockList().iterator();

                    while (stockIter.hasNext()) {
                        Stock curStock = stockIter.next();
                        if (curStock.getParentCompany().getCompanyName().equals(defunctCompany.getCompanyName())) {
                            stockIter.remove();
                        }
                    }

                    for (int i = 0; i < defunctStocks; i++){
                        player.getStockList().add(new Stock(defunctCompany));
                    }
                }
            }
        }

    /**
     * Gives players a cash payout based on have many stocks they have in a company relative to other players.
     * @param company The company that is doing the cash payout/losing the merge.
     */
    private void mergePayout(Company company){
            List<Player> majorityShareholders = checkMajority(company, 0);
            List<Player> minorityShareholders = checkMajority(company, 1);
            if(majorityShareholders != null) {
                if (majorityShareholders.size() == 1 && minorityShareholders.size() == 1) { // Only 1 majority and minority payout
                    majorityShareholders.get(0).setMoney(majorityShareholders.get(0).getMoney() + company.getMajorityPayout());
                    minorityShareholders.get(0).setMoney(minorityShareholders.get(0).getMoney() + company.getMinorityPayout());

                } else if(majorityShareholders.size() == 1 && minorityShareholders.size() == 0) { // Sole owner
                    int bothPayouts = company.getMajorityPayout() + company.getMinorityPayout();
                    int roundedBothPayouts = ((bothPayouts + 99) / 100) * 100; // Rounds up to nearist hundreth
                    majorityShareholders.get(0).setMoney(majorityShareholders.get(0).getMoney() + roundedBothPayouts);

                } else if (majorityShareholders.size() > 1) { // Split the payout for the majority tied players
                    int splitPayout = (company.getMajorityPayout() + company.getMinorityPayout()) / (majorityShareholders.size());
                    int roundedBothPayouts = ((splitPayout + 99) / 100) * 100;
                    for (Player curPlayer : majorityShareholders) {
                        curPlayer.setMoney(curPlayer.getMoney() + roundedBothPayouts);
                    }

                } else if (majorityShareholders.size() == 1 && minorityShareholders.size() > 1) { // Split minority payout
                    majorityShareholders.get(0).setMoney(majorityShareholders.get(0).getMoney() + company.getMajorityPayout());
                    int splitPayout = company.getMinorityPayout() / minorityShareholders.size();
                    int roundedBothPayouts = ((splitPayout + 99) / 100) * 100;
                    for (Player curPlayer : minorityShareholders) {
                        curPlayer.setMoney(curPlayer.getMoney() + roundedBothPayouts);
                    }
                }

            }
        }

    /**
     * Adds players to a list based on if they have the majority or minority socks in a company
     * @param company the company that is losing a merge and giving a payout
     * @param listToReturn 0 for a list of majority player, 1 for a list of minority players
     * @return either a list of majority or minority players.
     */
        private List<Player> checkMajority(Company company, int listToReturn){
            List<Player> tiedMajorityPlayers = new ArrayList<>();
            List<Player> tiedMinorityPlayers = new ArrayList<>();
            int largestStocks = 0;
            int nextLargest = 0;
            for(Player curPlayer : gameState.getPlayerList()){ // Sets the largest
                int curPlayerStocks = curPlayer.countStocks(company);
                if (curPlayerStocks > largestStocks) {
                    largestStocks = curPlayerStocks;
                }
            }
            for(Player curPlayer : gameState.getPlayerList()) { // Sets the nextLargest stock values
                int curPlayerStocks = curPlayer.countStocks(company);
                if(curPlayerStocks < largestStocks && curPlayerStocks > nextLargest){
                    nextLargest = curPlayerStocks;
                }
            }

            for (Player curPlayer : gameState.getPlayerList()){
                if (curPlayer.countStocks(company) == largestStocks && curPlayer.countStocks(company) != 0){
                    tiedMajorityPlayers.add(curPlayer);
                }
            }
            for (Player curPlayer : gameState.getPlayerList()) {
                if (curPlayer.countStocks(company) == nextLargest && curPlayer.countStocks(company) != 0) {
                    tiedMinorityPlayers.add(curPlayer);
                }
            }

            if(listToReturn == 0) {
                return tiedMajorityPlayers;
            }
            else{
                return tiedMinorityPlayers;
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
                    clearMergeTextBox();
                    return false;}
            }

            if (totalStocks > curPlayer.countStocks(curDefunctCompanies)){ // Check if the user has enough stocks to get rid of
                UIController.getInvalidInputLabel().setVisible(true);
                UIController.getInvalidInputLabel().setText("Not enough stocks");
                clearMergeTextBox();
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
        int totalPlayerStocks = player.countStocks(defunctCompany);

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
        clearMergeTextBox();
    }

    /**
     * This method is called after the board sees that two flipped uchartered tiles are next to eachother.
     * It presents to the user an option for which company from an unchartered company list to charter.
     * Then it calls gameState.addToACompany() with the chosen company passed in.
     * @param unCharteredComs List of current unchartered companies.
     */
    private void showCharterMenu(List<Company> unCharteredComs ){
        UIController.getActionLabel().setText("Choose a company to charter");
        UIController.getActionChoiceList().setVisible(true);
        for (Company com : unCharteredComs){
            Button choiceButton = new Button();
            choiceButton.setText(com.getCompanyName());
            choiceButton.setStyle("-fx-background-color: ffffff; -fx-border-color: black");
            choiceButton.setOnAction(a -> {
                try {
                    Tile charterTile = CompanyLedger.getInstance().getCharterTile();
                    charterTile.setCompany(com);
                    CompanyLedger.getInstance().setCharterComp(com);
                    gameState.addToACompany(com);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            if (!com.getCompanyName().equals("DEFAULT")) {
                UIController.getActionChoiceObserList().add(choiceButton);
            }
        }
    }

    /**
     * Checks if the companies in a merge are equal and lets the player choose which wins.
     * Starts the merge UI for players to sell/trade/keep stocks.
     * @param mergingCompanies The list of companies to be merged
     */
    private void checkEqualCompanies(List<Company> mergingCompanies){
        mergingCompanies.removeIf(com -> com.getCompanyName().equals("DEFAULT"));
        List<Company> listOfEqualCompanies = new ArrayList<>();
        Company biggestCompany = mergingCompanies.get(0);
        int currentMost = 0;

        for(Company c: mergingCompanies){
            if (c.getNumTiles()>currentMost){
                biggestCompany = c;
                currentMost = c.getNumTiles();
            }
        }
        //checks if the x amount of the largest companies are tied.
        for(Company c: mergingCompanies){
            if(c.getNumTiles()==currentMost){
                listOfEqualCompanies.add(c);
            }
        }

        if (listOfEqualCompanies.size()>1){ //If there are equal companies in a merge, let the player choose what company to keep
            UIController.getActionLabel().setText("Choose the company you'd like to win the merge");
            UIController.getActionChoiceList().setVisible(true);
            for (Company com : listOfEqualCompanies){
                if(!com.getCompanyName().equals("DEFAULT")) {
                    Button choiceButton = new Button();
                    choiceButton.setText(com.getCompanyName());
                    choiceButton.setStyle("-fx-background-color: ffffff; -fx-border-color: black");
                    choiceButton.setOnAction(a -> { // Set the winning company to the players choice and remove it from the defunct company list
                        mergingCompanies.remove(com);
                        UIController.getActionChoiceList().setVisible(false);
                        try {
                            showMergeInfo(com, mergingCompanies, gameState.getPlayerList(), mergingCompanies);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                    UIController.getActionChoiceObserList().add(choiceButton);
                }
            }
        }
        else { // There are no equal companies in the merge
            mergingCompanies.remove(biggestCompany);
            try{ showMergeInfo(biggestCompany, mergingCompanies, gameState.getPlayerList(), mergingCompanies);}
            catch (Exception e){ e.printStackTrace(); }
        }
    }
}