public class Actor extends Tile {

    private int x;
    private int y;

     /**
      * Creates a new actor object
      * @param x the x location of the tile
      * @param y the y location of the tile
      */
    public Actor(char character, String color, int hp, int defense, int x, int y) {
        super(character, color, hp, defense);
        this.x = x;
        this.y = y;
    }

    /**
     * Attempts to move the player character in the direction inputted. Stops from collision.
     * @param x how much to move in the x direction
     * @param y how much to move in the y direction
     */
    public void move(int x, int y) {
        if (Main.currentFloor.getTile(this.x + x, this.y) == null) {
            Main.currentFloor.moveTile(this.x, this.y, this.x + x, this.y);
            this.x += x;
        }

        if (Main.currentFloor.getTile(this.x, this.y + y) == null) {
            Main.currentFloor.moveTile(this.x, this.y, this.x, this.y + y);
            this.y += y;
        }

    }

    /**
     * Getter method to get the tile's x coordinate
     * @return the tile's x coordinate
     */
    public int getX() {
        return this.x;
    }

    /**
     * Getter method to get the tile's y coordinate
     * @return the tile's y coordinate
     */
    public int getY() {
        return this.y;
    }

    /**
     * Getter method to get the tile's x coordinate
     * @return the tile's x coordinate
     */
    public int getX() {
        return this.x;
    }

    /**
     * Getter method to get the tile's y coordinate
     * @return the tile's y coordinate
     */
    public int getY() {
        return this.y;
    }
}
