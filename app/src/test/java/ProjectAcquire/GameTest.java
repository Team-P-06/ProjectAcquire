package ProjectAcquire;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    void test_getTurn(){
        GameState test_Game = TestHelper.helperMethod_GameStateInit();
        Game test = Game.getInstance();
        test.setCurrentGameState(test_Game);
        assertNotNull(test.getTurn());
        TestHelper.helperMethod_tearDownGame();
    }

    @Test
    void test_getInstance(){
        Game test = Game.getInstance();
        assertNotNull(test);
        TestHelper.helperMethod_tearDownGame();

    }

    @Test
    void test_Start() {
        Game test = Game.getInstance();
        assertNull(test.start(3));
        TestHelper.helperMethod_tearDownGame();

    }

    @Test
    void test_loadGame(){
        Game test = Game.getInstance();
        GameState testGameState = TestHelper.helperMethod_GameStateInit();
        test.loadGame(testGameState);
        assertNotNull(Game.getInstance().getCurrentGameState());
        TestHelper.helperMethod_tearDownGame();
    }
}
