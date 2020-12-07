/**
 * A class for making profiles
 *
 * @author Charles Haden
 * @author Mathew Clarke
 */

public class Profile {
    private int[] wins;
    private int[] losses;
    private String name;

    
    /**
     * This the the constructor for the Profile Class
     * 
     * @param name Takes the name of the profile in string form
     * @param NUM_OF_PRESET_BOARDS Takes the number of presetBoards in int form
     * 			(used for building the array of wins and losses)
     */
    public Profile(String name, int NUM_OF_PRESET_BOARDS) {
        this.name = name;
        wins = new int[NUM_OF_PRESET_BOARDS];
        losses= new int[NUM_OF_PRESET_BOARDS];
    }
    
    /**
     * Used as a Getter for the wins 
     * 
     * @return wins depending on the current board type (presetBoardNum) 
     */
    public int getWins(int index) {
        return wins[index];
    }
    
    /**
     * Used as a Setter for the wins 
     * 
     * @param wins for the profile in the presetBoard Location. 
     */
    public void setWins(int wins, int index) {
        this.wins[index] = wins;
    }

    /**
     * Used as a Getter for the losses 
     * 
     * @return losses depending on the current board type (presetBoardNum) 
     */
    public int getLosses(int index) {
        return losses[index];
    }
    
    /**
     * Used as a Setter for the losses 
     * 
     * @param losses for the profile in the presetBoard Location. 
     */
    public void setLosses(int losses, int index) {
        this.losses[index] = losses;
    }
    
    /**
     * Used as a Getter for the name 
     * 
     * @return name returns the name of the profile
     */
    public String getName() {
        return name;
    }

    /**
     * Changes the current name of a profile.
     * @param name
     */
    public void changeName(String name) {
        this.name = name;
    }
}

