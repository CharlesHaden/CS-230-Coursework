import java.util.Random;

public class Board {

    private static FloorTile[][] tileList;
    private static int[] silkBag;
    private static int height;
    private static int width;
    private static Game curGame;
    private static int boardNumber;
    private FloorTile tempFloorTile;


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

    /**
     * insertTile is a method used to push new tiles onto the board, which in turn, shifts other tiles along, using for loops
     * @param x the x position from which the tile is to be inserted
     * @param y the y position from which the tile is to be inserted
     * @param horizontal a boolean specifying whether the tiles are to be shifted horizonally or vertically along the board
     * @return a boolean specifying whether or not the tile was successfully inserted, depending on fixed/frozen tiles
     */
    public static boolean insertTile(int x, int y, boolean horizontal) {
        //FloorTile tileToInsert = Game.getCurPlayer().getTempFloorTile();
        FloorTile tileToInsert = new CornerTile(2);
        FloorTile silkBagTile;
        silkBagTile = null;
        FloorTile[][] tempTileList = tileList;
        boolean frozenTileError = false;

        if (horizontal) {
            if (x == 0) {

                //inserting from left
                silkBagTile = tileList[width - 1][y];
                for (int i = 0; i < width; i++) {
                    if (tempTileList[width - (i + 1)][y].getIsFrozen() ||
                            tempTileList[width - (i + 1)][y].getFixed() == true){
                        frozenTileError = true;
                    } else {
                        if (width - (i + 1) == 0) {
                            tileList[0][y] = tileToInsert;
                        } else {
                            tileList[width - (i + 1)][y] = tileList[width - (i + 2)][y];
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
                for (int i = 0; i < height; i++) {
                    if (tempTileList[x][height - (i + 1)].getIsFrozen()
                            || tempTileList[x][height - (i + 1)].getFixed() == true) {
                        frozenTileError = true;
                    } else {
                        if (height - (i + 1) == 0) {

                            tileList[x][0] = tileToInsert;
                        } else {
                            tileList[x][height - (i + 1)] = tileList[x][height - (i + 2)];
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
    /**
     * This method is designed to check whether or not a row or a column can have tiles inserted into it
     * for the purposes of displaying insert buttons onto the screen.
     * @param x the x coordinate of the board corresponding to the button
     * @param y the x coordinate of the board corresponding to the button
     * @param horizontal a boolean specifying whether the tiles are to be shifted horizontally or vertically along the board
     * @return boolean specifying whether an insert button is to be displayed
     */
    public static boolean checkInsert(int x, int y, boolean horizontal) {
        boolean frozenTileError = false;
        FloorTile[][] tempTileList = tileList;
        if (horizontal) {
            if (x == 0) {
                //inserting from left
                for (int i = 1; i < width; i++) {
                    if (tempTileList[width - i][y].getIsFrozen() || tempTileList[width - i][y].getFixed() == true){
                        frozenTileError = true;
                    }
                }
            } else if (x == width - 1) {
                //inserting from right
                for (int i = 0; i < width; i++) {
                    if (tempTileList[i][y].getIsFrozen() || tempTileList[i][y].getFixed() == true) {
                        frozenTileError = true;
                    }
                }
            }
        } else {
            if (y == 0) {
                for (int i = 1; i < height; i++) {
                    if (tempTileList[x][height-i].getIsFrozen() || tempTileList[x][height-i].getFixed() == true) {
                        frozenTileError = true;
                    }
                }
            } else if (y == height - 1) {
                //inserting from below
                for (int i = 0; i < height; i++) {
                    if (tempTileList[x][i].getIsFrozen() || tempTileList[x][i].getFixed() == true) {
                        frozenTileError = true;
                    }
                }
            }
        }
        if (frozenTileError == false) { // gotta add this for fixed tiles too
            return true;
        }
        else return false;
    }
    /**
     * addToSilkBag inserts a new tile into silkBag, which has been pushed off the board by inserting a tile
     * @param silkBagTile The tile object to be added, which is then correlated to an increase in one of the array indexes of silkbag
     */
    public static void addToSilkBag(FloorTile silkBagTile) {
        String tileType = silkBagTile.getFloorTileType();

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
        if (silkBag[n] > 0) {

            int orientation = rand.nextInt(2);

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
                    System.out.println("Index error (out of range).   2");
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