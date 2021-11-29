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

import lombok.Getter;
import org.checkerframework.checker.guieffect.qual.UI;

import java.io.IOException;
import java.util.List;

/**
 * Main update UI logic class. Branches out to other parts of the UI Logic for better organization.
 */
@Generated
public class Update {

    @Getter
    public static final FXController UIController = new FXController();
    static final UpdateBoard boardUpdater = new UpdateBoard();
    static final UpdateHotel hotelUpdater = new UpdateHotel();
    static final UpdatePlayer playerUpdater = new UpdatePlayer();
    static final UpdateAction actionUpdater = new UpdateAction();

    /**
     * Main update logic that branches out and updates different parts of the UI(Player data, Board, Company stocks, and stock options
     */
    public void update(GameState gameState) throws IOException {
        UIController.showBoardMenu(UIController.getGameBoardLoader());
        hotelUpdater.update(gameState, UIController);
        playerUpdater.update(gameState, UIController);
    }

    /**
     * Generates a specific UI for if a merge occurs
     * @param gameState current gamestate
     * @param mergingCompanies List of companies going under
     * @throws IOException
     */
    public void mergeUI(GameState gameState, List<Company> mergingCompanies) throws IOException {
        update(gameState);
        boardUpdater.update(gameState, UIController, false);
        actionUpdater.update(gameState, UIController, false, mergingCompanies);
    }

    /**
     * Generates the UI for selling stocks after a tile has been placed
     * @param gameState the curretn gamestate
     * @throws IOException
     */
    public void buyUI(GameState gameState) throws IOException {
        update(gameState);
        boardUpdater.update(gameState, UIController, false);
        actionUpdater.update(gameState, UIController, false, null);
    }

    /**
     * Generates a UI for if a tile is placed and a new Company needs to be chartered
     * @param gameState the current gamestate.
     * @throws IOException
     */
    public void charterChoiceUI(GameState gameState) throws IOException {
        update(gameState);
        boardUpdater.update(gameState, UIController, false);
        actionUpdater.update(gameState, UIController, true, null);
    }

    public void nextTurnUI(GameState gameState) throws IOException {
        update(gameState);
        boardUpdater.update(gameState, UIController, true);
    }
}