import java.util.ArrayList;

/**
 * An action tile that checks if the fire tile is not used on an edge is not on an edge and that no players are on the
 * in a 3x3 area around the chosen floor tile, and sets that area on fire if these conditions are met.
 * 
 * @author Nim Man
 * @author Hyder Al-Hashimi
*/

public class FireTile extends ActionTile<int[]> {

    /**
     * Sets tiles in a 3x3 radius around the chosen tile on fire.
     *
     * @param chosenTile Specifies the chosen tile for the fire tile to be used on.
     */
    public void action(int[] chosenTile) {
        int col = chosenTile[0];
        int row = chosenTile[1];
        for (int x = col - 1; x < col + 2; x++) {
            for (int y = row - 1; y < row + 2; y++) {
                Board.getTile(x, y).setOnFire(true);
            }
        }
    }

    /**
     * Gets the type of action tile.
     *
     * @return String specifying this as an fire tile.
     */
    public String getActionTileType() {
        return "Fire";
    }

    /**
     * Checks to see if there is a 3x3 parameter around the chosenTile and whether there is
     * a player standing on those tiles.
     *
     * @param chosenTile Specifies the chosen tile for the ice tile to be use on.
     * @return Whether action tile is playable or not.
     */
    protected boolean isPlayable(int[] chosenTile) {
        int col = chosenTile[0];
        int row = chosenTile[1];
        if (col == 0 || col == Board.getWidth()-1 ||
                row == 0 || row == Board.getHeight()-1) {
            System.out.println("This action cannot be made here.");
            return false;
        }
        ArrayList<Player> currentPlayers = Game.getPlayers();
        int[][] affectedTiles = new int[9][2];
        int i = 0;
        for (int x = col-1; x < col+2; x++) {
            for (int y = row - 1; y < row + 2; y++) {
                affectedTiles[i][0] = x;
                affectedTiles[i][1] = y;
                i++;
            }
        }
        for (Player player : currentPlayers) {
            for (int[] affectedTile: affectedTiles) {
                if (player.getPlayerPosition() == affectedTile) {
                    return false;
                }
            }
        }
        return true;
    }

}