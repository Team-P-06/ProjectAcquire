/**
 * @author Team 404
 * @version v0.0.1
 */
package ProjectAcquire;

import lombok.Getter;
import lombok.Setter;

public class Tile {
<<<<<<< HEAD
   private @Getter @Setter Company company;
   private @Getter @Setter String coord;
=======
   private Company tilesCompany;
   private int tileCoord;
>>>>>>> feature/UILogic
   private Boolean flipped;


    Tile(){

<<<<<<< HEAD
        this.company = new Company();
        this.coord = "A1";
=======
        this.tilesCompany = new Company();
        this.tileCoord = 1;
>>>>>>> feature/UILogic
        this.flipped = false;
    }

    /**
     *
     * @param tilesCompany the company the tile is a part of.
     * @param tileCoord the coordinate of the tile
     * flipped default value should always be false when created. The default company should be a empty company
     */
<<<<<<< HEAD
    Tile(Company tilesCompany, String tileCoord){
        this.company = tilesCompany;
        this.coord = tileCoord;
=======
    Tile(Company tilesCompany, int tileCoord){ //It might work better to make tileCoord to be an int between 1 - 107
        this.tilesCompany = tilesCompany;
        this.tileCoord = tileCoord;
>>>>>>> feature/UILogic
        this.flipped = false;
    }

    /**
     * Checks if the tile is on the board yet.
     */
    public Boolean isFlipped(){
        return flipped;
    }

    /**
<<<<<<< HEAD
     * Sets the flipped instance variable to true
=======
     *
     * @return the coordinates of the tile
     */
    public int getCoord(){
        return tileCoord;
    }

    /**
     *
     * @return the company the tile belongs to.
>>>>>>> feature/UILogic
     */
    public void setFlipped(){
        this.flipped = true;
    }
}
