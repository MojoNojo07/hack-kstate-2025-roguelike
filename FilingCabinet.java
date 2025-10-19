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
        int lootId = random.nextInt(11 - 1 + 1);

        if (lootId < 3) {
            
            if (Main.playerDamage < 12) {
                UserInterface.log("File cabinet opened. Vorpal Fountain Pen inside! DMG up!");
                Main.playerDamage = 12;
                Main.playerWeapon = "Vorpal Fountain Pen";
            }
            else if (Main.playerDamage == 12) {
                UserInterface.log("File cabinet opened. Vorpal Fountain Pen inside! Already have one!");
            }
            else {
                UserInterface.log("File cabinet opened. Vorpal Fountain Pen inside! Weaker, discarded.");
            }
        }

        else if (lootId < 5) {
            if (Main.playerDamage < 15) {
                UserInterface.log("File cabinet opened. Stapler inside! DMG up!");
                Main.playerDamage = 15;
                Main.playerWeapon = "Stapler";
            }
            else if (Main.playerDamage == 15) {
                UserInterface.log("File cabinet opened. Stapler inside! Already have one!");
            }
            else {
                UserInterface.log("File cabinet opened. Stapler inside! Weaker, discarded.");
            }
        }

        else if (lootId < 6) {
            if (Main.playerDamage < 20) {
                UserInterface.log("File cabinet opened. Paper Cutter inside! DMG up!");
                Main.playerDamage = 20;
                Main.playerWeapon = "Paper Cutter";
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
