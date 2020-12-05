/**
 * Game.java
 * Represents and maintains a current instance of a Game
 * @version 1.0.0
 * @author James Hatch
 */
import java.util.ArrayList;

public class Game {

	private static int turn;
	private static ArrayList<Player> players;

	private static Player curPlayer;


	public Game(ArrayList<Player> players) {
		this.players = players;
		turn = 0; //assigns turn counter to 0 at the start of play
	}

	/**
	 * Method to return the current turn
	 *
	 * @return turn - the current turn as int
	 */
	public static int getTurn() {
		return turn;
	}

	/**
	 * Method to set the current turn
	 *
	 * @param newTurn - the incremented turn number as int
	 */
	public static void setTurn(int newTurn) {
		turn = newTurn;
	}

	/**
	 * Method to return the list of current game players
	 *
	 * @return players - an ArrayList of current game players
	 */
	public static ArrayList<Player> getPlayers() {
		return players;
	}

	/**
	 * Method to increment the turn counter
	 * turn is constantly incremented by 1 at each change of play
	 * newTurn calls update to check the current board tiles and update existing Action Tile plays
	 */

	public static void newTurn() {
		turn++;
		curPlayer = players.get(turn % players.size());
	}

	/**
	 * Method to check win state of a game
	 * fetches the tile type for each current player position in the ArrayList of players and...
	 * asks if the tile is the goal tile
	 * updates winning player's score if the player is on the goal tile.
	 *
	 * @return boolean - if win state of the game is true or false
	 */


	public static boolean checkWin() {
		if (Board.getTile(curPlayer.getPlayerPosition()[0],
				curPlayer.getPlayerPosition()[1]).getFloorTileType() == "Goal") {
			Profile curPlayerProfile = curPlayer.getPlayerProfile();
			curPlayerProfile.setWins(Board.getBoardNumber(), curPlayerProfile.getWins(Board.getBoardNumber(), curPlayerProfile.getWins()));
			for (Player player : players) {
				if (player != curPlayer) {
					// player.getPlayerProfile().setLosses(Board.getBoardNumber(), );
				}
			}
			return true;
		} else {
			return false;
		}
	}


	/**
	 * Method to set game players to a different/updated group of players
	 *
	 * @param newPlayers - the updated list of game players
	 */
	public static void setPlayers(ArrayList<Player> newPlayers) {
		players = newPlayers;
	}

	/**
	 * Method to update game board to appropriately remove action plays once expired
	 * Called from newTurn() at the start of each new turn
	 * fetches current list of board tiles as curBoard
	 * loops through each tile in the list and utilises checkActionTurns method in FloorTile...
	 * to update the number of remaining action-affected turns or reset the tile once affected turns has expired
	 */
	public static void update() {
		curBoard = Board.getTiles()
		for (int i = 0; i < curBoard.length(); i++) {
			for (int j = 0; j < curBoard[i].length; j++) {
				Tile.checkActionTurns(curBoard[i][j])
			}
		}
	}
}


