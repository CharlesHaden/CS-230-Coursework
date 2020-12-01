/**
 * 
 * 
 * @author Nim Man
 * @author Hyder Al-Hashimi
*/

public class DoubleMoveTile extends ActionTile<Player> {

    public void action(Player player) {
        if(isPlayable(player)) {
            player.makeMove(); //need to add some event
        }
    } 
    
    public boolean isPlayable(Player player) {
        if(player.canMove() == true) {
            return true;
        }
        return false;
    }

    public String getActionTileType() {
        return "Double Move";
    }
}