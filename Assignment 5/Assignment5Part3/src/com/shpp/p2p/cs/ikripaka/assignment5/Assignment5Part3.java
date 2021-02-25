package com.shpp.p2p.cs.ikripaka.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * ----- LICENSE PLATES GAME -----
 * This game consists in the fact that you have to enter the car number of the car,
 * which consists of 3 letters.
 * The program produces words that can be drawn from these 3 letters using the dictionary
 */
public class Assignment5Part3 extends TextProgram {

    private static final int MAX_CAPACITY = 3;
    private static final String VOCABULARY_FILE = "assets/en-dictionary.txt";

    public void run() {
        String input;
        println("Print '0000000', if you want to interrupt the session. ");
        while (true) {
            input = readLine("Enter license plates of cars: ");
            if (input.equals("0000000")) break;
            println(compareLettersWithVocabulary(input));
            println("*-- *-- * --* --*");
        }
    }

    /**
     * 1 - Creates new buffered reader which read a dictionary.
     * 2 - Finds three letters in the license plate
     * 3 - Compares letters with words
     *
     * @return - String line
     */
    private String compareLettersWithVocabulary(String userLine) {
        String word;

        if (userLine.length() > MAX_CAPACITY || userLine.length() < MAX_CAPACITY) {
            return "Incorrect license plate";

        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(VOCABULARY_FILE));
            char[] letters = new char[3];
            letters = findThreeLetters(letters, userLine);
            println(letters[0] + "" + letters[1] + "" + letters[2]);
            ArrayList<String> result = new ArrayList<>();
            word = reader.readLine();
            while (word != null) {
                if (compareWithWord(word.toCharArray(), letters)){
                    result.add(word);
                }
                word = reader.readLine();
            }
            reader.close();
            return " Plate of card compare with word: " + result.toString();


        } catch (FileNotFoundException e) {
            println("I can`t find file.");
            e.printStackTrace();
        } catch (IOException e) {
            println("You have some troubles with file");
            e.printStackTrace();
        }
        return "I don`t find the word. Please, try again.";
    }

    /**
     * Compares letters with word
     * @param word - word from dictionary
     * @param letters - letters from the license plate
     * @return - (true - compare) (false - not compare)
     */
    private boolean compareWithWord(char[] word, char[] letters) {
        int counter = 0, rightLetters = 0;
        for (int i = 0; i < word.length; i++) {
            if (counter <= 2 && word[i] == letters[counter]) {
                rightLetters++;
                counter++;
            }
        }
        return rightLetters == 3;
    }

    /**
     * Finds three letters in the license plate
     * @param letters - array in which we write value
     * @param lettersInLicensePlate - array with letters which user put
     * @return - array with letters
     */
    private char[] findThreeLetters(char[] letters, String lettersInLicensePlate) {
        lettersInLicensePlate = lettersInLicensePlate.toLowerCase();
        int j = 0;
        for (int i = 0; i < MAX_CAPACITY; i++) {
            letters[i] = lettersInLicensePlate.charAt(i);
        }

        return letters;
    }
}
