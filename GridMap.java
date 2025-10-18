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
        // this.generateMap();
    }

    /**
     * Generates a string block as a ui for the world
     * @return the string
     */
    public String getMapUI() {

        // creates a new viewport ui
        String mapUI = "";

        // gets the top left corner of the viewport, making sure to clamp it to avoid OOB errors
        int startX = Math.max(Math.min(Main.player.getX() - Constants.MAP_VIEW_X / 2, mapXMax - Constants.MAP_VIEW_X), 0);
        int startY = Math.max(Math.min(Main.player.getY() - Constants.MAP_VIEW_Y / 2 - 2, mapYMax - Constants.MAP_VIEW_Y), 0);

        // for every tile in the map view add it to the UI map
        for (int y = startY; y < Constants.MAP_VIEW_Y + startY; y++) {
            for (int x = startX; x < Constants.MAP_VIEW_X + startX; x++) {
                if (this.getTile(x, y) == null) {
                    mapUI += ".";
                }
                else {
                    mapUI += this.getTile(x, y).getColor();
                    mapUI += this.getTile(x, y).getCharacter();
                    mapUI += "\u001B[0m";
                }
            }
            mapUI += "\n";
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
        if (tile instanceof Tile){
            if (tile.x < 0 || tile.y < 0) {
                tile.x = x;
                tile.y = y;
            }
        }
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
                this.setTile(new Wall('█', "grey", Constants.Tiles.CONCRETE_WALL_HP, Constants.Tiles.CONCRETE_WALL_DEFENSE, '█', i, j), i, j);
            }
        }

        generateRooms(1, 1, Constants.MAX_ROOMS_TO_GENERATE);
        generateRooms(1, this.mapYMax - 20, Constants.MAX_ROOMS_TO_GENERATE);
        generateRooms(this.mapYMax - 20, 1, Constants.MAX_ROOMS_TO_GENERATE);
        generateRooms(this.mapXMax - 20, this.mapYMax - 20, Constants.MAX_ROOMS_TO_GENERATE);

        // Places all wood and windowed walls.
        for (int i = 0; i < this.mapXMax; i++) {
            for (int j = 0; j < this.mapYMax; j++) {

                // Finds the total of walls nearby in a 3x3 grid.
                int total = 0;
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        if (this.getTile(k, l) instanceof Wall && (i != 0 || j != 0)) {
                            total++;
                        }
                    }
                }

                if (total <= 3) {
                    this.setTile(new Wall('░', "light blue", Constants.Tiles.WINDOW_WALL_HP, Constants.Tiles.WINDOW_WALL_DEFENSE, '░', i, j), i, j);
                }
                else if (total <= 7) {
                    this.setTile(new Wall('█', "beige", Constants.Tiles.WOOD_WALL_HP, Constants.Tiles.WOOD_WALL_DEFENSE, '█', i, j), i, j);
                }
            }
        }
    }

    /**
     * Creates a room.
     * @param x The starting top left x pos
     * @param y The starting top left y pos
     * @param n The width (inclusive)
     * @param m The height (inclusive)
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
     * Recursively generates new rooms for the map
     * @param x The starting top left x pos
     * @param y The starting top left y pos
     * @param iterations The number of iterations before it will stop
     */
    private void generateRooms(int x, int y, int iterations) {

        // Stops recursing if it's recursed too many times.
        if (iterations != 0) {

            // Generates random numbers for a new room
            int n = random.nextInt(Constants.LARGEST_ROOM_X - Constants.SMALLEST_ROOM_X + 1) + Constants.SMALLEST_ROOM_X;
            int m = random.nextInt(Constants.LARGEST_ROOM_Y - Constants.SMALLEST_ROOM_Y + 1) + Constants.SMALLEST_ROOM_Y;

            createRoom(x, y, n, m);

            // Grabs a random starting point from within the room
            int startingX = random.nextInt((x + n) - x + 1) + x;
            int startingY = random.nextInt((y + m) - y + 1) + y;

            int[] direction = {0, 0};
            
            // Determines where to go with the new corridor
            if (random.nextBoolean()) {
                if (startingX - x > x) {
                    direction[0] = 1;
                }
                else {
                    direction[0] = -1;
                }

                startingX = x + n;
            }
            else {
                if (startingY - y > y) {
                    direction[1] = 1;
                }
                else {
                    direction[1] = -1;
                }

                startingY = y + m;
            }

            // Creates corridors through the map, stops if it has found empty space
            for (int i = 0; i < Constants.MAX_CORRIDOR_LENGTH; i++) {

                // Breaks if it has found open space
                if (this.getTile(x, y) == null) {
                    break;
                }

                this.setTile(null, x, y);
                x += direction[0];
                y += direction[1];
            }

            // Generates a new room if the current position is not empty
            if (this.getTile(x, y) != null) {
                generateRooms(x, y, iterations - 1);
            }
        }
    }
}