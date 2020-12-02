/** 
 * 
 * 
 * @author Nim Man
 * @author Hyder Al-Hashimi
*/

public class IceTile extends ActionTile<int[]> {

    public void action(int[] chosenTile) {
        FloorTile[][] boardTiles = Board.getTiles();
        int col = chosenTile[0];
        int row = chosenTile[1];
        for (int x = col-1; x < col+2; x++) {
            for (int y = row - 1; y < row + 2; y++) {
                Board.getTile((Board.getWidth()*y) + x).setIsFrozen(true);
            }
        }
    }

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

    public String getActionTileType(){
        return "Ice";
    }

}