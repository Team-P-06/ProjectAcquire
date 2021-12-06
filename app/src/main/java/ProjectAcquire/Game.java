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
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Game class that can get the turns, start, load, or end a game
 */
public class Game {
   private @Getter @Setter GameState currentGameState;
   private static Game instance;

    /**
     * Default constructor
     */
    private Game(){}

    /**
     * Get instance method for our singleton
     * @return
     */
    public static Game getInstance(){

        if(instance == null){
            instance = new Game();
        }
        return instance;
    }


    /**
     * Turn method that will determine which players turn it is
     * @return
     */
    public GameState getTurn(){
        return currentGameState;
    }
    /**
     * Creates a gamestate and starts the game with a given gamestate that was loaded from a json file
     * @param game the loaded gamestate from a file
     */

    public void loadGame(GameState game){
        loadCompanies(game);
        loadPlayers(game);
        for(Player player : game.getPlayerList()) {
                loadTileList(game, player);
            }
            this.currentGameState = game;
    }

    /**
     * When a game is loaded the references to the player tile list are not kept for some reason.
     * This function refreshes the player tile list at the begging of the load with the loaded, correct, tile list.
     * @param gameState the current loaded gameState
     * @param player the player whom we are updating the tile list for.
     */
    private void loadTileList(GameState gameState, Player player){
        List<Tile> playerTileListCopy = new ArrayList<>(player.getTileList());
        player.getTileList().clear();

            for (Tile playerTile : playerTileListCopy) {
                for (Tile boardTile : gameState.getCurrentBoard().getTileList()) {
                    if (playerTile.toString().equals(boardTile.toString())) {
                        player.getTileList().add(boardTile);
                        boardTile.setDealt(true);
                    }
                }
            }
        }

    /**
     * Makes sure all company references are correct when loading a game
     * @param gameState the loaded gamestate
     */
    private void loadCompanies(GameState gameState){
            List<Company> cCompanyListCopy = new ArrayList<>(gameState.getCurrentBoard().getCharteredCompanies());
            List<Company> uCompanyListCopy = new ArrayList<>(gameState.getCurrentBoard().getUncharteredCompanies());
            gameState.getCurrentBoard().getCharteredCompanies().clear();
            gameState.getCurrentBoard().getUncharteredCompanies().clear();
            for (Company cCompany : cCompanyListCopy){
                gameState.getCurrentBoard().getCharteredCompanies().add(cCompany);
            }

            for (Company uCompany : uCompanyListCopy){
                gameState.getCurrentBoard().getUncharteredCompanies().add(uCompany);
            }
        }

    /**
     * Makes sure all player references are correct when loading a game
     * @param gameState the loaded gamestate
     */
    private void loadPlayers(GameState gameState) {
            List<Player> playerListCopy = new ArrayList<>(gameState.getPlayerList());
            gameState.getPlayerList().clear();
            for (Player player : playerListCopy) {
                gameState.getPlayerList().add(player);
            }
        }

    /**
     * Start game method that will begin a new game that isn't already saved
     * This creates all the nessasary objects to create a gamestate, such as players, board, companies, and tiles.
     * @param numOfPlayers The number of players the the user specified to start the game with
     * @throws IOException
     * @return The newly constructed gameState to start the game
     */
    public GameState start(int numOfPlayers) {
        //queries for how many players there are, (UI), then adds x amount of players to a playerList.

            LinkedList<Player> playerList = new LinkedList<Player>();
            for (int i = 0; i < numOfPlayers; i++) {
                List<Tile> newPlayerTileList = new ArrayList<>();
                List<Stock> newPlayerStockList = new ArrayList<>();
                Player newPlayer = new Player("Player " + (i + 1), newPlayerTileList, 3000);
                newPlayer.setStockList(newPlayerStockList);
                playerList.add(newPlayer);
            }

            // Creates a list of chartered and unchartered companies (the unchartered list can be empty)
            List<Company> charteredList = new ArrayList<>();
            List<Company> uncharteredList = new ArrayList<>();
            Company worldwideCo = new Company("Worldwide", 0, false, false);
            uncharteredList.add(worldwideCo);
            Company SacksonCo = new Company("Sackson", 0, false, false);
            uncharteredList.add(SacksonCo);
            Company festivalCo = new Company("Festival", 0, false, false);
            uncharteredList.add(festivalCo);
            Company imperialCo = new Company("Imperial", 0, false, false);
            uncharteredList.add(imperialCo);
            Company americanCo = new Company("American", 0, false, false);
            uncharteredList.add(americanCo);
            Company continentalCo = new Company("Continental", 0, false, false);
            uncharteredList.add(continentalCo);
            Company towerCo = new Company("Tower", 0, false, false);
            uncharteredList.add(towerCo);
            Company defaultCo = new Company("DEFAULT", 0, false, false); // for placed tiles but no company
            uncharteredList.add(defaultCo);


            // Creates a list of tiles with a int[] as a coordinate.
            List<Tile> freeTileList = new ArrayList<>();
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 12; c++) {
                    Tile curTile = new Tile(defaultCo, new int[]{r, c});
                    freeTileList.add(curTile);
                }
            }
        try {
            //initialize a board/GameState
            Board board = Board.getInstance(freeTileList, uncharteredList, charteredList, playerList);

            GameState gameState = GameState.getInstance(board, playerList);
            setCurrentGameState(gameState);
            gameState.playTurn();
            return gameState;
        }
     catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }



    @Generated
    public void setNull(){
        instance = null;
    }


}
