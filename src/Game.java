import java.util.ArrayList;

public class Game {

    private static int turn;
    private static ArrayList<Player> players;
    private static Player curPlayer;

    public Game(ArrayList<Player> players) {
        this.players = players;
        turn = 0;
    }

    public static int getTurn() {
        return turn;
    }

    public static void setTurn(int newTurn) {
        turn = newTurn;
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static void setPlayers(ArrayList<Player> newPlayers) {
        players = newPlayers;
    }

    public static void newTurn() {
        turn++;
        curPlayer = players.get(turn % players.size());
    }

    public static void update() {

    }

    /**
    public static boolean checkWin() {
        if (Board.getTile(curPlayer.getPlayerPosition()[0],
                curPlayer.getPlayerPosition()[1]).getFloorTileType() == "Goal") {
            Profile curPlayerProfile = curPlayer.getPlayerProfile();
            curPlayerProfile.setWins(Board.getBoardNumber(), curPlayerProfile.getWins(Board.getBoardNumber(), curPlayerProfile.getWins()));
            for (Player player: players){
                if (player != curPlayer) {
                   // player.getPlayerProfile().setLosses(Board.getBoardNumber(), );
                }
            }
            return true;
        } else {
            return false;
        }
    }
     */
}
