/**
 * @author Team 404
 * @version v0.0.1
 */
package ProjectAcquire;

import lombok.Getter;
import lombok.Setter;

public class Tile {
   private @Getter @Setter Company company;
   private @Getter @Setter String coord;
   private Company tilesCompany;
   private int tileCoord;
   private Boolean flipped;


    Tile(){

        this.company = new Company();
        this.coord = "A1";
        this.tilesCompany = new Company();
        this.tileCoord = 1;
        this.flipped = false;
    }

    /**
     *
     * @param tilesCompany the company the tile is a part of.
     * @param tileCoord the coordinate of the tile
     * flipped default value should always be false when created. The default company should be a empty company
     */
    Tile(Company tilesCompany, int tileCoord){
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
     * Sets the flipped instance variable to true
     * @return the coordinates of the tile
     */
    public int getCoord(){
        return tileCoord;
    }

    /**
     *
     * @return the company the tile belongs to.
     */
    public void setFlipped(){
        this.flipped = true;
    }
}
