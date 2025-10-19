import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ConcurrentModificationException;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class InputHandler extends JFrame implements KeyListener {

    public JLabel mainGameArea;
    public String board;
    public Actor player;
    public static String log;

    InputHandler(Actor player){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(200, 200);
        this.setLayout(null);
        this.setVisible(true);
        this.setTitle("Office Goblin");
        this.getContentPane().setBackground(Color.BLACK);
        this.addKeyListener(this);

        this.player = player;

        this.board = "";
        log = "";
    }



    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        
        if (Main.isPlayerAlive) {
            if (keyCode == Constants.KeyBinds.MOVE_UP) {
                player.move(0, -1);
            } else if (keyCode == Constants.KeyBinds.MOVE_RIGHT) {
                player.move(1, 0);
            } else if (keyCode == Constants.KeyBinds.MOVE_DOWN) {
                player.move(0, 1);
            } else if (keyCode == Constants.KeyBinds.MOVE_LEFT) {
                player.move(-1, 0);
            } else if (keyCode == Constants.KeyBinds.ATK_UP) {
                player.attack(10, 0, -1);
            } else if (keyCode == Constants.KeyBinds.ATK_RIGHT) {
                player.attack(10, 1, 0);
            } else if (keyCode == Constants.KeyBinds.ATK_DOWN) {
                player.attack(10, 0, 1);
            } else if (keyCode == Constants.KeyBinds.ATK_LEFT) {
                player.attack(10, -1, 0);
        }
        // summon an office skeleton by pressing L, for testing
        } else if (keyCode == 76){
            Main.currentFloor.addEnemy(new Enemy('O', "\u001B[34m", 20, 5), player.getX() + 1, player.getY());

        // resolve enemy AI when the spacebar is pressed
        } else if (keyCode == 32) {
            
        }
        
        boolean zombieTime = false;
        int necroX = 0;
        int necroY = 0;
        for (int i : Main.currentFloor.enemies.keySet()) {
            try {
                Enemy enemy = Main.currentFloor.enemies.get(i);
                if (enemy instanceof NecromanCEO) {
                    if (enemy.getTurn() % 6 == 1) {
                        zombieTime = true;
                        necroX = enemy.getX();
                        necroY = enemy.getY();
                    }
                    enemy.resolveAI();
                } else if (enemy != null) {
                    enemy.resolveAI();
                }
            } catch (ConcurrentModificationException m) {
                UserInterface.log("Several enemies slain at once, crowd left stunned");
            }
        }

        if(zombieTime && Main.currentFloor.enemies.size() < 20) {
            if (Main.currentFloor.getTile(necroX, necroY - 3) == null) {
                    Main.currentFloor.addEnemy(new Enemy('Z', "\u001B[32m", 10, 2), necroX, necroY - 3);
                    zombieTime = false;
            } else if (Main.currentFloor.getTile(necroX, necroY + 3) == null) {
                Main.currentFloor.addEnemy(new Enemy('Z', "\u001B[32m", 10, 2), necroX, necroY + 3);
            }
        }

        UserInterface.updateUserInterface();
        if (Main.player.getHp() <= 0) {
            Main.isPlayerAlive = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {  
        // nothin 
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // nothin
    }
}