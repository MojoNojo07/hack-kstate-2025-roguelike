import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class Screen extends JFrame {

    public JLabel mainGameArea;
    public String board;
    public int characterX;
    public int characterY;
    InputHandler inputHandler;

    Screen(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(10, 10);
        this.setLayout(null);
        this.setVisible(true);
        this.setTitle("Kill the NecromanCEO");

        this.getContentPane().setBackground(Color.BLACK);

        inputHandler = new InputHandler(this);
        this.addKeyListener(inputHandler);


        characterX = 2;
        characterY = 3;

        board = "";
        this.updateBoard();
    }

    public void clearScreen() {  
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }  


    public void updateBoard() {
        board = "";
        int size = 5;
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if (j == characterX && i == characterY) {
                    board += "\u001B[32mG\u001B[0m";
                } else {
                    board += ".";
                }
            }
            board += "\n";
        }
    }
    
}
