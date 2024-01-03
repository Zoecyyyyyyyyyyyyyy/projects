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



    public engine(int width, int height, int num) {
        this.width = width;
        this.height = height;
        this.num = num;
        snake S = new snake(width, height, num);
        this.s = S;
        this.frame = S.frame;
        this.wholeButtons = S.wholeButtons;
        this.food = new Food(num, 996 ,666);
        this.me = new Avater(s);

        frame.addKeyListener(me);
        frame.setFocusable(true);
        frame.requestFocusInWindow();

        while (true) {
            if (!wheFood) {
                int foodCol = food.addFoodWidth();
                foodW = foodCol;
                int foodRow = food.addFoodHeight();
                foodH = foodRow;
                wholeButtons[foodW][foodH].setBackground(Color.BLACK);
                wheFood = true;
            }

            checkEnd();
            double currentTime = System.currentTimeMillis();
            if (currentTime - lastUpdateTime > 100) {
                currentDirection = me.currentDirection;
                me.move(currentDirection);
                lastUpdateTime = currentTime;
            }

            wheFoodExist();
        }
    }

    public void checkEnd() {
        END = me.END;
        if (END) {
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
