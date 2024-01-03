import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Queue;


public class Avater implements KeyListener {
    public int posX;
    public int posY;
    public JButton[][] wholeButtons;
    public int width;
    public int height;
    public boolean END = false;
    public int length = 1;
    public boolean addLength = false;
    public int endPosX;
    public int endPosY;
    public int endSecPosX;
    public int endSecPosY;
    public Queue<JButton> snakes;
    public Direction currentDirection = Direction.DOWN; // Default direction
    public int buttonWidth;
    public int input1;
    public int input2;


    public Avater(snake s) {
        this.posX = 0;
        this.posY = 0;
        this.endPosX = 0;
        this.endPosY = 0;
        this.endSecPosX = 0;
        this.endSecPosY = 0;
        this.wholeButtons = s.wholeButtons;
        this.width = wholeButtons.length;
        this.height = width;
        this.snakes = new LinkedList<>();
        this.buttonWidth = 11;
        snakes.add(wholeButtons[posX][posY]);
        wholeButtons[posX][posY].setBackground(Color.BLUE);
    }

    @Override
    public void keyPressed(KeyEvent e) {


    }

    public void move(Direction curr) {
        addLength = false;
        if (curr == Direction.LEFT) {
            if (canMove(posX, posY - 1)) {
                if (!addLength) {
                    wholeButtons[endPosX][endPosY].setBackground(Color.WHITE);
                    wholeButtons[posX][posY - 1].setBackground(Color.BLUE);
                    snakes.remove();
                    snakes.add(wholeButtons[posX][posY - 1]);
                    posY--;
                } else {
                    wholeButtons[endPosX][endPosY].setBackground(Color.BLUE);
                    wholeButtons[posX][posY - 1].setBackground(Color.BLUE);
                    snakes.add(wholeButtons[posX][posY - 1]);
                    posY--;
                    length++;
                }
            }
        } else if (curr == Direction.RIGHT) {
            if (canMove(posX, posY + 1)) {
                if (!addLength) {
                    wholeButtons[endPosX][endPosY].setBackground(Color.WHITE);
                    wholeButtons[posX][posY + 1].setBackground(Color.BLUE);
                    snakes.remove();
                    snakes.add(wholeButtons[posX][posY + 1]);
                    posY++;
                } else {
                    wholeButtons[endPosX][endPosY].setBackground(Color.BLUE);
                    wholeButtons[posX][posY + 1].setBackground(Color.BLUE);
                    snakes.add(wholeButtons[posX][posY + 1]);
                    posY++;
                    length++;
                }
            }
        } else if (curr == Direction.UP) {
            if (canMove(posX - 1, posY)) {
                if (!addLength) {
                    wholeButtons[endPosX][endPosY].setBackground(Color.WHITE);
                    wholeButtons[posX - 1][posY].setBackground(Color.BLUE);
                    snakes.remove();
                    snakes.add(wholeButtons[posX - 1][posY]);
                    posX--;
                } else {
                    wholeButtons[endPosX][endPosY].setBackground(Color.BLUE);
                    wholeButtons[posX - 1][posY].setBackground(Color.BLUE);
                    snakes.add(wholeButtons[posX - 1][posY]);
                    posX--;
                    length++;
                }
            }
        } else if (curr == Direction.DOWN) {
            if (canMove(posX + 1, posY)) {
                if (!addLength) {
                    wholeButtons[endPosX][endPosY].setBackground(Color.WHITE);
                    wholeButtons[posX + 1][posY].setBackground(Color.BLUE);
                    snakes.remove();
                    snakes.add(wholeButtons[posX + 1][posY]);
                    posX++;
                } else {
                    wholeButtons[endPosX][endPosY].setBackground(Color.BLUE);
                    wholeButtons[posX + 1][posY].setBackground(Color.BLUE);
                    snakes.add(wholeButtons[posX + 1][posY]);
                    posX++;
                    length++;
                }
            }
        }
        endPosY = (snakes.peek().getX() - 18) / buttonWidth;
        endPosX = (snakes.peek().getY() - 6) / buttonWidth;
    }

    private boolean canMove(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= width) {
            END = true;
            return false;
        }
        if (wholeButtons[x][y].getBackground().equals(Color.BLUE)) {
            END = true;
            return false;
        }
        if (wholeButtons[x][y].getBackground().equals(Color.BLACK)) {
            wholeButtons[x][y].setBackground(Color.BLUE);
            addLength = true;
            return true;
        } else {
            return true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Implementation for key release events, can be empty if not used
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'W':
                currentDirection = Direction.UP;
                break;
            case 'S':
                currentDirection = Direction.DOWN;
                break;
            case 'A':
                currentDirection = Direction.LEFT;
                break;
            case 'D':
                currentDirection = Direction.RIGHT;
                break;
        }
    }


}
