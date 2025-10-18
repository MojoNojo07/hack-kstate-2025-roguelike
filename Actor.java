public class Actor extends Tile {
    public Actor(char character, String color, int hp, int defense, int x, int y) {
        super(character, color, hp, defense, x, y);
    }

    /**
     * Attempts to move the player character in the direction inputted. Stops from collision.
     * @param x how much to move in the x direction
     * @param y how much to move in the y direction
     */
    private void move(int x, int y) {
        for (int i = Math.abs(x); i > 0; i--) {
            if (Main.currentFloor.getTile(x + x / Math.abs(x), 0) == null) {
                Main.currentFloor.moveTile(x, y, x + x / Math.abs(x), 0);
            }
        }

        for (int i = Math.abs(y); i > 0; i--) {
            if (Main.currentFloor.getTile(0, y + y / Math.abs(y)) == null) {
                Main.currentFloor.moveTile(x, y, 0, y + y / Math.abs(y));
            }
        }
    }
}
