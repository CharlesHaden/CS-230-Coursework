/**
 * This works as the leader board for viewing the wins and losses of players with assigned profiles in the game.
 *
 * @author Charles Haden
 * @author Mathew Clarke
 */
import java.util.ArrayList; // Import arrayList to allow flexible profile lists

public class LeaderBoard {
	
	//Used to store which order to display the profiles. 
	private ArrayList<Profile> profileList;
	//Used to store which order to display the profiles.  
	private boolean ascending;
	//Used in the bubble sort
	private boolean sorted;
	
	private Profile temp;
	int presetBoard;
	
	/**
	 * Constructs a LeaderBoard object and does't take any parameters.
	 */
	public LeaderBoard(ArrayList<Profile> profileList, int presetBoard) {
		this.profileList = profileList;
		this.ascending = true;
		this.presetBoard = presetBoard;
	}
	
	/**
	 * Used as a getter for number of preset board assigned.
	 * 
	 * @return Return type is an int and is the preset board number.
	 */
    public int getPresetBoard() {
        return presetBoard;
    }
	
	/**
	 * Used as a getter for list of profiles.
	 * 
	 * @return Return type is an array of strings and is the profileList.
	 */
    public ArrayList<Profile> getProfileList() {
        return profileList;
    }
    
	/**
	 * Used as a setter for list of profiles.
	 * 
	 * @param profileList Stores the array of strings used to store the profile classes
	 */
    public void setProfileList(ArrayList<Profile> profileList) {
        this.profileList = profileList;
    }
    
	/**
	 * Returns all profiles in the current array/list with their associated wins and losses.
	 * 
	 * Side effects: changes stored profileList in LeaderBoard as opposed to just returning it
	 * @return Returns the sorted profile list in either ascending or descending order.
	 */
	public ArrayList<Profile> sortBy() {
		// Bubble sort as array is likely to be small
		sorted = false;
		while(!sorted) {
			sorted = true;
			for (int i = 0; i < profileList.size() - 1; i++) {
				if ((profileList.get(i).getWins(presetBoard) > profileList.get(i+1).getWins(presetBoard)) ^ !ascending) {
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