package com.shpp.p2p.cs.ikripaka.assignment11;

import java.util.HashMap;
import java.util.Scanner;

/**
 * -------- CALCULATOR --------
 * This class calculates expressions
 * which can contain these symbols:
 * ^, *, /, +, -, (, )
 * and these functions:
 * sin, cos, tan, atan, log10, log2, sqrt
 *
 * It bases on the ReversePolishNotation
 */

public class Assignment11 extends Constants {
    public static void main(String[] args) {
        calculate(args, new Constants());
    }

    private static void calculate(String[] args, Constants constants) {
        try {
            checkLine(args, constants);

            System.out.println("Input " + args[0]);

            HashMap<String, Double> variables = fillInVariablesInHashMap(args, constants);

            Parser divideLine = new Parser(args[0]);
            String RPNNote = divideLine.getParsedLine();

            System.out.println("RPNNOTE: " + RPNNote);

            Calculator calculateExpression = new Calculator(RPNNote, variables);
            calculateExpression.calculate();
            System.out.println("Result " + calculateExpression.getResult());

            Scanner scanner = new Scanner(System.in);
            if(variables.size() != 0) {
                System.out.println("Do you want to change variables? Write Y -Yes or N -No");
                String line = scanner.nextLine();

                if (line.equalsIgnoreCase("Y")) {
                    new Communicator(calculateExpression, variables).talkWithUser();
                } else {
                    System.exit(0);
                }
            }

        } catch (Exception e) {
            System.err.println("Something went wrong");
            System.err.println(e.getMessage());
        }
    }

    /**
     * Checks line for the correct input
     * - is line empty
     * - is line has incorrect number of brackets
     * - is line has characters that program cannot handle
     *
     * @param arg - line
     * @throws Exception - Line empty
     */
    private static void checkLine(String[] arg, Constants constants) throws Exception {
        if (arg.length == 0 ) throw new Exception("The line is empty");

        for (int i = 0; i < arg[0].length(); i++) {
            if (!(constants.matches(String.valueOf(arg[0].charAt(i)), constants.LETTERS)
                    || constants.matches(String.valueOf(arg[0].charAt(i)), constants.NUMBER)
                    || constants.matches(String.valueOf(arg[0].charAt(i)), constants.ALL_ARITHMETICAL_OPERATIONS))) {
                throw new Exception("There are characters that program can't handle. Please, check input line.");
            }
        }

        int openBracket = 0, closeBracket = 0;
        for(int i =0; i < arg[0].length(); i++){
            if(arg[0].toCharArray()[i] == '(') openBracket++;
            if(arg[0].toCharArray()[i] == ')') closeBracket++;
        }
        if(openBracket != closeBracket) throw new Exception("Wrong parentheses");
    }

    /**
     * Fills HashMap with arrays and numeric numbers
     *
     * @param args - input String array
     */
    private static HashMap<String, Double> fillInVariablesInHashMap(String[] args, Constants constants) throws Exception {
        HashMap<String, Double> variables = new HashMap<>();
        if (args.length > 1) {
            for (int i = 1; i < args.length; i++) {
                String[] letter = args[i].split("=");
                if (!constants.matches(letter[0], constants.LETTERS)) {
                    throw new Exception("Incorrect VARIABLES");
                }
                variables.put(letter[0], Double.valueOf(letter[1]));
            }
        }
        return variables;
    }

}