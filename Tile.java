public class Tile {
    private final char character;

    private int maxHp;
    private int defense;
    private int hp;

    /**
     * Represents a tile on the map
     * @param character the single character that will show on the board
     * @param hp the hitpoints the tile has before it gets destroyed
     * @param defense subtracts from damage dealt to hp
     */
    public Tile(char character, int hp, int defense) {
        this.character = character;
        this.maxHp = hp;
        this.defense = defense;
    }
}
