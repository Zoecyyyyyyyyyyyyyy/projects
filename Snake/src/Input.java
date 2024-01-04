import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input extends JPanel {
    public int width;
    public int height;
    public int input1 = 0;
    public int input2 = 0;
    public Input(int width, int height) {
        this.width = width;
        this.height = height;
        JFrame frame = new JFrame("VVV");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(width, height); // Set the size of this panel
        frame.add(this); // Add this panel to the frame

        frame.setSize(width, height); // Adjusts frame to fit the preferred size and layouts
        frame.setVisible(true);

        focus fo = new focus();
        frame.addKeyListener(fo);
        frame.setFocusable(true);
        frame.requestFocusInWindow();

        Timer timer = new Timer(100, e -> {
            input1 = fo.input1;
            input2 = fo.input2;

            if (input1 != 0 && input2 != 0) {
                frame.dispose();
//                this.setVisible(false);
                ((Timer)e.getSource()).stop(); // Stop the timer
            }
        });
        timer.start();
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Please input two different integers between 0 (exclusive) and 9 and press enter after each typing of num.", 5, height / 3);
    }


    public static void main(String[] args) {
        Input in = new Input(600, 600);
    }
}
