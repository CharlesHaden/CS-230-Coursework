/**
 * Floor tiles are what make up the game board, with 4 different shapes, sub-classes Tshaped, goal, straight and corner,
 * each with different open paths and orientations. Orientation is tracked by an integer up to 3, each being a
 * rotation 90 degrees clockwise. An open path is an array of 4 booleans, with starting from North and going clockwise,
 * with true showing a open path in that direction. Action tiles played on the floor tiles as well as turns left for
 * the effect to be removed are stored.
 *
 * @author Nim Man
 * @author Hyder Al-Hashimi
 **/

public abstract class FloorTile extends Tile {

    private static final int FIRE_TURN_ROTATIONS = 2;
    protected boolean[] openPath;
    protected boolean[] orientedOpenPath;
    private boolean fixed;
    private int orientation;
    private boolean isFrozen = false;
    private boolean isOnFire = false;
    private int fireTurnsLeft;
    private int iceTurnsLeft;

    /**
     * Constructor for a non-fixed tile.
     *
     * @param orientation orientation of tile on board.
     * @param openPath open path of specific floor tile shape.
     */
    public FloorTile(int orientation, boolean[] openPath) {
        this.orientation = orientation;
        this.fixed = false;
        this.openPath = openPath;
        setOrientedOpenPath();
    }

    /**
     * Constructor for a fixed tile.
     *
     * @param orientation Orientation of tile on board.
     * @param fixed Whether the tile is fixed or not.
     * @param openPath open path of specific floor tile shape.
     */
    public FloorTile(int orientation, boolean fixed, boolean[] openPath) {
        this.orientation = orientation;
        this.fixed = fixed;
        this.openPath = openPath;
        setOrientedOpenPath();
    }

    /**
     * Gets the un-oriented open paths.
     *
     * @return array of open paths.
     */
    public boolean[] getOpenPath(){
        return openPath;
    }

    /**
     * Gets the oriented open paths.
     *
     * @return array of open paths.
     */
    public boolean[] getOrientedOpenPath(){
        return orientedOpenPath;
    }

    /**
     * Gets the tile type.
     *
     * @return String of tile type.
     */
    public String getTileType(){
        return "Floor";
    }

    /**
     * Gets the whether the tile is on fire.
     *
     * @return whether the tile is on fire or not.
     */
    public boolean getOnFire(){
        return isOnFire;
    }

    /**
     * Gets the whether the tile is on frozen.
     *
     * @return whether the tile is on frozen or not.
     */
    public boolean getIsFrozen(){
        return isFrozen;
    }

    /**
     * Get the orientation of tile.
     *
     * @return the orientation of tile.
     */
    public int getOrientation() {
        return orientation;
    }

    /**
     * Returns whether a floor tile is fixed.
     *
     * @return true if its frozen or instantiated as fixed.
     */
    public boolean getFixed() {
        if (isFrozen) {
            return true;
        }
        return fixed;
    }

    /**
     * Decrements the turns left for each action, removing the status whe the duration is done.
     */
    public void checkActionTurns() {
        if (getIsFrozen()) {
            if (iceTurnsLeft > 0) {
                iceTurnsLeft--;
            }
            if (iceTurnsLeft == 0) {
                setIsFrozen(false);
            }
        }
        if (getOnFire()) {
            if (fireTurnsLeft > 0) {
                fireTurnsLeft--;
            }
            if (fireTurnsLeft == 0) {
                setOnFire(false);
            }
        }
    }

    /**
     * Gets the type of floor tile.
     *
     * @return String of floor tile type.
     */
    public abstract String getFloorTileType();

    /**
     * Freezes the floor tile.
     *
     * @param isFrozen whether the tile is being frozen or unfrozen.
     */
    protected void setIsFrozen(boolean isFrozen){
        this.isFrozen = isFrozen;
        if (isFrozen) {
            iceTurnsLeft = Game.getPlayers().size();
        }
    }

    /**
     * Sets the floor tile on fire.
     *
     * @param onFire whether the tile is being set on fire or off.
     */
    protected void setOnFire(boolean onFire) {
        this.isOnFire = onFire;
        if(isOnFire) {
            fireTurnsLeft = Game.getPlayers().size() * FIRE_TURN_ROTATIONS;
        }
    }

    /**
     * Orientates the tile to get new open path.
     */
    protected void setOrientedOpenPath() {
        for (int i = 0; i < orientation; i++) {
            boolean first = openPath[0];
            int j;
            for (j = 0; j < openPath.length-1; j++) {
                openPath[j] = openPath[j+1];
            }
            openPath[j] = first;
        }
    }

}
