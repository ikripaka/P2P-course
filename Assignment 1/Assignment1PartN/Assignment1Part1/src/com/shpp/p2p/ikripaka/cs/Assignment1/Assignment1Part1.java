package com.shpp.p2p.ikripaka.cs.Assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part1 extends KarelTheRobot {

    /**
     * This program pick newspaper and come home
     */
    public void run() throws Exception {
        goToTheBeeper();
        comeToTheStartPoint();
    }

    /**
     * result : robot turns around on 270 degrees
     * @throws Exception
     */
    public void turnRight() throws Exception{
        turnLeft();
        turnLeft();
        turnLeft();
    }


    /**
     * input data: start position
     * result: robot move to newspaper
     */
    public void goToTheBeeper() throws Exception{
       moveToWall();
       turnRight();
       move();
       turnLeft();
       while(noBeepersPresent()){
          move();
       }
       pickBeeper();
       turnLeft();
       turnLeft();
    }

    /**
     * input data: robot stay at the newspaper
     * result: robot come home( to start point)
     */
    public void comeToTheStartPoint() throws Exception{
        moveToWall();
        turnRight();
        move();
    }

    /**
     * result: robot come to the closest wall
     */
    public void moveToWall()throws Exception{
        while(frontIsClear()){
            move();
        }
    }

}
