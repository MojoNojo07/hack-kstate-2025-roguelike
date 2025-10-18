import java.util.Arrays;
import java.lang.Math;

/**
 * A grid for the level map
 * @param n the rows
 * @param m the columns
 */
public class GridMap {
    private Tile[][][][] grid;
    private int mapXMax;
    private int mapYMax;

    /**
     * Constructor method for GridMap
     * @param n the width of the chunks
     * @param m the height of the chunks
     */
    public GridMap(int n, int m) {
        this.grid = new Tile[n][m][Constants.CHUNK_SIZE][Constants.CHUNK_SIZE];
        this.mapXMax = n * Constants.CHUNK_SIZE;
        this.mapYMax = m * Constants.CHUNK_SIZE;
        //TODO: add tile generation and such
    }

    public Tile[][] getMapUI() {

        // creates a new viewport ui
        Tile[][] mapUI = new Tile[Constants.MAP_VIEW_X][Constants.MAP_VIEW_Y];

        //TODO CHANGE THISSSSSSSSSSSSSSSSS
        int playerX = 0;
        int playerY = 0;
        // gets the top left corner of the viewport, making sure to clamp it to avoid OOB errors
        int startX = Math.min(playerX - Constants.MAP_VIEW_X / 2, mapXMax - Constants.CHUNK_SIZE - 2);
        int startY = Math.min(playerY - Constants.MAP_VIEW_Y / 2, mapYMax - Constants.CHUNK_SIZE - 2);

        // for every tile in the map view add it to the UI map
        for (int x = 0; x < Constants.MAP_VIEW_X; x++) {
            for (int y = 0; y < Constants.MAP_VIEW_Y; y++) {
                // gets the tile at the position and adds its character to the ui map
                mapUI[x][y] = this.getTile(x, y);
            }
        }

        return mapUI;
    }

    /**
     * Returns a tile at the global coords
     * @param x global coord x
     * @param y global coord y
     * @return the tile at the given location
     */
    public Tile getTile(int x, int y) {
        
        int[] coordinates = this.getTileLocation(x, y);
        return this.grid[coordinates[0]][coordinates[1]][coordinates[2]][coordinates[3]];
    }

    /**
     * Private method to get the local coordinate location of a tile
     * @param x glocal coord x
     * @param y global coord y
     * @return an int array, with the following coordinates: chunk x, chunk y, local x, local y (in order)
     */
    private int[] getTileLocation(int x, int y) {
        int localX = x - x % Constants.CHUNK_SIZE;
        int localY = y - y % Constants.CHUNK_SIZE;
        int chunkX = (x - localX) / Constants.CHUNK_SIZE;
        int chunkY = (y - localY) / Constants.CHUNK_SIZE;

        int[] coordinates = {chunkX, chunkY, localX, localY};
        return coordinates;
    }

    /**
     * Sets a tile at a specified location
     * @param tile the new tile
     * @param x glocal coord x
     * @param y global coord y
     */
    public void setTile(Tile tile, int x, int y) {
        int[] coordinates = this.getTileLocation(x, y);
        this.grid[coordinates[0]][coordinates[1]][coordinates[2]][coordinates[3]] = tile;
    }
    
    /**
     * Moves a tile into a new location, leaving old tile null
     * @param previousX
     * @param previousY
     * @param newX
     * @param newY
     */
    public void moveTile(int previousX, int previousY, int newX, int newY) {
        int[] newCoordinates = this.getTileLocation(newX, newY);
        int[] previousCoordinates = this.getTileLocation(previousX, previousY);

        grid[newCoordinates[0]][newCoordinates[1]][newCoordinates[2]][newCoordinates[3]] = grid[previousCoordinates[0]][previousCoordinates[1]][previousCoordinates[2]][previousCoordinates[3]];
        grid[previousCoordinates[0]][previousCoordinates[1]][previousCoordinates[2]][previousCoordinates[3]] = null;
    }
}