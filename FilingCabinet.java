import java.util.Random;

public class FilingCabinet extends Tile {
    Random random = new Random();

    public FilingCabinet(char character, String color, int hp, int defense, int x, int y) {
        super(character, color, hp, defense, x, y);
    }

    @Override
    public void slay() {
        Main.currentFloor.setTile(null, this.x, this.y);
        Main.currentFloor.actors.remove(this.id - 1);
        int lootId = random.nextInt(10 - 1 + 1);

        if (lootId < 1) {
            
            if (Main.playerDamage < 12) {
                UserInterface.log("File cabinet opened. Vorpal Fountain Pen inside! DMG up!");
                Main.playerDamage = 12;
            }
            else {
                UserInterface.log("File cabinet opened. Vorpal Fountain Pen inside! Weaker, discarded.");
            }
        }

        else if (lootId < 2) {
            if (Main.playerDamage < 15) {
                UserInterface.log("File cabinet opened. Stapler inside! DMG up!");
                Main.playerDamage = 15;
            }
            else {
                UserInterface.log("File cabinet opened. Stapler inside! Weaker, discarded.");
            }
        }

        else if (lootId < 3) {
            if (Main.playerDamage < 20) {
                UserInterface.log("File cabinet opened. Paper Cutter inside! DMG up!");
                Main.playerDamage = 20;
            }
            else {
                UserInterface.log("File cabinet opened. Paper Cutter inside! Already have one!");
            }
        }

        else {
            UserInterface.log("File cabinet opened. Empty!");
        }
    }
}
