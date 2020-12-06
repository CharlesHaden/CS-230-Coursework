/**
 * An action tile that checks which tile a player was at 1 and 2 moves ago, and moves back one move ago if it is not
 * on fire, then if the tile 2 moves ago isn't on fire, it will move to that tile.
 *
 * @author Nim Man
 * @author Hyder Al-Hashimi
*/

public class BacktrackTile extends ActionTile<Player> {

    /**
     * Moves the chosen player back to where they were 2 moves ago, if not possible, then to where
     * they were one move ago.
     *
     * @param player
     */
    public void action(Player player) {
        int[][] lastMoves = player.getLastMoves();
        int[] oneMoveAgo = {lastMoves[0][0], lastMoves[0][1]};
        int[] twoMovesAgo = {lastMoves[1][0], lastMoves[1][1]};
        if(isPlayable(player)) {
            if (Board.getTile(twoMovesAgo[0], twoMovesAgo[1]).getOnFire() &&
                    !Board.getTile(oneMoveAgo[0], oneMoveAgo[1]).getOnFire()) {
                player.setPlayerPosition(oneMoveAgo);
            } else {
                player.setPlayerPosition(twoMovesAgo);
            }
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
     * @return Whether action tile is playable or not.
     */
    protected boolean isPlayable(Player player) {
        if (player.getBacktrackUsed()) {
            return false;
        }
        int[][] lastMoves = player.getLastMoves();
        int[] oneMoveAgo = {lastMoves[0][0], lastMoves[0][1]};
        int[] twoMovesAgo = {lastMoves[1][0], lastMoves[1][1]};
        if (Board.getTile(twoMovesAgo[0], twoMovesAgo[1]).getOnFire() &&
                !Board.getTile(oneMoveAgo[0], oneMoveAgo[1]).getOnFire()) {
            return true;
        } else if (Board.getTile(twoMovesAgo[0], twoMovesAgo[1]).getOnFire()) {
            return false;
        } else if (player.getLastMoves() == null) {
            return false;
        } else {
            return true;
        }
    }

}
