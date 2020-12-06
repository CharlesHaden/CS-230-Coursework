/**
 * A corner floor tile, with default open path at the East and South.
 *
 * @author Nim Man
 * @author Hyder Al-Hashimi
 */

public class CornerTile extends FloorTile {

    /**
     * Constructor for a non-fixed corner tile.
     *
     * @param orientation Orientation of tile on board.
     */
    public CornerTile(int orientation) {
        super(orientation, new boolean[] {false, true, true, false});
    }

    /**
     * Constructor for a fixed corner tile.
     *
     * @param orientation Orientation of tile on board.
     * @param fixed Whether the tile is fixed or not.
     */
    public CornerTile(int orientation, boolean fixed) {
        super(orientation, fixed, new boolean[] {false, true, true, false});
    }

    /**
     * Gets the type of floor tile.
     *
     * @return String specifying this as a corner tile.
     */
    public String getFloorTileType() {
        return "Corner";
    }
}
