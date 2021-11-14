package ProjectAcquire;

import lombok.Getter;

import java.io.IOException;
import java.util.List;

/**
 * Main update UI logic class. Branches out to other parts of the UI Logic for better organization.
 */
public class Update {

    @Getter
    FXController UIController = new FXController();
    static final UpdateBoard boardUpdater = new UpdateBoard();
    static final UpdateHotel hotelUpdater = new UpdateHotel();
    static final UpdatePlayer playerUpdater = new UpdatePlayer();
    static final UpdateAction actionUpdater = new UpdateAction();

    /**
     * Main update logic that branches out and updates different parts of the UI(Player data, Board, Company stocks, and stock options
     */
    public void update(GameState gameState) throws IOException {
        UIController.getMainStage().hide();
        UIController.showBoardMenu(UIController.getGameBoardLoader());
        //boardUpdater.update(gameState, UIController, false);//Make a borad update with a true/false for active tiles? true = new current player/ false = no action tile
        hotelUpdater.update(gameState, UIController);
        playerUpdater.update(gameState, UIController);
    }

    /**
     * Generates a specific UI for if a merge occurs
     * @param gameState current gamestate
     * @param defunctCompany the company that's going under
     * @throws IOException
     */
    public void mergeUI(GameState gameState, Company defunctCompany) throws IOException {
        update(gameState);
        boardUpdater.update(gameState, UIController, false);
        actionUpdater.update(gameState, UIController, false, true,
                defunctCompany, false, null);
    }

    /**
     * Generates the UI for selling stocks after a tile has been placed
     * @param gameState the curretn gamestate
     * @throws IOException
     */
    public void sellUI(GameState gameState) throws IOException {
        update(gameState);
        boardUpdater.update(gameState, UIController, false);
        actionUpdater.update(gameState, UIController, false, false,
                null, false, null);
    }

    /**
     * Generates a UI for if a tile is placed and a new Company needs to be chartered
     * @param gameState the current gamestate.
     * @throws IOException
     */
    public void charterChoiceUI(GameState gameState) throws IOException {
        update(gameState);
        boardUpdater.update(gameState, UIController, false);
        actionUpdater.update(gameState, UIController, true, false, null, false,
                null);
    }

    /**
     * Generates a UI for a list of companies if a merge has equal numer of tiles in a company
     * @param gameState the current gamestate
     * @param companyChoiceList the list of companies that have the same number of tiles on the board.
     * @throws IOException
     */
    public void mergeChoiceUI(GameState gameState, List<Company> companyChoiceList) throws IOException{
        update(gameState);
        boardUpdater.update(gameState, UIController, false);
        actionUpdater.update(gameState, UIController, true, false,
                null, true, companyChoiceList);
    }

    public void nextTurnUI(GameState gameState) throws IOException {
        update(gameState);
        boardUpdater.update(gameState, UIController, true);
    }
}