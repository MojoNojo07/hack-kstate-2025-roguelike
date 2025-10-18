
class Main {
    public static final GridMap currentFloor = new GridMap(4, 4);
    public static Actor player = new Actor('G', "\u001B[32m", 10, 2, 3, 4);

    public static void main(String args[]) {
        
        currentFloor.setTile(player, player.getX(), player.getY());

        
        new InputHandler(player);
    }
}