/**
 * @author Alex Diviney, Team 404, Project Aquire
 * @version v0.0.1
 */

package ProjectAcquire;
import java.util.ArrayList;
import java.util.List;

public class Board {
    //instance variables
    private List<Tile> tileList;
    private List<Company> uncharteredCompanies;
    private List<Company> charteredCompanies;
    private List<Player> playerList;
    private Player CurrentPlayer;

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


    //Basic getters

    /**
     *
     * @return A list of the currently chartered companies
     */
    List getCharteredCompanies() {
        return charteredCompanies;
    }

    /**
     *
     * @return A list of the currently unchartered companies
     */
    List getUncharteredCompanies(){
            return uncharteredCompanies;
    }

    /**
     *
     * @return A list of the current Tiles on the board (all tiles except dead ones?)
     */
    List getTileList(){
            return tileList;
        }

    /**
     *
     * @return A list of the players
     */
    List getPlayerList(){
            return playerList;
    }

    //basic setters

    /**
     *
     * @param charteredCompanies A list of companies to update the charteredCompanies to
     */
    public void setCharteredCompanies(List charteredCompanies) {
        this.charteredCompanies = charteredCompanies;
    }

    /**
     *
     * @param uncharteredCompanies A list of companies to update the uncharteredCompanies to
     */
    public void setUncharteredCompanies(List uncharteredCompanies) {
        this.uncharteredCompanies = uncharteredCompanies;
    }

    /**
     *
     * @param playerList A list of players to set the playerList to
     */
    public void setPlayerList(List playerList) {
        this.playerList = playerList;
    }

    //Don't be scared of this. We will call this when updating the Board

    /**
     *
     * @param board_instance board state to set instance to
     */
    public static void setBoardInstance(Board board_instance) {
        Board.instance = board_instance;
    }

    /**
     *
     * @param currentPlayer Player to set currentPlayer to.
     */
    public void setCurrentPlayer(Player currentPlayer) {
        CurrentPlayer = currentPlayer;
    }

    /**
     *
     * @param tileList List of tiles to set tileList to
     */
    public void setTileList(List tileList) {
        this.tileList = tileList;
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
    int getCompanyNumberOfTiles(Company company){
        return company.getTilesOnBoard();
    }

    List getTilesAround(String coord){ return new ArrayList<String>();}

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

        //Since our board is instantiated, our BoardLogic methods must be static methods.
        //This further suggest that we may want to merge Company Logic and Board.
        BoardLogic.charterLogic(company);

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


}
