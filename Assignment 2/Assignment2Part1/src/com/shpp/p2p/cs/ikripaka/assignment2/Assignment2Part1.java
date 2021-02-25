package com.shpp.p2p.cs.ikripaka.assignment2;

import com.shpp.cs.a.console.TextProgram;

    /**
    * This program finds roots of the expression ax^2+bx+c=0
    */
public class Assignment2Part1 extends TextProgram {

    public void run() {
        while(true) {
            println("Make so, if you want to interrupt the session. " +
                    "Assign variables a,b,c value '0' .");
            double a, b, c, discriminant;
            a = inputDouble("a");
            b = inputDouble("b");
            c = inputDouble("c");

            if(a == 0 && b==0 && c==0){
                println("You interrupted session.");
                break;
            }

            if (a == 0) {
                println("Incorrect value. Please try try one more time. ");
            } else {
                discriminant = calculateDiscriminant(a, b, c);
                calculateRoots(a, b, discriminant);
            }
        }
    }

    /**
     * This function return double number which user print
     *
     * @param letter - name of the letter which is entered
     * @return double number which user print
     */
    private double inputDouble(String letter) {
        print("Please enter " + letter + " : ");
        return readDouble();
    }

    /**
     * Calculates discriminant
     * @return Discriminant value
     */
    private double calculateDiscriminant(double a, double b, double c) {
        return squareNumber(b) - (4 * a * c);
    }

    /**
     * @return square number
     */
    private double squareNumber(double number) {
        number = number * number;
        return number;
    }

    /**
     * Calculate roots
     */
    private void calculateRoots(double a, double b, double discriminant) {
        double x1, x2;
        if (discriminant == 0) {
            x1 = -(b / (2 * a));
            println("There is one real root: " + x1);
        } else {
            if (discriminant > 0) {
                x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                x2 = (-b - Math.sqrt(discriminant)) / (2 * a);
                println("There are two real roots: " + x1 + " and " + x2);
            } else {
                println("There are no real roots.");
            }
        }
        println();
    }
}
