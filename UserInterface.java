public class UserInterface {
    private static String[] log = new String[5];

    /**
     * Updates the screen with new information
     */
    public static void updateUserInterface() {
        // ASCII escape code, jumps to the next line
        // bc java can't clear the terminal. for some reason.
        System.out.print("\033[H\033[2J");
        System.out.println("\n  ===== KILL THE NECROMANCEO =====\n");
        System.out.print("Coffee: \u001B[37m[\u001B[33m");
        for (int i = 0; i < Main.player.getHp(); i++) {System.out.print("▓");}
        System.out.println("\u001B[37m]\u001B[0m");
        System.out.println(Main.currentFloor.getMapUI());
        System.out.println(log);
    }

    /**
     * Clears the screen
     */
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }  


}
