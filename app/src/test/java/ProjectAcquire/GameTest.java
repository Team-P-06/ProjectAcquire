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
        Game test = new Game();
        test.setCurrentGameState(test_Game);
        assertNotNull(test.getTurn());
    }

    @Test
    void test_getInstance(){
        Game test = new Game();
        assertNotNull(test.getInstance());
    }

    @Test
    void test_Start() throws IOException {
        Game test = new Game();
        assertNotNull(test.start(2));
    }
}
