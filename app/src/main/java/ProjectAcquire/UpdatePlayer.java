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

import java.util.LinkedList;
import java.util.List;

/**
 * Logic to update the player information (bottom left of UI)
 */
@Generated
public class UpdatePlayer implements Updatable{
    private FXController UIController;

    /**
     * Updates the information about the players, e.g. money, net worth, and tile hand
     * Tile hand with swap every turn to represent the current players hand.
     * TileList needs to loop to extract the tile coordinates and put them into the string list
     */
    public void update(GameState gameState, FXController UIController){
        this.UIController = UIController;
        LinkedList<Player> playerList = gameState.getPlayerList();
        Player currentPlayer = gameState.nextTurn();
        updateCurrentPlayerLabel(currentPlayer);
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
            UIController.getPlayerTilesObserList().add(tile.tileCoordToString());
        }
        UIController.getPlayerNameList().setItems(UIController.getPlayerNameObserList());
        UIController.getPlayerMoneyList().setItems(UIController.getPlayerMoneyObserList());
        UIController.getPlayerNetList().setItems(UIController.getPlayerNetObserList());
        UIController.getPlayerTilesList().setItems(UIController.getPlayerTilesObserList());

    }

    /**
     * Calulates the net value of the player by the stock prices they hold plus current money on hand.
     * @param  player who's net worth would you like to calculate
     * @return  int value for cash worth
     */
    private int calculateNet(Player player){
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
    }

    private void updateCurrentPlayerLabel(Player currentPlayer){
        UIController.getCurrentPlayerTurnLabel().setText(currentPlayer.getName());
    }

    public void updateMergeOptions(){

    }

}
