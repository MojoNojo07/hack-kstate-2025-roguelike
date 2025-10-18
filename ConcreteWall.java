public class ConcreteWall extends Wall {
    /**
     * Creates a new ConcreteWall
     * @param x sets the x position of the object
     * @param y sets the x position of the object
     */
    public ConcreteWall(int x, int y) {
        super(' ', "grey", Constants.Tiles.CONCRETE_WALL_HP, Constants.Tiles.CONCRETE_WALL_DEFENSE, x, y, '█');
    }
}
