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
import lombok.Getter;
import lombok.Setter;
import lombok.Generated;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Getter @Setter
public class Board {
    //instance variables

    private
    /**
     * @param tileList List of tiles to set tileList to
     * @return A list of the current Tiles on the board (all tiles except dead ones?)
     */
    List<Tile> tileList;

    /**
     * @param companyList list of companies to update companies to
     * @return A list of the current companies
     */
    private List<Company> uncharteredCompanies;
    private List<Company> charteredCompanies;
    private List<Player> playerList;
    private Player CurrentPlayer;

    private static Board instance = null; //Singleton instance variable

    /**
     * Default constructor
     */
    private Board() {
        this.tileList = new ArrayList<Tile>();
        this.uncharteredCompanies = new ArrayList<Company>();
        this.charteredCompanies = new ArrayList<Company>();
        this.playerList = new ArrayList<Player>();

    }

    /**
     * Constructor with variables passed in from getInstance()
     *
     * @param tl list of tiles
     * @param uc list of unchartered companies
     * @param cc list of chartered companies
     * @param pl list of players
     */
    private Board(List tl, List uc, List cc, List pl) {
        this.tileList = tl;
        this.uncharteredCompanies = uc;
        this.charteredCompanies = cc;
        this.playerList = pl;
        //  this.tileList2D = tileList2D;
    }

