package com.shpp.p2p.cs.ikripaka.assignment8;

import acm.graphics.GRect;

import java.awt.*;

/**
 * Class Brick is supplemented class GRect
 * It uses for identifying color that brick has.
 * Color can be:
 *  - Green - "Grass"
 *  - Blue - "Water"
 *  - Black - "Seeds"
 *  - White - "Air"
 */
class Brick extends GRect {
    private Color brickColor;

    /**
     * Creates GRect
     * @param v - x coord
     * @param v1 - y coord
     * @param v2 - length
     * @param v3 - width
     */
    Brick(double v, double v1, double v2, double v3) {
        super(v, v1, v2, v3);
    }

    /**
     * Sets brickColour to brick
     * @param brickColor - colour that brick has
     */
    void setBrickColor(Color brickColor) {
        this.brickColor = brickColor;
    }

    /**
     * Returns brick colour
     * @return - brickColor
     */
    Color getBrickColor() {
        return brickColor;
    }
}
