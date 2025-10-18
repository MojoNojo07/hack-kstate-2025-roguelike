public class WindowWall extends Wall {
    /**
     * Creates a new WindowWall
     * @param x sets the x position of the object
     * @param y sets the x position of the object
     */
    public WindowWall(int x, int y) {
        super(' ', "light blue", Constants.Tiles.WINDOW_WALL_HP, Constants.Tiles.WINDOW_WALL_DEFENSE, x, y, '█');
    }
}
