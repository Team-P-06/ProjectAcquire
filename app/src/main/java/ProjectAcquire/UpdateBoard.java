package ProjectAcquire;

import javafx.geometry.HPos;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.List;

/**
 * Updates the tiles' information that are places on the board. (top left of UI)
 */
public class UpdateBoard {

    private FXController UIController;

    /**
     * Creates 108 buttons and assigns them to a place on the grid with colors and name.
     * The list of tiles will need to pull from the tile pool and players pool to properly color and associate them with a company.
     * @param gameState current gamestate
     * @param UIController UIController for updating the UI
     * @param placeableTiles weather or not a player should be able to place a tile.
     */
    public void update(GameState gameState, FXController UIController, boolean placeableTiles) {
        this.UIController = UIController;
        List<Player> playerList = gameState.getPlayerList();
        List<Tile> allTileList = gameState.getCurrentBoard().getTileList();
        Player currentPlayer = gameState.getCurrentPlayer();
        updateSaveGameButton(gameState);
        UIController.getEndTurnButton().setVisible(false);

        if(endGameCondion(gameState.getCurrentBoard().getCharteredCompanies())){ setEndGameButton(gameState); }

        for (Tile tile : allTileList) {
            if(!tile.isDealt()) { //Only add tiles that are not in a players hand
                Button currentButton = setButtonProperties(tile.getCompany().getCompanyName());
                currentButton.setText(tile.tileCoordToString());
                UIController.getTileGrid().add(currentButton, tile.getCoord()[1], tile.getCoord()[0]);
            }
        }

        for (Player player : playerList)
            if (player.equals(currentPlayer)) {
                makeCurrentPlayerTiles(player.getTileList(), player, gameState, placeableTiles);
            } else {
                makeOtherPlayerTile(player.getTileList());
            }

    }

    /**
     * Creates a unique button for the current player, so they can play a tile and have a custom border color
     * If this is a fresh turn, e.g the player should place a tile. Then the placetile action is set to the players tilelist. otherwise, no action.
     * @param tileList the list of tiles in the curret players hand
     * @param currentPlayer who the current player is
     */
    private void makeCurrentPlayerTiles(List<Tile> tileList, Player currentPlayer,
                                        GameState gameState, boolean placeableTiles){
        for (Tile tile : tileList) {
            Button currentButton = new Button();
            currentButton.setStyle("-fx-background-color: 000000; -fx-border-color: red; -fx-border-width: 1; ");
            currentButton.setText(tile.tileCoordToString());
            currentButton.setMinSize(50, 45);
            if(placeableTiles) { // If this is a fresh turn allow the tile to be placed.
                UIController.getActionLabel().setText("Place a tile");
                currentButton.setOnAction(action -> {
                    try { currentPlayer.placeTile(tile, gameState); }
                    catch (IOException e) { e.printStackTrace(); }
                });
            }
            UIController.getTileGrid().add(currentButton, tile.getCoord()[1], tile.getCoord()[0]);
        }
    }

    /**
     * Makes the tiles for the rest of the players. This will be a blank spot in the board
     * @param tileList
     */
    private void makeOtherPlayerTile(List<Tile> tileList) {
        for (Tile tile : tileList) {
            Button currentButton = new Button();
            currentButton.setStyle("-fx-background-color: 000000");
            currentButton.setText(tile.tileCoordToString());
            UIController.getTileGrid().add(currentButton, tile.getCoord()[1], tile.getCoord()[0]);
        }
    }

    /**
     * Default button style for the all the charted companies
     * @param tileCom
     * @return
     */
    private Button setButtonProperties(String tileCom) {
        Button currentButton = colorButton(tileCom);
        currentButton.setMinSize(45, 45);
        UIController.getTileGrid().setHalignment(currentButton, HPos.CENTER);
        return currentButton;
    }

    /**
     * Uniquely colors each button based on their respective company.
     * @param companyName
     * @return
     */
    private Button colorButton(String companyName) {
        Button button = new Button();
        switch (companyName) {
            case "Worldwide" -> button.setStyle("-fx-background-color: purple");
            case "Sackson" -> button.setStyle("-fx-background-color: orange");
            case "Festival" -> button.setStyle("-fx-background-color: green");
            case "Imperial" -> button.setStyle("-fx-background-color: yellow");
            case "American" -> button.setStyle("-fx-background-color: blue");
            case "Continental" -> button.setStyle("-fx-background-color: red");
            case "Tower" -> button.setStyle("-fx-background-color: grey");
            case "default" -> button.setStyle("fx-background-color: black; -fx-text-fill: white");
            default -> button.setStyle("-fx-background-color: 000000;");
        }
        return button;
    }

    /**
     * Check for the end game conditions if met, a chain has 41 or more tiles OR all hotel chains are permanent
     * All chain safe means that the total # of safe companies is 7.
     * @return
     */
    private boolean endGameCondion(List<Company> charteredCom){
        int numberOfPerminetCom = 0;
        for (Company curCompany : charteredCom){
            if (curCompany.getTilesOnBoard() >= 41){ return true; }
            if (curCompany.isPermanent()){ numberOfPerminetCom++; }
        }
        return numberOfPerminetCom == 7;
    }

    /**
     * If a player chooses to end the game it will add a end game button to the action list.
     */
    private void setEndGameButton(GameState gameState){
        UIController.getEndGameListView().setVisible(true);
        UIController.getEndGameObserListView().clear();
        Button endGameButton = new Button();
        endGameButton.setText("End Game");
        endGameButton.setOnAction(c -> {
            try {
                UIController.endGame(gameState);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        UIController.getEndGameObserListView().add(endGameButton);
        UIController.getEndGameListView().setItems(UIController.getEndGameObserListView());
    }

    /**
     * Relates the save game button with the current gameState to save to files.
     * @param gameState the current gamestate
     */
    private void updateSaveGameButton(GameState gameState){
        IOManager ioManager = new IOManager();
        Button saveGameButton = UIController.getSaveGameButton();
        saveGameButton.setOnAction(a -> {
            saveGameButton.setText("Saved!");
            ioManager.saveGame(gameState);});
        ioManager.saveGame(gameState); //Could be wrong but just added this to write the file -Tyler
    }


}
