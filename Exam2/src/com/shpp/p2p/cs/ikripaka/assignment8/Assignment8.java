package com.shpp.p2p.cs.ikripaka.assignment8;

import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * The main class.
 * This program represents game.
 * Game description in file "TASK.txt"
 */
public class Assignment8 extends WindowProgram {
    public static final int APPLICATION_WIDTH = 600;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Separation between bricks
     */
    public static final int BRICK_SEP = 4;

    /**
     * Determines how many squares will be in one column
     */
    public static final int BRICKS_PER_ROW = 15;

    /**
     * Array that include all bricks inside it
     */
    public Brick[][] bricks = new Brick[BRICKS_PER_ROW][BRICKS_PER_ROW];

    public void init() {
        addMouseListeners();
    }

    public void run() {
        paintField();
        setRandomSeedsToBricks();
        for (; ; ) {
            pause(1000);
            // Goes through all array of bricks to move them
            for (int i = BRICKS_PER_ROW - 1; i >= 0; i--) {
                for (int j = 0; j < BRICKS_PER_ROW; j++) {
                    // if blue brick (water) that situated not bottom and lower brick grass(green) or seeds(black)
                    if (i != (BRICKS_PER_ROW - 1) && bricks[i][j].getBrickColor() == Color.BLUE
                            && bricks[i + 1][j].getBrickColor() != Color.WHITE) {

                        bricks[i + 1][j].setColor(Color.GREEN);
                        bricks[i + 1][j].setBrickColor(Color.GREEN);
                        bricks[i][j].setColor(Color.GREEN);
                        bricks[i][j].setBrickColor(Color.GREEN);

                        // if blue brick (water) situated in the bottom
                    } else if (i == (BRICKS_PER_ROW - 1) && bricks[i][j].getBrickColor() == Color.BLUE) {
                        bricks[i][j].setColor(Color.WHITE);
                        bricks[i][j].setBrickColor(Color.WHITE);

                        // if brick not white(air) move bricks
                    } else if (i != (BRICKS_PER_ROW - 1) && bricks[i][j].getBrickColor() != Color.WHITE
                            && bricks[i + 1][j].getBrickColor() != Color.BLACK
                            && bricks[i + 1][j].getBrickColor() != Color.GREEN) {

                        bricks[i + 1][j].setColor(bricks[i][j].getBrickColor());
                        bricks[i + 1][j].setBrickColor(bricks[i][j].getBrickColor());
                        bricks[i][j].setColor(Color.WHITE);
                        bricks[i][j].setBrickColor(Color.WHITE);
                    }

                }
            }
        }
    }

    /**
     * Set to bricks colour BLACK (Seeds)
     */
    private void setRandomSeedsToBricks() {
        RandomGenerator randomGenerator = new RandomGenerator();
        for (int i = 0; i < BRICKS_PER_ROW; i++) {
//            for (int j = randomGenerator.nextInt(0, BRICKS_PER_ROW - 1); j > 0; j--) {
                int randomCord = randomGenerator.nextInt(0, BRICKS_PER_ROW - 1);
                bricks[i][randomCord].setBrickColor(Color.BLACK);
                bricks[i][randomCord].setColor(Color.BLACK);
//            }
        }
    }

    /**
     * Paints field that consist of squares
     */
    private void paintField() {
        // one brick length
        int brickLength = (getHeight() - (BRICKS_PER_ROW + 1) * BRICK_SEP) / BRICKS_PER_ROW;
        // x coordinate for creating one brick
        int x1;
        // y coordinates for creating one brick
        int y1, y2 = 0;
        Brick brick;
        for (int i = 0; i < BRICKS_PER_ROW; i++) {
            y1 = y2 + BRICK_SEP;
            y2 = y1 + brickLength;

            for (int j = 0; j < BRICKS_PER_ROW; j++) {
                x1 = BRICK_SEP + brickLength * j + BRICK_SEP * j;
                brick = new Brick(x1, y1, brickLength, brickLength);
                brick.setFilled(true);
                brick.setColor(Color.WHITE);
                brick.setBrickColor(Color.WHITE);
                add(brick);
                bricks[i][j] = brick;
            }
        }
    }

    /**
     * When mouse pressed BLUE square (water) appears
     *
     * @param me
     */
    public void mousePressed(MouseEvent me) {
        Object obj = getElementAt(me.getX(), me.getY());
        if (obj != null) {
            ((Brick) obj).setBrickColor(Color.BLUE);
            ((Brick) obj).setColor(Color.BLUE);
        }
    }


}
