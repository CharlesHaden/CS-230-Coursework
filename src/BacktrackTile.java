/**
 * An action tile that checks which tile a player was at 2 moves ago, and moves them there if there is
 *
 * checks if the fire tile if it is not on an edge and that no players are on the in a 3x3 area
 * around the chosen floor tile, and sets that area on fire if these conditions are met.
 * 
 * @author Nim Man
 * @author Hyder Al-Hashimi
*/

public class BacktrackTile extends ActionTile<Player> {

    /**
     * Moves the chosen player back to where they were 2 moves ago.
     *
     * @param player
     */
    public void action(Player player) {
        int[][] lastMoves = player.getLastMoves();
        //int[] oneMoveAgo = {lastMoves[0][0], lastMoves[0][1]};
        int[] twoMovesAgo = {lastMoves[1][0], lastMoves[1][1]};
        if(isPlayable(player)) {
            System.out.println(twoMovesAgo[0] + " " + twoMovesAgo[1]);
            player.setPlayerPosition(twoMovesAgo);
        }
    }

    /**
     * Gets the type of action tile.
     *
     * @return String specifying this as an backtrack tile.
     */
    public String getActionTileType() {
        return "Backtrack";
    }

    /**
     * Checks to see if the tile one move ago and two moves ago aren't on fire, if at least one move ago isn't on fire,
     * the backtrack tile is playable. Also not playable if the player has been backtracked before.
     *
     * @param player The player chosen to be returned 2 spaces.
     * @return
     */
    protected boolean isPlayable(Player player) {
        if (player.getBacktrackUsed()) {
            return false;
        } else {
            int[][] lastMoves = player.getLastMoves();
            //int[] oneMoveAgo = {lastMoves[0][0], lastMoves[0][1]};
            int[] twoMovesAgo = {lastMoves[1][0], lastMoves[1][1]};
            System.out.println(twoMovesAgo[0] + " " + twoMovesAgo[1]);
            if (Board.getTile(twoMovesAgo[0], twoMovesAgo[1]).getOnFire()) {
                return false;
            } else if (player.getLastMoves() == null) {
                return false;
            } else {
                return true;
            }
        }
    }

}
