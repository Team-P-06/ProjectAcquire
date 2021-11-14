/**
 * @author Team 404
 * @version v0.0.1
 */
package ProjectAcquire;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

public class Tile {
    /**
     * Coord is {Row, Column}
     */
   private @Getter @Setter Company company;
   private @Getter @Setter int[] coord;
   private boolean flipped;
   private @Setter boolean dealt;


   //Default constructor
    Tile(){
        this.coord = new int[]{0, 0};
        this.company = new Company();
        this.flipped = false;
        this.dealt = false;
    }

    /**
     *
     * @param tilesCompany the company the tile is a part of.
     * @param tileCoord the coordinate of the tile
     * flipped default value should always be false when created. The default company should be a empty company
     */
    Tile(Company tilesCompany, int[] tileCoord){
        this.company = tilesCompany;
        this.coord = tileCoord;
        this.flipped = false;
        this.dealt = false;
    }

    /**
     * Checks if the tile is on the board yet.
     */
    public boolean isFlipped(){ return flipped; }

    public boolean isDealt(){ return dealt; }

    public boolean isDealable(){
        return !isFlipped() && !isDealt();
    }

    /**
     *
     * @return the company the tile belongs to.
     */
    public void setFlipped(){
        this.flipped = true;
    }

    /**
     * Extracts the string names of the tiles in the list of tiles
     *
     * @param tile the tile you'd like to get the name of.
     * @return A coordinate name of the unique tile.
     */
    public String tileCoordToString() {
        String tileString;
        int[] coord = getCoord();
        int row = coord[0];
        String col = Integer.toString(coord[1] + 1);
        switch (row) {
            case 0 -> tileString = "A" + (col);
            case 1 -> tileString = ("B" + col);
            case 2 -> tileString = ("C" + col);
            case 3 -> tileString = ("D" + col);
            case 4 -> tileString = ("E" + col);
            case 5 -> tileString = ("F" + col);
            case 6 -> tileString = ("G" + col);
            case 7 -> tileString = ("H" + col);
            case 8 -> tileString = ("I" + col);
            default -> tileString = ("0" + col);
        }
        return tileString;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "coord=" + tileCoordToString() +
                '}';
    }
}
