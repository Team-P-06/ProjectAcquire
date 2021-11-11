package ProjectAcquire;

import javafx.geometry.HPos;
import javafx.scene.control.Button;

import java.util.List;

public class UpdateBoard implements Updatable{

    private FXController UIController;

    /**
     * Creates 108 buttons and assigns them to a place on the grid with colors and name.
     * If our tiles have a name/int from 0 - 107 associated with them you can find the respective col/row by such
     * row = n/12 (round down)
     * col = n % 12
     * The list of tiles will need to pull from the tile pool and players pool to properly color and associate them with a company.
     */
    public void update(GameState gameState, FXController UIController) {
        this.UIController = UIController;
        List<Player> playerList = gameState.getPlayerList();
        List<Tile> tilesNotInPlayerHand = gameState.getCurrentBoard().getTileList();
        Player currentPlayer = gameState.getCurrentPlayer();
        for (Tile tile : tilesNotInPlayerHand) {
            Button currentButton = setButtonProperties(tile.getCompany().getCompanyName());
            currentButton.setText(getTileCoord(tile));
            UIController.getTileGrid().add(currentButton, calculateCol(tile.getCoord()), calculateRow(tile.getCoord()));
        }

        for (Player player : playerList)
            if (player.equals(currentPlayer)) {
                makeCurrentPlayerTiles(player.getTileList(), player);
            } else {
                makeOtherPlayerTile(player.getTileList());
            }

    }

    private void makeCurrentPlayerTiles(List<Tile> tileList, Player player) {
        for (Tile tile : tileList) {
            Button currentButton = new Button();
            currentButton.setStyle("-fx-border-color: red");
            currentButton.setStyle(getTileCoord(tile));
            currentButton.setOnAction(action -> {
                System.out.println("YASSSS");/*player.placeTile(tile)*/
                ;
            });
        }
    }

    private void makeOtherPlayerTile(List<Tile> tileList) {
        for (Tile tile : tileList) {
            Button currentButton = new Button();
            currentButton.setStyle("-fx-background-color: 000000");
            currentButton.setText(getTileCoord(tile));
            UIController.getTileGrid().add(currentButton, calculateCol(tile.getCoord()), calculateRow(tile.getCoord()));
        }
    }

    private Button setButtonProperties(String tileCom) {
        Button currentButton = colorButton(tileCom);
        currentButton.setMinSize(40, 45);
        UIController.getTileGrid().setHalignment(currentButton, HPos.CENTER);
        return currentButton;
    }

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
     * Gets the row if we count from 0 - 107 and the tiles start from the top left
     * Int division always rounds towards zero
     *
     * @param n number of the tile
     * @return row
     */
    private int calculateRow(int n) {
        return n / 12;
    }

    /**
     * remainder of tile / 12 is the column
     *
     * @param n number of the tile
     * @return column
     */
    private int calculateCol(int n) {
        return n % 12;
    }

    /**
     * Extracts the string names of the tiles in the list of tiles
     *
     * @param tile the tile you'd like to get the name of.
     * @return A coordinate name of the unique tile.
     */
    public String getTileCoord(Tile tile) {
        String tileString;
        int remainder;
        int mod = (tile.getCoord() % 12) + 1;
        switch (remainder = (tile.getCoord() / 12)) {
            case 0 -> tileString = ("A" + mod);
            case 1 -> tileString = ("B" + mod);
            case 2 -> tileString = ("C" + mod);
            case 3 -> tileString = ("D" + mod);
            case 4 -> tileString = ("E" + mod);
            case 5 -> tileString = ("F" + mod);
            case 6 -> tileString = ("G" + mod);
            case 7 -> tileString = ("H" + mod);
            case 8 -> tileString = ("I" + mod);
            default -> tileString = ("0" + mod);
        }
        return tileString;

    }

}
