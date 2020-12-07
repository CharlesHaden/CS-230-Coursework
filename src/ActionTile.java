/** 
 * An action tile can one of four different types, fire, ice, backtrack or double move, each with a different
 * effect on the board or a player and different conditions for them to be playable.
 * 
 * @author Nim Man
 * @author Hyder Al-Hashimi
*/

abstract class ActionTile<T> extends Tile {

    /**
     * Gets the tile type.
     *
     * @return String of tile type.
     */
    public String getTileType() {
        return "Action";
    }

    /**
     * Applies an action to a tile or player.
     *
     * @param t Object for action to be applied to.
     */
    public abstract void action(T t);

    /**
     * Gets the type of action tile.
     *
     * @return String of action tile type.
     */
    public abstract String getActionTileType();

    /**
     * Checks to see whether an action tile is playable with the given tile or player.
     *
     * @param t Object for action to be applied to.
     * @return Whether action tile is playable or not.
     */
    protected abstract boolean isPlayable(T t);

}


 


    

    
        
