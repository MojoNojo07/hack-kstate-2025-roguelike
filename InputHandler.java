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

        // ASCII escape code, jumps to the next line
        // bc java can't clear the terminal. for some reason.
        System.out.print("\033[H\033[2J");
        screen.updateBoard();
        System.out.println("\n===== KILL THE NECROMANCEO =====\n");
        System.out.println(screen.board);
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