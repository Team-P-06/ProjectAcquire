package ProjectAcquire;

import java.io.IOException;

/**
 * Interface for each class to update it's UI elements, such as Player and board.
 */
public interface Updatable {

    /**
     * Updates UI
     */
    void update(GameState gameState) throws IOException;

}
