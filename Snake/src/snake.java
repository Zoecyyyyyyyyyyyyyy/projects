import javax.swing.*;
import java.awt.*;

public class snake extends JPanel{
    public int gridNumber;
    public JFrame frame;
    public JButton[][] wholeButtons;
    public snake(int width, int height, int num) {
        gridNumber = num;
        JFrame frame = new JFrame("Viper Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel();

        JPanel panel2 = new JPanel();
        JButton[][] buttonGrid = new JButton[num][num];
        this.wholeButtons = buttonGrid;
        panel2.setLayout(new GridLayout(num,num));

        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                JButton button = new JButton();
                button.setBackground(Color.WHITE);
                buttonGrid[i][j] = button;
                panel2.add(button);
            }
        }

        frame.add(panel2);
        frame.setSize(width, height); // Set the size of the frame
        frame.setVisible(true);
        this.frame = frame;
    }


}
