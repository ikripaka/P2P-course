package com.shpp.p2p.cs.ikripaka.assignment15;

/**
 * The main class
 * This program can:
 * - archive file in format (.par) using Huffman algorithm
 * - unpack file from (.par) to (.uar) using Huffman algorithm
 * <p>
 * Class consist of main function that:
 * - controls program
 * - catches exceptions
 */

public class Assignment15 extends Constants {
    public static void main(String[] args) {
        try {
            double startTime = System.currentTimeMillis();

            if(args.length == 0){
                System.out.println("Please write path to the file");
                System.exit(0);
            }

            Switcher switcher = new Switcher(args);
            switcher.recognizeAction();

            double timeSpent = (System.currentTimeMillis() - startTime) / MSEC_IN_ONE_SEC;
            System.out.println("Time: " + timeSpent + "sec.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
    }
}

