package com.shpp.p2p.cs.ikripaka.assignment11;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class consists of the constants which is using in the classes
 */

class Constants {
    Pattern NUMBER = Pattern.compile("[\\d|\\.]+");
    Pattern LETTERS = Pattern.compile("[a-z|A-Z]+");

    Pattern ALL_ARITHMETICAL_OPERATIONS = Pattern.compile("[/^*+\\-()]");
    List<String> FUNCTIONS = Arrays.asList("sin", "cos", "tan", "atan", "log10", "log2", "sqrt");
    /**
     * Checks if the symbol - operator
     *
     * @param symbols - one symbol in String
     * @return - true/false  if symbol matches
     */
    boolean matches(String symbols, Pattern pattern) {
        Matcher match = pattern.matcher(String.valueOf(symbols));
        return match.matches();
    }
}
