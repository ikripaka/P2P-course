package com.shpp.cs.ikripaka.assignment7;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import com.shpp.cs.a.simple.SimpleProgram;

import java.awt.event.*;

import javax.swing.*;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {

    /* Method: init() */

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */

    private JTextField nameField;
    private NameSurferGraph graph;
    private NameSurferDataBase dataBase;

    public void init() {
        JLabel nameLabel = new JLabel();
        nameLabel.setText("Name:");
        add(nameLabel, NORTH);

        nameField = new JTextField(NDECADES);
        nameField.setActionCommand("EnterPressed");
        nameField.addActionListener(this);
        add(nameField, NORTH);

        JButton graphButton = new JButton("Graph");
        graphButton.setActionCommand("PrintGraph");
        add(graphButton, NORTH);

        JButton clearButton = new JButton("Clear");
        clearButton.setActionCommand("ClearField");
        add(clearButton, NORTH);

        graph = new NameSurferGraph();
        add(graph);

        dataBase = new NameSurferDataBase("assets/" + NAMES_DATA_FILE);

        addActionListeners();
    }

    /* Method: actionPerformed(e) */

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals("EnterPressed") || cmd.equals("PrintGraph")) {


            if (dataBase.findEntry(nameField.getText()) != null) {
                NameSurferEntry entry = dataBase.findEntry(nameField.getText());
                graph.addEntry(entry);
            } else {
                System.out.println("I can`t find this name.");
            }


        } else if (cmd.equals("ClearField")) {
            System.out.println("Clear!");
            graph.clear();
        }

    }

}
