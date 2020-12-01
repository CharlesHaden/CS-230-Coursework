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
	private static ArrayList<Tile> playerHand = new ArrayList<Tile>();
	
	public Player(int playerNum, String orientation, int score, int[][] lastMoves, int[] playerPosition, ArrayList<Tile> playerHand) {
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
	
	public ArrayList<Tile> getPlayerHand() {
		return playerHand;
	}
	
	public void setPlayerHand(ArrayList<Tile> playerHand) {
		this.playerHand = playerHand;
	}
	
	public void drawTile() {
		Tile tile = Board.getTile();
		
		if (tile.getTileType().equals("Floor")) {
			Board.insertTile(tile);
		} 
		else if (tile.getTileType().equals("Action")) {
			playerHand.add(tile);
		}
	}
	
	public static void playTile(Tile tile) {
		Boolean played = false;
		
		//Displays the action tiles in the players hand
		for (Tile tiles : playerHand) {
			System.out.println(tiles.getActionTileType());
		}
		
		Scanner input = new Scanner(System.in);
		
		do {
			//Gets user to choose action tile
			String tileType = input.next();
			System.out.println("Choose action tile to play: ");
		
			//Goes through each tile in the players hand to see if they have that tile to play
			for (Tile tiles : playerHand) { //WARNING: may loop through and remove several tiles of same type
				//If they have the tile to play then it is removed from their hand and played
				if (tiles.getActionTileType().equals(tileType)) {
					playerHand.remove(tiles);
					Board.playActionTile(tileType);
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
	
	public void makeMove(KeyEvent e) {
		//Do while player not moved
		Tile currentTile = currentTile.getTile(playerPosition[0],playerPosition[1]);
		Boolean[] currentTileOpenPath = currentTile.getOpenPath();
		//Boolean[] currentTileOpenPath = Tile.getOpenPath();
		int keyCode = e.getKeyCode();
	    switch (keyCode) { 
	        case KeyEvent.VK_UP:
	        	//if current tiles open path = {X,TRUE,X,X}
	        	//and above tiles open path = {TRUE,X,X,X} 
	        	//where {up,down,left,right}
	        	Tile upTile = upTile.getTile(playerPosition[0],playerPosition[1] + 1);
	        	Boolean[] upTileOpenPath = upTile.getOpenPath();
	        	if ((currentTileOpenPath[0] = true) && (upTileOpenPath[1] = true)) {
	        		//y = y + 1
		        	playerPosition[1] = playerPosition[1] + 1;
	        	}
	            break;
	        case KeyEvent.VK_DOWN:
	        	Tile downTile = downTile.getTile(playerPosition[0],playerPosition[1] - 1);
	        	Boolean[] downTileOpenPath = downTile.getOpenPath();
	        	if ((currentTileOpenPath[1] = true) && (downTileOpenPath[0] = true)) {
	        		//y = y - 1
		        	playerPosition[1] = playerPosition[1] - 1;
	        	}
	            break;
	        case KeyEvent.VK_LEFT:
	        	Tile leftTile = leftTile.getTile(playerPosition[0] - 1,playerPosition[1]);
	        	Boolean[] leftTileOpenPath = leftTile.getOpenPath();
	        	if ((currentTileOpenPath[2] = true) && (leftTileOpenPath[3] = true)) {
	        		//x = x - 1
		        	playerPosition[0] = playerPosition[0] - 1;
	        	}
	            break;
	        case KeyEvent.VK_RIGHT:
	        	Tile rightTile = rightTile.getTile(playerPosition[0] + 1,playerPosition[1]);
	        	Boolean[] rightTileOpenPath = rightTile.getOpenPath();
	        	if ((currentTileOpenPath[3] = true) && (rightTileOpenPath[2] = true)) {
	        		//x = x + 1
		        	playerPosition[0] = playerPosition[0] + 1;
	        	}
	            break;
	     }

	}
	
}
