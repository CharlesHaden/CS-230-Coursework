/**
 * An action tile that allows the player to move twice.
 * 
 * @author Nim Man
 * @author Hyder Al-Hashimi
*/

public class DoubleMoveTile extends ActionTile<Player> {


    public void action(Player player) {
        if (isPlayable(player)) {
            //player.makeMove(); //need to add some event
        }
    }

    public String getActionTileType() {
        return "Double Move";
    }

    protected boolean isPlayable(Player player) {
        return true;
    }

}