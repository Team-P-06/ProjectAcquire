/**
 * @author Team 404
 * @version v0.0.1
 */
package ProjectAcquire;

import lombok.Getter;
import lombok.Setter;

public class Tile {
   private @Getter @Setter Company company;
   private @Getter @Setter int coord;
   private Boolean flipped;
   private @Getter @Setter Boolean dealt;


   //Default constructor
    Tile(){
        this.coord = 1;
        this.company = new Company();
        this.flipped = false;
    }

    /**
     *
     * @param tilesCompany the company the tile is a part of.
     * @param tileCoord the coordinate of the tile
     * flipped default value should always be false when created. The default company should be a empty company
     */
    Tile(Company tilesCompany, int tileCoord){
        this.company = tilesCompany;
        this.coord = tileCoord;
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
     * @return the company the tile belongs to.
     */
    public void setFlipped(){
        this.flipped = true;
    }
}
