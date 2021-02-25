package com.shpp.p2p.cs.ikripaka.assignment5;

import com.shpp.cs.a.console.TextProgram;

/**
 * -----  SYLLABLE COUNTER ------
 * This class calculates the number of syllables in a word.
 */
public class Assignment5Part1 extends TextProgram {
    private static final char[] VOWEL_LETTERS = {'a', 'e', 'i', 'o', 'u', 'y'};

    public void run() {
        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.
         */
        while (true) {
            String word = readLine("Enter a single word: ");
            println("  Syllable count: " + syllablesIn(word));
        }
    }

    /**
     * Counts number of syllables
     * @param word - word entered by user
     * @return - number of syllables
     */
    private int syllablesIn(String word) {
        word = word.toLowerCase();
        int numberOfSyllables = 0;
        int wordLength = word.length();
        boolean isPreviousVovel = false;

        for (int i = 0; i < wordLength; i++) {
            // If the last letter - e
            if (i == wordLength - 1 && word.charAt(wordLength - 1) == 'e'){
                break;
            }

            // Check if the letter vowel
            for(int j = 0; j < VOWEL_LETTERS.length; j++){
                // If letter vowel
                if(word.charAt(i) == VOWEL_LETTERS[j]){
                    // If there is a couple (2 or 3) vowel letters
                    if(isPreviousVovel){
                        break;
                    }
                    numberOfSyllables++;
                    isPreviousVovel = true;
                    println("it is vovel");
                    break;
                }
                // If letter isn't vowel
                if(j == VOWEL_LETTERS.length - 1){
                    isPreviousVovel = false;
                    println("it isn`t vovel");
                }
            }

        }
        return numberOfSyllables == 0 ? 1: numberOfSyllables;
    }


}