/**
 * Player Class
 * @author Lewis Hotchkiss
 */

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Scanner;

public class Player {

	private int playerNum;
	private String orientation;
	private int score;
	private int[][] lastMoves;
	private int[] playerPosition;
	private ArrayList<ActionTile> playerHand = new ArrayList<ActionTile>();
	private Profile playerProfile;
	
	public Player(int playerNum, String orientation, int score, int[][] lastMoves, int[] playerPosition, ArrayList<ActionTile> playerHand) {
		this.playerNum = playerNum;
		this.orientation = orientation;
		this.score = score;
		this.lastMoves = lastMoves;
		this.playerPosition = playerPosition;
		this.playerHand = playerHand;
	}
	
	public int getPlayerNum() {
		return playerNum;
	}
	
	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}
	
	public String orientation() {
		return orientation;
	}
	
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int[][] getLastMoves() {
		return lastMoves;
	}
	
	public void setLastMoves(int[][] lastMoves) {
		this.lastMoves = lastMoves;
	}
	
	public int[] getPlayerPosition() {
		return playerPosition;
	}
	
	public void setPlayerPosition(int[] playerPosition) {
		this.playerPosition = playerPosition;
	}
	
	public ArrayList<ActionTile> getPlayerHand() {
		return playerHand;
	}
	
	public void setPlayerHand(ArrayList<ActionTile> playerHand) {
		this.playerHand = playerHand;
	}
	
	public Profile getPlayerProfile() {
		return playerProfile;
	}
	
	public void setPlayerProfile(Profile playerProfile) {
		this.playerProfile = playerProfile;
	}
	
	public void drawTile(Board currentBoard) {
		Tile tile = currentBoard.getTileFromSilkBag();
		
		if (tile.getTileType().equals("Floor")) {
			FloorTile floorTile = (FloorTile) tile;
			//NEED TO GET WHERE PLAYER WANTS TO INSERT TILE
			int x;
			int y;
			boolean horizontal = true;
			currentBoard.insertTile(floorTile, x, y, horizontal);
		} 
		else if (tile.getTileType().equals("Action")) {
			ActionTile actionTile = (ActionTile) tile;
			playerHand.add(actionTile);
		}
	}
	
	public void playTile(ActionTile tile) {
		Boolean played = false;
		
		//Displays the action tiles in the players hand
		for (ActionTile tiles : playerHand) {
			System.out.println(tiles.getActionTileType());
		}
		
		Scanner input = new Scanner(System.in);
		
		do {
			//Gets user to choose action tile
			String tileType = input.next();
			System.out.println("Choose action tile to play: ");
		
			//Goes through each tile in the players hand to see if they have that tile to play
			for (ActionTile tiles : playerHand) { //WARNING: may loop through and remove several tiles of same type
				//If they have the tile to play then it is removed from their hand and played
				if (tiles.getActionTileType().equals(tileType)) {
					if (tiles.getActionTileType().equals("Backtrack")) {
						tiles.action(this);
					}
					else if (tiles.getActionTileType().equals("DoubleMove")) {
						tiles.action(this);
					}
					else if (tiles.getActionTileType().equals("Fire")) {
						//NEED TO GET CHOSEN TILE
						int row;
						int col;
						int[] chosenTile = {row,col};
						tiles.action(chosenTile);
					}
					else if (tiles.getActionTileType().equals("Ice")) {
						//NEED TO GET CHOSEN TILE
						int row;
						int col;
						int[] chosenTile = {row,col};
						tiles.action(chosenTile);
					}
					
					playerHand.remove(tiles);
					played = true;
				//If they haven't got that tile to play then they will have to choose again
				} else {
					System.out.println("Cant play that action tile");
					played = false;
				}
			}
		} while (played = false);
		
		input.close();
	}
	
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
	 * @param e
	 */
	public void makeMove(Board currentBoard, KeyEvent e) {
		boolean moved = false;
		FloorTile currentTile = (FloorTile) currentBoard.getTile(playerPosition[0], playerPosition[1]);
		boolean[] currentTileOpenPath = currentTile.getOpenPath();
		
		do {
			int keyCode = e.getKeyCode();
		    switch (keyCode) { 
		        case KeyEvent.VK_UP:
		        	movePlayer(currentBoard, currentTileOpenPath, playerPosition[0], playerPosition[1] + 1, 0, 1);
		            break;
		        case KeyEvent.VK_DOWN:
		        	movePlayer(currentBoard, currentTileOpenPath, playerPosition[0], playerPosition[1] - 1, 1, 0);
		            break;
		        case KeyEvent.VK_LEFT:
		        	movePlayer(currentBoard, currentTileOpenPath, playerPosition[0] - 1, playerPosition[1], 2, 3);
		            break;
		        case KeyEvent.VK_RIGHT:
		        	movePlayer(currentBoard, currentTileOpenPath, playerPosition[0] + 1, playerPosition[1], 3, 2);
		            break;
		     }
		} while (moved == false);
		
	}
	
	/**
	 * Changes the players position to the new position
	 * @param currentTileOpenPath Boolean array where {0,1,2,3} {UP,DOWN,LEFT,RIGHT}
	 * @param x The x coordinate of the next tile
	 * @param y The y coordinate of the next tile
	 * @param currentPath The position of the boolean in the array {UP,DOWN,LEFT,RIGHT} of the current tile
	 * @param nextPath The position of the boolean in the array {UP,DOWN,LEFT,RIGHT} of the next tile
	 * @return Returns true if the player was able to move
	 */
	public boolean movePlayer(Board currentBoard, boolean[] currentTileOpenPath, int x, int y, int currentPath, int nextPath) {
		Boolean moved = false;
		FloorTile nextTile = (FloorTile) currentBoard.getTile(x, y);
    	boolean[] nextTileOpenPath = nextTile.getOpenPath();

    	//If there is a clear path from the current tile to the next, and it is frozen or has a player, then player can move
    	if ((currentTileOpenPath[currentPath] = true) && (nextTileOpenPath[nextPath] = true)) {
    		if ((nextTile.getOnFire() == true) || (checkForPlayers(x, y) == true)) {
    			moved = false;
    		} else {
    			playerPosition[0] = x;
	        	playerPosition[1] = y;
	        	moved = true;
    		}
    	}
		return moved;
	}
	
	/**
	 * Will check if there is a player on any of the surrounding tiles
	 * @param x The x coordinate of the next tile
	 * @param y The y coordinate of the next tile
	 * @return Returns true if there is a player on any surrounding tile
	 */
	public boolean checkForPlayers(int x, int y) {
		boolean playerOnTile = false;
		Player players[] = Game.getPlayers(); //Gets the array of players from the game class
		int i;
  
        for (i = 0; i < players.length; i++) { 
        	int otherPlayerPosition[] = players[i].getPlayerPosition();
        	if ((otherPlayerPosition[0] == x && otherPlayerPosition[1] == y)) {
        		playerOnTile = true;
        	} else {
        		playerOnTile = false;
        	}
        } 
		
		return playerOnTile;
	}
	

 
}
