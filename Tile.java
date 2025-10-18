public class Tile {
    private final char CHARACTER;

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
        this.CHARACTER = character;
        this.maxHp = hp;
        this.defense = defense;
    }

    /**
     * Getter method to get the tile's character
     * @return the tile's character
     */
    public char getCharacter() {
        return this.CHARACTER;
    }
}
