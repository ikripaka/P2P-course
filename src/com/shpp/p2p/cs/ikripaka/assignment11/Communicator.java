package com.shpp.p2p.cs.ikripaka.assignment11;


import com.shpp.p2p.cs.ikripaka.assignment17.CustomHashMap;

import java.util.Scanner;

/**
 * This class communicates with user and changes variables
 * on the user's desire
 */

class Communicator {

    private Calculator calculator;
    private CustomHashMap<String, Double> variables;
    private Scanner scanner;

    Communicator(Calculator calculator, CustomHashMap<String, Double> variables) {
        this.calculator = calculator;
        this.variables = variables;
        scanner = new Scanner(System.in);
    }

    /**
     * Talks with user:
     * - offers user to change variables
     */
    void talkWithUser() {
        System.out.println("Write numbers to change variables");
        for (String key: variables) {
            System.out.println(key + ": ");
            String line = scanner.nextLine();
            checkLine(line);
            variables.put(key, Double.valueOf(line));
        }

        calculator.setAllVariables(variables);
        calculator.calculate();
        System.out.println(calculator.getResult());

        System.out.println("Do you want to change variables? Write Y -Yes or N -No");
        String line = scanner.nextLine();
        if (line.equalsIgnoreCase("Y")) {
            talkWithUser();
        } else {
            System.exit(0);
        }
    }

    /**
     * Checks input data to correctness
     *
     * @param line - user input data
     */
    private void checkLine(String line) {
        if (line.split(" ").length > 1 || line.contains("*/+^")) {
            System.err.println("Incorrect data for variables");
            System.exit(0);
        }
    }
}
