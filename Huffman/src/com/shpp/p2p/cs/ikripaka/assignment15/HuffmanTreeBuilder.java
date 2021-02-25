package com.shpp.p2p.cs.ikripaka.assignment15;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * HuffmanTreeBuilder build huffman tree using:
 * - PriorityQueue
 * - Node
 */

class HuffmanTreeBuilder {
    private HashMap<Byte, Integer> symbolFrequency;
    private HashMap<Byte, String> ciphersForSymbols;
    private PriorityQueue<Node> priorityQueue;

    /**
     * Constructor
     *
     * @param symbolFrequency - symbol frequency in the file
     */
    HuffmanTreeBuilder(HashMap<Byte, Integer> symbolFrequency) {
        this.symbolFrequency = symbolFrequency;
        priorityQueue = new PriorityQueue<>();
        ciphersForSymbols = new HashMap<>();
    }

    /**
     * Builds Huffman tree
     */
    void buildTree() {
        fillInPriorityQueue();
        createAssociationTree();
        buildCodeSequence();
    }

    /**
     * Fills in priorityQueue with symbolFrequency
     */
    private void fillInPriorityQueue() {
        for (Byte key : symbolFrequency.keySet()) {
            priorityQueue.add(new Node(key, symbolFrequency.get(key)));
        }
    }

    /**
     * Creates association tree
     */
    private void createAssociationTree() {
        while (priorityQueue.size() > 1) {
            Node first = priorityQueue.poll();
            Node second = priorityQueue.poll();
            Node node = new Node(first, second);
            priorityQueue.add(node);
        }
    }

    /**
     * Turns on building code sequence for symbols
     */
    private void buildCodeSequence() {
        Node node = priorityQueue.poll();
        if (node != null) {
            if (!node.isNodeHasChildren()) {
                ciphersForSymbols.put(node.symbol, "0");
            } else {
                buildCode("", node);
            }
        }
    }

    /**
     * Builds code for symbols
     * @param code - String code sequence
     * @param node - one node
     */
    private void buildCode(String code, Node node) {
        Node left = node.getLeft();
        Node right = node.getRight();
        if (left.isNodeHasChildren()) {
            buildCode(code + "0", left);
        } else {
            ciphersForSymbols.put(left.symbol, code + "0");
        }

        if (right.isNodeHasChildren()) {
            buildCode(code + "1", right);
        } else {
            ciphersForSymbols.put(right.symbol, code + "1");
        }
    }

    HashMap<Byte, String> getCiphersForSymbols() {
        return ciphersForSymbols;
    }
}
