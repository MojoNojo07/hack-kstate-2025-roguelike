package Tiles;
public class Wall extends Tile {

    private char defaultCharacter;

    public Wall(char character, String color, int hp, int defense, int x, int y, char defaultCharacter) {
        super(character, color, hp, defense, x, y);
        this.defaultCharacter = defaultCharacter;
    }

    /**
     * Updates the wall to show as the default wall character
     */
    public void showWall() {
        this.setCharacter(this.defaultCharacter);
    }
    
}
