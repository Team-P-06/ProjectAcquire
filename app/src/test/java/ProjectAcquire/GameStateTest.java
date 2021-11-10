package ProjectAcquire;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameStateTest {


    /**
     * Tests initalization of gameState. the gamestate tester should have an instance variable isOver that starts off as false
     */
    @Test void test_GameStateStart(){

      GameState tester = TestHelper.helperMethod_GameStateInit();

      assertTrue(tester.isOver()==false);

    }

    /**
     * Tests that our first turn goes smoothly after an initialization
     */
    @Test void test_GameStateInitialTurn(){

        GameState tester = TestHelper.helperMethod_GameStateInit();

        tester.playTurn();

        assertTrue(tester.isOver()==false);

    }


    }


