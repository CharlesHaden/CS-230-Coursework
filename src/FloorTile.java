/**
 * Abstract class floor tile stores each floor tiles attributes
 *
 * @author Nim Man
 * @author Hyder Al-Hashimi
 */

public abstract class FloorTile extends Tile {

    private boolean fixed;
    private int orientation;
    protected boolean openPath[];
    protected boolean orientedOpenPath[];
    private boolean isFrozen = false;
    private boolean isOnFire = false;
    private int fireTurnsLeft;
    private int iceTurnsLeft;

    public FloorTile(int orientation) {
        this.orientation = orientation;
        this.fixed = false;
        setOrientedOpenPath();
    }

    public FloorTile(int orientation, boolean fixed) {
        this.orientation = orientation;
        this.fixed = fixed;
        setOrientedOpenPath();
    }

    /**
     * Orientates the floor tile, adjusting its open path.
     */
    private void setOrientedOpenPath() {
        for(int i = 0; i < orientation; i++) {
            boolean first = openPath[0];
            int j;
            for(j = 0; j < openPath.length-1; j++) {
                openPath[j] = openPath[j+1];
            }
            openPath[j] = first;
        }
    }

    public boolean[] getOpenPath(){
        return openPath;
    }

    public boolean[] getOrientedOpenPath(){
        return orientedOpenPath;
    }

    public String getTileType(){
        return "Floor";
    }

    protected void setOnFire(boolean onFire){
        this.isOnFire = onFire;
        if(isOnFire) {
            fireTurnsLeft = Game.getPlayers().length * 2;
        }
    }

    public boolean getOnFire(){
        return isFrozen;
    }

    protected void setIsFrozen(boolean isFrozen){
        this.isFrozen = isFrozen;
        if (getIsFrozen()) {
            iceTurnsLeft = Game.getPlayers().length;
        }
    }

    public int getOrientation() {
        return orientation;
    }

    public boolean getIsFrozen(){
        return isFrozen;
    }

    public abstract String getFloorTileType();

    /**
     * Gets if tile is fixed due to being frozen or instantiated as fixed.
     *
     * @return whether the floor tile is fixed
     */
    public boolean getFixed() {
        if(getIsFrozen()){
            return true;
        }
        return fixed;
    }

    /**
     * Decrements the turns left on an action tile used on the floor tile, and removes effect when turns left finish.
     *
     * @param tile floor tile to decrement and check
     */
    public void checkActionTurns(FloorTile tile) {
        if(getIsFrozen()){
            if (iceTurnsLeft > 0){
                iceTurnsLeft--;
            }
            if (iceTurnsLeft == 0){
                setIsFrozen(false);
            }
        }
        if(getOnFire()){
            if (fireTurnsLeft > 0){
                fireTurnsLeft--;
            }
            if (fireTurnsLeft == 0){
                setIsFrozen(false);
            }
        }
    }

}
