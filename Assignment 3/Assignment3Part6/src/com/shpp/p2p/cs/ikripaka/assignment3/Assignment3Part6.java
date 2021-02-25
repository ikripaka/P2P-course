package com.shpp.p2p.cs.ikripaka.assignment3;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * This class make a solar system which consist of Sun and Earth
 * Also, this program shows how the Earth moves for 1 calendar year!
 */
public class Assignment3Part6 extends WindowProgram {
    private static final int EARTH_RADIUS = 45;
    private static final int SUN_RADIUS = 75;
    private static final int EARTH_ORBIT_RADIUS = 100;
    private static final Color EARTH_COLOR = Color.BLUE;
    private static final Color EARTH_BORDER_COLOR = Color.BLUE;
    private static final Color SUN_BORDER_COLOR = Color.ORANGE;
    private static final Color SUN_COLOR = Color.YELLOW;

    private static final double MAX_TIME = 5000;
    private static final double AMOUNT_OF_FRAMES = 1900;
    private static final double PAUSE_TIME = MAX_TIME / AMOUNT_OF_FRAMES;


    public void run() {
        GOval earth = createCircle(EARTH_RADIUS, EARTH_RADIUS, EARTH_BORDER_COLOR);
        earth.setFillColor(EARTH_COLOR);
        GOval sun = createCircle(SUN_RADIUS, SUN_RADIUS, SUN_BORDER_COLOR);
        sun.setFillColor(SUN_COLOR);
        add(earth);
        add(sun);

        solarSystemAnimation(earth, sun);
    }

    /**
     * This function moves in a circle Earth
     */
    private void solarSystemAnimation(GOval earth, GOval sun) {
        double xEarthOrbit = getWidth() / 2.0;
        double yEarthOrbit = getHeight() / 2.0;
        sun.setLocation(xEarthOrbit, yEarthOrbit);

        pause(2100);
        int angle = 1;
        for (double i = 0; i < MAX_TIME; i += PAUSE_TIME) {
            if (angle >= 360) {
                angle = 0;
            }
            angle++;

            // calculate the position of x and y of the earth
            earth.setLocation(xEarthOrbit + EARTH_ORBIT_RADIUS * Math.cos(Math.PI * angle / 180),
                    yEarthOrbit + EARTH_ORBIT_RADIUS * Math.sin(Math.PI * angle / 180));

            pause(PAUSE_TIME);
        }
    }

    /**
     * @return - created circle
     */
    private GOval createCircle(double width, double height, Color color) {
        GOval circle = new GOval(0, 0, width, height);
        circle.setFilled(true);
        circle.setColor(color);
        return circle;
    }
}
