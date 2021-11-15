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

import java.util.ArrayList;
import java.util.List;


public class BoardLogic {

    //ALEX NOTE: I AM DEPRECATING THIS CLASS AND MOVING ALL METHODS TO BOARD


    /**
     *  Should check for an action on a passed in coord string, using the current board. Essentially, if the tile at that coord touches a tile of a different company than itself, an action is chosen.
     *  Based on the action (merge or charter), the Board.merge() or Board.charter() functions are called.
     *
     *  If a charter action is chosen, before the charter method is called, the system should prompt the user to choose a company, then that company is passed in to the charter method.
     */
    public static void checkForAction(Board currentBoard, int[] coord){

        List<Tile> currentTileList = currentBoard.getTileList();

        List<Tile> tilesAroundCoord = new ArrayList<Tile>();

        int row = coord[0];
        int column = coord[1];

        //tilesAroundCoord.add(currentTileList.get(coord[0]).get(coord[1]))

        //Grab tiles from array above and below, and then the tiles beside the tile, making sure to check for borders

        //checks how many of the tiles are flipped, then chartered then calls appropriate method with some if elses. 

    }

   private static void merge(){} //leaving this alone for now.

    /**
     * Design Notes: Our conceptual diagram has this as a private method, but it is currently public
     * due to the way I have set up the action process. We should look over the call sequence to
     * determine if this is acceptable or not. This method is currently accessed by Board.charter(), instead of only directly by checkForAction()
     *
     * method description: Should check for action on a company to determine initial size, name etc.
     *
     *
     * @param company
     */
    static void charterLogic(Company company){


       // System.out.println("Company "+ company.getCompanyName() + " is now chartered");
    }




}
