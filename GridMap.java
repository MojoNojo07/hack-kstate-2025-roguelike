import java.util.Arrays;
import java.lang.Math;
import java.util.Random;

/**
 * A grid for the level map
 * @param n the rows
 * @param m the columns
 */
public class GridMap {
    private Tile[][][][] grid;
    private int mapXMax;
    private int mapYMax;
    private Random random = new Random();

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

    public String getMapString() {
        String board = "";
        for(int y = 0; y < Constants.MAP_VIEW_Y; y++) {
            for(int x = 0; x < Constants.MAP_VIEW_X; x++) {
                Tile tile = this.getTile(x, y);
                if (tile == null) {
                    board += ".";
                } else {
                    board += tile.getCharacter();
                    System.out.println("found something at " + x + ", " + y);
                }
            }
            board += "\n";
        }
        return board;
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
        int localX = x % Constants.CHUNK_SIZE;
        int localY = y % Constants.CHUNK_SIZE;
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
        this.setTile(this.getTile(previousX, previousY), newX, newY);
        this.setTile(null, previousX, previousY);
    }

    private void generateMap() {

        // Sets all tiles to concrete walls.
        for (int i = 0; i < this.mapXMax; i++) {
            for (int j = 0; j < this.mapYMax; j++) {
                this.setTile(new Wall(' ', "grey", Constants.Tiles.CONCRETE_WALL_HP, Constants.Tiles.CONCRETE_WALL_DEFENSE, '█'), i, j);
            }
        }

        createRoom(1, 1);
        createRoom(1, this.mapYMax - 20);
        createRoom(this.mapYMax - 20, 1);
        createRoom(this.mapXMax - 20, this.mapYMax - 20);


    }

    /**
     * Creates a room.
     * @param x The starting top left x pos
     * @param y The starting top left y pos
     * @param n the width (inclusive)
     * @param m the height (inclusive)
     */
    private void createRoom(int x, int y, int n, int m) {
        n = Math.min(x + n, this.mapXMax - 2);
        m = Math.min(y + m, this.mapYMax - 2);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.setTile(null, i + x, j + y);
            }
        }
    }

    /**
     * Creates a room.
     * @param x The starting top left x pos
     * @param y The starting top left y pos
     */
    private void createRoom(int x, int y) {
        int n = random.nextInt(30 - 3 + 1) + 3;
        int m = Math.min(random.nextInt(n - 4 + 1) + 4, 20);

        n = Math.min(x + n, this.mapXMax - 2);
        m = Math.min(y + m, this.mapYMax - 2);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.setTile(null, i + x, j + y);
            }
        }
    }
}