
public class Wall extends Tile {

    private char defaultCharacter;

    public Wall(char character, String color, int hp, int defense, char defaultCharacter, int x, int y) {
        super(character, color, hp, defense, x, y);
        this.defaultCharacter = defaultCharacter;
    }

    /**
     * Updates the wall to show as the default wall character
     */
    public void showWall() {
        this.setCharacter(this.defaultCharacter);
    }

    @Override
    public void slay() {
        Main.currentFloor.setTile(null, this.x, this.y);
        Main.currentFloor.actors.remove(this.id - 1);
    }
    
}
