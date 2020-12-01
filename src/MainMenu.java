
/**
 * This works as the main menu for starting and saving games as well as creating and deleting profiles.
 *
 * @author Charles Haden
 * @author Mathew Clarke
 */

import java.io.File;		// Import the file class
import java.io.FileNotFoundException;
import java.io.FileWriter;	// Import the FileWriter class
import java.io.IOException;	// Import the IOException class to handle errors
import java.util.Scanner;	// Import scanner to read in files
import java.util.ArrayList; // Import arrayList to allow flexible profile lists

public class MainMenu {
    private Game curGame;
    private ArrayList<Profile> allProfiles;
    private Board[] presetBoards;
    private LeaderBoard curLeaderBoard;
    private final int NUM_OF_PRESET_BOARDS = 4;

    /**
     * Empty constructor for the MainMenu class.
     */
    public MainMenu() {

    }

    /**
     * Used as a getter for the current game.
     * 
     * @return Return type is 'Game' and is the current game.
     */
    public Game getCurrentGame() {
        return curGame;
    }

    /**
     * Used as a getter for the preset boards.
     * 
     * @return Return is an array of type Board.
     */
    public Board[] getPresetBoards() {
        return presetBoards;
    }

    /**
	 * Used as a setter for the array of preset boards.
	 * 
	 * @param presetBoards Stores the array presetBoards of type Board.
	 */
    public void setPresetBoards(Board[] presetBoards) {
        this.presetBoards = presetBoards;
    }
    
	/**
	 * Used as a setter for the current game.
	 * 
	 * @param curGame Stores the reference for the current game of type Game.
	 */
    public void setCurrentGame(Game curGame) {
        this.curGame = curGame;
    }

    /**
     * Opens a game state from a preset board save file.
     */
    public void loadPresetBoard(int presetBoard) {
    	String curLine = "";
    	int curPlayer = 0;
    	int curSegment = 0;
    	int[] curSilkbag = new int[8];
    	int X = 0;
    	int Y = 0;
    	
    	Game savedGame = new Game();
		Board curBoard = new Board("");
		String fileName = ("SavedPresetBoard" + presetBoard + ".txt");
		// assumes order of data is max row, max column
    	try {
    		File file = new File (fileName);
    		Scanner inputFromFile = new Scanner (file);
    		while (inputFromFile.hasNextLine()) {
				Scanner inputFromLine = new Scanner (inputFromFile.nextLine());
				curLine = inputFromLine.nextLine();
				if (curLine == "...") {
					curSegment ++;
				}
				
				if (curSegment == 0) {
					X = Integer.parseInt(curLine.split(",")[0]);
					Y = Integer.parseInt(curLine.split(",")[1]);
				} else if (curSegment == 1) {
					for (int i = 0; i < curLine.split(",").length; i++) {
						curSilkbag[i] = Integer.parseInt(curLine.split(",")[i]);
					}
					curBoard = new Board(X, Y, curSilkbag);
				} else if (curSegment == 2) {
					readTile(curLine, curBoard);
				} else {
					// read player location and pass to ?
				}
				inputFromLine.close();
			}
			inputFromFile.close();
    	} catch(FileNotFoundException e){
			System.out.println("FileNotFoundException");
			e.printStackTrace();
		}
    	savedGame.setBoard(curBoard);
    	curGame = savedGame;
    }
    
