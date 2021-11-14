/**
 * @author Team 404
 * @version v0.0.1
 */

package ProjectAcquire;
import lombok.Getter;
import lombok.Setter;
import lombok.Generated;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Board {
    //instance variables

    private
    /**
     * @param tileList List of tiles to set tileList to
     * @return A list of the current Tiles on the board (all tiles except dead ones?)
     */
    @Getter @Setter List<Tile> tileList;


    /**
     * @param companyList list of companies to update companies to
     * @return A list of the current companies
     */
    private @Getter @Setter List<Company> uncharteredCompanies;

    private @Getter @Setter List<Company> charteredCompanies;
    private @Getter @Setter List<Player> playerList;
    private @Getter @Setter Player CurrentPlayer;

    private static Board instance = null; //Singleton instance variable

    /**
     * Default constructor
     */
    private Board(){
    this.tileList = new ArrayList<Tile>();
    this.uncharteredCompanies = new ArrayList<Company>();
    this.charteredCompanies = new ArrayList<Company>();
    this.playerList = new ArrayList<Player>();

    }

    /**
     * Constructor with variables passed in from getInstance()
     * @param tl list of tiles
     * @param uc list of unchartered companies
     * @param cc list of chartered companies
     * @param pl list of players
     */
    private Board(List tl, List uc, List cc, List pl){
        this.tileList = tl;
        this.uncharteredCompanies = uc;
        this.charteredCompanies = cc;
        this.playerList = pl;
    }

    /**
     * Default getInstance
     * @return a board instance
     */
    public static Board getInstance(){

        if(instance == null){
            instance = new Board();
        }
        return instance;
    }

    /**
     *
     * @param tl list of tiles
     * @param uc list of unchartered companies
     * @param cc list of chartered companies
     * @param pl list of players
     * @return instance of a board
     */
    public static Board getInstance(List tl, List uc, List cc, List pl){

        if(instance == null){
            instance = new Board(tl,uc,cc,pl);
        }
        return instance;
    }

    public void setNull(){
        instance =null;
    }

    //special getter methods

    /**
     * Should query the number of tiles of a given company that are on a board
     * @param company A company
     * @return
     */
    int getTilesOnBoard(Company company) {
        int tilesOnBoard = company.getTilesOnBoard();
        return tilesOnBoard;
    }

    /**
     *
     * @param company Company passed in
     * @return the number of tiles the company has on the board
     */
   public int getCompanyNumberOfTiles(Company company){
        return company.getTilesOnBoard();
    }


    /**
     * This
     *
     * @param coord Coordinate of a tile
     * @return An ArrayList of all of the Tiles around the passed in coordinate.
     */
    private List<Tile> getTilesAround(int[] coord){

      List<Tile> tilesAround =  new ArrayList<Tile>();
      Tile adjTile;
      String[] cardinalDirs = {"NORTH","SOUTH","EAST","WEST"};
      for(String x : cardinalDirs){
         adjTile = getAdjacentTile(coord,x);
         if(adjTile!=null) {
             tilesAround.add(adjTile);
         }
      }

        return tilesAround ;
    }

    /**
     * Last changed by Alex.
     * This method is NOT my finest work. It is vomitous, as they say.
     *
     * @param coord tile coordinate
     * @param cardinalDir A cardinal direction "WEST, EAST, NORTH, SOUTH"
     * @return a tile to the cardinal direction of the passed in coord eg. "West of [1,1]" would return [1,0]
     */
    public Tile getAdjacentTile( int[] coord, String cardinalDir){

        int row = coord[0];
        int col = coord[1];
        int[] adjCoord = new int[2];


       if (cardinalDir.equals("NORTH")) {
           //gets tile to the north
           if (row > 0) {
               adjCoord[0] = row - 1;
               adjCoord[1] = col;
           }
               for (Tile tl : getTileList()) {
                   if (tl.getCoord() == adjCoord) {
                       return tl;
                   }
               }
           }
       else if (cardinalDir.equals("SOUTH")){
           //gets tile to the south

           if (row < 9) { //ALEX NOTE: This is a magic number, I know.
               adjCoord[0] = row + 1;
               adjCoord[1] = col;
           }
           for (Tile tl : getTileList()) {
               if (tl.getCoord() == adjCoord) {
                   return tl;
               }
           }
        }
       else if (cardinalDir.equals("WEST")){
           //gets tile to the west
           if (col>0) {
               adjCoord[0] = row;
               adjCoord[1] = col-1;
           }
           for (Tile tl : getTileList()) {
               if (tl.getCoord() == adjCoord) {
                   return tl;
               }
           }
       }
       else if (cardinalDir.equals("EAST")){
           //gets tile to the east
           if (col<12) { //ALEX NOTE: This is a magic number, I know.
               adjCoord[0] = row;
               adjCoord[1] = col-1;
           }
           for (Tile tl : getTileList()) {
               if (tl.getCoord() == adjCoord) {
                   return tl;
               }
           }
       }
       return null;
    }

    //other methods

    /**
     * Will deal with the entire chartering process of a company, including prompting for user input and calling our update() method when we implement the observable pattern
     * @param company Company to charter
     */
    void charter(Company company){
        //sets up the data structures to charter
        company.setChartered(true);
        charteredCompanies.add(company);
        uncharteredCompanies.remove(company);

        //initiates charter logic. This will do things like initiate a user action to decide
        //which company they want to charter, and then fill in data like initial stock price and
        //initial stocks on board.
        charterLogic(company);

    }

    /**
     * Unlike our charter method, our uncharter method should not initiate the unchartering process. Instead it should be called from our BoardLogic.merge() function as part of the clean up process of a merge.
     * @param company Company to uncharter
     */
    void unCharter(Company company){
        company.setChartered(false);
        charteredCompanies.remove(company);
        uncharteredCompanies.add(company);
    }

    void updateBoard(){
        //This has to do with our updatable interface/observable design pattern.

    }

    /**
     * This method will set the number of tiles on the board that a passed in company will have. It should be called by our checkForAction method as part of an action to execute.
     * @param company Company to update the tiles of
     * @param tileNum number of tiles the company should now have
     */
    private void updateCompanyTiles(Company company, int tileNum){
        company.setTilesOnBoard(tileNum);
    }

    void setDeadTile(Tile tile){} //don't remember what this is. Does it remove a tile from the board?

    /**
     *
     * @param coord String coordinate location of tile
     * @return if the company that is at that tile location is permanent.
     */
    boolean checkPermanent(String coord){return false;}



    /**
     * This action checker should be called a bunch of times from charterLogic.
     * It basically returns true if the tile that we passed in needs to be added to a chartered company
     *
     * @param coord The coordinate of a tile
     */
    private boolean checkForTileAction(int[] coord){

        //ALEX NOTE: I think that this method is important to implement, but I don't remember why atm. leaving true for now

        boolean actionIsRequired = false;
        List<Tile> currentTileList = instance.getTileList();

        List<Tile> tilesAroundCoord = new ArrayList<Tile>();

        int row = coord[0];
        int column = coord[1];

        //tilesAroundCoord.add(currentTileList.get(coord[0]).get(coord[1]))

        //Grab tiles from array above and below, and then the tiles beside the tile, making sure to check for borders

        //checks how many of the tiles are flipped, then chartered calls appropriate method with some if elses.

        //if action is required, return true

        return true;
    }

    /**
     * Should check for an action on a passed in coord string, using the current board. Essentially, if the tile at that coord touches a tile of a different company than itself, an action is chosen.
     * Based on the action (merge or charter), the Board.merge() or Board.charter() functions are called.
     *
     *  If a charter action is chosen, before the charter method is called, the system should prompt the user to choose a company, then that company is passed in to the charter method.
     *
     * @param  tile a FLIPPED passed in tile.
     *
     */
    public void checkForActionInitiation(Tile tile, GameState gameState) throws IOException {

        //ALEX NOTE: If the passed in tile does not have a true isFlipped status we need to throw an exception,
        //but i dont know how to do that.

        List<Tile> currentTileList = instance.getTileList();
        List<Tile> tilesAroundCoord = getTilesAround(tile.getCoord());

        List<Company> uniqueCompaniesAroundTile = new ArrayList<Company>();
        int flippedTilesAroundTile =0;

        //makes a list of all the unique companies around the tile.
        for (Tile tl : tilesAroundCoord){
            if(!tl.getCompany().getCompanyName().equals("DEFAULT")){ //if the company of this tile is not the default one.
                if(!uniqueCompaniesAroundTile.contains(tl.getCompany())){ //and if we have not already added this tile
                 uniqueCompaniesAroundTile.add(tl.getCompany());  // then add the tile.
                }
            }
        }

        for(Tile tl: tilesAroundCoord){
            if(tl.isFlipped()){
                flippedTilesAroundTile++;
            }
        }

        if(uniqueCompaniesAroundTile.isEmpty() && flippedTilesAroundTile>0){ //this checks if we have a flipped but unchartered tile next to us
            //This should mean that we can charter a new company.
            //charter(tile.getCompany());
            gameState.charterChoiceInterrupt(); //Calls UI to update the screen with a choice for the player to choose. After choice UI calls charter().
        }
        else if(uniqueCompaniesAroundTile.size()==1 ){
            //If there is one company found around this tile, we can add this tile to that company
            //We do this by passing in the tile's company to charterLogic, which will initiate our algorithm.
            charterLogic(tile.getCompany());
        }
        else if(uniqueCompaniesAroundTile.size()>1 ){

            //merge needed
        }
        
    }




    private void merge(){} //leaving this alone for now.


    /**
     *This method could more accurately be called "add surrounding flipped tiles to a company"
     * It essentially runs through the whole board and adds unchartered flipped tiles to the companies next to them (if any)
     *
     * @param company The Company that we are setting flipped adjacent tiles to be part of.
     */
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
                List<Tile> tilesAroundThisPos = getTilesAround(tile.getCoord());
                boolean one_of_the_tiles_around_the_current_tile_has_our_company = false; //explains itself
                for (Tile tl : tilesAroundThisPos) {
                    if (tl.getCompany().equals(company)) {
                        one_of_the_tiles_around_the_current_tile_has_our_company = true;
                    }

                    //If our current tile is flipped but of a default company, and
                    //If checkForAction returns true, and if we have an adjacent chartered tile
                    if (checkForTileAction(tile.getCoord()) && tile.getCompany().getCompanyName().equals("DEFAULT") &&
                            tile.isFlipped() && one_of_the_tiles_around_the_current_tile_has_our_company) {
                        tile.setCompany(company); //set our current tile to be part of our passed in company
                        foundTiles++; // if this is hit, we have found a tile, so our loop will restart after it hits the last tile on the board.
                    }

                }
            }


            // System.out.println("Company "+ company.getCompanyName() + " is now chartered");
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
