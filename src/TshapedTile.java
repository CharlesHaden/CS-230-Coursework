/**
 *
 *
 * @author Nim Man
 * @author Hyder Al-Hashimi
 */

public class TshapedTile extends FloorTile {

    public TshapedTile(int orientation) {
        super(orientation, new boolean[] {false, true, true, true});
    }

    public TshapedTile(int orientation, boolean fixed) {
        super(orientation, fixed, new boolean[] {false, true, true, true});
    }

    public String getFloorTileType() {
        return "Tshaped";
    }
}
