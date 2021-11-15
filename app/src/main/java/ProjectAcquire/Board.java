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
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Board {
    //instance variables

    private
    /**
     * @param tileList List of tiles to set tileList to
     * @return A list of the current Tiles on the board (all tiles except dead ones?)
     */
    @Getter @Setter List<Tile> tileList;
    @Getter @Setter List<List<Tile>> tileList2D;


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
      //  this.tileList2D = tileList2D;
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
     * @param company Company passed in
     * @return the number of tiles the company has on the board
     */
   public int getCompanyNumberOfTiles(Company company){
        return company.getTilesOnBoard();
    }

    /**
     * Gives a specified player a new tile from the list of tiles that are not currently on the board in a hand.
     * @param allTiles full tile list
     * @param player  current player who we are dealing tiles to
     */
    public void dealTile(Player player){
        Random ran = new Random();
        int randomIndex = ran.nextInt(getTileList().size());
        Tile pulledTile = getTileList().get(randomIndex);
        if (!pulledTile.isFlipped()){ // If the tile is able to be dealt
            //System.out.println(pulledTile.isDealt());
            pulledTile.setDealt(true);
            player.addTile(pulledTile);
        }
        else{ // recursive call if it can't deal the tile
            dealTile(player);
        }
        //System.out.println("DEAL REACHED");
    }

    public int getLowestStockPrice(){
        int currentLowestPrice = 100000; // Default value, very high since we descend as we get better prices.
        for (Company company : charteredCompanies){
            if (currentLowestPrice > company.getStockPrice()){
             currentLowestPrice = company.getStockPrice();
            }
        }
        return  currentLowestPrice;
    }

    /**
     * This
     *
     * @param coord Coordinate of a tile
     * @return An ArrayList of all of the Tiles around the passed in coordinate.
     */
    public List<Tile> getTilesAround(int[] coord) throws Exception {

      List<Tile> tilesAround =  new ArrayList<Tile>();

      Tile south = getAdjacentTile(coord,"SOUTH");
      Tile north = getAdjacentTile(coord,"NORTH");
      Tile west = getAdjacentTile(coord,"WEST");
      Tile east = getAdjacentTile(coord,"EAST");
      if(south!=null){
          tilesAround.add(south);
      }
        if(north!=null){
            tilesAround.add(north);
        }
        if(west!=null){
            tilesAround.add(west);
        }
        if(east!=null){
            tilesAround.add(east);
        }
        System.out.println("Tiles around "+ coord[0]+ " " + coord[1]+ ": ");

        for(Tile tl : tilesAround) {
            System.out.print(tl);
        }
        return tilesAround;
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
        int[] adjCoord = {-1,-1};
        //System.out.println("["+row + " "+col+"]");
       // System.out.println(getTileList());


       if (cardinalDir.equals("NORTH")) {
           //gets tile to the north
           if (row > 0) {
               adjCoord[0] = row - 1;
               adjCoord[1] = col;
           }

           }
       else if (cardinalDir.equals("SOUTH")){
           //gets tile to the south

           if (row < 9) { //ALEX NOTE: This is a magic number, I know.
               adjCoord[0] = row + 1;
               adjCoord[1] = col;
           }
        }
       else if (cardinalDir.equals("WEST")){
           //gets tile to the west
           if (col>0) {
               adjCoord[0] = row;
               adjCoord[1] = col-1;
           }
           //System.out.println(adjCoord[0]+ " "+ adjCoord[1]);

       }
       else if (cardinalDir.equals("EAST")){
           //gets tile to the east
           if (col<12) { //ALEX NOTE: This is a magic number, I know.
               adjCoord[0] = row;
               adjCoord[1] = col+1;

             //  System.out.println(row+ " "+ col);
              // System.out.println(adjCoord[0]+ " "+ adjCoord[1]);

           }

       }
       return arrayEquals(adjCoord);
    }

//    /**
//     *
//     * @param coord Tile coordinate passed in
//     * @param upOrDown -1 for down 1 for up
//     * @param leftOrRight -1 for left 1 for right
//     * @return an adjacent Tile, if not null.
//     */
    //public Tile getAdjacentTile(int[] coord, int upOrDown, int leftOrRight)throws Exception{

       //int[] coord = tl.getCoord();

       // List<List<Tile>> tileList2d = new ArrayList<>();

        //Tile adjCoord;
//        try {
//             adjCoord = tileList2d.get(coord[0] + upOrDown).get(coord[1] + leftOrRight);
//            System.out.println("ADJ COORD = "+adjCoord.toString());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        finally {
//           adjCoord = new Tile(new Company(), new int[]{-1,-1});
//       }
//       return adjCoord;

   // }


    private Tile arrayEquals(int[] adj) {

        for (Tile tl : getTileList()) {
            if (Arrays.equals(tl.getCoord(), adj)) {
                   System.out.println("the tile associated with "+ adj[0] + " " + adj[1] + " is "+ tl);
                return tl;
            }
        }
        return new Tile();
    }

    //other methods

    /**
     * Will deal with the entire chartering process of a company, including prompting for user input and calling our update() method when we implement the observable pattern
     * @param company Company to charter
     */
    void charter(Company company) throws Exception {
        //sets up the data structures to charter
        company.setChartered(true);
        charteredCompanies.add(company);
        uncharteredCompanies.remove(company);

        //initiates charter logic. This will do things like initiate a user action to decide
        //which company they want to charter, and then fill in data like initial stock price and
        //initial stocks on board.
        charterLogic(company);

        System.out.println("Board.charter() was called");

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
    private boolean checkForTileAction(int[] coord) throws Exception {

        //ALEX NOTE: I think that this method is important to implement, but I don't remember why atm. leaving true for now

        boolean actionIsRequired = false;
        List<Tile> currentTileList = instance.getTileList();

        List<Tile> tilesAroundCoord = getTilesAround(coord);
        int numFlipped =0;
        for(Tile x : tilesAroundCoord){
            if (x.isFlipped()){
                numFlipped++;
            }
        }
        //checks how many of the tiles are flipped, then chartered calls appropriate method with some if elses.

        //if action is required, return true

        if (numFlipped>0){
            return true;
        }

        return false;
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
    public int checkForActionInitiation(Tile tile) throws Exception {

        //ALEX NOTE: If the passed in tile does not have a true isFlipped status we need to throw an exception,
        //but i dont know how to do that.

        List<Tile> currentTileList = instance.getTileList();
        List<Tile> tilesAroundCoord = getTilesAround(tile.getCoord());
     //   System.out.println(tilesAroundCoord);

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
        //System.out.println(uniqueCompaniesAroundTile);
        for(Tile tl: tilesAroundCoord){
            if(tl.isFlipped()){
                flippedTilesAroundTile++;
            }
        }
       // System.out.println(flippedTilesAroundTile);

        if(uniqueCompaniesAroundTile.isEmpty() && flippedTilesAroundTile>0){ //this checks if we have a flipped but unchartered tile next to us
            //This should mean that we can charter a new company.
            //charter(tile.getCompany());

            return 1; //ALEX NOTE: I am moving our interrupt back to GameState to avoid having to pass GameState around
            //gameState.charterChoiceInterrupt(); //Calls UI to update the screen with a choice for the player to choose. After choice UI calls charter().
        }
        else if(uniqueCompaniesAroundTile.size()==1 ){
            //If there is one company found around this tile, we can add this tile to that company
            //We do this by passing in the tile's company to charterLogic, which will initiate our algorithm.
            //charterLogic(tile.getCompany());
            return 2;
        }
        else if(uniqueCompaniesAroundTile.size()>1 ){

            //merge needed
            // This should simply return 3 back to getTileChoice() in GameState. It then turns around and calls board.merge()
            return 3;
        }

        return 0;
        
    }




    public void merge(Company winnerCompany){} //leaving this alone for now.

    public List<Company> checkEqualsMerge(){ //Should return a list of companies that are equal, return null if non are equal.
        return null;
    }

    public Company getDefunctCompany(){ // Should return the company that is being defunct(assuming 2 are not equal)
        return null;
    }


    /**
     *This method could more accurately be called "add surrounding flipped tiles to a company"
     * It essentially runs through the whole board and adds unchartered flipped tiles to the companies next to them (if any)
     *
     * @param company The Company that we are setting flipped adjacent tiles to be part of.
     */
    public void charterLogic(Company company) throws Exception {

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
                    // if we have an adjacent chartered tile
                    if ( tile.getCompany().getCompanyName().equals("DEFAULT") &&
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
