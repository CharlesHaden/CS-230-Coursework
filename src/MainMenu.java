/**
 * This works as the main menu for starting and saving games as well as creating and deleting profiles.
 *
 * @author Charles Haden
 * @author Mathew Clarke
 */

/**
 * The Main menu class works to manage:
 * Profiles, creation and removal, along with saving to allow wins and losses to be stored throughout the games.
 * Pick the current loaded board in play (whether in progress or a new preset board).
 * Save in progress boards.
 * Assist other classes with things such as generating players to fit the requirements and creating the fixed tiles for the preset boards.
 * 
 */

import java.io.File;		// Import the file class
import java.io.FileNotFoundException;
import java.io.FileWriter;	// Import the FileWriter class
import java.io.IOException;	// Import the IOException class to handle errors
import java.util.Scanner;	// Import scanner to read in files
import java.util.ArrayList; // Import arrayList to allow flexible profile lists

public  class MainMenu {

	private static ArrayList<Profile> allProfiles = new ArrayList<Profile>();
	private static LeaderBoard curLeaderBoard;
	private static final int NUM_OF_PRESET_BOARDS = 4;
	private static ArrayList<Player> curGamePlayers = new ArrayList<Player>();

	/**
	 * Empty constructor for the MainMenu class.
	 */
	public MainMenu() {

	}

	/**
	 * Opens a game state from a preset board save file.
	 */
	public static void loadPresetBoard(int presetBoard) {

		String[] playerPos;
		int[] intPlayerPos;
		String curLine = "";
		int curSegment = 0;
		int[] curSilkbag = new int[8];
		int X = 0;
		int Y = 0;

		String fileName = ("src/SavedPresetBoard" + presetBoard + ".txt");

		// assumes order of data is max row, max column
		try {
			File file = new File (fileName);
			Scanner inputFromFile = new Scanner (file);
			while (inputFromFile.hasNextLine()) {
				Scanner inputFromLine = new Scanner (inputFromFile.nextLine());
				curLine = inputFromLine.nextLine();

				if (curLine.equals("...")) {
					curSegment ++;
				} else {
					switch (curSegment) {
						case 0:
							for (int i = 0; i < curLine.split(",").length-1; i++) {
								curSilkbag[i] = Integer.parseInt(curLine.split(",")[i]);
							}
							break;
						case 1:
							X = Integer.parseInt(curLine.split(",")[0]);
							Y = Integer.parseInt(curLine.split(",")[1]);
							new Board(X, Y, curSilkbag, presetBoard);
							break;
						case 2:
							readTile(curLine);
							break;
						case 3:
							if (Integer.parseInt(curLine.split(":")[0]) < curGamePlayers.size()) {
								playerPos = curLine.split(":")[1].split(",");
								intPlayerPos = new int[] {Integer.parseInt(playerPos[0]),Integer.parseInt(playerPos[1])};
								curGamePlayers.get(Integer.parseInt(curLine.split(":")[0])).setPlayerPosition(intPlayerPos);
							}
							break;

						default:
							System.out.println("Index error (out of range).");


					}
					inputFromLine.close();
				}
			}
			inputFromFile.close();
			Board.buildBoard();
		} catch(FileNotFoundException e){
			System.out.println("FileNotFoundException");
			e.printStackTrace();
		}
	}


