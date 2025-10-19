public class NecromanCEO extends Enemy {

    public NecromanCEO(char character, String color, int hp, int defense) {
        super(character, color, hp, character);
        this.turn = 0;
        this.defaultColor = color;
    }

    @Override
    public void slay() {
        UserInterface.log("NecromanCEO was slain!");
        Main.currentFloor.setTile(null, this.x, this.y);
        Main.currentFloor.enemies.remove(this.id);
    }

    @Override
    public void resolveAI() {
        Tile player = Main.currentFloor.actors.get(0);
        int diffX = Math.abs(this.x - player.getX());
        int diffY = Math.abs(this.y - player.getY());

        this.turn++;

        if (this.turn % 3 == 0) {
            this.setColor(this.defaultColor);

            if (diffX + diffY < 5) {
                if (Main.currentFloor.getTile(x, y - 3) == null) {
                    Main.currentFloor.addEnemy(new Enemy('Z', "\u001B[32m", 10, 2), x, y - 3);
                }
                
            } else {
                if (diffX > diffY) {
                    if (this.x < player.getX() - 3) {
                    this.move(1, 0);
                    } else if (this.x > player.getX() + 3) {
                        this.move(-1, 0);
                    }
                } else {
                    if (this.y < player.getY() - 3) {
                        this.move(0, 1);
                    } else if (this.y > player.getY() + 3) {
                        this.move(0, -1);
                    }
                }
            }
        }

        if (this.turn % 3 == 2) {
            this.setColor("\u001B[42m" + this.defaultColor);
        }
    }
}
