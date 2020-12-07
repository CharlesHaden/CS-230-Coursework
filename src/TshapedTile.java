/**
 * A T-shaped floor tile, with default open path at the East, South and West.
 *
 * @author Nim Man
 * @author Hyder Al-Hashimi
 */

public class TshapedTile extends FloorTile {

    /**
     * Constructor for a non-fixed T-shaped tile.
     *
     * @param orientation Orientation of tile on board.
     */
    public TshapedTile(int orientation) {
        super(orientation, new boolean[] {false, true, true, true});
    }

    /**
     * Constructor for a fixed T-shaped tile.
     *
     * @param orientation Orientation of tile on board.
     * @param fixed Whether the tile is fixed or not.
     */
    public TshapedTile(int orientation, boolean fixed) {
        super(orientation, fixed, new boolean[] {false, true, true, true});
    }

    /**
     * Gets the type of floor tile.
     *
     * @return String specifying this as a T-shaped tile.
     */
    public String getFloorTileType() {
        return "Tshaped";
    }
}
