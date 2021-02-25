package com.shpp.cs.ikripaka.assignment7;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */

    private ArrayList<String> names;
    private HashMap<String, NameSurferEntry> popularityDataHashMap;
    private Color[] graphColors = {Color.BLUE, Color.RED, Color.MAGENTA, Color.BLACK};

    // Constructor: NameSurferGraph

    public NameSurferGraph() {
        addComponentListener(this);
        popularityDataHashMap = new HashMap<>();
        names = new ArrayList<>();
        update();
    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        removeAll();
        popularityDataHashMap.clear();
        names.clear();
        update();
    }


    /* Method: addEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        // Verification for (name is repeated)
        int counter = 0;

        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equals(entry.getName())) {
                counter++;
                System.out.println("The name is repeated. Please, enter another name.");
                break;
            }
        }
        if(counter == 0){
            popularityDataHashMap.put(entry.getName(), entry);
            names.add(entry.getName());
            update();
        }
    }


    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        drawBackgroundGrid();

        JLabel label;
        GLine line;

        double decadeXOffset = (double) getWidth() / NDECADES;
        double x1, x2, y1, y2;

        if (popularityDataHashMap != null) {
            int hashMapSize = popularityDataHashMap.size();
            int colorCounter;

            // Goes through the HashMap
            for (int i = 0; i < hashMapSize; i++) {

                //Determine color
                colorCounter = i % graphColors.length;

                y2 = (getHeight() - 2 * GRAPH_MARGIN_SIZE * 1.0) / MAX_RANK * (popularityDataHashMap.get(names.get(i)).getRank(0));
                // Goes through the popularity data
                for (int j = 0; j < NDECADES; j++) {
                    x1 = (int) ((j * decadeXOffset) == 0 ? 0 : (j * decadeXOffset) - decadeXOffset);
                    x2 = (int) (j * decadeXOffset);
                    y1 = y2;

                    //Verification for equality 0 (for y2 coordinate)
                    if (popularityDataHashMap.get(names.get(i)).getRank(j) == 0) {
                        y2 = getHeight() - GRAPH_MARGIN_SIZE;
                    } else {
                        y2 = (getHeight() - 2 * GRAPH_MARGIN_SIZE * 1.0) / MAX_RANK * (popularityDataHashMap.get(names.get(i)).getRank(j)) + GRAPH_MARGIN_SIZE;
                    }

                    line = new GLine(x1, y1, x2, y2);
                    line.setColor(graphColors[colorCounter]);

                    //Verification for equality 0 (for JLabel)
                    if (popularityDataHashMap.get(names.get(i)).getRank(j) == 0 || popularityDataHashMap.get(names.get(i)).getRank(j) == 1000) {
                        label = new JLabel(names.get(i) + "*");
                        label.setLocation((int) (x2 + LABEL_OFFSET / 2), (int) (y2 - 2 * LABEL_OFFSET ));
                    } else {
                        label = new JLabel(names.get(i) + " " + (popularityDataHashMap.get(names.get(i)).getRank(j)));
                        label.setLocation((int) (x2 + LABEL_OFFSET / 2), (int) y2);
                    }

                    label.setFont(new Font("Arial", Font.BOLD, 11));


                    add(line);
                    add(label);
                }

            }
        }
    }

    // Paints
    private void drawBackgroundGrid() {
        JLabel decadeLabel;
        GLine line;

        double decadeXOffset = (double) getWidth() / NDECADES;
        double x1 = 0, y1 = 0, y2 = getHeight();

        // Paints vertical lines and decades
        for (int i = 0; i < NDECADES; i++) {
            line = new GLine(x1, y1, x1, y2);

            decadeLabel = new JLabel(String.valueOf(START_DECADE + i * 10));
            decadeLabel.setFont(new Font("Georgia", 1, 13));
            decadeLabel.setLocation((int) (i * decadeXOffset + LABEL_OFFSET), getHeight() - GRAPH_MARGIN_SIZE);

            add(line);
            add(decadeLabel);

            x1 += decadeXOffset;

        }
        // Draw horizontal lines
        line = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
        add(line);

        line = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
        add(line);
    }

    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}
