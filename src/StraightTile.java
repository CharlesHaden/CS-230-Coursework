/**
 * A straight floor tile, with default open path at the North and South.
 *
 * @author Nim Man
 * @author Hyder Al-Hashimi
 */

public class StraightTile extends FloorTile {

    /**
     * Constructor for a non-fixed straight tile.
     *
     * @param orientation Orientation of tile on board.
     */
    public StraightTile(int orientation) {
        super(orientation, new boolean[] {true, false, true, false});
    }

    /**
     * Constructor for a fixed straight tile.
     *
     * @param orientation Orientation of tile on board.
     * @param fixed Whether the tile is fixed or not.
     */
    public StraightTile(int orientation, boolean fixed) {
        super(orientation, fixed, new boolean[] {true, false, true, false});
        setOrientedOpenPath();
    }

    /**
     * Gets the type of floor tile.
     *
     * @return String specifying this as a straight tile.
     */
    public String getFloorTileType() {
        return "Straight";
    }
}
