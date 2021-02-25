package com.shpp.p2p.cs.ikripaka.assignment14;

/**
 * The main class
 * <p>
 * This program can:
 * - archive file in format (.par)
 * - unpack file from (.par) to (.uar)
 * <p>
 * Class consist of main function that:
 * - controls program
 * - catches exceptions
 */

public class Assignment14 {


    public static void main(String[] args) {
        try {
            double startTime = System.currentTimeMillis();

            Switcher switcher = new Switcher(args);
            switcher.recognizeAction();
            System.out.println(Integer.toBinaryString(58));
            double timeSpent = (System.currentTimeMillis() - startTime) / 1000;
            System.out.println("Time: " + timeSpent + "sec.");

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
    }
}
