import javax.swing.*;


//import snake;
public class Main {
    public static int input1;
    public static int input2;
    public static Input in;
    public static void main(String[] args) {
        in = new Input(600, 600);

        Timer timer = new Timer(100, e -> {
            input1 = in.input1;
            input2 = in.input2;
            if (input1 != 0 && input2 != 0) {
                SwingUtilities.invokeLater(() -> {

                    engine start = new engine(600, 600, 50, input1, input2);
                });
                ((Timer)e.getSource()).stop(); // Stop the timer
            }
        });
        timer.start();


    }

}