import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class Screen extends JFrame {

    public JLabel mainGameArea;
    public String board;
    public int characterX;
    public int characterY;
    InputHandler inputHandler;

    Screen(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(null);
        this.setVisible(true);
        this.setTitle("Kill the NecromanCEO");

        this.getContentPane().setBackground(Color.BLACK);

        inputHandler = new InputHandler(this);
        this.addKeyListener(inputHandler);


        characterX = 2;
        characterY = 3;

        board = "";

        mainGameArea = new JLabel(board, SwingConstants.CENTER);
        mainGameArea.setSize(500, 500);
        mainGameArea.setForeground(Color.WHITE);
        mainGameArea.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 24));
        this.add(mainGameArea);
    }

    public void updateBoard() {
        board = "";
        int size = 5;
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if (j == characterX && i == characterY) {
                    board += "G";
                } else {
                    board += ".";
                }
            }
            board += "<br>";
        }
    }
    
}
