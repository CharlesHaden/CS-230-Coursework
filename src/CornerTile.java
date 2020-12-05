/**
 *
 *
 * @author Nim Man
 * @author Hyder Al-Hashimi
 */

public class CornerTile extends FloorTile {

    public CornerTile(int orientation) {
        super(orientation, new boolean[] {false, true, true, false});
    }

    public CornerTile(int orientation, boolean fixed) {
        super(orientation, fixed, new boolean[] {false, true, true, false});
    }

    public String getFloorTileType() {
        return "Corner";
    }
}
