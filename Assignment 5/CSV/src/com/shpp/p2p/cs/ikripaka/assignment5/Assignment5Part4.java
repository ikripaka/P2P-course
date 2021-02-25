package com.shpp.p2p.cs.ikripaka.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * ----- CSV READER -----
 * This class can read file and split into pieces (like EXCEL).
 */
public class Assignment5Part4 extends TextProgram {
    private static final String FILENAME = "assets/taskFile.csv";

    public void run() {
        int input = readInt("Enter column index");
        int lines = countLines();
        if (input <= lines) {
            String result = extractColumn(input).toString();
            println();
            println(" list - " + result);
        } else {
            println("Incorrect column index. :(");
        }
    }

    /**
     *
     * @param columnIndex - column that user want to display
     * @return ArrayList column
     */
    private ArrayList<String> extractColumn(int columnIndex) {
        ArrayList<String> result = new ArrayList<>();
        int lines = countLines();
        for (int i = 0; i < lines; i++) {
            String line = returnOneLine(i);
            ArrayList<String> splitOneLine = new ArrayList<>();

            // if there no any brackets
            if (line.indexOf('"') == -1) {
                int length = line.length();
                for (int j = 0; j < length; j++) {
                    splitOneLine.addAll(Arrays.asList(line.split(",")));
                }
                // if there are some brackets in the line
            } else {

                int end = line.length() - 1;
                int begin = 0;
                int length = line.length();
                // take over all the letters in the line
                for (int j = 0; j < line.length(); j++) {

                    // if j at the end of the line
                    if(j == length - 1){
                        end = length - 1;
                    }

                    // if 1 symbol = "
                    if(j != 0 && line.charAt(j) == '"'){
                        j = jumpOverTheBrackets(j, line) ;
                    }

                    // if there are no comas in the line
                    if (line.copyValueOf(line.toCharArray(), end, (length - end - 1)).indexOf(',') == -1 ) {
                        splitOneLine.add(clean(line.copyValueOf(line.toCharArray(), end, length - end - 1)));
                    }

                    // if 1 symbol == ,
                    if (j != 0 && line.charAt(j) == ',') {
                        begin = end + 1;
                        end = jumpToTheComa(j, line);
                        j = end;
                        splitOneLine.add(clean(line.copyValueOf(line.toCharArray(), begin, end - begin)));
                    }

                    // is executed once at the beginning of the line
                    if (j == 0) {
                        if (line.charAt(j) == '"') {
                            end = jumpOverTheBrackets(j, line);
                            j = end;
                        } else {
                            end = jumpToTheComa(j, line);
                            j = end;
                        }
                        end = jumpToTheComa(j, line);
                        j = end;
                        splitOneLine.add(clean(line.copyValueOf(line.toCharArray(), begin, end - begin)));
                    }
                }

            }
            result.add(splitOneLine.get(columnIndex));
        }
        return result;
    }

    /**
     *  Cleans word from the brackets "
     * @param word - word that we must put in the ArrayList
     * @return - "clear" word without "
     */
    private String clean(String word) {
        if(word.indexOf('"') != -1){
            word = word.copyValueOf(word.toCharArray(), 1, word.length() - 2);
        }
        return word;
    }


    /**
     *
     * @param counter - which line should be returned to the function
     * @return - next line from the file
     */
    private String returnOneLine(int counter) {
        String oneLine = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
            for (int i = 0; i <= counter; i++) {
                oneLine = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            println("I can`t find file. :(");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            println("There are some troubles with file. :(");
        }

        return oneLine;
    }


    /**
     * Jumps over the brackets
     * @param counter - line index from which
     *                we should start to jump over the brackets
     * @param line - from the file
     * @return - index by the brackets ( " )
     */
    private int jumpOverTheBrackets(int counter, String line) {
        int endOfTheBrackets = 0;
        for (int i = counter + 1; i < line.length(); i++) {
            if (line.charAt(i) == '"') {
                endOfTheBrackets = i;
                break;
            }
        }
        return endOfTheBrackets;
    }

    /**
     * Jumps to the next coma
     * @param counter - line index from which
     *                we should start to jump over the coma
     * @param line - from the file
     * @return - index by the coma ( , )
     */
    private int jumpToTheComa(int counter, String line) {
        int endOfTheComa = 0;
        for (int i = counter; i < line.length(); i++) {
            if (line.charAt(i) == ',') {
                endOfTheComa = i;
                break;
            }
        }
        return endOfTheComa;
    }

    /**
     * @return - Number of the lines in the file
     */
    private int countLines() {
        int lineNumber = 0;
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
            while (true) {
                line = reader.readLine();
                if (line == null) break;
                //println(line);
                lineNumber++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            println("I can`t find file. :(");
            e.printStackTrace();
        } catch (IOException e) {
            println("There are some troubles with file. :(");
            e.printStackTrace();
        }

        return lineNumber;
    }
}

