
public class Tile {
    private char character;
    private String color;
    private int maxHp;
    private int hp;
    private int defense;
    private int x;
    private int y;

    /**
     * Represents a tile on the map
     * @param character the single character that will show on the board
     * @param color the color of the tile
     * @param hp the hitpoints the tile has before it gets destroyed
     * @param defense subtracts from damage dealt to hp
     * @param x the x location of the tile
     * @param y the y location of the tile
     */
    public Tile(char character, String color, int hp, int defense, int x, int y) {
        this.character = character;
        this.color = color;
        this.maxHp = hp;
        this.defense = defense;
        this.x = x;
        this.y = y;
    }

    /**
     * Getter method to get the tile's character
     * @return the tile's character
     */
    public char getCharacter() {
        return this.character;
    }

    /**
     * Getter method to get the tile's color
     * @return the tile's color
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Getter method to get the tile's x coordinate
     * @return the tile's x coordinate
     */
    public int getX() {
        return this.x;
    }

    /**
     * Getter method to get the tile's y coordinate
     * @return the tile's y coordinate
     */
    public int getY() {
        return this.y;
    }

    /**
     * Setter method to set the tile's character
     * @param character the new character
     */
    public void setCharacter(char character) {
        this.character = character;
    }

    /**
     * Damages a tile (amount subtracted by defense)
     * @param damage the amount of damage to deal
     */
    public void damage(int damage) {
        this.hp -= Math.min(damage - defense, 0);
    }
}
