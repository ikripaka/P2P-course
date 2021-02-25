package com.shpp.cs.ikripaka.assignment7;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

    private String name;
    private ArrayList<Integer> popularity;

    /* Constructor: NameSurferEntry(line) */

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {
        String[] splittedLine = line.split(" ");
        name = splittedLine[0];
        popularity = returnArrayList(splittedLine);
    }

    /**
     * Returns int Array from a data line
     *
     * @param line - a line that is separated by a space
     * @return - int array which contain popularity of name
     */
    private ArrayList<Integer> returnArrayList(String[] line) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 1; i < line.length; i++) {
            result.add(Integer.parseInt(line[i]));
        }
        return result;
    }

    /* Method: getName() */

    /**
     * Returns the name associated with this entry.
     */
    public String getName() {
        return name;
    }

    /* Method: getRank(decade) */

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        return popularity.get(decade);
    }

    /* Method: toString() */

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    @Override
    public String toString() {
        return name + Arrays.toString(popularity.toArray());
    }
}

