/**
 * Player Class
 * @author Lewis Hotchkiss
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Player {

	private int playerNum;
	private String orientation;
	private int[][] lastMoves;
	private int[] playerPosition;
	private ArrayList<ActionTile> playerHand = new ArrayList<>();
	private Profile playerProfile;
	private Boolean backtrackUsed;
	private FloorTile tempFloorTile;

	/**
	 * Constructs a player
	 * @param playerNum The number of the player in the game
	 * @param orientation Which way the player is facing
	 * @param lastMoves The last moves of the player (will be empty at start)
	 * @param playerPosition The players position on the board
	 * @param playerHand The action tiles the player has in hand (will be empty at start)
	 */
	public Player(int playerNum, String orientation, int[][] lastMoves, int[] playerPosition, ArrayList<ActionTile> playerHand) {
		this.playerNum = playerNum;
		this.orientation = orientation;
		this.lastMoves = lastMoves;
		this.playerPosition = playerPosition;
		this.playerHand = playerHand;
	}

	/**
	 * 
	 * @return Returns the player number
	 */
	public int getPlayerNum() {
		return playerNum;
	}

	/**
	 * Sets the player number
	 * @param playerNum
	 */
	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}

	/**
	 * @return Returns the orientation of the player: north, east, south or west
	 */
	public String orientation() {
		return orientation;
	}

	/**
	 * Sets the orientation of the player
	 * @param orientation Orientation of the player: north, east, south or west
	 */
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	/**
	 * Gets the players last moves where:
	 * [0][0] = x position one move ago
	 * [0][1] = y position two moves ago
	 * [1][0] = x position two moves ago
	 * [1][1] = y position two moves ago
	 * 
	 * @return Returns an array of the players last two moves
	 */
	public int[][] getLastMoves() {
		return lastMoves;
	}

	/**
	 * Sets the players last moves
	 * 
	 * @param lastMoves
	 */
	public void setLastMoves(int[][] lastMoves) {
		this.lastMoves = lastMoves;
	}

	/**
	 * Returns the player position on the board where:
	 * [0] = x and [1] = y
	 */
	public int[] getPlayerPosition() {
		return playerPosition;
	}

	/**
	 * Sets the players position on the board
	 * 
	 * @param playerPosition
	 */
	public void setPlayerPosition(int[] playerPosition) {
		this.playerPosition = playerPosition;
	}

	/**
	 * Returns an array lost of action tiles in the players hand
	 * 
	 * @return Returns array list of action tiles
	 */
	public ArrayList<ActionTile> getPlayerHand() {
		return playerHand;
	}

	/**
	 * Sets the array list of action tiles for player hand
	 * @param playerHand Array list of action tiles which are playable
	 */
	public void setPlayerHand(ArrayList<ActionTile> playerHand) {
		this.playerHand = playerHand;
	}

	/**
	 * Gets the players profile associated with player
	 * @return Returns a profile linked to player
	 */
	public Profile getPlayerProfile() {
		return playerProfile;
	}

	/**
	 * Sets the players profile
	 * @param playerProfile The profile to be associated with player
	 */
	public void setPlayerProfile(Profile playerProfile) {
		this.playerProfile = playerProfile;
	}

	/**
	 * Gets weather or not the backtrack action has been used on the player
	 * @return Returns true if it has been used
	 */
	public Boolean getBacktrackUsed() {
		return backtrackUsed;
	}

	/**
	 * Sets if the backtrack action has been used
	 * @param backtrackUsed Set to true if it has been used on player
	 */
	public void setBacktrackUsed(Boolean backtrackUsed) {
		this.backtrackUsed = backtrackUsed;
	}

	/**
	 * Draws a tile from the silk bag in board. If it is a floor tile then it can be inserted,
	 * if it is an action tile then it is added to the players hand.
	 */
	public void drawTile() {
		Tile tile = Board.getTileFromSilkBag();
		if (tile.getTileType().equals("Floor Tile")) {
			tempFloorTile = (FloorTile) tile;
		} else if (tile.getTileType().equals("Action Tile")) {
			ActionTile actionTile = (ActionTile) tile;
			playerHand.add(actionTile);
		}
	}
	/**
	 * Return temporarily stored floor tile in hand.
	 *
	 * @return Temporary floor tile.
	 */
	public FloorTile getTempFloorTile() {
		return tempFloorTile;
	}

	/**
	 * Allows the player to play an action tile. The chosen tile will be played and removed from hand.
	 *
	 * @param actionTile
	 * @param chosenTile
	 */
	public void playFireIceTile(ActionTile actionTile, FloorTile chosenTile) {
		if (actionTile.getActionTileType().equals("Fire")) {
			actionTile.action(chosenTile);
		} else if (actionTile.getActionTileType().equals("Ice")) {
			actionTile.action(chosenTile);
		}
		playerHand.remove(actionTile);
	}

	/**
	 *
	 */
	public void playBackDoubleTile(ActionTile actionTile, Player player){
		if (actionTile.getActionTileType().equals("Double Move")) {
			actionTile.action(player);
		} else if (actionTile.getActionTileType().equals("Backtrack")) {
			actionTile.action(player);
		}
		playerHand.remove(actionTile);
	}
	/**
	 * Updates the players last moves by assigning a new position one move ago and moving
	 * the previous one move ago to two moves ago
	 * @param x x position of player one move ago
	 * @param y y position of player one move ago
	 */
	public void updateLastMoves(int x, int y) {
		//changes one position ago to two positions ago
		lastMoves[1][0] = lastMoves[0][0];
		lastMoves[1][1] = lastMoves[0][1];
		//sets x,y one position ago
		lastMoves[0][0] = x;
		lastMoves[0][1] = y;
	}

	/**
	 * Gets the arrow key pressed by the user and moves the player accordingly
	 * @param direction
	 */
	public void makeMove(String direction) {
		boolean moved = false;

		do {
			switch (direction) {
				case "Up":
					moved = movePlayer(playerPosition[0], playerPosition[1] + 1, 0, 1);
					break;
				case "Down":
					moved = movePlayer(playerPosition[0], playerPosition[1] - 1, 1, 0);
					break;
				case "Left":
					moved = movePlayer(playerPosition[0] - 1, playerPosition[1], 2, 3);
					break;
				case "Right":
					moved = movePlayer(playerPosition[0] + 1, playerPosition[1], 3, 2);
					break;
			}
		} while (moved == false);

	}

	/**
	 * Changes the players position to the new position
	 * @param x The x coordinate of the next tile
	 * @param y The y coordinate of the next tile
	 * @param currentPath The position of the boolean in the array {UP,DOWN,LEFT,RIGHT} of the current tile
	 * @param nextPath The position of the boolean in the array {UP,DOWN,LEFT,RIGHT} of the next tile
	 * @return Returns true if the player was able to move
	 */
	public boolean movePlayer(int x, int y, int currentPath, int nextPath) {
		Boolean moved = false;
		boolean playerAvailableToMove = playerCanMove(x, y, currentPath, nextPath);

		if (playerAvailableToMove) {
			playerPosition[0] = x;
			playerPosition[1] = y;
			moved = true;
		} else {
			moved = false;
		}

		return moved;
	}

	/**
	 * Checks if the player can move. If player can move then returns true
	 * @param x The x coordinate of the next tile
	 * @param y The y coordinate of the next tile
	 * @param currentPath The position of the boolean in the array {UP,DOWN,LEFT,RIGHT} of the current tile
	 * @param nextPath The position of the boolean in the array {UP,DOWN,LEFT,RIGHT} of the next tile
	 * @return Returns true if the player was able to move
	 */
	public boolean playerCanMove(int x, int y, int currentPath, int nextPath) {
		FloorTile currentTile = (FloorTile) Board.getTile(playerPosition[0], playerPosition[1]);
		boolean[] currentTileOpenPath = currentTile.getOrientedOpenPath();

		FloorTile nextTile = Board.getTile(x, y);
		boolean[] nextTileOpenPath = nextTile.getOrientedOpenPath();
		if ((currentTileOpenPath[currentPath]) && (nextTileOpenPath[nextPath])) {
			if ((!nextTile.getOnFire()) && (checkForPlayers(x, y))) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	/**
	 * Will check if there is a player on any of the surrounding tiles
	 * @param x The x coordinate of the next tile
	 * @param y The y coordinate of the next tile
	 * @return Returns true if there is a player on any surrounding tile
	 */
	public boolean checkForPlayers(int x, int y) {
		boolean playerOnTile = false;
		ArrayList<Player> players = Game.getPlayers(); //Gets the array of players from the game class
		int i;

		for (i = 0; i < players.size(); i++) {
			int otherPlayerPosition[] = players.get(i).getPlayerPosition();
			if ((otherPlayerPosition[0] == x && otherPlayerPosition[1] == y)) {
				playerOnTile = true;
			} else {
				playerOnTile = false;
			}
		}

		return playerOnTile;
	}

}