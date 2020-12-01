/**
 * This works as the leader board for viewing the wins and losses of players with assigned profiles in the game.
 *
 * @author Charles Haden
 * @author Mathew Clarke
 */
public class LeaderBoard {
	
	//Used to store which order to display the profiles. 
	private String[] profileList;
	//Used to store which order to display the profiles.  
	private boolean ACSENDING = true;
	
	/**
	 * Constructs a LeaderBoard object and does't take any parameters.
	 */
	public LeaderBoard() {
		
	}
	
	/**
	 * Used as a getter for list of profiles.
	 * 
	 * @return Return type is an array of strings and is the profileList.
	 */
    public String[] getProfileList() {
        return profileList;
    }
    
	/**
	 * Used as a setter for list of profiles.
	 * 
	 * @param profileList Stores the array of strings used to store the profile classes
	 */
    public void setProfileList(String[] profileList) {
        this.profileList = profileList;
    }
    
	/**
	 * Prints all profiles in the current array/list with their associated wins and losses.
	 * 
	 * @return Returns the sorted profile list in either ascending or descending order.
	 */
	public String[] sortBy() {
		return profileList;
	}
	
}