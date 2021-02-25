package com.shpp.cs.ikripaka.assignment7;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NameSurferDataBase implements NameSurferConstants {

    private ArrayList<NameSurferEntry> data = new ArrayList<>(); // ArrayList contain lines from the file

    /* Constructor: NameSurferDataBase(filename) */

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */
    public NameSurferDataBase(String filename) { //конструктор

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            while (true) {
                String lineWithData = reader.readLine();
                if (lineWithData == null) break;
                data.add(new NameSurferEntry(lineWithData));
            }
            reader.close();
        }catch (IOException i){
            i.printStackTrace();
            System.err.println("You have some troubles with file! Fix file!");
        }
    }

    /* Method: findEntry(name) */

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {
        NameSurferEntry lineWithNecessaryData = null;
        for (int i = 0; i < data.size(); i++) {

            if (data.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
                lineWithNecessaryData = data.get(i);
                break;
            }
        }
        return lineWithNecessaryData;
    }


}

