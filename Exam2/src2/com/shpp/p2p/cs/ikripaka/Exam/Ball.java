package com.shpp.p2p.cs.ikripaka.Exam;

import acm.graphics.GOval;

import java.awt.*;

public class Ball extends GOval implements FallingBallsConstants {
    public boolean isActive = false;

    // Polarity characterizes the attraction of an element to the ceiling or floor (true - floor) (false - ceiling)
    public boolean polarity = true;

    // dy - speed by Y
    private double dy = 0;

    private int size, color = START_COLOR;

    /**
     * Constructor
     *
     * @param v  - x
     * @param v1 - y
     * @param v2 - diameter
     * @param v3 - diameter
     */
    public Ball(double v, double v1, double v2, double v3) {
        super(v, v1, v2, v3);
    }

    /**
     * Increments size
     *
     * @param ball - ball in that we want to change SIZE
     */
    public void incrementSise(Ball ball) {
        size += SIZE_INCREASE_VALUE;
        ball.setSize(size, size);
    }

    /**
     * Decrements color
     *
     * @param ball - ball in which we want to change COLOR
     */
    public void decrementColor(Ball ball) {
        if (color - COLOR_REDUCTION_VALUE <= 0) {
            color = 0;
        } else {
            color -= COLOR_REDUCTION_VALUE;
        }

        ball.setColor(new Color(color, color, color));
    }


    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getDy() {
        return dy;
    }

    public int getBallSize() {
        return size;
    }

    public void setElsePolarity() {
        polarity = !polarity;
    }
}
