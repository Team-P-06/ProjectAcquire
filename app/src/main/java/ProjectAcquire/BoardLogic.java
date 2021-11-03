/**
 * @author Alex Diviney, Team 404, Project Aquire
 * @version v0.0.1
 */

package ProjectAcquire;

import java.util.List;

/**
 * @author Alex Diviney, Team 404, Project Aquire
 * @version v0.0.1
 */

public class CompanyLogic {

    //Not sure these instance variables are needed, since CompanyLogic is not an Object.
    String coordinate;
    List currentTileList;


    /**
     *  Should check for an action on a passed in coord string. Essentially, if the tile at that coord touches a tile of a different company than itself, an action is chosen.
     *  Based on the action (merge or charter), the Board.merge() or Board.charter() functions are called.
     *
     *  If a charter action is chosen, before the charter method is called, the system should prompt the user to choose a company, then that company is passed in to the charter method.
     */
    public static void checkForAction(String coord){}

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


        System.out.println("Company "+ company.getCompanyName() + " is now chartered");
    }




}
