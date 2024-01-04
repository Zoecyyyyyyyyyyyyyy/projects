import javax.swing.*;
import java.awt.*;


enum Direction {
    UP, DOWN, LEFT, RIGHT
}

public class engine {
    public JFrame frame;
    public boolean wheFood = false;
    public int width;
    public int height;
    public int num;
    public snake s;
    public JButton[][] wholeButtons;
    public int foodW;
    public int foodH;
    public Food food;
    public boolean END = false;
    public Avater me;
    public double lastUpdateTime = 0.0;
    private Direction currentDirection = Direction.DOWN; // Default direction



    public engine(int width, int height, int num, int input1, int input2) {
        this.width = width;
        this.height = height;
        this.num = num;
        snake S = new snake(width, height, num);
        this.s = S;
        this.frame = S.frame;
        this.wholeButtons = S.wholeButtons;
        this.food = new Food(num, input1 ,input2);
        this.me = new Avater(s);

        frame.addKeyListener(me);
        frame.setFocusable(true);
        frame.requestFocusInWindow();

        Timer timer = new Timer(100, e -> {
            if (!wheFood) {
                int foodCol = food.addFoodWidth();
                foodW = foodCol;
                int foodRow = food.addFoodHeight();
                foodH = foodRow;
                wholeButtons[foodW][foodH].setBackground(Color.BLACK);
                wheFood = true;
            }

            checkEnd();

            currentDirection = me.currentDirection;
            me.move(currentDirection);

            wheFoodExist();
        });
        timer.start();
    }

    public void checkEnd() {
        END = me.END;
        if (END) {
            System.out.println("AAAAA");
            System.exit(0);
        }
    }

    public void wheFoodExist() {
        if (wholeButtons[foodW][foodH].getBackground().equals(Color.BLACK)) {
            wheFood = true;
        } else {
            wheFood = false;
        }
    }
}
