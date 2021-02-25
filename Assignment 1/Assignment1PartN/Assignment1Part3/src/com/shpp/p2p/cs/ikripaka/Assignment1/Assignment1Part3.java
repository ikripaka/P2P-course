package com.shpp.p2p.cs.ikripaka.Assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part3 extends KarelTheRobot {

    /**
     * This program finds center of the line.
     */
    public void run() throws Exception {
        findCenter();
    }

    /**
     * input data: robot stand at the beginning of the level
     * result: it finds center of the line
     */
    public void findCenter() throws Exception{
        if(frontIsBlocked()){
            putBeeper();
        }else{
            fillLineWithBeepers();
            turnAround();
            pickBeeper();
            move();
            while(beepersPresent()){
                goToTheNearestBeeper();
                turnAround();
                move();
                pickBeeper();
                move();
            }
            turnAround();
            move();
            putBeeper();
        }

    }

    /**
     * input data: robot stand at the beginning of the line which it must fill with beepers
     * result: a line with beepers
     */
    public void fillLineWithBeepers() throws Exception{
        while(frontIsClear()){
            move();
            putBeeper();
        }
    }

    /**
     * input data: robot must stay on the beeper
     * result: robot go to the nearest cell without beeper
     */
    public void goToTheNearestBeeper() throws Exception{
        while(beepersPresent()){
            move();
        }
    }

    /**
     * result: robot turn on 180 degrees
     */
    public void turnAround() throws Exception{
        turnLeft();
        turnLeft();
    }
}