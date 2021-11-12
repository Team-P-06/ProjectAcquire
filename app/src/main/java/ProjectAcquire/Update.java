package ProjectAcquire;

import lombok.Getter;

import java.io.IOException;

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
        System.out.println("yes");
        UIController.getMainStage().hide();
        UIController.showBoardMenu(UIController.getGameBoardLoader());
        boardUpdater.update(gameState, UIController);
        hotelUpdater.update(gameState, UIController);
        playerUpdater.update(gameState, UIController);
        actionUpdater.update(gameState, UIController);
    }
}