/**
 *
 *
 * @author Nim Man
 * @author Hyder Al-Hashimi
 */

public class TshapedTile extends FloorTile {

    public TshapedTile(int orientation) {
        super(orientation);
        openPath =  new boolean[] {false, true, true, true};
    }

    public TshapedTile(int orientation, boolean fixed) {
        super(orientation, fixed);
        setOrientedOpenPath();
    }

    public String getFloorTileType() {
        return "Tshaped";
    }
}
