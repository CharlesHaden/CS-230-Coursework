/** 
 * 
 * 
 * @author Nim Man
 * @author Hyder Al-Hashimi
*/

abstract class ActionTile<T> extends Tile {

    public abstract void action(T t);

    public abstract boolean isPlayable(T t);

    public abstract String getActionTileType();

    public String getTileType() {
        return "Action Tile";
    }

}


 


    

    
        
