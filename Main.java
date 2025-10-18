
class Main {
    public static final GridMap currentFloor = new GridMap(20, 20);

    public static void main(String args[]) {
        Actor player = new Actor('G', "\u001B[32m", 10, 2, 3, 4);
        currentFloor.setTile(player, player.getX(), player.getY());

        Tile wall = new Tile('X', "\u001B[9m", 10, 20);

        currentFloor.setTile(wall, 10, 10);
        currentFloor.setTile(wall, 11, 10);
        currentFloor.setTile(wall, 10, 11);
        currentFloor.setTile(wall, 11, 11);
        new InputHandler(player);
    }
}