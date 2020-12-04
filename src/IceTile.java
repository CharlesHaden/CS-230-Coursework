/** 
 * Sub-class of the ActionTile class that checks if the ice tile if playable, and plays it if it is.
 * 
 * @author Nim Man
 * @author Hyder Al-Hashimi**
*/

public class IceTile extends ActionTile<int[]> {

    /**
     * Freezes tiles in a 3x3 radius around the chosen tile
     *
     * @param chosenTile specifies the chosen tile for the ice tile to be use on
     */
    public void action(int[] chosenTile) {
        FloorTile[][] boardTiles = Board.getTiles();
        int col = chosenTile[0];
        int row = chosenTile[1];
        for (int x = col-1; x < col+2; x++) {
            for (int y = row - 1; y < row + 2; y++) {
                Board.getTile(x,y).setIsFrozen(true);
            }
        }
    }

    /**
     * Checks to see if there is a 3x3 parameter around the chosenTile.
     *
     * @param chosenTile specifies the chosen tile for the ice tile to be use on
     * @return boolean of whether tile is playable
     */
    public boolean isPlayable(int[] chosenTile) {
        FloorTile[][] boardTiles = Board.getTiles();
        int col = chosenTile[0];
        int row = chosenTile[1];
        if (col == 0 || col == Board.getWidth()-1 ||
                row == 0 || row == Board.getHeight()-1) {
            System.out.println("This action cannot be made here.");
            return false;
        }
        return true;
    }

    /**
     * Returns the type of action tile
     *
     * @return string specifying this as an ice tile.
     */
    public String getActionTileType(){
        return "Ice";
    }

}