/**
 * UpdateBoard.java
 *
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
 * @version v1.1.0
 */

package ProjectAcquire;

import javafx.geometry.HPos;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.List;

/**
 * Updates the tiles' information that are places on the board. (top left of UI)
 */
@Generated
public class UpdateBoard {

    private FXController UIController;

    /**
     * Creates 108 buttons and assigns them to a place on the grid with colors and name.
     * The list of tiles will need to pull from the tile pool and players pool to properly color and associate them with a company.
     * @param gameState current gamestate
     * @param UIController UIController for updating the UI
     * @param placeableTiles weather or not a player should be able to place a tile.
     */
    public void update(GameState gameState, FXController UIController, boolean placeableTiles) throws IOException {
        this.UIController = UIController;
        List<Player> playerList = gameState.getPlayerList();
        List<Tile> allTileList = gameState.getCurrentBoard().getTileList();
        Player currentPlayer = gameState.getCurrentPlayer();
        updateSaveGameButton(gameState);
        UIController.getEndTurnButton().setVisible(false);

        if(endGameCondition(gameState.getCurrentBoard().getCharteredCompanies())){ setEndGameButton(); }

        for (Tile tile : allTileList) {
            if(!tile.isDealt()) { //Only add tiles that are not in a players hand
                Button currentButton = setButtonProperties(tile.getCompany().getCompanyName(), tile.isFlipped());
                currentButton.setText(tile.tileCoordToString());
                UIController.getTileGrid().add(currentButton, tile.getCoord()[1], tile.getCoord()[0]);
            }
        }

        for (Player player : playerList)
            if (player.equals(currentPlayer)) { // Make tiles for current player
                makeCurrentPlayerTiles(player.getTileList(), player, gameState, placeableTiles);
            } else { // Make tiles for all other players
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
                    try { gameState.getTileChoice(tile, currentPlayer); } //Alex NOTE: This is Logic, and is the first step in a charter
                    catch (IOException e) { e.printStackTrace(); } catch (Exception e) {e.printStackTrace();} });
            }
            UIController.getTileGrid().add(currentButton, tile.getCoord()[1], tile.getCoord()[0]);
        }
    }

    /**
     * Makes the tiles for the rest of the players. This will be a blank spot in the board
     * @param tileList the list of tiles belonging to the non current players
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
     * @param tileCom the name of the company
     * @return a button
     */
    private Button setButtonProperties(String tileCom, boolean isFlipped) {
        Button currentButton = colorButton(tileCom, isFlipped);
        currentButton.setMinSize(45, 45);
        UIController.getTileGrid().setHalignment(currentButton, HPos.CENTER);
        return currentButton;
    }

    /**
     * Uniquely colors each button based on their respective company.
     * @param companyName the name of the company accosted with the button on the board
     * @return a button
     */
    private Button colorButton(String companyName, boolean isFlipped) {
        Button button = new Button();
        switch (companyName) {
            case "Worldwide" -> button.setStyle("-fx-background-color: purple");
            case "Sackson" -> button.setStyle("-fx-background-color: orange");
            case "Festival" -> button.setStyle("-fx-background-color: green");
            case "Imperial" -> button.setStyle("-fx-background-color: yellow");
            case "American" -> button.setStyle("-fx-background-color: blue");
            case "Continental" -> button.setStyle("-fx-background-color: red");
            case "Tower" -> button.setStyle("-fx-background-color: grey");
            default -> button.setStyle("-fx-background-color: 000000;");
        }
        if (companyName.equals("DEFAULT") && isFlipped){
            button.setStyle("-fx-background-color: black; -fx-text-fill: white");
        }
        return button;
    }

    /**
     * Check for the end game conditions if met, a chain has 41 or more tiles OR all hotel chains are permanent
     * All chain safe means that the total # of safe companies is 7.
     * @return true or false based on the end game state
     */
    private boolean endGameCondition(List<Company> charteredCom){
        int numberOfPermanentCom = 0;
        for (Company curCompany : charteredCom){
            if (curCompany.getNumTiles() >= 41){ return true; }
            if (curCompany.isPermanent()){ numberOfPermanentCom++; }
        }
        if (numberOfPermanentCom == 7) { return true; }
        return false;
    }

    /**
     * If a player chooses to end the game it will add a end game button to the action list.
     */
    private void setEndGameButton(){
        UIController.getEndGameButton().setVisible(true);
    }

    /**
     * Relates the save game button with the current gameState to save to files.
     * @param gameState the current gamestate
     */
    private void updateSaveGameButton(GameState gameState) throws IOException {
        IOManager ioManager = new IOManager();
        Button saveGameButton = UIController.getSaveGameButton();
        saveGameButton.setOnAction(a -> {
            saveGameButton.setText("Saved!");
            try {
                ioManager.saveGame(gameState);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        ioManager.saveGame(gameState);

            ioManager.saveGame(gameState);};
    }
