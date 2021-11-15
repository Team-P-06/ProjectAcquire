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

        tester.playTurn(); //plays a turn
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

    @Test
    void test_CharterChoiceInterrupt(){
        GameState testGame = new GameState();
    }


}


