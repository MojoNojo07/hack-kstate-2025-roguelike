import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class InputHandler extends JFrame implements KeyListener {

    public JLabel mainGameArea;
    public String board;
    public Actor player;
    InputHandler inputHandler;

    InputHandler(Actor player){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(100, 100);
        this.setLayout(null);
        this.setVisible(true);
        this.setTitle("Kill the NecromanCEO");
        this.getContentPane().setBackground(Color.BLACK);
        this.addKeyListener(this);

        this.player = player;

        board = "";
        updateBoard();
    }

    public void clearScreen() {  
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }  


    public void updateBoard() {
        board = Main.currentFloor.getMapString();
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
        }

        // ASCII escape code, jumps to the next line
        // bc java can't clear the terminal. for some reason.
        System.out.print("\033[H\033[2J");
        updateBoard();
        System.out.println("\n===== KILL THE NECROMANCEO =====\n");
        System.out.println(board);
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