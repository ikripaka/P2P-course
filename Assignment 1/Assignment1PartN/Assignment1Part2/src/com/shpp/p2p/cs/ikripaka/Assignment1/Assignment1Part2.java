package com.shpp.p2p.cs.ikripaka.Assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part2 extends KarelTheRobot {
    /**
     * This program fills in column with beepers
     */
    public void run() throws Exception {
        makeColumns();
    }

    /**
     * input data: robot stay under the column
     * result: robot move to the end of column and fills it with beepers
     */
    public void findColumn() throws Exception {
        turnLeft();
        while(frontIsClear()){
            if(noBeepersPresent()){
                putBeeper();
            }
            move();
            if(noBeepersPresent()){
                putBeeper();
            }
        }
        turnLeft();
        turnLeft();
    }

    /**
     * input data: start coordinates
     * result: robot make columns
     */

    public void makeColumns() throws Exception{
        findColumn();
        while (leftIsClear()){
            goToTheNextColumn();
            findColumn();
        }
    }

    /**
     * input data: robot start at the beginning of the level
     * result: robot come to the closest wall
     */
    public void comeToWall() throws Exception{
        while(frontIsClear()){
            move();
        }
    }

    /**
     * input data: robot stands at the beginning of the wall
     * result: robot move to next column
     */
    public void goToTheNextColumn() throws Exception{
        comeToWall();
        turnLeft();
        for(int i = 0; i < 4; i++){
            move();
            if(beepersPresent()) pickBeeper();
        }
    }
}
