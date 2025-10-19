public class NecromanCEO extends Enemy {
    int necroticEnergy;
    int defensivePower;

    public NecromanCEO(char character, String color, int hp, int defense) {
        super(character, color, hp, character);
        this.turn = 0;
        this.necroticEnergy = 35;
        this.defensivePower = 8;
        this.defaultColor = color;
    }

    @Override
    public void slay() {
        UserInterface.log("NecromanCEO was slain! You win!!");
        Main.currentFloor.setTile(null, this.x, this.y);
        Main.currentFloor.enemies.remove(this.id);
    }

    @Override
    public void damage(int damage) {
        int totalDmg = Math.max(damage - this.defensivePower, 0);
        this.necroticEnergy -= totalDmg;

        if (this.necroticEnergy <= 0) {
            this.slay();
        }
    }

    @Override
    public void resolveAI() {
        Tile player = Main.currentFloor.actors.get(0);
        int diffX = Math.abs(this.x - player.getX());
        int diffY = Math.abs(this.y - player.getY());

        this.turn++;

        if (this.turn % 3 == 0) {
            this.setColor(this.defaultColor);

            if (diffX + diffY < 2) {
                attack(8, -1, -1);
                attack(8, 0, -1);
                attack(8, 1, -1);

                attack(8, -1, 0);
                attack(8, 1, 0);

                attack(8, 1, 1);
                attack(8, 0, 1);
                attack(8, -1, 1);
            }
            else if (diffX > diffY) {
                if (this.x < player.getX()) {
                this.move(1, 0);
                } else if (this.x > player.getX()) {
                    this.move(-1, 0);
                }
            } else {
                if (this.y < player.getY()) {
                    this.move(0, 1);
                } else if (this.y > player.getY()) {
                    this.move(0, -1);
                }
            }

        }

        if (this.turn % 3 == 2) {
            this.setColor("\u001B[107m" + this.defaultColor);
        }
    }
}
