package com.shpp.p2p.cs.ikripaka.assignment3;

import acm.util.RandomGenerator;
import com.shpp.cs.a.console.TextProgram;

/**
 * This class does simple casino game
 */
public class Assignment3Part5 extends TextProgram{
    /**
     * coin = true - eagle
     * coin = false - tails
     */

    public void run(){
        int scoreLucky = 0;
        int moneyOntHeTable = 0;

        println("The game starts");
        println("Lucky has 0$");
        gamingOperations( scoreLucky, moneyOntHeTable);

    }

    /**
     * This function do all game mechanic
     * 1 - if (coin = true) => sweating put on the table double of amount or 1$
     * 2 - if (coin = false) => lucky takes away the whole amount from the table
     */
    private void gamingOperations( int scoreLucky, int moneyOnTable) {
        boolean coin;
        while(scoreLucky < 20) {
            coin = RandomGenerator.getInstance().nextBoolean();
            if (coin) {
                if(moneyOnTable == 0){
                    moneyOnTable = 1;
                }else{
                    moneyOnTable *= 2;
                }
                println("This game, you earned $0");
            } else {
                scoreLucky += moneyOnTable;
                println("This game, you earned $" + moneyOnTable);
                moneyOnTable = 0;
            }
            println("Your total score is $" + scoreLucky);
            println(" | -- -- -- -- -- | ");
        }
    }
}
