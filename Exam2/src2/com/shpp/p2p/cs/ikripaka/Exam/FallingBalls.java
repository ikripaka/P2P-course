package com.shpp.p2p.cs.ikripaka.Exam;

import acm.graphics.GObject;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class FallingBalls extends WindowProgram implements FallingBallsConstants {
    private ArrayList<Ball> ballsArrayList = new ArrayList<>();

    public void init() {
        addMouseListeners();
    }

    public void run() {
        for (; ; ) {
            for (int i = 0; i < ballsArrayList.size(); i++) {
                if (ballsArrayList.get(i).isActive) {
                    animateOneBall(i);
                } else {
                    ballsArrayList.get(i).incrementSise(ballsArrayList.get(i));
                    ballsArrayList.get(i).decrementColor(ballsArrayList.get(i));
                }
            }
            pause(PAUSE_TIME);
        }
    }

    /**
     * Animates one ball
     *
     * @param counter - number of ball that wo should change
     */
    private void animateOneBall(int counter) {

        if (ballsArrayList.get(counter).polarity) {
            ballsArrayList.get(counter).setDy(ballsArrayList.get(counter).getDy() + GRAVITY);
        } else {
            ballsArrayList.get(counter).setDy(ballsArrayList.get(counter).getDy() - GRAVITY);
        }

        if ((ballBelowTheFloor(ballsArrayList.get(counter)) && ballsArrayList.get(counter).getDy() > 0) || (ballOverTheCeiling(ballsArrayList.get(counter)))) {
            ballsArrayList.get(counter).setDy(ballsArrayList.get(counter).getDy() * -ELASTICITY);
        }

        ballsArrayList.get(counter).move(0, ballsArrayList.get(counter).getDy());
    }

    /**
     * If ball below the floor
     *
     * @param ball - object of class " Ball " which we want to know where it is located
     * @return - boolean (if ball below the floor)
     */
    private boolean ballBelowTheFloor(Ball ball) {
        return ball.getY() + ball.getBallSize() >= getHeight();
    }

    /**
     * If ball over the ceiling
     *
     * @param ball - object of class " Ball " which we want to know where it is located
     * @return - boolean (if ball over the ceiling)
     */
    private boolean ballOverTheCeiling(Ball ball) {
        return ball.getY() <= 0;
    }

    /**
     * Works when mouse is PRESSED
     *
     * @param me - MouseEvent
     */
    public void mousePressed(MouseEvent me) {
        GObject object = getElementAt(me.getX(), me.getY());
        if (ballsArrayList.contains(object)) {
            ((Ball) object).setElsePolarity();

        } else {
            Ball newBall = new Ball(me.getX(), me.getY(), BALL_DIAMETER, BALL_DIAMETER);
            newBall.setFilled(true);
            newBall.setColor(new Color(START_COLOR, START_COLOR, START_COLOR));
            add(newBall);

            ballsArrayList.add(newBall);
        }

    }

    /**
     * Works when mouse is RELEASED
     *
     * @param me - MouseEvent
     */
    public void mouseReleased(MouseEvent me) {
        if (ballsArrayList.size() != 0) {
            ballsArrayList.get(ballsArrayList.size() - 1).isActive = true;
        }
        if (ballBelowTheFloor(ballsArrayList.get(ballsArrayList.size() - 1))) {
            ballsArrayList.get(ballsArrayList.size() - 1).setLocation(ballsArrayList.get(ballsArrayList.size() - 1).getX(),
                    getHeight() - ballsArrayList.get(ballsArrayList.size() - 1).getBallSize());
        }
    }
}
