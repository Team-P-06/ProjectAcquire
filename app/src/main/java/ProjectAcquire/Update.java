package ProjectAcquire;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import lombok.Getter;
import org.checkerframework.checker.guieffect.qual.UI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
        boardUpdater.update(gameState, UIController);
        hotelUpdater.update(gameState, UIController);
        playerUpdater.update(gameState, UIController);
        actionUpdater.update(gameState, UIController);
    }
}