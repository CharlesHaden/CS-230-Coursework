/**
 *
 *
 * @author Nim Man
 * @author Hyder Al-Hashimi
 */

public class GoalTile extends FloorTile {

    public GoalTile(int orientation) {
        super(orientation, new boolean[] {true, true, true, true});
    }

    public GoalTile(int orientation, boolean fixed) {
        super(orientation, fixed, new boolean[] {true, true, true, true});
    }

    public String getFloorTileType() {
        return "Goal";
    }
}
