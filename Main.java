
class Main {
    public static final GridMap currentFloor = new GridMap(20, 20);

    public static void main(String args[]) {
        Actor player = new Actor('G', "\u001B[32m", 10, 2, 3, 4);
        currentFloor.setTile(player, player.getX(), player.getY());

        Tile wall = new Tile('X', "\u001B[40m", 10, 20, 10, 10);
        Actor officeSkeleton = new Actor('O', "\u001B[34m", 20, 5, 5, 5);

        currentFloor.setTile(wall, wall.getX(), wall.getY());
        currentFloor.setTile(wall, wall.getX()+1, wall.getY());
        currentFloor.setTile(wall, wall.getX(), wall.getY()+1);
        currentFloor.setTile(wall, wall.getX()+1, wall.getY()+1);

        currentFloor.setTile(officeSkeleton, officeSkeleton.getX(), officeSkeleton.getY());
        new InputHandler(player);
    }
}