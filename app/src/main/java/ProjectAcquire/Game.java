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
    public Game(){}

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
     * Load game method that accepts a gson file to get the game state of a specific game
     * @param game
     */
    @Generated //Until we use the method
    public void loadGame(String game)throws IOException{

        //this.currentGameState = (gamestate that we pull from a file)
    }

    /**
     * Start game method that will begin a new game that isn't already saved
     */
    public GameState start() throws IOException {
        //Company defaultCompany = new Company("emptyCo", 0, false, false);

        /*
        precheck: UI initializes with a starting screen. Player chooses either to exit, start, or load. if load, then call the loadGame() function instead.

        App starts the UI with start(Stage stage) (Not sure if that can be changed), but from there selecting new game can start this e.g: Game.start()
       when the player hits the start button in the UI, the UI calls this method, which does the following:
         */

        //1. queries for how many players there are, (UI), then adds x amount of players to a playerList.
        //This is just a static creation of players for now, will be more dynamic in the future.
        //List<Tile> playerTileList = new ArrayList<>();
        LinkedList<Player> playerList = new LinkedList<Player>();
        int numOfPlayers = 3;
        for (int i = 0; i < numOfPlayers; i++) {
            List<Tile> newPlayerTileList = new ArrayList<>();
            List<Stock> newPlayerStockList = new ArrayList<>();
            Player newPlayer = new Player("Player " + (i+1), newPlayerTileList, 0);
            newPlayer.setStockList(newPlayerStockList);
            playerList.add(newPlayer);
        }

        //2. creates our list of chartered and unchartered companies (the unchartered list can be empty)
        List<Company> charteredList = new ArrayList<>();
        List<Company> uncharteredList = new ArrayList<>();
        Company worldwideCo =  new Company("Worldwide", 0, false, false);
        uncharteredList.add(worldwideCo);
        Company SacksonCo =  new Company("Sackson", 0, false, false);
        uncharteredList.add(SacksonCo);
        Company festivalCo =  new Company("Festival", 0, false, false);
        uncharteredList.add(festivalCo);
        Company imperialCo =  new Company("Imperial", 0, false, false);
        uncharteredList.add(imperialCo);
        Company americanCo =  new Company("American", 0, false, false);
        uncharteredList.add(americanCo);
        Company continentalCo =  new Company("Continental", 0, false, false);
        uncharteredList.add(continentalCo);
        Company towerCo =  new Company("Tower", 0, false, false);
        uncharteredList.add(towerCo);
        Company defaultCo =  new Company("DEFAULT", 0, false, false); // for placed tiles but no company
        uncharteredList.add(defaultCo);


        //3. creates 2d list of tiles.
        List<Tile> freeTileList = new ArrayList<>();
        for (int r = 0; r < 9; r++){
            for(int c = 0; c < 12; c++) {
                Tile curTile = new Tile(defaultCo, new int[]{r, c});
                freeTileList.add(curTile);
              //  tl2D.get(r).add(curTile);
            }
        }



        //4. initialize a board using 1-4 as our parameters (using getInstance())
        Board board = Board.getInstance(freeTileList, uncharteredList, charteredList, playerList);
      //  board.setTileList2D(tl2D);
        //System.out.println(board.getTileList2D().get(0));

        //6. initialize our GameState using 4, 1 as our parameters.
        //ALEX NOTE: it seems that a GameState object holds exactly the same data as the Board object, since Board has the playerList just a thought.
        GameState gameState =  GameState.getInstance(board, playerList);


        //7. call setCurrentGame() of Game using 6 as our parameter
        setCurrentGameState(gameState);
        //gameState.playTurnNoUI();
        gameState.playTurn();

        //if loadGame was called, then simply: setCurrentGame(loadGame())

        //now we pass this to JavaFX (javafx calls this whole method)
        //Game.start() is invoked upon the player hitting "new game". So FXController call Game.start()

        return gameState;
    }

    /**
     * Run a game that is already saved from the gson file gathered from loadGame, (runs a game continuousely given starting data)
     */
    @Generated //Until we use the method
    public void runGame() throws IOException {
        //currentGameState.playTurn();
        //while the game has not ended
//        while(!currentGameState.isOver()){
//            //game plays
//            currentGameState.playTurnNoUI();
//        }
        /*for(int x=0;x<9;x++) {
            currentGameState.playTurn();
            //currentGameState.playTurnNoUI();
            System.out.println("Current Player is: " + currentGameState.getCurrentPlayer());
            int[] testCoord ={1,1};
            System.out.println("Tiles around 1,1: " + currentGameState.getCurrentBoard().getTilesAround(testCoord));
        }
        System.out.println("Game.runGame() was finished");*/

    }


    /**
     * Once a player ends the game on their turn the game will end using this method and determine the winner
     */
    @Generated //Not used yet
    public void endGame(){ }//We should probably move this to GameState.

}
