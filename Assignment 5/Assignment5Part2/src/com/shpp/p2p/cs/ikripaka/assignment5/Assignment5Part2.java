package com.shpp.p2p.cs.ikripaka.assignment5;

import com.shpp.cs.a.console.TextProgram;

/**
 * ----- ALGORISM ALGORITHMS ------
 * This class combines two numbers to bigger number.
 */
public class Assignment5Part2 extends TextProgram {
    private final static int MAX_DIGIT = 10;

    public void run() {
        /* Sit in a loop, reading numbers and adding them. */
        while (true) {
            String n1 = readLine("Enter first number:  ");
            String n2 = readLine("Enter second number: ");
            println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
            println();
        }
    }

    /**
     * Given two string representations of nonnegative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String n1, String n2) {
        StringBuilder result = new StringBuilder();
        int maxNumLength = Math.max(n1.length(), n2.length());
        int remainder = 0;
        int sum = 0;

        for (int i = 0; i < maxNumLength; i++) {
            int number1 = 0;
            int number2 = 0;

            if (i < n1.length()) {
                number1 = n1.charAt(n1.length() - i - 1) - '0';
            }
            if (i < n2.length()) {
                number2 = n2.charAt(n2.length() - i - 1) - '0';
            }
            sum = number1 + number2 + remainder;
            remainder = 0;
            if (sum >= MAX_DIGIT) {
                sum -= MAX_DIGIT;
                remainder = 1;
            }
            result.insert(0, sum);

        }

        if(remainder == 1){
            result.insert(0, remainder);
        }

        return result.toString();
    }

}

