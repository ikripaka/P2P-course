package com.shpp.p2p.cs.ikripaka.assignment3;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * This class paint a pyramid
 */
public class Assignment3Part4 extends WindowProgram {
    private static final int WIDTH_OF_THE_BRICK = 100;
    private static final int HEIGHT_OF_THE_BRICK = 60;
    private static final Color BRICK_COLOR = Color.GRAY;
    private static final Color BRICK_CONTOUR_COLOR = Color.BLACK;


    private static final int BRICKS_IN_THE_BASE = 10;


    @Override
    public void run() {
        double x, y;

        for (int i = 0; i < BRICKS_IN_THE_BASE; i++) {
            x = calculateXCoordinate(i);
            y = getHeight() - ((i+1) * HEIGHT_OF_THE_BRICK);

            for(int j = 0; j < BRICKS_IN_THE_BASE - i; j++){
                paintRectangle(x , y, WIDTH_OF_THE_BRICK, HEIGHT_OF_THE_BRICK);
                x = x + WIDTH_OF_THE_BRICK;
            }
        }
    }

    /**
     * It paints rectangle
     */
    private void paintRectangle(double x, double y, double width, double height) {
        GRect rectangle = new GRect(x, y, width, height);
        rectangle.setFilled(true);
        rectangle.setFillColor(BRICK_COLOR);
        rectangle.setColor(BRICK_CONTOUR_COLOR);
        add(rectangle);
    }

    /**
     * @return x coordinate at the beginning of the line
     */
    private double calculateXCoordinate( int i){
        if((BRICKS_IN_THE_BASE-i)%2 == 0){
            return (getWidth()-WIDTH_OF_THE_BRICK * (BRICKS_IN_THE_BASE - i))/2;
        }
        return (getWidth()-WIDTH_OF_THE_BRICK * (BRICKS_IN_THE_BASE - i))/2;
    }
}
