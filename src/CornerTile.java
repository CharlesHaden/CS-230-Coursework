/**
 *
 *
 * @author Nim Man
 * @author Hyder Al-Hashimi
 */

public class CornerTile extends FloorTile {

    public CornerTile(int orientation) {
        super(orientation);
        openPath =  new boolean[] {false, true, true, false};
    }

    public String getFloorTileType() {
        return "Corner";
    }
}
