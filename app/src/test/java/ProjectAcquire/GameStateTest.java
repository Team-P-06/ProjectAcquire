package ProjectAcquire;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameStateTest {


    /**
     * Tests initalization of gameState. the gamestate tester should have an instance variable isOver that starts off as false
     */
    @Test void test_GameStateStart(){

      GameState tester = TestHelper.helperMethod_GameStateInit();

      assertTrue(!tester.isOver()); //game should start with isOVer as false

    }

    /**
     * Tests that our first turn goes smoothly after an initialization
     */
    @Test void test_GameState_initialTurn_currentPlayerNotNull(){

        GameState tester = TestHelper.helperMethod_GameStateInit();

        //Player initial turn

        Player initPlayer = tester.getCurrentPlayer(); //currentPlayer on initialization
       // System.out.println("Current player is: " + initPlayer);
       // System.out.println("playerList: "+ tester.getPlayerList());

        Player newCurrentPlayer = tester.getCurrentPlayer();//currentPlayer should be different
        //System.out.println("Current player is: " + newCurrentPlayer);
        //System.out.println("playerList: "+ tester.getPlayerList());

        //current player should now not be null.
        assertNotNull(newCurrentPlayer);

    }

    /**
     * Tests our GameState
     */
    @Test void test_GameState_initialTurn_deal_cards(){

        GameState test_gameState = TestHelper.helperMethod_GameStateInit();

    }


    @Test void test_playTurnWorksProperly(){



    }

    /**
     * We have tested the run game method thorughout the actual game in app
     * @throws IOException
     */
    /*
    @Test
    void test_RunGame() throws IOException {
        Game testGame = new Game();
        testGame.runGame();
        assertEquals(1,1);
    }

     */

    /**
     * We have tested the start method through actually implementing the game in app
     * @throws IOException
     */
    /*
    @Test
    void test_Start() throws IOException {
        Game testGame = new Game();
        testGame.start();
        assertEquals(1,1);
    }
     */


    /**
     * Test the next turn method to see if we can properly see who has the next turn in the game
     */
    @Test
    void test_nextTurn(){
        GameState test_gameState = TestHelper.helperMethod_GameStateInit();
        assertNotNull(test_gameState.nextTurn());

    }

    /**
     * Test if the initial turn method peeks the current player and sets the current player correctly
     */
    @Test
    void test_InitialTurn(){
        GameState test_gameState = TestHelper.helperMethod_GameStateInit();
        Player testPlayer = new Player();
        testPlayer = test_gameState.getCurrentPlayer();
        test_gameState.setUpInitialTurn();
        assertEquals(test_gameState.getCurrentPlayer(), testPlayer);
    }

    /**
     * Test to see if the player has a tile to play within their tile list that is not null
     */
    @Test
    void test_HasTileToPlay(){
        GameState test_gameState = TestHelper.helperMethod_GameStateInit();
        Player testPlayer = test_gameState.getCurrentPlayer();
        assertTrue(test_gameState.hasTileToPlay(testPlayer));
    }

    /**
     * Test to see if the player has a tile to play but this time their tile list is null so they can't play a tile
     */
    @Test
    void test_HasTileToPlayFalse(){
        GameState test_gameState = TestHelper.helperMethod_GameStateInit();
        Player testPlayer = test_gameState.getCurrentPlayer();
        List<Tile> testList = testPlayer.getTileList();
        testList = null;
        testPlayer.setTileList(testList);
        assertFalse(test_gameState.hasTileToPlay(testPlayer));
    }

    /**
     * Test to see if the game will keep flowing properly as long as the is over variable is false
     */
    @Test
    void test_SetOver(){
        GameState test_gameState = TestHelper.helperMethod_GameStateInit();
        test_gameState.setOver(false);
        assertFalse(test_gameState.getisOver());
    }

    /**
     * Test to see if an instance is created when a null instance is already contained in a gamestate
     */
    @Test
    void test_getInstance(){
        GameState test_gameState = new GameState();
        assertNotNull(test_gameState.getInstance());
    }

    /**
     * Test to make sure the play turn method actually gets the tilesize correctly and that it will deal cards after turn
     *
     * @throws IOException
     */
    @Test
    void test_playTurn() throws IOException {
        GameState test = TestHelper.helperMethod_GameStateInit();
        test.playTurn();
        assertEquals(6, test.getCurrentPlayer().getTileList().size());
    }

    /**
     * Test to make sure that when a null gamestate is passed that the current player is initialized so the game
     * can begin properly. In this case until a current player is set the current player in the front of the list
     * is a null player which is tested to just to make sure playTurn() is hitting the try block
     * @throws IOException
     */
    @Test
    void test_playTurnNull() throws IOException {
        GameState test = new GameState();
        test.playTurn();
        assertNull(test.getCurrentPlayer());
    }
}


