public class Actor extends Tile {

    

     /**
      * Creates a new actor object
      * @param x the x location of the tile
      * @param y the y location of the tile
      */
    public Actor(char character, String color, int hp, int defense, int x, int y) {
        super(character, color, hp, defense, x, y);
    }

    public Actor(char character, String color, int hp, int defense) {
        super(character, color, hp, defense, -1, -1);
    }

    /**
     * Attempts to move the player character in the direction inputted. Stops from collision.
     * @param x how much to move in the x direction
     * @param y how much to move in the y direction
     */
    public void move(int x, int y) {
        int newX = this.x + x;
        int newY = this.y + y;
        
        if (!(newX != -1 && newX != Main.currentFloor.getMapWidth() && newY != -1 && newY != Main.currentFloor.getMapHeight())) {
            this.slay();
            UserInterface.updateUserInterface();
        }
        else {
            if (Main.currentFloor.getTile(this.x + x, this.y + y) == null) {
                Main.currentFloor.moveTile(this.x, this.y, newX, newY);
                this.x = newX;
                this.y = newY;
            }
        }

    }

    public void attack(int damage, int relX, int relY) {
        if (relX + x != -1 && relX + x != Main.currentFloor.getMapWidth() && relY + y != -1 && relY + y != Main.currentFloor.getMapHeight()) {
            Tile target = Main.currentFloor.getTile(this.x + relX, this.y + relY);

            if (target != null) {
                target.damage(damage);
            }
        }
    }
}
