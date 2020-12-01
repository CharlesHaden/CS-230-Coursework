/**
 *
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

    public void setOrientedOpenPath() {
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
    }

    public boolean getOnFire(){
        return isFrozen;
    }

    protected void setIsFrozen(boolean isFrozen){
        this.isFrozen = isFrozen;
        if (isFrozen) {
            fixed = true;
        } else {
            fixed = false;
        }
    }

    public boolean getIsFrozen(){
        return isFrozen;
    }

    public abstract String getFloorTileType();

    public boolean getFixed(){
        return fixed;
    }

}
