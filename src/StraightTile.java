/**
 *
 *
 * @author Nim Man
 * @author Hyder Al-Hashimi
 */

public class StraightTile extends FloorTile {

    public StraightTile(int orientation) {
        super(orientation);
        openPath =  new boolean[] {true, false, true, false};
    }

    public String getFloorTileType() {
        return "Straight";
    }
}
