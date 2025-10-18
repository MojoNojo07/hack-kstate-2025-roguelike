public class WoodWall extends Wall {
    /**
     * Creates a new WoodWall
     * @param x sets the x position of the object
     * @param y sets the x position of the object
     */
    public WoodWall(int x, int y) {
        super(' ', "brown", Constants.Tiles.WOOD_WALL_HP, Constants.Tiles.WOOD_WALL_DEFENSE, x, y, '█');
    }
}
