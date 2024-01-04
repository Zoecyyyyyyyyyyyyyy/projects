import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class focus implements KeyListener {

    public int input1 = 0;
    public int input2 = 0;
    public int input = 0;

    @Override
    public void keyTyped(KeyEvent e) {
        int a = e.getKeyChar() - '0';
        input = 10 * input + a;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (this.input1 == 0) {
                this.input1 = input;
            } else {
                this.input2 = input;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
