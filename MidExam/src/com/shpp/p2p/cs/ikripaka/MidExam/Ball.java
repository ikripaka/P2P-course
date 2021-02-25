package com.shpp.p2p.cs.ikripaka.MidExam;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;


public class Ball extends WindowProgram implements FallingBallsConstants {

    private double dy, dx;
    private GOval ball;

    /**
     * height - point to which the selected ball will pull
     */
    private double height, width;

    // Constructor
    public Ball(GOval startCircle, double workScreenHeight, double workScreenWidth ) {
        ball = startCircle;
        height = workScreenHeight;
        width = workScreenWidth;
    }

    // Animates selected ball
    public void animCircle() {
        dy = 0;
        dx = HORIZONTAL_VELOCITY;
        while (true) {

            /* Move the ball by the current velocity. */
            ball.move(dx, dy);

            /* Gravity pulls downward, increasing downward acceleration. */
            if(height == 0){
                dy -= GRAVITY;
            }else{
                dy += GRAVITY;
            }

            /* If the ball drops below the floor or above the ceiling, we turn it around. There's
             * a tricky case to watch out for - if the ball is already in
             * the floor or at the ceiling, we don't turn it around.
             */

            if ((isBallBelowTheFloor(ball) && dy > 0) || (isBallAboveTheCeiling(ball) && dy < 0) ) {
                dy *= -ELASTICITY;
            }

            if(isBallAfterField(ball)){
                dx = -dx;
            }

            pause(PAUSE_TIME);
        }
    }

    private boolean isBallAfterField(GOval ball) {
        return ball.getX() < 0 || ball.getX() + ball.getHeight() > width;
    }

    public void setTippingPoint(double tippingPoint){
        height = tippingPoint;
    }

    public double getTipppingPoint(){ return height;}

    private boolean isBallBelowTheFloor(GOval ball) {
         return ball.getY() + ball.getHeight() >= height;
    }

    private boolean isBallAboveTheCeiling(GOval ball) {
        return ball.getY() <= 0;
    }
}
