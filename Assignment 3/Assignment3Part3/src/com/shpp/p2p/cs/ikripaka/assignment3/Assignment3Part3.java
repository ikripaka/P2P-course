package com.shpp.p2p.cs.ikripaka.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * It calculate number in root
 */
public class Assignment3Part3 extends TextProgram {

    public void run() {
        double result, userNumber;
        int power;

        userNumber = enterUserNumber();
        power = enterUserPower();

        result = raiseToPower(userNumber, power);
        displayTheResult(result);

    }

    private void displayTheResult(double result) {
        println("Result: " + result);
    }

    private double enterUserNumber() {
        println("Enter the number and the degree to which you want to raise that number");
        return readDouble("Number: ");
    }

    private int enterUserPower() {
        return readInt("Power: ");
    }

    /**
     * @return number in power
     * splitter - divider for fractional part and whole
     * splitter[0] - return 9  (9.0)
     * splitter[1] - return 0
     */
    private double raiseToPower(double number, int power) {
        double numberInDegree = 0.1, numeral = 1, denominator = 1;
        String[] splitter = String.valueOf(9.0).split("\\.");
        int splitterInt = Integer.parseInt(splitter[1]);

        if (number == 0) {
            println(" Incorrect number. ");
            numberInDegree = 0.2;
        } else {
            if (power == 0) {
                numberInDegree = 1;
            } else {
                if (power > 0) {
                    numberInDegree = multiplyNumberInPower(returnPositiveNumber(number), power);
                } else {
                    if (power < 0) {
                         if(splitterInt > 1){
                             numberInDegree = 1 / multiplyNumberInPower(returnPositiveNumber(number), power);
                             println(multiplyNumberInPower(returnPositiveNumber(number), power));
                         }
                         if(splitterInt < 1){

                             denominator = multiplyNumberInPower(number, splitter[1].length()); //знаменник
                             numeral = Double.parseDouble(splitter[1]); // чисельник
                             numberInDegree = multiplyNumberInPower(numeral, -power) / denominator;
                         }
                    }
                }
            }
        }
        return numberInDegree;

    }

    /**
     * @return The number elevated to a degree
     */
    private double multiplyNumberInPower(double number, int power) {
        double numberInPower = number;
        for (int i = 1; i < power; i++) {
            numberInPower *= number;
        }
        return numberInPower;
    }

    /**
     * @return positive number (-5) = 5
     */
    private double returnPositiveNumber(double number) {
        if (number > 0) return number;
        return -number;
    }
}