class Main {
    public static final GridMap currentFloor = new GridMap(3, 3);
    public static Actor player = new Actor('G', Constants.GOBLIN_COLOR, Constants.Player.STARTING_MAX_HP, Constants.Player.STARTING_DEFENSE, 2, 2);
    public static boolean isPlayerAlive = true;

    public static void main(String args[]) {
        currentFloor.addActor(player, 2, 2);
        
        new InputHandler(player);
        currentFloor.populateMap();
        UserInterface.updateUserInterface();
    }
}