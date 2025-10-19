public class UserInterface {
    private static String[] log = new String[Constants.LOG_DEPTH];

    /**
     * Updates the screen with new information
     */
    public static void updateUserInterface() {
        // ASCII escape code, jumps to the next line
        // bc java can't clear the terminal. for some reason.
        System.out.print("\033[H\033[2J");
        System.out.println("\n  ===== KILL THE NECROMANCEO =====\n");
        System.out.print("Coffee: \u001B[37m[\u001B[33m");
        for (int i = 0; i < Main.player.getHp(); i++) {System.out.print("▒");}
        System.out.println("\u001B[37m]\u001B[0m");
        System.out.println(Main.currentFloor.getMapUI());
        for (int i = 0; i < log.length; i++) {
            if (log[i] != null) {System.out.println(log[i]);}
        }
    }

    /**
     * Clears the screen
     */
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }  

    /**
     * Adds a new log to the ui log, flushing out old ones
     * @param text the new log to... log
     * @return the flushed value
     */
    public static String log(String text) {
        String flushedValue = log[log.length - 1];
        for (int i = log.length - 1; i > 0; i--) {
            log[i] = log[i - 1];
        }
        log[0] = text;
        return flushedValue;
    }

}
