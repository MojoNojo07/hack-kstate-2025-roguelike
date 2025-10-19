import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class InputHandler extends JFrame implements KeyListener {

    public JLabel mainGameArea;
    public String board;
    public Actor player;
    public static String log;

    InputHandler(Actor player){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(100, 100);
        this.setLayout(null);
        this.setVisible(true);
        this.setTitle("Kill the NecromanCEO");
        this.getContentPane().setBackground(Color.BLACK);
        this.addKeyListener(this);

        this.player = player;

        this.board = "";
        this.log = "";
    }



    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println("keycode " + keyCode);
        if (keyCode == Constants.KeyBinds.MOVE_UP) {
            player.move(0, -1);
        } else if (keyCode == Constants.KeyBinds.MOVE_RIGHT) {
            player.move(1, 0);
        } else if (keyCode == Constants.KeyBinds.MOVE_DOWN) {
            player.move(0, 1);
        } else if (keyCode == Constants.KeyBinds.MOVE_LEFT) {
            player.move(-1, 0);
        } else if (keyCode == Constants.KeyBinds.ATK_UP) {
            player.attack(0, -1);
        } else if (keyCode == Constants.KeyBinds.ATK_RIGHT) {
            player.attack(1, 0);
        } else if (keyCode == Constants.KeyBinds.ATK_DOWN) {
            player.attack(0, 1);
        } else if (keyCode == Constants.KeyBinds.ATK_LEFT) {
            player.attack(-1, 0);

        // summon an office skeleton by pressing L, for testing
        } else if (keyCode == 76){
            Main.currentFloor.addEnemy(new Enemy('O', "\u001B[34m", 20, 5), player.getX() + 1, player.getY());

        // resolve enemy AI when the spacebar is pressed
        } else if (keyCode == 32) {
            for(int i = 0; i < Main.currentFloor.enemies.size(); i++) {
                Enemy enemy = Main.currentFloor.enemies.get(i);
                System.out.println("resolving " + enemy.getCharacter() + "'s AI at " + enemy.getX() + ", " + enemy.getY());
                enemy.resolveAI();
            }
        }

        UserInterface.updateUserInterface();
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