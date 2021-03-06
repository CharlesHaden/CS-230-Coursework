/**
 * 
 * 
 * @author Nim Man
 * @author Hyder Al-Hashimi
*/

public class DoubleMoveTile extends ActionTile<Player> {

    /**
     * Allows the player to move again
     *
     * @param player The player to be moved twice.
     */

    public void action(Player player) {
        if (isPlayable(player)) {
           //player.makeMove(); //need to add some event
        }
    }

    /**
     * Gets the type of action tile.
     *
     * @return String specifying this as an double move tile.
     */
    public String getActionTileType() {
        return "Double Move";
    }

    protected boolean isPlayable(Player player) {
        return true;
    }

}