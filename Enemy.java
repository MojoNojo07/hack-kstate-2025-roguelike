public class Enemy extends Actor {
    private int turn;
    private String defaultColor;

    public Enemy(char character, String color, int hp, int defense, int x, int y) {
        super(character, color, hp, defense, x, y);
        this.turn = 0;
        this.defaultColor = color;
    }

    public Enemy(char character, String color, int hp, int defense) {
        super(character, color, hp, defense, -1, -1);
        this.id = Main.currentFloor.enemies.size();
        this.defaultColor = color;
    }

    @Override
    public void slay() {
        System.out.println("Enemy was slain!");
        Main.currentFloor.setTile(null, this.x, this.y);
        Main.currentFloor.enemies.remove(this.id);
    }

    public void resolveAI() {
        Tile player = Main.currentFloor.actors.get(0);
        int diffX = Math.abs(this.x - player.getX());
        int diffY = Math.abs(this.y - player.getY());

        this.turn++;

        if (this.turn % 3 == 0) {
            this.setColor(this.defaultColor);

            if (diffX + diffY < 2) {
                attack(5, 1, 0);
                attack(5, -1, 0);
                attack(5, 0, 1);
                attack(5, 0, -1);
    
                
            } else {
                if (diffX > diffY) {
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
        }

        if (this.turn % 3 == 2) {
            this.setColor("\u001B[47m" + this.defaultColor);
        }
    }
}
