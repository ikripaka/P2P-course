package com.shpp.p2p.cs.ikripaka.assignment2;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * This program paint first illusion ( 4 ovals, 1 rectangle )
 */
public class Assignment2Part2 extends WindowProgram {

    private static final int APPLICATION_WIDTH = 300;
    private static final int APPLICATION_HEIGHT = 300;
    private static final double DIAMETR = 80.0;
    private static final double HALF_OF_THE_DIAMETR = DIAMETR/2;

    public void run() {
        createCircle(0, 0);
        createCircle(getWidth() - DIAMETR, 0);
        createCircle(0, getHeight() - DIAMETR);
        createCircle(getWidth() - DIAMETR, getHeight() - DIAMETR);
        createRectangle(HALF_OF_THE_DIAMETR, HALF_OF_THE_DIAMETR, getWidth() - DIAMETR, getHeight() - DIAMETR);
    }

    /**
     * This function create 1 BLACK circle
     */
    private void createCircle(double x, double y) {
        GOval circle = new GOval(x, y, DIAMETR, DIAMETR);
        circle.setFilled(true);
        circle.setFillColor(Color.BLACK);
        add(circle);
    }

    /**
     * This function create 1 WHITE Rectangle
     *
     * @param x      left x coordinate
     * @param y      left y coordinate
     * @param width  width of the rectangle
     * @param height height of the rectangle
     */
    public void createRectangle(double x, double y, double width, double height) {
        GRect rectangle = new GRect(x, y, width, height);
        rectangle.setFilled(true);
        rectangle.setFillColor(Color.GRAY);
        rectangle.setColor(Color.WHITE);
        add(rectangle);
    }
}