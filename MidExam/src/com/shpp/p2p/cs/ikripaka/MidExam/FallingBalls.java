package com.shpp.p2p.cs.ikripaka.MidExam;

import acm.graphics.GObject;
import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class FallingBalls extends WindowProgram implements FallingBallsConstants {
    // This hashmap could contain all circles with their animation class
    private HashMap<GObject, Ball> ballData = null;

    // It characterizes the color of the ball
    private int color = 255;
    private GOval circle;

    public void init() {
        ballData = new HashMap<>();
        color = 0;
    }

    public void run() {
        addMouseListeners();
        GOval circle = getMeFilledOval(BALL_RADIUS, BALL_RADIUS);
        add(circle);
        Ball bouncingBall = new Ball(circle, getHeight(), getWidth());
        ballData.put(circle, bouncingBall);
        bouncingBall.animCircle();
    }

    public void mouseClicked(MouseEvent me) {
        GObject comp = getElementAt(me.getX(), me.getY());
        if (comp != null) { // Reverse ball falling
            if (ballData.get(comp).getTipppingPoint() == 0) {
                ballData.get(comp).setTippingPoint(getHeight());
            } else {
                ballData.get(comp).setTippingPoint(0);
            }
        } /*else { */
        /**
         *  It should make a bigger size to the ball
         */
         /*   circle = getMeFilledOval(me.getX(), me.getY());
            add(circle);
            while (true) {
                circle.scale(1);
                circle.setFillColor(new Color(color, color, color));
                color--;
            }
        }*/
        /**
         * This commented code can add circles without animation
          */
        /*GObject comp = getElementAt(me.getX(), me.getY());
            System.out.println("i here 2");
            if(comp == null){
                GOval circle = getMeFilledOval(me.getX(), me.getY());
                add(circle);
                Ball bouncingBall = new Ball(circle, getHeight());
                ballData.put(circle, bouncingBall);
                System.out.print("Click");
                //bouncingBall.animCircle();

            }else{
                System.out.print("Click");

                if(ballData.get(comp).getTipppingPoint() == 0){
                    ballData.get(comp).setTippingPoint(getHeight());
                }else{
                    ballData.get(comp).setTippingPoint(0);
                }
            }*/

    }

    /**
     * If mouse released than it should write it to the hashmap and animate circle
     * @param me - MouseEvent
     */
    /*public void mouseReleased(MouseEvent me) {
        Ball bouncingBall = new Ball(circle, getHeight(), getWidth());
        ballData.put(circle, bouncingBall);
        ballData.get(circle).animCircle();
    }*/

    /**
     * Gets filled oval
     * @param x - x coordinate
     * @param y - y coordinate
     * @return - filled with black color oval
     */
    private GOval getMeFilledOval(int x, int y) {
        GOval circle = new GOval(x, y, BALL_RADIUS, BALL_RADIUS);
        circle.setFilled(true);
        circle.setColor(Color.BLACK);
        return circle;
    }
}
