package com.shpp.p2p.cs.ikripaka.assignment11;

import com.shpp.p2p.cs.ikripaka.assignment16.CustomArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class consists of the constants which is using in the classes
 */

class Constants {
    static final Pattern NUMBER = Pattern.compile("[\\d|\\.]+");
    static final Pattern LETTERS = Pattern.compile("[a-z|A-Z]+");

    static final Pattern ALL_ARITHMETICAL_OPERATIONS = Pattern.compile("[/^*+\\-()]");
    static final CustomArrayList<String> FUNCTIONS = new CustomArrayList<>();

    Constants(){
        String[] allFunctions = {"sin", "cos", "tan", "atan", "log10", "log2", "sqrt"};
        for(int i = 0; i < allFunctions.length; i++){
            FUNCTIONS.add(allFunctions[i]);
        }
    }
    /**
     * Checks if the symbol - operator
     *
     * @param symbols - one symbol in String
     * @return - true/false  if symbol matches
     */
    static boolean matches(String symbols, Pattern pattern) {
        Matcher match = pattern.matcher(String.valueOf(symbols));
        return match.matches();
    }
}
