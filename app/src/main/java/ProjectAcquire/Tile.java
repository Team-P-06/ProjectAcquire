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
