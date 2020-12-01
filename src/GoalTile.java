/**
 *
 *
 * @author Nim Man
 * @author Hyder Al-Hashimi
 */

public class GoalTile extends FloorTile {

    public GoalTile(int orientation) {
        super(orientation);
        openPath =  new boolean[] {true, true, true, true};
    }

    public GoalTile(int orientation, boolean fixed) {
        super(orientation, fixed);
        setOrientedOpenPath();
    }

    public String getFloorTileType() {
        return "Goal";
    }
}
