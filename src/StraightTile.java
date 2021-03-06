/**
 *
 *
 * @author Nim Man
 * @author Hyder Al-Hashimi
 */

public class StraightTile extends FloorTile {

    public StraightTile(int orientation) {
        super(orientation, new boolean[] {true, false, true, false});
    }

    public StraightTile(int orientation, boolean fixed) {
        super(orientation, fixed, new boolean[] {true, false, true, false});
        setOrientedOpenPath();
    }

    public String getFloorTileType() {
        return "Straight";
    }
}
