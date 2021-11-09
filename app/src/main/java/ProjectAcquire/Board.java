/**
 * @author Team 404
 * @version v0.0.1
 */

package ProjectAcquire;
import lombok.Getter;
import lombok.Setter;
import lombok.Generated;

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

    private
    /**
     * @param companyList list of companies to update companies to
     * @return A list of the current companies
     */
    @Getter @Setter List<Company> uncharteredCompanies;

    private @Getter @Setter List<Company> charteredCompanies;
    private @Getter @Setter List<Player> playerList;
    private @Getter @Setter Player CurrentPlayer;

    private static Board instance = null; //Singleton instance variable

    /**
     * Default constructor
     */
    public Board(){
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
    public Board(List tl, List uc, List cc, List pl){
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
