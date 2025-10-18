import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

    Screen screen;

    InputHandler(Screen screen){
        this.screen = screen;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == 87) {
            screen.characterY--;
        } else if (keyCode == 68) {
            screen.characterX++;
        } else if (keyCode == 83) {
            screen.characterY++;
        } else if (keyCode == 65) {
            screen.characterX--;
        }
        String message = "key " + e.getKeyCode() + " pressed";
        screen.updateBoard();
        screen.mainGameArea.setText("<html>" + screen.board + "</html>");
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