public class Game {
    private int turn;

    public Game() {
    }

    public void newTurn() {
        //need to index number of players
        //loop through this index.
        turn = Player.getPlayerNum();
        if (turn < /*playerIndex*/) {
            turn = currentPlayer+1;
        } else {
            turn = 1;
        }
    }

    public void update() {
    }

    public boolean checkWin() {
        if (Board.getTile(Player.getPlayerPosition()[0], Player.getPlayerPosition()[1]).getFloorTileType == "Goal") {
            Player.setScore(Player.getScore()+1));
            return True
        } else {
            return False
        }
    }
}
