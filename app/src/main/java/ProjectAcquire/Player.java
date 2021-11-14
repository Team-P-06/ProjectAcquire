/**
 * @author Team 404
 * @version v0.0.1
 */

package ProjectAcquire;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

/**
 * Class to manage the players. This holds their stats and actions
 */
public class Player{
    @Getter @Setter private String name;
    @Getter @Setter private int money;
    @Getter @Setter private List<Stock> stockList;
    @Getter @Setter private List<Tile> tileList;

    /**
     * Default constructor with dummy values
     */
    Player(){
        this.name = "player1";
        this.money = 400;
        List<Stock> stockList = new ArrayList<>();
        List<Tile> tileList = new ArrayList<>();
    }

    /**
     *
     * @param name name of the player
     * @param tileHand the 6 tiles the player holds at any given time
     * @param money how much money they have
     */
    Player(String name, List<Tile> tileHand, Integer money){
        this.name = name;
        this.money = money;
        this.tileList = tileHand;
    }
    /**
     *
     * @return Checks if the current tile on the board is occupied.
     */
    public boolean availableTile(String coord){
        return true;
    }

    /**
     * Places a tile on the board
     * UI calls this when clicking on a tile that's in the players hand, this then goes to Board.checkForActionInitiation().
     * @param tile the tile that was placed
     * @param gameState the current gamestate
     */
    public void placeTile(Tile tile, GameState gameState) throws IOException {

        //Sets the tile to be flipped.
        tile.setFlipped();

        //removes from the player's hand.
        tileList.remove(tile);
        Board curBoard = Board.getInstance();

        curBoard.checkForActionInitiation(tile, gameState);

    }

    /**
     * Player chooses to buy 1 - 3 stocks
     * @param stock what company the stocks are coming from
     */
    public void buyStock(Stock stock){
        stockList.add(stock);
    }

    /**
     * Player cheese to buy 1 - 3 stocks
     * @param parentCompany stocks from the company they would like to sell.
     * @param numberOfStocks the number of stocks they are selling
     */
    public void sellStock(Company parentCompany, int numberOfStocks){

    }

    /**
     * Keep the stocks from a company after a merge
     * @param parentCompany stocks from the company they would like to keep.
     * @param numberOfStocks the number of stocks they are keep
     */
    public void keepStock(Company parentCompany, int numberOfStocks){

    }

    /**
     * Trades stocks from a defunct company to the larger company
     * @param parentCompany stocks from the company they would like to sell.
     * @param numberOfStocks the number of stocks they are selling
     */
    public void tradeStock(Company parentCompany, int numberOfStocks){

    }



    /*/**
     *
     * Moved to Board and renamed dealTile() - Show
     * ALEX NOTE: This Method Will not work as written, we should probably move it to Board and change the name to "dealTile".
     * This is because we are initializing arbitrary tiles here when we should be initializing all tiles in our Game inititalize() method.
     * DrawTile may be best as a passive action.
     *
     * creates a new unique tile and give it to the player who drew it.
     * @return a new unique tile
     */
    /*public Tile drawTile(List<Tile> tilesNotOnBoardOrOtherPlayersHand){
        Random ran = new Random();
        int randomIndex = ran.nextInt(tilesNotOnBoardOrOtherPlayersHand.size());
        tileList.add(tilesNotOnBoardOrOtherPlayersHand.get(randomIndex));
        tilesNotOnBoardOrOtherPlayersHand.remove(randomIndex);
        //Tile drawnTile = new Tile(); //DEFAULT TILE
        //tileList.add(drawnTile);
        return drawnTile;
    }*/

    /**
     * Removes a tile from the players hand
     */
    public void discardTile(Tile tile){
    tileList.remove(tile);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return money == player.money && Objects.equals(name, player.name) && Objects.equals(stockList, player.stockList) && Objects.equals(tileList, player.tileList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, money, stockList, tileList);
    }

    @Override
    public String toString() {

        String retString1 = name;

        String retString2 = "Player{" +
                "name='" + name + '\'' +
                ", money=" + money +
                ", stockList=" + stockList +
                ", tileList=" + tileList +
                '}';

        return retString1;
    }
}