    /**
     * Saves the current game state to txt file
     */
    public void saveBoard() {
    	try {
    		// 	safely creating the files
    		File SavedBoard = new File ("SavedBoard.txt");
    		if (SavedBoard.exists()) {
    			if(!SavedBoard.delete()) {
    				System.out.println("File present, but unable to delete.");
    			}
    		}
    		if (!SavedBoard.createNewFile()) {
    			System.out.println("File already present.");
    		}
    		FileWriter Writer = new FileWriter(SavedBoard);
    		
    	    // writing tiles
    		Player curPlayer;
    	    Board curBoard = curGame.getBoard();
    	    String orientation = "";
    	    FloorTile curTile;
    	    
    	    for (int i = 0; i < 7; i++){
    	    	Writer.write(curBoard.getSilkBag()[i] + ",");
    	    }
    	    
    	    Writer.write(".../n");
    	    
    	    for (int X = 0; X < (curBoard.getTiles()).length; X++){
    	    	for (int Y = 0; Y < (curBoard.getTiles()[X]).length; Y++){
    	    		curTile = curBoard.getTiles()[X][Y];
    	    		// Translates the orientation from a int to a string as laid out in design.
    	  		  	switch (curTile.getOrientation()) {
    	  		  	case 0:
    	  		  		orientation = "North";
    	  		  		break;
    	  		  	case 1:
    	  		  		orientation = "East";
    	  		  		break;
    	  		  	case 2:
    	  		  		orientation = "South";
    	  		  		break;
    	  		  	case 3:
    	  		  		orientation = "West";
    	  		  		break;
    	  		  	default:
    	  		  		System.out.println("Index error (out of range).");
    	  		  }
				
    	  		  // writes the current board layout in this format (X,Y : Tile Type : Fixed or not : Orientation)
    	  		  Writer.write("\n" + X + "," + Y + ":" + curTile.getTileType() + curTile.getFixed() + ":" + orientation + "\n");
    	  	  	} 
    	    }
    	      
    	    // writes ... to show that the file is now onto players
    	    Writer.write(".../n");
    	      
    	    // writes players
    	    for (int i = 0; i < (curGame.getPlayers()).length; i++){
    	    	curPlayer = curGame.getPlayers()[i];
    	    	Writer.write(curPlayer.getPlayerNum() + ":" + curPlayer.getProfile().getName() + ":");
    	    	for (int j = 0; j < (curPlayer.getPlayerHand()).size(); j++) { 
    	    		if (j != 0) {
    	    			Writer.write(",");
    	    		}
    	    		Writer.write(curPlayer.getPlayerHand().get(j).getTileType());
    	    	}
    	    	Writer.write(":" + curPlayer.getPlayerPosition()[0] + "," + curPlayer.getPlayerPosition()[1] + "\n");
    	    }
    	    // writes ... to show that the file is now onto turn
    	    Writer.write(".../n");
    	    Writer.write(curGame.getTurn());
    	      
    	    Writer.close();
    	} catch (IOException e) {
    		System.out.println("An error occurred.");
    		e.printStackTrace();
    	}
    }

    /**
     * Opens a game state from a save file.
     */
    public void loadBoard() {
    	String curLine = "";
    	int curPlayer = 0;
    	int curSegment = 0;
    	int[] curSilkbag = new int[8];
    	
    	Game savedGame = new Game();
		Board curBoard = new Board("");
    	try {
    		File file = new File ("SavedBoard.txt");
    		Scanner inputFromFile = new Scanner (file);
    		while (inputFromFile.hasNextLine()) {
				Scanner inputFromLine = new Scanner (inputFromFile.nextLine());
				curLine = inputFromLine.nextLine();
				if (curLine == "...") {
					curSegment ++;
				}
				
				if (curSegment == 0) {
					for(int i = 0; i < curLine.split(",").length; i++){
						curSilkbag[i] = Integer.parseInt(curLine.split(",")[i]);
				    }
				} else if (curSegment == 1) {
					readTile(curLine, curBoard);
				} else if (curSegment == 2) {
					savedGame.setPlayers(readPlayer(""), curPlayer);
					curPlayer ++;
				} else {
					while (savedGame.getTurn() != Integer.parseInt(curLine)) {
						savedGame.newTurn();
					}
				}
				inputFromLine.close();
			}
			inputFromFile.close();
    	} catch(FileNotFoundException e){
			System.out.println("FileNotFoundException");
			e.printStackTrace();
		}
    }

