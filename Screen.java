import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class Screen extends JFrame implements KeyListener {

    private JLabel mainGameArea;
    private String board;
    private int characterX;
    private int characterY;

    Screen(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(null);
        this.addKeyListener(this);
        this.setVisible(true);
        this.setTitle("Kill the NecromanCEO");

        this.getContentPane().setBackground(Color.BLACK);


        characterX = 2;
        characterY = 3;

        board = "";

        mainGameArea = new JLabel(board, SwingConstants.CENTER);
        mainGameArea.setSize(500, 500);
        mainGameArea.setForeground(Color.WHITE);
        mainGameArea.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 24));
        this.add(mainGameArea);
    }

    private void updateBoard() {
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

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == 87) {
            characterY--;
        } else if (keyCode == 68) {
            characterX++;
        } else if (keyCode == 83) {
            characterY++;
        } else if (keyCode == 65) {
            characterX--;
        }
        String message = "key " + e.getKeyCode() + " pressed";
        this.updateBoard();
        mainGameArea.setText("<html>" + board + "</html>");
        System.out.println(message);
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
