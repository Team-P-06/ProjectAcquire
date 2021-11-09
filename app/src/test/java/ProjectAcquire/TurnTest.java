/**
 *  @author Team 404
 *  @version v0.0.1
 */
package ProjectAcquire;
import org.junit.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * TurnTest class that will test the methods of hasturn and nextturn since they are related together
 */
public class TurnTest {
    /**
     * Create a fake gamestate so we can use known variables for the test
     * @return
     */
    GameState testTurns() {
        Player Player1 = new Player();
        Player Player2 = new Player();
        Board Board = new Board();
        List<Player> turnList = null;
        turnList.add(Player1);
        turnList.add(Player2);
        GameState testGame = new GameState(Player1, Player2, Board, turnList);
        return testGame;
    }

    /**
     * Test the methods of hasturn and nextturn methods inside the gamestate class
     */
    @Test public void test_Turn_sequence(){
        GameState runTestGame = testTurns();
        //has turn method that will take the has turn method from the original gamestate class and then passes the parameter
        //of the fake gamestate we created with the fake first player inside of that fake gamestate to test and see if the
        //first player has a turn. The assert should be true since the list added the first player in the first index
        //of the player list
        assertTrue(runTestGame.hasTurn(runTestGame.getFirstPlayer()));

        //This test essentially will do the same thing with the has turn method inside the origianl gamestate class
        //using the fake gamestate and fake players as the test above will do but this one will return false because the
        //parameter passed in the has turn method is the second fake player wich has the second index of the player list
        assertFalse(runTestGame.hasTurn(runTestGame.getSecondPlayer()));

        //next turn method test here will test who the next player in the list is which will correspond to them having the
        //next turn in the game. the assert equals will take the fake gamestate created above and get the second player
        //since they have the second index in our faked player list and compare that to the nextTurn method which should
        //return back a player object corresponding to the second index, in this case we know it should be the second player
        assertEquals(runTestGame.getSecondPlayer(), runTestGame.nextTurn());

        //The play turn method being tested here will be called when each player plays their turn, but after each player
        //plays their turn the order of who the next player and the current player is has to rotate orderly. Thus the player
        //in the first index will then become the second player in the index and the second player in the index will take
        //the spot of the first index to have a next turn available
        runTestGame.playTurn();

        //These three tests here will run after the play method is called, and they are the same exact tests above but
        //instead of the first player being in index 1 and has a current turn the second player should be rotated
        //to the first index making the second player have a current turn and the first player to have the next turn available
        assertTrue(runTestGame.hasTurn(runTestGame.getSecondPlayer()));
        assertFalse(runTestGame.hasTurn(runTestGame.getFirstPlayer()));
        assertEquals(runTestGame.getFirstPlayer(), runTestGame.nextTurn());
    }

}
