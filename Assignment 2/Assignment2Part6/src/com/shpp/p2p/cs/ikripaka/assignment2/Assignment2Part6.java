package com.shpp.p2p.cs.ikripaka.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * This class paint a caterpillar.
 */
public class Assignment2Part6 extends WindowProgram {
    // Number of all segments of caterpillar
    private final static int NUMBER_OF_SEGMENTS = 9;
    private final static double CIRCLE_DIAMETR = 160.0;
    private final static double CIRCLE_RADIUS = CIRCLE_DIAMETR / 2;
    private final static double OFFSET = CIRCLE_DIAMETR/3;
    // Additional colour
    private static final Color CARDINAL_RED = new Color(196, 30, 58);


    public void run() {

        double x = (getWidth() - OFFSET * (NUMBER_OF_SEGMENTS+1)) / 2.0;
        double y = (getHeight() - OFFSET * 3) / 2;
        for (int i = 0; i < NUMBER_OF_SEGMENTS; i++) {
            if (i % 2 == 0) {
                paintSegment(x + OFFSET * i, y);
            } else {
                paintSegment(x + OFFSET * i, y - CIRCLE_RADIUS);

            }
        }
    }

    /**
     * This function paint 1 segment of caterpillar
     * @param x - x coordinate of the last segment
     * @param y - y coordinate of the last segment
     */
    private void paintSegment(double x, double y) {
        GOval circle = new GOval(x, y, CIRCLE_DIAMETR, CIRCLE_DIAMETR);
        circle.setFilled(true);
        circle.setFillColor(Color.GREEN);
        circle.setColor(CARDINAL_RED);
        add(circle);
    }
}