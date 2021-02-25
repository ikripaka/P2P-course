package com.shpp.p2p.cs.ikripaka.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;


public class Assignment2Part4 extends WindowProgram {

    public static final int APPLICATION_WIDTH = 500;
    public static final int APPLICATION_HEIGHT = 500;
    private static final Color CARDINAL_RED = new Color(196, 30, 58);
    private static final double HEIGHT_OF_RECTANGLE = APPLICATION_HEIGHT * 0.6;
    private static final double WIDTH_OF_RECTANGLE = APPLICATION_WIDTH * 0.25;

    /**
     * This program print three-colored flag and flag description.
     */
    public void run() {
        String flagText = "Flag of Belgium";

        paintPartOfTheFlag(getWidth() / 2 - WIDTH_OF_RECTANGLE - WIDTH_OF_RECTANGLE / 2,
                (getHeight() - HEIGHT_OF_RECTANGLE) / 2, Color.BLACK);
        paintPartOfTheFlag(getWidth() / 2 - WIDTH_OF_RECTANGLE / 2,
                (getHeight() - HEIGHT_OF_RECTANGLE) / 2, Color.YELLOW);
        paintPartOfTheFlag(getWidth() / 2 + WIDTH_OF_RECTANGLE / 2,
                (getHeight() - HEIGHT_OF_RECTANGLE) / 2, CARDINAL_RED);

        printFladText(flagText);
    }

    /**
     * This function print first part of the flag ( first from the left side ).
     */
    private void paintPartOfTheFlag(double x, double y, Color color) {
        GRect rectangle = new GRect(x, y, WIDTH_OF_RECTANGLE, HEIGHT_OF_RECTANGLE);
        rectangle.setFilled(true);
        rectangle.setFillColor(color);
        rectangle.setColor(color);
        add(rectangle);
    }

    /**
     * Paint flag description.
     * @param flagText - information that you want to print in the window.
     */
    public void printFladText(String flagText) {
        GLabel l = new GLabel(flagText, 0, 0); // I add one more label, because I get size of the label
        l.setFont("Georgia-30");
        int tailOfTheLetterG = 8;
        GLabel text = new GLabel(flagText, getWidth() - l.getWidth(), getHeight() - tailOfTheLetterG );
        text.setFont("Georgia-30");
        add(text);
    }
}
