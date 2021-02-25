package com.shpp.p2p.cs.ikripaka.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * This class determine your health
 */
public class Assignment3Part1 extends TextProgram {

    private static final int CARDIVACULAR_TRAINING = 30;
    private static final int AMOUNT_OF_CARDIVACULAR_TRAINING  = 5;

    private static final int PRESSURE_TRAINING = 40;
    private static final int AMOUNT_OF_PRESSURE_TRAINING = 3;

    public void run() {
        int[] amountOfTraingTime = new int[10];
        amountOfTraingTime = enterData(amountOfTraingTime);
        determineCardiovacularHealth(amountOfTraingTime);
        determinePressureHealth(amountOfTraingTime);

    }

    /**
     *  Compares with normal AMOUNT_OF_CARDIVACULAR_TRAINING
     * @param amountOfTrainingTime - an array with time which you used to train
     */
    private void determineCardiovacularHealth(int[] amountOfTrainingTime) {
        int amountOfProperTraining = 0;
        for (int i = 0; i < 7; i++) {
            if(amountOfTrainingTime[i] >= CARDIVACULAR_TRAINING){
                amountOfProperTraining++;
            }
        }
        if(amountOfProperTraining < AMOUNT_OF_CARDIVACULAR_TRAINING){
            println("You needed to train hard for at least " + (AMOUNT_OF_CARDIVACULAR_TRAINING - amountOfProperTraining) + " more day(s) a week!");
        }else{
            if(amountOfProperTraining >= AMOUNT_OF_CARDIVACULAR_TRAINING){
                println("Great job! You've done enough exercise for cardiovacular health.");
            }
        }
    }

    /**
     * Compares with normal AMOUNT_OF_PRESSURE_TRAINING
     * @param amountOfTrainingTime - an array with time which you used to train
     */
    private void determinePressureHealth(int[] amountOfTrainingTime) {
        int amountOfProperTraining = 0;
        for (int i = 0; i < 7; i++) {
            if(amountOfTrainingTime[i] >= PRESSURE_TRAINING){
                amountOfProperTraining++;
            }
        }
        if(amountOfProperTraining < AMOUNT_OF_PRESSURE_TRAINING){
            println("You needed to train hard for at least " + (AMOUNT_OF_PRESSURE_TRAINING - amountOfProperTraining) + " more day(s) a week!");
        }else{
            if(amountOfProperTraining >= AMOUNT_OF_PRESSURE_TRAINING){
                println("Great job! You've done enough exercise to keep a low blood pressure.");
            }
        }
    }

    /**
     * @return a massive with time which you used to train
     */
    private int[] enterData(int[] amountOfTraingTime) {
        for (int i = 0; i < 7; i++) {
            amountOfTraingTime[i] = readInt("How many minutes did you do on day " + (i+1) + "?");
        }
        return amountOfTraingTime;
    }


}