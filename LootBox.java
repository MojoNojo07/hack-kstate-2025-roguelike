import java.util.Random;

public class LootBox extends Tile {
    Random random;

    public LootBox(char character, String color, int hp, int defense, int x, int y) {
        super(character, color, hp, defense, x, y);
    }

    @Override
    public void slay() {
        Main.currentFloor.setTile(null, this.x, this.y);
        Main.currentFloor.actors.remove(this.id - 1);
        int lootId = random.nextInt(5 - 1 + 1) + 1;
        if (lootId < 3) {
            UserInterface.log("Loot box broken. Coffee inside!");
            Main.player.setHp(Main.player.getHp() + 5);
        }

        else if (lootId == 3) {
            UserInterface.log("Loot box broken. Coffee Mug inside!");
            Main.player.setMaxHp(Main.player.getMaxHp() + 2);
        }

        else if (lootId == 4) {
            UserInterface.log("Loot box broken. Suit inside!");
            Main.player.setDefense(Main.player.getDefense() + 1);
        }
    }

}
