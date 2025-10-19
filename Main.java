
class Main {
    public static final GridMap currentFloor = new GridMap(3, 3);
    public static Actor player = new Actor('G', Constants.GOBLIN_COLOR, 10, 2, 2, 2);

    public static void main(String args[]) {
        currentFloor.addActor(player, 2, 2);

        Tile wall = new Tile('X', "\u001B[40m", 10, 20, 10, 10);

        currentFloor.setTile(wall, wall.getX(), wall.getY());
        currentFloor.setTile(wall, wall.getX()+1, wall.getY());
        currentFloor.setTile(wall, wall.getX(), wall.getY()+1);
        currentFloor.setTile(wall, wall.getX()+1, wall.getY()+1);
        new InputHandler(player);
    }
}