import java.util.Random;

public class Food{
    public int num;
    public Random random1;
    public Random random2;


    public Food(int num, int input1, int input2) {
        random1 = new Random(input1);
        random2 = new Random(input2);
        this.num = num;
    }

    public int addFoodWidth() {
        int randomWidth = random1.nextInt(0, num);
        return randomWidth;
    }

    public int addFoodHeight() {
        int randomHeight = random2.nextInt(0, num);
        return randomHeight;
    }

}
