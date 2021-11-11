package ProjectAcquire;

import java.util.LinkedList;
import java.util.List;

public class UpdatePlayer implements Updatable{
    private FXController UIController;

    /**
     * Updates the information about the players, e.g. money, net worth, and tile hand
     * Tile hand with swap every turn to represent the current players hand.
     * TileList needs to loop to extract the tile coordinates and put them into the string list
     */
    public void update(GameState gameState, FXController UIController){
        this.UIController = UIController;
        UpdateBoard boardUpdater = new UpdateBoard();
        LinkedList<Player> playerList = gameState.getPlayerList();
        Player currentPlayer = gameState.nextTurn();
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
            UIController.getPlayerTilesObserList().add(boardUpdater.getTileCoord(tile));
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

}
