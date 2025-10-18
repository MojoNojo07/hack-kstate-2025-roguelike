public final class Constants {

    public static final int CHUNK_SIZE = 20;
    
    // Need to be divisible by 2
    public static final int MAP_VIEW_X = 36;
    public static final int MAP_VIEW_Y = 20;

    public static class Tiles {
        public static final int WOOD_WALL_HP = 5;
        public static final int WOOD_WALL_DEFENSE = 4;
        public static final String WOOD_WALL_COLOR = "\u001B[32m";
        public static final int CONCRETE_WALL_HP = 5;
        public static final int CONCRETE_WALL_DEFENSE = 9;
        public static final String CONCRETE_WALL_COLOR = "\u001B[32m";
        public static final int WINDOW_WALL_HP = 2;
        public static final int WINDOW_WALL_DEFENSE = 0;
        public static final String WINDOW_WALL_COLOR = "\u001B[32m";
    }

    public static class KeyBinds {
        // 87 is the W key
        public static final int MOVE_UP = 87;
        // 65 is the A key
        public static final int MOVE_LEFT = 65;
        // 83 is the S key
        public static final int MOVE_DOWN = 83;
        // 68 is the D key
        public static final int MOVE_RIGHT = 68;

        // 38 is the up arrow key
        public static final int ATK_UP = 38;
        //37 is the left arrow key
        public static final int ATK_LEFT = 37;
        // 40 is the down arrow key
        public static final int ATK_DOWN = 40;
        // 39 is the right arrow key
        public static final int ATK_RIGHT = 39;



    }

    public static final int LARGEST_ROOM_X = 30;
    public static final int SMALLEST_ROOM_X = 3;
    public static final int LARGEST_ROOM_Y = 20;
    // I believe this value must be at least 1 more than the x min
    public static final int SMALLEST_ROOM_Y = 4;

    public static final int MAX_ROOMS_TO_GENERATE = 4;
    public static final int MAX_CORRIDOR_LENGTH = 16;


    public static final String GOBLIN_COLOR = "\u001B[32m";


}
