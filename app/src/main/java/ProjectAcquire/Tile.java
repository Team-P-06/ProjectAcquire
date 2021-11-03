/**
 * @author Show Pratoomratana, Team 404 Project Acquire
 * @version v0.0.1
 */
package ProjectAcquire;

public class Tile {
    Company tilesCompany;
    String tileCoord;
    Boolean flipped;

    /**
     *
     * @param tilesCompany the company the tile is apart of.
     * @param tileCoord the coordinate of the tile
     * flipped default value should always be false when created. The default company should be a empty company
     */
    Tile(Company tilesCompany, String tileCoord){
        this.tilesCompany = tilesCompany;
        this.tileCoord = tileCoord;
        this.flipped = false;
    }

    /**
     * Checks if the tile is on the board yet.
     */
    public Boolean isFlipped(){
        return flipped;
    }

    /**
     *
     * @return the coordinates of the tile
     */
    public String getCoord(){
        return tileCoord;
    }

    /**
     *
     * @return the company the tile belongs to.
     */
    public Company getCompany(){
        return tilesCompany;
    }

    /**
     * Changes the company the tile belongs to
     * @param newCompany the new company the tile is changing to.
     */
    public void setCompany(Company newCompany){
        tilesCompany = newCompany;
    }

    /**
     * creates a new unique tile and give it to the player who drew it.
     * @return a new unique tile
     */
    public Tile drawTile(){

    }

    /**
     * This might be better to be in Player since Player already has an array of tiles
     * Removes a tile from the players hand
     */
    public void discardTile(Tile){

    }

}
