import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class InputHandler extends JFrame implements KeyListener {

    public JLabel mainGameArea;
    public String board;
    public int characterX;
    public int characterY;
    InputHandler inputHandler;

    InputHandler(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(100, 100);
        this.setLayout(null);
        this.setVisible(true);
        this.setTitle("Kill the NecromanCEO");

        this.getContentPane().setBackground(Color.BLACK);
        this.addKeyListener(this);


        characterX = 0;
        characterY = 0;

        board = "";
        updateBoard();
    }

    public void clearScreen() {  
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }  


    public void updateBoard() {
        board = "";
        int sizeX = 20;
        int sizeY = 80;
        for(int i = 0; i < sizeX; i++) {
            for(int j = 0; j < sizeY; j++) {
                if (j == characterX && i == characterY) {
                    board += "\u001B[32mG\u001B[0m";
                } else {
                    board += ".";
                }
            }
            board += "\n";
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println("keycode " + keyCode);
        if (keyCode == 87) {
            this.characterY--;
        } else if (keyCode == 68) {
            this.characterX++;
        } else if (keyCode == 83) {
            this.characterY++;
        } else if (keyCode == 65) {
            this.characterX--;
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