public class Tile {
    private char character;
    private int maxHp;
    private int defense;
    private int hp;
    private String color;

    /**
     * Represents a tile on the map
     * @param character the single character that will show on the board
     * @param hp the hitpoints the tile has before it gets destroyed
     * @param defense subtracts from damage dealt to hp
     */
    public Tile(char character, String color, int hp, int defense) {
        this.character = character;
        this.maxHp = hp;
        this.defense = defense;
        this.color = color;
    }

    /**
     * Getter method to get the tile's character
     * @return the tile's character
     */
    public char getCharacter() {
        return this.character;
    }

    /**
     * Getter emthod to get the tile's color
     * @return the tile's color
     */
    public String getColor() {
        return this.color;
    }
}
