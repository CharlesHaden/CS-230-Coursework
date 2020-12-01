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

    public Profile(String name, int NUM_OF_PRESET_BOARDS) {
        this.name = name;
        wins = new int[NUM_OF_PRESET_BOARDS];
        losses= new int[NUM_OF_PRESET_BOARDS];
    }

    public int getWins(int index) {
        return wins[index];
    }

    public void setWins(int wins, int index) {
        this.wins[index] = wins;
    }

    public int getLosses(int index) {
        return losses[index];
    }

    public void setLosses(int losses, int index) {
        this.losses[index] = losses;
    }

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
