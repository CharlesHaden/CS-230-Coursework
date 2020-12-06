/** 
 * An action tile that checks if the fire tile is not used on an edge, and freezes 3x3 area around the chosen
 * tile if this condition is met.
 * 
 * @author Nim Man
 * @author Hyder Al-Hashimi
*/

public class IceTile extends ActionTile<int[]> {

    /**
     * Freezes tiles in a 3x3 radius around the chosen tile.
     *
     * @param chosenTile Specifies the chosen tile for the ice tile to be used on.
     */
    public void action(int[] chosenTile) {
        int col = chosenTile[0];
        int row = chosenTile[1];
        for (int x = col - 1; x < col + 2; x++) {
            for (int y = row - 1; y < row + 2; y++) {
                Board.getTile(x,y).setIsFrozen(true);
            }
        }
    }

    /**
     * Gets the type of action tile.
     *
     * @return String specifying this as an ice tile.
     */
    public String getActionTileType() {
        return "Ice";
    }

    /**
     * Checks to see if there is a 3x3 parameter around the chosenTile.
     *
     * @param chosenTile Specifies the chosen tile for the ice tile to be use on.
     * @return Whether action tile is playable or not.
     */
    protected boolean isPlayable(int[] chosenTile) {
        int col = chosenTile[0];
        int row = chosenTile[1];
        if (col == 0 || col == Board.getWidth() - 1 ||
                row == 0 || row == Board.getHeight() - 1) {
            System.out.println("This action cannot be made here.");
            return false;
        }
        return true;
    }

}