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

    public GridMap(int n, int m) {
        this.grid = new Tile[n][m][Constants.CHUNK_SIZE][Constants.CHUNK_SIZE];
        this.mapXMax = n * Constants.CHUNK_SIZE;
        this.mapYMax = m * Constants.CHUNK_SIZE;
        //TODO: add tile generation and such
    }

    public char[][] getMapUI() {

        char[][] mapUI = new char[Constants.MAP_VIEW_X][Constants.MAP_VIEW_Y];

        //TODO CHANGE THISSSSSSSSSSSSSSSSS
        int playerX = 0;
        int playerY = 0;
        // gets the top left corner of the viewport, making sure to clamp it to avoid OOB errors
        int startX = Math.min(playerX - Constants.MAP_VIEW_X / 2, mapXMax - Constants.CHUNK_SIZE - 1);
        int startY = Math.min(playerY - Constants.MAP_VIEW_Y / 2, mapYMax - Constants.CHUNK_SIZE - 1);

        for (int x = 0; x < Constants.MAP_VIEW_X; x++) {
            for (int y = 0; y < Constants.MAP_VIEW_Y; y++) {
                mapUI[x][y] = this.getTile(x, y).getCharacter();
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
    private Tile getTile(int x, int y) {
        int localX = x - x % Constants.CHUNK_SIZE;
        int localY = y - y % Constants.CHUNK_SIZE;
        int chunkX = (x - localX) / Constants.CHUNK_SIZE;
        int chunkY = (y - localY) / Constants.CHUNK_SIZE;

        return this.grid[chunkX][chunkY][localX][localY];
    }

}