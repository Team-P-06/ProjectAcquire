package ProjectAcquire;

import org.junit.jupiter.api.Test;

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
        System.out.println("Current player is: " + initPlayer);
        System.out.println("playerList: "+ tester.getPlayerList());

        tester.playTurn(); //plays a turn
        Player newCurrentPlayer = tester.getCurrentPlayer();//currentPlayer should be different
        System.out.println("Current player is: " + newCurrentPlayer);
        System.out.println("playerList: "+ tester.getPlayerList());

        //current player should now not be null.
        assertNotNull(newCurrentPlayer);

    }

    /**
     * Tests our GameStat
     */
    @Test void test_GameState_initialTurn_deal_cards(){

        GameState test_gameState = TestHelper.helperMethod_GameStateInit();



    }


    }


