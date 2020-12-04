import java.util.Random;

public class Board {

    private static FloorTile[][] tileList;
    private static int[] silkBag;
    private static int height;
    private static int width;
    private static Game curGame;

    public Board(int width, int height, int[] bag){
        tileList = new FloorTile[width][height];
        silkBag = bag;
        this.height = height;
        this.width = width;
    }

    public static void setFloorTile(FloorTile curTile, int x, int y){
        tileList[x][y] = curTile;
    }

    public static void buildBoard() {
        FloorTile curTile;
        curTile = null;


        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                if (tileList[i][j] == null) {

                    curTile = selectFromSilkBag();
                    tileList[i][j] = (curTile);
                }
            }
        }
    }

    private static FloorTile selectFromSilkBag(){
        FloorTile curTile;
        curTile = null;// because of switch statement

        Random rand = new Random();
        int n = rand.nextInt(3);
        n += 1;

        if (silkBag[n] > 0){

            int orientation = rand.nextInt(3);
            orientation += 1;

            switch (n) {
                case 0:
                    curTile = new CornerTile(orientation);
                    break;
                case 1:
                    curTile = new StraightTile(orientation);
                    break;
                case 2:
                    curTile = new TshapedTile(orientation);
                    break;
                case 3:
                    curTile = new GoalTile(orientation);
                    break;
                default:
                    System.out.println("Index error (out of range).");
            }

            silkBag[n] -= 1;
        }
        return curTile;
    }

    public static boolean insertTile(FloorTile tileToInsert, int x, int y, boolean horizontal){
        FloorTile silkBagTile;
        silkBagTile = null;
        FloorTile[][] tempTileList = tileList;
        boolean frozenTileError = false;

        if (horizontal) {
            if (x == 0) {

                //inserting from left
                silkBagTile = tileList[width - 1][y];
                for (int i = 1; i < width; i++) {
                    if (tempTileList[width - i][y].getIsFrozen() || tempTileList[width - i][y].getFixed() == true){
                        frozenTileError = true;
                    } else {
                        if (width - i == 0) {
                            tileList[0][y] = tileToInsert;
                        } else {
                            tileList[width - i][y] = tileList[width - (i + 1)][y];
                        }
                    }
                }
            }
            else if (x == width - 1) {
                //inserting from right
                silkBagTile = tileList[0][y];
                for (int i = 0; i < width; i++) {
                    if (tempTileList[i][y].getIsFrozen() || tempTileList[i][y].getFixed() == true) {
                        frozenTileError = true;
                    } else {
                        if (i == width - 1) {
                            tileList[width - 1][y] = tileToInsert;
                        } else {
                            tileList[i][y] = tileList[i + 1][y];
                        }
                    }
                }
            }
        }
        else{
            if (y == 0) {
                //inserting from above
                silkBagTile = tileList[x][height-1];
                for (int i = 1; i < height; i++) {
                    if (tempTileList[x][height-i].getIsFrozen() || tempTileList[x][height-i].getFixed() == true) {
                        frozenTileError = true;
                    }else{
                        if (height - i == 0) {
                            tileList[x][0] = tileToInsert;
                        } else {
                            tileList[x][height - i] = tileList[x][height - (i + 1)];
                        }
                    }
                }
            } else if (y == height - 1) {
                //inserting from below
                silkBagTile = tileList[x][0];
                for (int i = 0; i < height; i++) {
                    if (tempTileList[x][i].getIsFrozen() || tempTileList[x][i].getFixed() == true) {
                        frozenTileError = true;
                    } else {
                        if (i == height - 1) {
                            tileList[x][height - 1] = tileToInsert;
                        } else {
                            tileList[x][i] = tileList[x][i + 1];
                        }
                    }
                }
            }
        }
        if (frozenTileError == false) { // gotta add this for fixed tiles too
            tileList = tempTileList;
            addToSilkBag(silkBagTile);
            return true;
        }
        else return false;
    }

    public static void addToSilkBag(Tile silkBagTile){
        String tileType = silkBagTile.getTileType();

        switch (tileType) {
            case "Corner":
                silkBag[0] += 1;
                break;
            case "Straight":
                silkBag[1] += 1;
                break;
            case "Tshaped":
                silkBag[2] += 1;
                break;
            case "Goal":
                silkBag[3] += 1;
                break;
            default:
                System.out.println("Index error (out of range).");
        }
    }

    public static Tile getTileFromSilkBag(){
        Tile curTile;
        curTile = null;// because of switch statement

        Random rand = new Random();
        int n = rand.nextInt(7);
        n += 1;

        if (silkBag[n] > 0){

            int orientation = rand.nextInt(3);
            orientation += 1;

            switch (n) {
                case 0:
                    curTile = new CornerTile(orientation);
                    break;
                case 1:
                    curTile = new StraightTile(orientation);
                    break;
                case 2:
                    curTile = new TshapedTile(orientation);
                    break;
                case 3:
                    curTile = new GoalTile(orientation);
                    break;
                case 4:
                    curTile = new IceTile();
                    break;
                case 5:
                    curTile = new FireTile();
                    break;
                case 6:
                    curTile = new DoubleMoveTile();
                    break;
                case 7:
                    curTile = new BacktrackTile();
                    break;
                default:
                    System.out.println("Index error (out of range).");
            }
            silkBag[n] -= 1;
        }
        return curTile;
    }
    public static FloorTile[][] getTiles(){
        return tileList;
    }

    public static int[] getSilkBag(){
        return silkBag;
    }
    public static int getWidth(){
        return width;
    }

    public static int getHeight(){
        return height;
    }

    public static FloorTile getTile(int x, int y){
        return tileList[x][y];
    }
}