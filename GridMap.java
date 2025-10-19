import java.lang.Math;
import java.util.HashMap;
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
    public HashMap<Integer, Actor> actors; 
    public HashMap<Integer, Enemy> enemies; 
    private Random random = new Random();

    /**
     * Constructor method for GridMap
     * @param n the width of the chunks
     * @param m the height of the chunks
     */
    public GridMap(int n, int m) {
        this.grid = new Tile[m][n][Constants.CHUNK_SIZE][Constants.CHUNK_SIZE];
        this.mapXMax = m * Constants.CHUNK_SIZE;
        this.mapYMax = n * Constants.CHUNK_SIZE;
        this.generateMap();
        this.actors = new HashMap<Integer, Actor>();
        this.enemies = new HashMap<Integer, Enemy>();
        // this.generateMap();
    }

    public void addActor(Actor actor, int x, int y) {
        if(Main.currentFloor.getTile(x, y) == null) {
            int id = actors.size();
            actor.id = id;
            this.actors.put(id, actor);
            setTile(actor, x, y);
            System.out.println("Created actor at " + x + ", " + y + " with ID " + id);
        } else {
            System.out.println("Cannot create actor at " + x + ", " + y + "; tile already populated");
        }
    }

    public void addEnemy(Enemy enemy, int x, int y) {
        if(Main.currentFloor.getTile(x, y) == null) {
            int id = enemies.size();
            enemy.id = id;
            this.enemies.put(id, enemy);
            setTile(enemy, x, y);
            System.out.println("Created enemy at " + x + ", " + y + " with ID " + id);
        } else {
            System.out.println("Cannot create enemy at " + x + ", " + y + "; tile already populated");
        }
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

    public int getMapWidth() {
        return this.mapXMax;
    }

    public int getMapHeight() {
        return this.mapYMax;
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
                this.setTile(new Wall('█', Constants.Tiles.CONCRETE_WALL_COLOR, Constants.Tiles.CONCRETE_WALL_HP, Constants.Tiles.CONCRETE_WALL_DEFENSE, '█', i, j), i, j);
            }
        }

        generateRooms(1, 1, Constants.MAX_ROOMS_TO_GENERATE);
        generateRooms(1, this.mapYMax - 10, Constants.MAX_ROOMS_TO_GENERATE);
        generateRooms(this.mapXMax - 10, 1, Constants.MAX_ROOMS_TO_GENERATE);
        generateRooms(this.mapXMax - 10, this.mapYMax - 10, Constants.MAX_ROOMS_TO_GENERATE);

        // Places all wood and windowed walls.
        for (int i = 0; i < this.mapXMax; i++) {
            for (int j = 0; j < this.mapYMax; j++) {


                if (this.getTile(i, j) != null) {

                    // Finds the total of walls nearby in a 3x3 grid.
                    int total = 0;
                    for (int k = i - 1; k <= i + 1; k++) {
                        for (int l = j - 1; l <= j + 1; l++) {
                            if (k != -1 && k != this.mapXMax && l != -1 && l != this.mapYMax) {
                                if (this.getTile(k, l) instanceof Wall && (i != 0 || j != 0)) {
                                    total++;
                                }
                            }
                        }
                    }

                    if (total <= 3) {
                        this.setTile(new Wall('░', Constants.Tiles.WINDOW_WALL_COLOR, Constants.Tiles.WINDOW_WALL_HP, Constants.Tiles.WINDOW_WALL_DEFENSE, '░', i, j), i, j);
                    }
                    else if (total <= 8) {
                        this.setTile(new Wall('█', Constants.Tiles.WOOD_WALL_COLOR, Constants.Tiles.WOOD_WALL_HP, Constants.Tiles.WOOD_WALL_DEFENSE, '█', i, j), i, j);
                    }
                }
            }
        }
    }

    /**
     * Creates a room.
     * @param x The starting top left x pos
     * @param y The starting top left y pos
     * @param width The width (inclusive)
     * @param height The height (inclusive)
     */
    private void createRoom(int x, int y, int width, int height) {
        width = Math.min(x + width, this.mapXMax - 1);
        height = Math.min(y + height, this.mapYMax - 1);

        for (int i = x; i < width; i++) {
            for (int j = y; j < height; j++) {
                this.setTile(null, i, j);
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
            int width = random.nextInt(Constants.LARGEST_ROOM_X - Constants.SMALLEST_ROOM_X + 1) + Constants.SMALLEST_ROOM_X;
            int height = random.nextInt(Math.max(Constants.LARGEST_ROOM_Y, width) - Constants.SMALLEST_ROOM_Y + 1) + Constants.SMALLEST_ROOM_Y;
            width = Math.min(width, this.mapXMax - x - 1);
            height = Math.min(height, this.mapYMax - y - 1);

            // Reduces room size to accomadate other rooms
            while (this.getTile(width + x, height + y) == null || this.getTile(width + x, 0) == null || this.getTile(0, height + y) == null) {
                width -= 1;
                height -= 1;

                if (width <= 0 || height <= 0) {
                    width = 4;
                    height = 4;
                    break;
                }
            }

            if (width > 0 && height > 0) {
                createRoom(x, y, width, height);
            }

            // Grabs a random starting point from within the room
            int startingX = random.nextInt((x + width) - x + 1) + x;
            int startingY = random.nextInt((y + height) - y + 1) + y;

            int[] direction = {0, 0};
            
            // Determines where to go with the new corridor
            if (random.nextBoolean()) {
                if (startingX < this.mapXMax / 2) {
                    direction[0] = 1;
                    startingX = x + width;
                }
                else {
                    direction[0] = -1;
                    startingX = x;
                }
            }
            else {
                if (startingY < this.mapYMax / 2) {
                    direction[1] = 1;
                    startingY = y + height;
                }
                else {
                    direction[1] = -1;
                    startingY = y;
                }
            }

            x = startingX;
            y = startingY;

            // Creates corridors through the map, stops if it has found empty space
            for (int i = 0; i < Constants.MAX_CORRIDOR_LENGTH; i++) {

                if (!(x <= -1 || x >= this.mapXMax || y <= -1 || y >= this.mapYMax)) {
                    this.setTile(null, x, y);
                    x += direction[0];
                    y += direction[1];
                }

                if (!(x <= -1 || x >= this.mapXMax || y <= -1 || y >= this.mapYMax)) {
                    // Breaks if it has found open space
                    if (this.getTile(x, y) == null) {
                        break;
                    }
                }
            }

            // Generates a new room if the current position is not empty
            if (!(x <= -1 || x >= this.mapXMax || y <= -1 || y >= this.mapYMax)) {
                if (this.getTile(x, y) != null) {
                    generateRooms(x, y, iterations - 1);
                }
            }
        }
    }

    public void populateMap() {
        // Places all enemy units
        for (int i = 0; i < this.mapXMax; i++) {
            for (int j = 0; j < this.mapYMax; j++) {
                if (this.getTile(i, j) == null) {
                    if (random.nextInt(100 - 1 + 1) + 1 == 5) {
                        this.addEnemy(new Enemy('O', "\u001B[34m", 20, 5), i, j);
                    }
                }
                if (this.getTile(i, j) == null) {
                    if (random.nextInt(200 - 1 + 1) + 1 == 5) {
                        this.setTile(new LootBox('D', "\u001B[31m\u001B[43m", 1, 1, i, j), i, j);
                    }
                }
                if (this.getTile(i, j) == null) {
                    if (random.nextInt(100 - 1 + 1) + 1 == 5) {
                        this.setTile(new FilingCabinet('F', "\u001B[47m\u001B[31m", 1, 1, i, j), i, j);
                    }
                }
            }
        }

        this.addEnemy(new NecromanCEO('N', "\u001B[37m", 35, 5), mapXMax - 10, mapYMax - 10);
    }
}