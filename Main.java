
class Main {
    public static final GridMap currentFloor = new GridMap(20, 20);

    public static void main(String args[]) {
        Actor player = new Actor('G', "\u001B[32m", 10, 2, 3, 4);
        currentFloor.setTile(player, player.getX(), player.getY());
        new InputHandler(player);
    }
}