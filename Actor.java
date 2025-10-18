public class Actor extends Tile {

    

     /**
      * Creates a new actor object
      * @param x the x location of the tile
      * @param y the y location of the tile
      */
    public Actor(char character, String color, int hp, int defense, int x, int y) {
        super(character, color, hp, defense, x, y);
    }

    /**
     * Attempts to move the player character in the direction inputted. Stops from collision.
     * @param x how much to move in the x direction
     * @param y how much to move in the y direction
     */
    public void move(int x, int y) {
        if (Main.currentFloor.getTile(this.x + x, this.y + y) == null) {
            Main.currentFloor.moveTile(this.x, this.y, this.x + x, this.y + y);
            this.x += x;
            this.y += y;
        }

    }

    public void attack(int relX, int relY) {
        Tile target = Main.currentFloor.getTile(this.x + relX, this.y + relY);

        if (target != null) {
            target.damage(15);
        }
    }
}
