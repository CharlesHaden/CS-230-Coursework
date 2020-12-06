
* This works as the leader board for viewing the wins and losses of players with assigned profiles in the game.
 *
 * @author Charles Haden
 * @author Mathew Clarke
 */
import java.util.ArrayList; // Import arrayList to allow flexible profile lists

public class LeaderBoard {
	
	//Used to store which order to display the profiles. 
	private static ArrayList<Profile> profileList;
	//Used to store which order to display the profiles.  
	private static boolean ascending;
	
	private static int presetBoard;
	
	/**
	 * Constructs a LeaderBoard object and does't take any parameters.
	 */
	public LeaderBoard(ArrayList<Profile> newProfileList, int newPresetBoard) {
		profileList = newProfileList;
		ascending = true;
		presetBoard = newPresetBoard;
	}
	
	/**
	 * Used as a getter for number of preset board assigned.
	 * 
	 * @return Return type is an int and is the preset board number.
	 */
    public static int getPresetBoard() {
        return presetBoard;
    }
    
	/**
	 * Used as a getter for number of preset board assigned.
	 * 
	 * @return Return type is an int and is the preset board number.
	 */
    public static void setPresetBoard(int newPresetBoard) {
    	presetBoard = newPresetBoard;
    }
	
	/**
	 * Used as a getter for list of profiles.
	 * 
	 * @return Return type is an array of strings and is the profileList.
	 */
    public static ArrayList<Profile> getProfileList() {
        return profileList;
    }
    
	/**
	 * Used as a setter for list of profiles.
	 * 
	 * @param profileList Stores the array of strings used to store the profile classes
	 */
    public static void setProfileList(ArrayList<Profile> newProfileList) {
        profileList = newProfileList;
    }
    
	/**
	 * Returns all profiles in the current array/list with their associated wins and losses.
	 * 
	 * Side effects: changes stored profileList in LeaderBoard as opposed to just returning it
	 * @return Returns the sorted profile list in either ascending or descending order.
	 */
	public static ArrayList<Profile> sortBy() {
		// Bubble sort as array is likely to be small
		Profile temp;
		boolean sorted;
		sorted = false;
		
		while(!sorted) {
			
			sorted = true;
			for (int i = 0; i < profileList.size() - 1; i++) {
				
				if ((profileList.get(i).getWins() > profileList.get(i+1).getWins()) ^ !ascending) {
					
					temp = profileList.get(i);
					profileList.set(i, profileList.get(i+1));
					profileList.set(i+1, temp);
					sorted = false;
				}
			}
		}
		return profileList;
	}


}
