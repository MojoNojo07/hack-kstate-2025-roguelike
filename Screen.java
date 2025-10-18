import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class Screen extends JFrame implements KeyListener {

    private JLabel mainGameArea;

    Screen(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(null);
        this.addKeyListener(this);
        this.setVisible(true);
        this.setTitle("Kill the NecromanCEO");

        this.getContentPane().setBackground(Color.BLACK);


        mainGameArea = new JLabel("true!!", SwingConstants.CENTER);
        mainGameArea.setSize(500, 500);
        mainGameArea.setForeground(Color.WHITE);
        mainGameArea.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 24));
        this.add(mainGameArea);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String message = "key " + e.getKeyCode() + " pressed";
        mainGameArea.setText("<html>" + message + "<br/>You sure did press that button!! </html>");
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
