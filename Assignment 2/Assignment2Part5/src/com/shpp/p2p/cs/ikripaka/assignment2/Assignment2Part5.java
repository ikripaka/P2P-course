package com.shpp.p2p.cs.ikripaka.assignment2;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * This class paint jne more optical illusion which consist of rectangle of black squares
 */
public class Assignment2Part5 extends WindowProgram {

    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 6;
    private static final int NUMB_OF_VERTICAL_SPACING = NUM_ROWS-1;
    private static final int NUMB_OF_HORIZONTALLY_SPACING = NUM_COLS-1;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 40;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 10;

    /**
     * This program paint square optical illusion which consist of 30 squares (5*6).
     */
    public void run() {
        double x, y;

        /**
         * Paint ROW
         */
        for (int i = 0; i < NUM_ROWS; i++) {
            x = ((getWidth() - NUM_COLS * BOX_SIZE - NUMB_OF_HORIZONTALLY_SPACING * BOX_SPACING)/ 2);
            y = (getHeight() - NUM_ROWS * BOX_SIZE - NUMB_OF_VERTICAL_SPACING * BOX_SPACING )/ 2 + (BOX_SPACING + BOX_SIZE) * i;

            /**
             * Paint COLUMN
             */
            for (int j = 0; j < NUM_COLS; j++) {
                paintSquare(x, y);
                x = x + (BOX_SIZE + BOX_SPACING);
            }
        }
    }

    /**
     * This function paint 1 BLACK square
     * @param x - left x coordinate of the square
     * @param y - left y coordinate of the square
     */
    private void paintSquare(double x, double y) {
        GRect square = new GRect(x, y, BOX_SIZE, BOX_SIZE);
        square.setFilled(true);
        square.setFillColor(Color.BLACK);
        add(square);
    }
}