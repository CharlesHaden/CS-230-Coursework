import java.util.Random;

/**
 * Board class, stores and controls the current board data as a 2D array of tiles, as well as the silk bag.
 * This class is responsible for providing getter and setter methods for the tile objects
 * at specified coordinates, as given by the array indexes of the 2 dimensional array.
 * It also must allow for tiles to be inserted onto the board which which will shift all other tiles
 * along the axis of the board, meaning the items in the array will be shifted along accordingly.
 * @author Laurence
 */

public class Board {
    private static FloorTile[][] tileList;
    private static int[] silkBag;
    private static int height;
    private static int width;
    private static Game curGame;
    private static int boardNumber;

    /**
     * Constructor for Board class, width and height are used as variables in the class as well as to create the 2D array of floor tiles
     * @param width Width of board,
     * @param height Height of board
     * @param bag Array of integers to represent the contents of the silk bag
     * @param boardNumber Specifies which board we are currently using
     */
    public Board(int width, int height, int[] bag, int boardNumber) {
        tileList = new FloorTile[width][height];
        silkBag = bag;
        this.height = height;
        this.width = width;
        this.boardNumber = boardNumber;
    }

    /**
     * Used to set a specified position in the 2D array to a new tile
     * @param curTile the specified FloorTile object
     * @param x the specified x position in the 2D array
     * @param y the specified y position in the 2D array
     */
    public static void setFloorTile(FloorTile curTile, int x, int y) {
        tileList[x][y] = curTile;
    }

    /**
     * buildBoard() fills in all of the null values in the 2D array, tileList, using data from silkBag
     */
    public static void buildBoard() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (tileList[i][j] == null) {
                    tileList[i][j] = selectFromSilkBag();

                }
            }
        }
    }

    /**
     * @return returns a FloorTile object by randomly selecting from silkBag, also specifies random orientation
     */
    private static FloorTile selectFromSilkBag() {
        FloorTile curTile;
        curTile = null;// because of switch statement
        Random rand = new Random();
        int n = rand.nextInt(3);


        if (silkBag[n] > 0) {
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
                default:
                    System.out.println("Index error (out of range).");
            }
            silkBag[n] -= 1;
        }
        return curTile;
    }

    /**
     * insertTile is a method used to push new tiles onto the board, which in turn, shifts other tiles along, using for loops
     * @param tileToInsert the specified FloorTile object to insert into the 2D array, tileList
     * @param x the x position from which the tile is to be inserted
     * @param y the y position from which the tile is to be inserted
     * @param horizontal a boolean specifying whether the tiles are to be shifted horizontally or vertically along the board
     * @return a boolean specifying whether or not the tile was successfully inserted, depending on fixed/frozen tiles
     */
    public static boolean insertTile(FloorTile tileToInsert, int x, int y, boolean horizontal) {
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
            } else if (x == width - 1) {
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
        } else {
            if (y == 0) {
                //inserting from above
                silkBagTile = tileList[x][height-1];
                for (int i = 1; i < height; i++) {
                    if (tempTileList[x][height-i].getIsFrozen() || tempTileList[x][height-i].getFixed() == true) {
                        frozenTileError = true;
                    } else {
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
    public static void addToSilkBag(Tile silkBagTile) {
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
            default:
                System.out.println("Index error (out of range).");
        }
    }

    /**
     * getTileFromSilkBag will return a random tile selected from silkBag, and then decrease the value of that index of silkBag by 1
     * @return returns a Tile object, can either be an action or a floor tile
     */
    public static Tile getTileFromSilkBag() {
        Tile curTile;
        curTile = null;// because of switch statement
        Random rand = new Random();
        int n = rand.nextInt(6);
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
                    curTile = new IceTile();
                    break;
                case 4:
                    curTile = new FireTile();
                    break;
                case 5:
                    curTile = new DoubleMoveTile();
                    break;
                case 6:
                    curTile = new BacktrackTile();
                    break;
                default:
                    System.out.println("Index error (out of range).");
            }
            silkBag[n] -= 1;
        }
        return curTile;
    }

    /**
     * Getter for the entire board
     * @return type 2D array of FloorTile objects
     */
    public static FloorTile[][] getTiles() {
        return tileList;
    }

    /**
     * getter for the silk bag array
     * @return type int array
     */
    public static int[] getSilkBag() {
        return silkBag;
    }


    /**
     * getter for the width of the board
     * @return type int
     */
    public static int getWidth() {
        return width;
    }


    /**
     * getter for the height of the board
     * @return type int
     */
    public static int getHeight() {
        return height;
    }

    /**
     * getter for the board number
     * @return type int
     */
    public static int getBoardNumber() {
        return boardNumber;
    }


    /**
     * getter for a tile specified by an x and y coordinate in tileList
     * @return type FloorTile object
     */
    public static FloorTile getTile(int x, int y) {
        return tileList[x][y];
    }
}