
public class Wall extends Tile {

    private char defaultCharacter;

    public Wall(char character, String color, int hp, int defense, char defaultCharacter) {
        super(character, color, hp, defense);
        this.defaultCharacter = defaultCharacter;
    }

    /**
     * Updates the wall to show as the default wall character
     */
    public void showWall() {
        this.setCharacter(this.defaultCharacter);
    }
    
}