    /**
     * Reads individual lines from the txt file and adds them to the current board.
     * 
     * Side effects include changing tiles on the current board to the saved one.
     */
    private Player readPlayer(String curLine) {
    	
    	int index = 0;
    	String curAttribute;
    	int curSegment = 0;
    	
    	Player newPlayer = null;
    	
    	int playerNum = 0;
    	String playerName = "";
    	int X = 0;
    	int Y = 0;
    	ArrayList<Tile> playerHand = new ArrayList<Tile>();
    	
    	Tile curTile = null;
    	
    	while (curSegment < 7) {
    		curAttribute = "";
    		
    		while(curLine.charAt(index) != ':' && index < curLine.length()) {
    			curAttribute = curAttribute + curLine.charAt(index);
    			index ++;
    		}
    		switch (curSegment) {
			case 0:
				playerNum = Integer.parseInt(curAttribute);
				break;
			case 1:
				playerName = curAttribute;
				break;
			case 2:
				for (int i = 0; i < curAttribute.split(",").length; i++) {
					switch (curAttribute.split(",")[i]) {
					case "Ice":
						curTile = new IceTile();
						break;
					case "Fire":
						curTile = new FireTile();
						break;
					case "Double":
						curTile = new DoubleMoveTile();
						break;
					case "Backtrack":
						curTile = new BacktrackTile();
						break;
					default:
						System.out.println("Index error (out of range).");
					}
					playerHand.add(curTile);
				}
			case 3:
				X = Integer.parseInt(curAttribute.split(",")[0]);
				Y = Integer.parseInt(curAttribute.split(",")[1]);
				break;
			default:
				System.out.println("Index error (out of range).");
    		}
    		
			index ++;
    		curSegment ++;
    	}
    	for(int i = 0; i < allProfiles.size(); i++) {
    		if (allProfiles.get(i).getName() == playerName) {
    			newPlayer = new Player(playerNum, "", 0, null, new int[]{X,Y}, playerHand, allProfiles.get(i));
    		}
    		
    	}
    	
    	return(newPlayer);
    }
    
    /**
	 * Reads individual lines from the txt file as floor tiles and adds them to the current board.
	 * 
	 * @param curLine is the current line assumed to contain the relevant data to create a tile and it's location.
	 * @param curBoard is the current board being created for the saved game.
	 */
    private void readTile(String curLine, Board curBoard) {

    	int index = 0;
    	String curAttribute;
    	int curSegment = 0;
    	
    	int X = 0;
    	int Y = 0;
    	boolean fixed = true;
    	String tileType = "";
    	int orientation = 0;
    	FloorTile curTile;
    	
    	while (curSegment < 7) {
    		curAttribute = "";
    		
    		while(curLine.charAt(index) != ':' && index < curLine.length()) {
    			curAttribute = curAttribute + curLine.charAt(index);
    			index ++;
    		}
    		switch (curSegment) {
			case 0:
				X = Integer.parseInt(curAttribute.split(",")[0]);
				Y = Integer.parseInt(curAttribute.split(",")[1]);
				break;
			case 1:
				tileType = curAttribute;
				break;
			case 2:
				if (curAttribute == "true") {
					fixed = true;
				} else {
					fixed = false;
				}
				break;
			case 3:
				switch (curAttribute) {
				case "North":
					orientation = 0;
					break;
				case "East":
					orientation = 1;
					break;
				case "South":
					orientation = 2;
					break;
				case "West":
					orientation = 3;
					break;
				default:
					System.out.println("Index error (out of range).");
				}
				break;
			default:
				System.out.println("Index error (out of range).");
    		}
    		
			switch (tileType) {
			case "Corner":
				curTile = new CornerTile(orientation);
				break;
			case "Straight":
				curTile = new StraightTile(orientation);
				break;
			case "Tshaped":
				curTile = new Tshaped(orientation);
				break;
			case "Goal":
				curTile = new GoalTile(orientation);
				break;
			default:
				System.out.println("Index error (out of range).");
			}
			
			index ++;
    		curSegment ++;

    	}
		curTile.setFixed(fixed);
		
		curBoard.setFloorTile(curTile, X, Y);
    }
    
