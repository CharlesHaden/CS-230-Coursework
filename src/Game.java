import java.util.ArrayList;

public class Game {
    private static int turn;
    private static ArrayList<Player> players;
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

    public static void setPlayers(ArrayList<Player> newplayers) {
        players = newplayers;
    }



    public static void newTurn() {
        //need to index number of players
        //loop through this index.
        turn = Player.getPlayerNum();
        if (turn < /*playerIndex*/) {
            turn = currentPlayer+1;
        } else {
            turn = 1;
        }
    }

    public static void update() {
    }

    public static boolean checkWin() {
        if (Board.getTile(Player.getPlayerPosition()[0], Player.getPlayerPosition()[1]).getFloorTileType == "Goal") {
            Player.setScore(Player.getScore()+1));
            return True
        } else {
            return False
        }
    }
}
