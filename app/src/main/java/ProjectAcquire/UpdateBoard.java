package ProjectAcquire;

import javafx.geometry.HPos;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.List;

/**
 * Updates the tiles' information that are places on the board. (top left of UI)
 */
public class UpdateBoard implements Updatable{

    private FXController UIController;

    /**
     * Creates 108 buttons and assigns them to a place on the grid with colors and name.
     * The list of tiles will need to pull from the tile pool and players pool to properly color and associate them with a company.
     */
    public void update(GameState gameState, FXController UIController) {
        this.UIController = UIController;
        List<Player> playerList = gameState.getPlayerList();
        List<Tile> tilesNotInPlayerHand = gameState.getCurrentBoard().getTileList();
        Player currentPlayer = gameState.getCurrentPlayer();
        if(endGameCondion(gameState.getCurrentBoard().getCharteredCompanies())){ goToEndScreen(); }
        for (Tile tile : tilesNotInPlayerHand) {
            Button currentButton = setButtonProperties(tile.getCompany().getCompanyName());
            currentButton.setText(tile.tileCoordToString());
            UIController.getTileGrid().add(currentButton, tile.getCoord()[1], tile.getCoord()[0]);
        }

        for (Player player : playerList)
            if (player.equals(currentPlayer)) {
                makeCurrentPlayerTiles(player.getTileList(), player);
            } else {
                makeOtherPlayerTile(player.getTileList());
            }

    }

    /**
     * Creates a unique button for the current player, so they can play a tile and have a custom border color
     * @param tileList
     * @param currentPlayer
     */
    private void makeCurrentPlayerTiles(List<Tile> tileList, Player currentPlayer) {
        for (Tile tile : tileList) {
            Button currentButton = new Button();
            currentButton.setStyle("-fx-background-color: 000000; -fx-border-color: red; -fx-border-width: 3");
            currentButton.setText(tile.tileCoordToString());
            currentButton.setMinSize(45, 45);
            currentButton.setOnAction(action -> {
                currentPlayer.placeTile(tile);
            });
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
            default -> button.setStyle("-fx-background-color: 000000");
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
    private void goToEndScreen(){
        UIController.getEndGameListView().setVisible(true);
        UIController.getEndGameObserListView().clear();
        Button endGameButton = new Button();
        endGameButton.setText("End Game");
        endGameButton.setOnAction(c -> {
            try {
                UIController.endGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        UIController.getEndGameObserListView().add(endGameButton);
        UIController.getEndGameListView().setItems(UIController.getEndGameObserListView());
    }


}