	/**
	 * Saves the current game state to txt file
	 */
	public static void saveBoard() {
		try {
			// 	safely creating the files
			File SavedBoard = new File ("file://SavedBoard.txt");
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

			String orientation = "";
			FloorTile curTile;

			for (int i = 0; i < 7; i++){
				Writer.write(Board.getSilkBag()[i] + ",");
			}

			Writer.write(".../n");

			Writer.write(Board.getBoardNumber() + "/n");

			Writer.write(".../n");

			Writer.write(Board.getWidth() + "," + Board.getHeight() + "/n");

			Writer.write(".../n");

			for (int X = 0; X < (Board.getTiles()).length; X++){
				for (int Y = 0; Y < (Board.getTiles()[X]).length; Y++){
					curTile = Board.getTiles()[X][Y];
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
			for (int i = 0; i < (Game.getPlayers()).size(); i++){
				curPlayer = Game.getPlayers().get(i);
				Writer.write(curPlayer.getPlayerNum() + ":" + curPlayer.getPlayerProfile().getName() + ":");
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
			Writer.write(Game.getTurn());

			Writer.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	/**
	 * Opens a game state from a save file.
	 */
	public static void loadBoard() {
		String curLine = "";

		ArrayList<Player> curPlayers = new ArrayList<Player>();
		int curSegment = 0;
		int[] curSilkbag = new int[8];
		int presetBoardNum = 0;
		int turn = 0;

		try {
			File file = new File ("SavedBoard.txt");
			Scanner inputFromFile = new Scanner (file);
			while (inputFromFile.hasNextLine()) {

				Scanner inputFromLine = new Scanner (inputFromFile.nextLine());
				curLine = inputFromLine.nextLine();

				if (curLine.equals("...")) {
					curSegment ++;
				} else  {
					switch (curSegment) {
						case 0:
							for(int i = 0; i < curLine.split(",").length-1; i++){
								curSilkbag[i] = Integer.parseInt(curLine.split(",")[i]);
							}
							break;

						case 1:
							presetBoardNum = Integer.parseInt(curLine);
							break;

						case 2:
							int width = Integer.parseInt(curLine.split(",")[0]);
							int height = Integer.parseInt(curLine.split(",")[1]);
							new Board(width,height,curSilkbag,presetBoardNum);
							break;

						case 3:
							readTile(curLine);
							break;

						case 4:
							curPlayers.add(readPlayer(curLine));
							break;

						case 5:
							turn = Integer.parseInt(curLine);
							break;

						default:
							System.out.println("Index error (out of range).");

					}

				}
				inputFromLine.close();
			}
			new Game(curPlayers);

			while (Game.getTurn() != turn) {
				Game.newTurn();
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
	private static Player readPlayer(String curLine) {

		int index = 0;
		String curAttribute;
		int curSegment = 0;

		Player newPlayer = null;

		int playerNum = 0;
		String playerName = "";
		int X = 0;
		int Y = 0;
		ArrayList<ActionTile> playerHand = new ArrayList<ActionTile>();

		ActionTile curTile = null;

		while (curSegment < 4) {
			curAttribute = "";
			while(index < curLine.length() && curLine.charAt(index) != ':') {

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
					break;

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
				newPlayer = new Player(playerNum, "", new int[0][0], new int[]{X,Y}, playerHand);
				newPlayer.setPlayerProfile(allProfiles.get(i));
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

	private static void readTile(String curLine) {

		int index = 0;
		String curAttribute;
		int curSegment = 0;

		int X = 0;
		int Y = 0;
		boolean fixed = true;
		String tileType = "";
		int orientation = 0;

		while (curSegment < 4) {
			curAttribute = "";
			while(index < curLine.length() && curLine.charAt(index) != ':') {
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
					if (curAttribute.equals("true")) {
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

			index ++;
			curSegment ++;

		}
		switch (tileType) {
			case "Corner":
				Board.setFloorTile(new CornerTile(orientation, fixed), X, Y);
				break;
			case "Straight":
				Board.setFloorTile(new StraightTile(orientation, fixed), X, Y);
				break;
			case "Tshaped":
				Board.setFloorTile(new TshapedTile(orientation, fixed), X, Y);
				break;
			case "Goal":
				Board.setFloorTile(new GoalTile(orientation, fixed), X, Y);
				break;
			default:
				System.out.println("Index error (out of range).");
		}



	}

	/**
	 * Saves the current profileList txt file
	 */
	public static void saveProfile() {
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
	public static void loadProfile() {
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
	 * Used as a getter for the current players in the game.
	 *
	 * @return Return type is an ArrayList containing type 'Player'.
	 */

	public static ArrayList<Player> getCurGamePlayers() {
		return curGamePlayers;
	}

	/**
	 * Used as a setter for the ArrayList of players for this game.
	 *
	 * @param newCurGamePlayers Stores the ArrayList players to be involved in this game.
	 */
	public static void setCurGamePlayers(ArrayList<Player> newCurGamePlayers) {
		curGamePlayers = newCurGamePlayers;
	}

	/**

	 * Reads individual lines from the txt file.
	 *
	 * @param curLine the current line from the file reader in a String format.
	 *
	 * @return Returns the individual profiles of type Profile.
	 */
	private static Profile readProfileLine(String curLine) {

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

	/**
	 * Used to link the players to their correct profiles.
	 *
	 * @param playerLink This is the player chosen to link with the profile.
	 * @param profileLink This is the profile chosen to link with the player.
	 */
	public static void playerProfileLink (Player playerLink,Profile profileLink){
		playerLink.setPlayerProfile(profileLink);
	}

	/**
	 * Used for generating the array of players for the current game.
	 *
	 * @param numOfPlayers This is the number of players chosen to take part in this game.
	 */

	public static void curGenPlayers(int numOfPlayers){
		for(int i = 1; i <= numOfPlayers; i++ ){
			curGamePlayers.add(new Player(i,"North",new int[0][0], new int[0],new ArrayList<ActionTile>()));
		}
		new Game(curGamePlayers);
	}

	/**
	 * returns an array for the leader board
	 *
	 * @return String[] and array containing all the details for the player from the leader board separated by a ":".
	 */
	public static String[] displayLeaderBoard() {
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
	 * @param name name of the profile
	 *  TO DO--- Unique identifier for each profile
	 */
	public static void newProfile(String name) {
		Profile profileAdd = new Profile(name, NUM_OF_PRESET_BOARDS);
		allProfiles.add(profileAdd);

	}

	/**
	 * deletes a profile
	 * @param name name connected with profile
	 */
	public static void deleteProfile(String name) {
		for(int i = 0; i<allProfiles.size();i++){
			if(allProfiles.get(i).getName().equals(name)){
				allProfiles.remove(i);
			}
		}
	}

}
