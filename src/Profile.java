/**
 * A class for making profiles
 *
 * @author Charles Haden
 * @author Mathew Clarke
 */
public class Profile {
    private int wins;
    private int losses;
    private String name;

    public Profile(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
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
