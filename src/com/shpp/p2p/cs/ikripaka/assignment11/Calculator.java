package com.shpp.p2p.cs.ikripaka.assignment11;

import com.shpp.p2p.cs.ikripaka.assignment17.CustomHashMap;
import com.shpp.p2p.cs.ikripaka.assignment16.CustomStack;

/**
 * Calculates expression
 * - that written on RPN
 * - from left side to right side
 */

class Calculator extends Constants {
    private double result = 0;
    /**
     * Contains line that divided by " "
     */
    private String[] dividedLine;
    /**
     * Contains variables that user wrote
     */
    private CustomHashMap<String, Double> allVariables;

    /**
     * Constructs
     *
     * @param RPNNote   - parsed line in the rpn note
     * @param variables - variables that user entered
     */
    Calculator(String RPNNote, CustomHashMap<String, Double> variables) {
        allVariables = variables;
        dividedLine = RPNNote.split(" ");
    }

    /**
     * Calculates expression from left side to right one
     */
    void calculate() {
        CustomStack<Double> stack = new CustomStack<>();
        FunctionCalculator functionCalculator = new FunctionCalculator();

        try {
            for (int i = 0; i < dividedLine.length; i++) {
                if (!matches(dividedLine[i], ALL_ARITHMETICAL_OPERATIONS) && !FUNCTIONS.contains(dividedLine[i])) {
                    if (!matches(dividedLine[i], NUMBER)) {
                        if (allVariables.size() == 0) {
                            System.err.println("You didn't write variable(s) value(s)");
                            System.exit(0);
                        }
                        stack.push(allVariables.get(dividedLine[i]));
                    } else {
                        stack.push(Double.parseDouble(dividedLine[i]));
                    }
                } else {
                    if (matches(dividedLine[i], ALL_ARITHMETICAL_OPERATIONS)) {
                        stack.push(performArithmeticAction(stack.pop(), stack.pop(), dividedLine[i]));
                    } else {
                        stack.push(functionCalculator.allFunctions.get(dividedLine[i]).calculate(stack.pop()));
                    }
                }
            }
            result = stack.pop();
        } catch (Exception e) {
            System.err.println("There are some troubles with the calculation");
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }

    }

    /**
     * Calculates result according to arithmetical operation
     *
     * @param num2      - second number
     * @param num1      - first number
     * @param operation - arithmetical operation
     * @return - result after operation on this numbers
     * @throws Exception
     */
    private double performArithmeticAction(double num2, double num1, String operation) throws Exception {
        if (operation.equals("/") && num2 == 0) throw new Exception("Can't divide by zero");

        switch (operation) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            case "^":
                return Math.pow(num1, num2);
            default:
                return 0;
        }
    }

    double getResult() {
        return result;
    }

    void setAllVariables(CustomHashMap<String, Double> allVariables) {
        this.allVariables = allVariables;
    }
}
