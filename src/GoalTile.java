/**
 * A goal floor tile, with default open path in all directions.
 *
 * @author Nim Man
 * @author Hyder Al-Hashimi
 */

public class GoalTile extends FloorTile {

    /**
     * Constructor for a non-fixed goal tile.
     *
     * @param orientation Orientation of tile on board..
     */
    public GoalTile(int orientation) {
        super(orientation, new boolean[] {true, true, true, true});
    }

    /**
     * Constructor for a fixed goal tile.
     *
     * @param orientation Orientation of tile on board.
     * @param fixed Whether the tile is fixed or not.
     */
    public GoalTile(int orientation, boolean fixed) {
        super(orientation, fixed, new boolean[] {true, true, true, true});
    }

    /**
     * Gets the type of floor tile.
     *
     * @return String specifying this as a goal tile.
     */
    public String getFloorTileType() {
        return "Goal";
    }
}