    /**
     * Saves the current profileList txt file
     */
    public void saveProfile() {
    	String writen;
    	try {
    		// safely creating the files
    		File SavedProfiles = new File ("SavedProfiles.txt");
    		if (SavedProfiles.exists()) {
    			if(!SavedProfiles.delete()) {
    				System.out.println("File present, but unable to delete.");
    			}
    		}
    		if (!SavedProfiles.createNewFile()) {
    			System.out.println("File already present.");
    		}
    		FileWriter Writer = new FileWriter("SavedProfiles.txt");
    		
    		// writing tiles
    		ArrayList<Profile> curProfileList = curLeaderBoard.getProfileList();
    		for (int i = 0; i < (curProfileList).size(); i++){
    				Profile curProfile = curProfileList.get(i);
    				writen = (curProfile.getName());
    				for (int j = 0; j < (curProfileList).size(); j++){
    					writen += (":" + curProfile.getWins(j) + "," + curProfile.getLosses(j));
    				}
    				writen += ("\n");
    				// writes the current profileList layout in this format (name : wins , losses) wins and losses to be repeated for each preset board.
    				Writer.write(writen);
    		}
    		Writer.close();
    	} catch (IOException e) {
    		System.out.println("An error occurred.");
    		e.printStackTrace();
    	}
    }
    
    /**
     * Opens a profile list from a save file and turns them in to the current leaderboard's array of profiles.
     */
    public void loadProfile() {
    	try {
    		File file = new File ("SavedProfiles.txt");
    		Scanner inputFromFile = new Scanner (file);
    		while (inputFromFile.hasNextLine()) {
				Scanner inputFromLine = new Scanner (inputFromFile.nextLine());
				readProfileLine(inputFromLine.nextLine());
				inputFromLine.close();
			}
			inputFromFile.close();
    	} catch(FileNotFoundException e){
			System.out.println("FileNotFoundException");
			e.printStackTrace();
		}
    }
    
    /**
     * Reads individual lines from the txt file.
     * 
     * @param Takes the current line from the file reader in a String format.
     * 
     * @return Returns the individual profiles of type Profile.
     */
    private Profile readProfileLine(String curLine) {
    	
    	int index = 0;
    	String curAttribute;
    	int curSegment = 0;
    	Profile curProfile = new Profile(null, NUM_OF_PRESET_BOARDS);
    	
    	while (curSegment < 7) {
    		curAttribute = "";
    		
    		while(curLine.charAt(index) != ':' && index < curLine.length()) {
    			curAttribute = curAttribute + curLine.charAt(index);
    			index ++;
    		}
    		if (curSegment == 0) {
    			curProfile = new Profile(curAttribute, NUM_OF_PRESET_BOARDS);
    		} else {
    			curProfile.setWins(Integer.parseInt(curAttribute.split(",")[0]), curSegment - 1);
    			curProfile.setLosses(Integer.parseInt(curAttribute.split(",")[1]), curSegment - 1);
    		}
    		index ++;
    		curSegment ++;
    	}
    	
    	return(curProfile);
    }
    public void playerProfileLink (Player playerLink,Profile profileLink){
    	playerLink.setPlayerProfile(profileLink);
	}
    
    /**
     * Starts the game
     */
    public void startGame() {

    }

    /**
     * returns an array for the leader board
     *
     * @return String[] and array containing all the details for the player from the leader board separated by a ":".
     */
    public String[] displayLeaderBoard() {
    	ArrayList<Profile> curProfileList = curLeaderBoard.getProfileList();
    	String[] printToScreen = new String[curProfileList.size()];
    	int BoardNum = curLeaderBoard.getPresetBoard();
    	
    	for (int i = 0; i < curProfileList.size(); i++) {
    		printToScreen[i] = (curProfileList.get(i).getName() + ":" + curProfileList.get(i).getWins(BoardNum) + ":" + curProfileList.get(i).getLosses(BoardNum) + "\n");
    	}
        return(printToScreen);
    }

    /**
     * Creates a new profile
     */
    public void newProfile(String name) {
    	Profile profileAdd = new Profile(name, NUM_OF_PRESET_BOARDS);
    	allProfiles.add(profileAdd);

    }

    /**
     * deletes a profile
     */
    public void deleteProfile() {

    }

}
