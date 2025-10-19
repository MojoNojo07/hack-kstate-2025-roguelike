public class NecromanCEO extends Enemy {
    public NecromanCEO(char character, String color, int hp, int defense, int x, int y) {
        super(character, color, hp, character, x, y);
    }

    @Override
    public void slay() {
        UserInterface.log("NecromanCEO was slain!");
        Main.currentFloor.setTile(null, this.x, this.y);
        Main.currentFloor.enemies.remove(this.id);
    }

}