    /**
     * Default getInstance
     *
     * @return a board instance
     */
    public static Board getInstance() {

        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    /**
     * @param tl list of tiles
     * @param uc list of unchartered companies
     * @param cc list of chartered companies
     * @param pl list of players
     * @return instance of a board
     */
    //Instance variable for the singleton
    public static Board getInstance(List tl, List uc, List cc, List pl) {

        if (instance == null) {
            instance = new Board(tl, uc, cc, pl);
        }
        return instance;
    }

    public void setNull() {
        instance = null;
    }

    //special getter methods

    /**
     * Should query the number of tiles of a given company that are on a board
     *
     * @param company A company
     * @return
     */
    List<Tile> getTilesOnBoard(Company company) {
        List<Tile> tilesOnBoard = company.getTilesOnBoard();
        return tilesOnBoard;
    }

    /**
     * @param company Company passed in
     * @return the number of tiles the company has on the board
     */
    public int getCompanyNumberOfTiles(Company company) {
        return company.getNumTiles();
    }

    /**
     * Gives a specified player a new tile from the list of tiles that are not currently on the board in a hand.
     *
     * @param player current player who we are dealing tiles to
     */
   //Tested when playing the game in the UI
    public void dealTile(Player player) {
        Random ran = new Random();
        int randomIndex = ran.nextInt(getTileList().size());
        Tile pulledTile = getTileList().get(randomIndex);
        if (!pulledTile.isFlipped() && !pulledTile.isDealt() && !pulledTile.isDead()) { // If the tile is able to be dealt
            pulledTile.setDealt(true);
            player.addTile(pulledTile);
        } else { // recursive call if it can't deal the tile
            dealTile(player);
        }
    }

    /**
     * Finds the lowest stock price for all the stocks.
     *
     * @return Returns the lowest stock price of any company in chartered companies
     */

    //Need a full board with companies and proper UI set up in order to test. Testing inside of the game with UI
    public int getLowestStockPrice() {
        int currentLowestPrice = 10000; // Default value, very high since we descend as we get better prices.
        for (Company company : charteredCompanies) {
            if (currentLowestPrice > company.calculateStockPrice()) {
                currentLowestPrice = company.calculateStockPrice();
            }
        }
        return currentLowestPrice;
    }

    /**
     * This returns a list of Tiles around a given coord.
     *
     * @param coord Coordinate of a tile
     * @return A List of all of the Tiles around the passed in coordinate.
     */
    public List<Tile> getTilesAround(int[] coord) throws Exception {

        List<Tile> tilesAround = new ArrayList<Tile>();

        Tile south = getAdjacentTile(coord, "SOUTH");
        Tile north = getAdjacentTile(coord, "NORTH");
        Tile west = getAdjacentTile(coord, "WEST");
        Tile east = getAdjacentTile(coord, "EAST");

        if (south != null) {
            tilesAround.add(south);
        }
        if (north != null) {
            tilesAround.add(north);
        }
        if (west != null) {
            tilesAround.add(west);
        }
        if (east != null) {
            tilesAround.add(east);
        }

        tilesAround.removeIf(tl -> Arrays.equals(tl.getCoord(), new int[]{-1, -1})); //tilesAround shouldn't include tiles off border.
        return tilesAround;
    }


    /**
     * This method is NOT my finest work. It is vomitous, as they say.
     *
     * It returns a Tile based on a passed in coordinate and a cardinal direction, eg.  ([1,1], "EAST") returns the Tile on the board
     * that has the coordinate [1,2]
     *
     * @param coord       tile coordinate
     * @param cardinalDir A cardinal direction "WEST, EAST, NORTH, SOUTH"
     * @return a tile to the cardinal direction of the passed in coord eg. "West of [1,1]" would return [1,0]
     */

    public Tile getAdjacentTile(int[] coord, String cardinalDir) {

        int row = coord[0];
        int col = coord[1];
        int[] adjCoord = {-1, -1};
        if (cardinalDir.equals("NORTH")) {
            //gets tile to the north
            if (row > 0) {
                adjCoord[0] = row - 1;
                adjCoord[1] = col;
            }
        } else if (cardinalDir.equals("SOUTH")) {
            //gets tile to the south
            if (row < 9) { //ALEX NOTE: This is a magic number, I know.
                adjCoord[0] = row + 1;
                adjCoord[1] = col;
            }
        } else if (cardinalDir.equals("WEST")) {
            //gets tile to the west
            if (col > 0) {
                adjCoord[0] = row;
                adjCoord[1] = col - 1;
            }
        } else if (cardinalDir.equals("EAST")) {
            //gets tile to the east
            if (col < 12) { //ALEX NOTE: This is a magic number, I know.
                adjCoord[0] = row;
                adjCoord[1] = col + 1;
            }
        }
        return arrayEquals(adjCoord);
    }


    /**
     * Checks if a passed in coordinate has a tile associated with it and returns it
     * otherwise returns a tile with the coordinate [-1,-1]
     *
     * @param adj the adjacent tile that our getAdjacentTile() method has created.
     * @return
     */
    private Tile arrayEquals(int[] adj) {
        for (Tile tl : getTileList()) { //for each tile on the board.
            if (Arrays.equals(tl.getCoord(), adj)) { //checks if the passed in coordinate equals the coordinate of the current tile.
                //System.out.println("the tile associated with "+ adj[0] + " " + adj[1] + " is "+ tl);
                return tl; //returns the tile associated with that coordinate
            }
        }
        return new Tile(); //else our default constructor for a Tile object instantiates a Tile with the Coordinate [-1,-1] and returns it
    }

    //other methods

    /**
     * sets a passed in company to be chartered then calls charterLogic.
     *
     * @param company Company to charter
     */
    public void charter(Company company) {
        //sets up the data structures to charter
        company.setChartered(true);
        charteredCompanies.add(company);
        uncharteredCompanies.remove(company);
        company.setNumTiles(1); // Any new company that is made will start with 1 tile set.
        //initiates charter logic. This will do things like initiate a user action to decide
        //which company they want to charter, and then fill in data like initial stock price and
        //initial stocks on board.
        charterLogic(company);
    }

    /**
     * Unlike our charter method, our uncharter method should not initiate the unchartering process. Instead it should be called as part of the clean up process of a merge.
     *
     * @param company Company to uncharter
     */
    public void unCharter(Company company) {
        company.setChartered(false);
        company.setNumTiles(0);
        charteredCompanies.remove(company);
        uncharteredCompanies.add(company);
    }


    /**
     * Looks at the tiles around a played tile and decided whether a merge, charter, add to company, or no action is needed.
     *
     *
     * @param tile Tile that was just played by a player
     * @return an integer representing the action that needs to happen.
     */
    //we want jacoco to ignore this, beacuse it throws an exception (tests are implemented though)
    public int checkForActionInitiation(Tile tile) {

        //ALEX NOTE: If the passed in tile does not have a true isFlipped status we need to throw an exception,
        //but i dont know how to do that.
        List<Tile> tilesAroundCoord = null;
        try {
            tilesAroundCoord = getTilesAround(tile.getCoord());
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Company> uniqueCompaniesAroundTile = new ArrayList<Company>();
        int flippedTilesAroundTile = 0;
        int numOfPermanentCompanies = 0;

        //makes a list of all the unique companies around the tile.

        for (Tile tl : tilesAroundCoord) {
            if (!tl.getCompany().getCompanyName().equals("DEFAULT")) { //if the company of this tile is not the default one.
                if (!uniqueCompaniesAroundTile.contains(tl.getCompany())) { //and if we have not already added this tile
                    uniqueCompaniesAroundTile.add(tl.getCompany());  // then add the tile.
                    if(tl.getCompany().isPermanent()){ numOfPermanentCompanies++;}
                }
            }
        }
        //checks if any tiles around our Tile are flipped.
        for (Tile tl : tilesAroundCoord) {
            if (tl.isFlipped()) {
                flippedTilesAroundTile++;
            }
        }
        //this checks iff we have a flipped and unchartered tile next to our Tile, (iff= if and only iff)
        if (uniqueCompaniesAroundTile.isEmpty() && flippedTilesAroundTile > 0) {
            return 1; //flipped but not chartered
        }
        //If there is one company found around this tile, we can add this tile to that company
        else if (uniqueCompaniesAroundTile.size() == 1) {
            return 2; //1 chartered comp seen
        }
        else if (uniqueCompaniesAroundTile.size() > 1 && numOfPermanentCompanies <=1) {

            //merge needed
            // This should simply return 3 back to getTileChoice() in GameState. It then turns around and calls board.merge()
            return 3;
        }
        else if(uniqueCompaniesAroundTile.size() > 1 && numOfPermanentCompanies > 1 ){
            // You place a tile between two percent companies
            return 4;
        }
        return 0;

    }


    /**
     * @param tl this should return a list of companies around a given tile, using the getTilesAround method
     * @return A list of unique non default companies around a tile
     */
    //Tested while playing the game in the UI
    public List<Company> companiesAroundTile(Tile tl) {
        List<Tile> tilesAround = null;
        try {
            tilesAround = getTilesAround(tl.getCoord());
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Company> companiesAround = new ArrayList<Company>();
        for (Tile t : tilesAround) {

            //Adds tiles companies around the current tile if they haven't been added already and aren't default.
            if (!t.getCompany().getCompanyName().equals("DEFAULT") && !companiesAround.contains(t.getCompany())) //This saying that objects equal if an attribute is equal is a code smell
            {
                System.out.println(t.getCompany());
                companiesAround.add(t.getCompany());
            }
        }
        return companiesAround;
    }

    /**
     * This method could more accurately be called "add surrounding flipped tiles to a company"
     * It essentially runs through the whole board and adds uncharted flipped tiles to the companies next to them (if any)
     *
     * @param company The Company that we are setting flipped adjacent tiles to be part of.
     */
     //tested while playing the game with the UI
    public void charterLogic(Company company) {

        //essentially what we have to do here, is look at every flipped and unchartered tile on the board, to see which of them have
        //neighbors that are flipped and chartered.
        // Then, every tile that is neighboring a chartered tile, is flipped, and has a default company, has its
        //company set to the passed in company.

        //So we can set up a very ugly nested while for loop:
        int foundTiles = 1; //flag
        while (foundTiles > 0) { //while we still have tiles to add to companies
            foundTiles = 0; //reset counter
            for (Tile tile : getTileList()) { //for every tile on the board
                if (!tile.getCompany().getCompanyName().equals("DEFAULT")) {
                    // System.out.println(tile);
                }
                List<Tile> tilesAroundThisPos = null;
                try {
                    tilesAroundThisPos = getTilesAround(tile.getCoord());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //System.out.println(tilesAroundThisPos.toString());
                boolean one_of_the_tiles_around_the_current_tile_has_our_company = false; //explains itself

                for (Tile tl : tilesAroundThisPos) {

                    //   System.out.println(tl.tileCoordToString()+ " " + tl.getCompany().getCompanyName()+ " " + company.getCompanyName());
                    if (tl.getCompany().getCompanyName().equals(company.getCompanyName())) {
                        one_of_the_tiles_around_the_current_tile_has_our_company = true;
                    }
                    //If our current tile is flipped but of a default company, and
                    // if we have an adjacent chartered tile
                    //   System.out.println("Name of company: "+tile.getCompany().getCompanyName()+ " isFlipped: "+ tile.isFlipped()+ " tilearoundhascurrentcomp: "+one_of_the_tiles_around_the_current_tile_has_our_company );
                    if (tile.getCompany().getCompanyName().equals("DEFAULT") &&
                            tile.isFlipped() && one_of_the_tiles_around_the_current_tile_has_our_company) {
                        //  System.out.println("TILE AROUND HAS COMPANY");
                        tile.setCompany(company); //set our current tile to be part of our passed in company
                        company.setNumTiles(company.getNumTiles() + 1);
                        foundTiles++; // if this is hit, we have found a tile, so our loop will restart after it hits the last tile on the board.
                    }
                }
            }
            //System.out.println("Company "+ company.getCompanyName() + " is now chartered");
        }
    }

    /**
     * This algorithm should be pretty close to the one above, except that it checks inside out (tile -> adjacent tiles) as opposed to
     * outside in (adjacent -> the tile that we are observing).
     *
     * @param comp The comp to set our adj tile to
     */
     //This is tested inside of the actual game UI
    public void addToCompLogic(Company comp)  {
        //System.out.println(comp.getCompanyName());
        int counter = 1;
        //while loop so that we can deal with multiple adjacent tiles
        while (counter > 0) {
            for (Tile tl : tileList) { //for every tile on the board
                counter = 0; //resets counter
                if (tl.getCompany().getCompanyName().equals(comp.getCompanyName())) { //if our current tile has the company to add adjacent tiles to
                    try {
                        for (Tile adj : getTilesAround(tl.getCoord())) {
                            if (adj.isFlipped() && adj.getCompany().getCompanyName().equals("DEFAULT")) { //then for any flipped default tile
                                adj.setCompany(comp); //add it
                                comp.setNumTiles(comp.getNumTiles() + 1); // Increase tile count for company
                                counter++; //and keep the loop going to check for stragglers.
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Lets a merge happen based on a passed in winner and list of losers (companies)
     *
     * @param winnerCo The winning company of the merger
     * @param loserCos The Losing companies of the merger
     */
    public void mergeLogic(Company winnerCo, List<Company> loserCos){

        //this algorithm checks if a tile is associated with a loser company
        //and if it is, changes its company to be the winner company
        for(Tile tl: tileList){
                for(Company c:loserCos){
                    if(tl.getCompany().equals(c))
                    {
                        tl.setCompany(winnerCo);
                    }
            }
        }
        //uncharters the small companies, increase number of tiles for winner, and reset loser companies to 0 tiles on board.
        for(Company c: loserCos){
            c.setNumTiles(0);
            winnerCo.setNumTiles(winnerCo.getNumTiles() + c.getNumTiles());
            unCharter(c);
        }
    }


    @Override
    public String toString() {
        return "Board{" +
                "tileList=" + tileList +
                ", uncharteredCompanies=" + uncharteredCompanies +
                ", charteredCompanies=" + charteredCompanies +
                ", playerList=" + playerList +
                ", CurrentPlayer=" + CurrentPlayer +
                '}';
    }

}
