package com.shpp.p2p.cs.ikripaka.assignment15;

import com.shpp.p2p.cs.ikripaka.assignment17.CustomHashMap;
import com.shpp.p2p.cs.ikripaka.assignment17.CustomPriorityQueue;

/**
 * HuffmanTreeBuilder build huffman tree using:
 * - PriorityQueue
 * - Node
 */

class HuffmanTreeBuilder {
    private CustomHashMap<Byte, Integer> symbolFrequency;
    private CustomHashMap<Byte, String> ciphersForSymbols;
    private CustomPriorityQueue<Node> priorityQueue;

    /**
     * Constructor
     *
     * @param symbolFrequency - symbol frequency in the file
     */
    HuffmanTreeBuilder(CustomHashMap<Byte, Integer> symbolFrequency) {
        this.symbolFrequency = symbolFrequency;

        priorityQueue = new CustomPriorityQueue<>(Node.class);
        ciphersForSymbols = new CustomHashMap<>();
    }

    /**
     * Builds Huffman tree
     */
    void buildTree() throws Exception {
        fillInPriorityQueue();
        createAssociationTree();
        buildCodeSequence();
    }

    /**
     * Fills in priorityQueue with symbolFrequency
     */
    private void fillInPriorityQueue() {
        for (Byte key : symbolFrequency) {
            priorityQueue.add( new Node(key, symbolFrequency.get(key)));
        }
    }

    /**
     * Creates association tree
     */
    private void createAssociationTree() throws Exception {
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
    private void buildCodeSequence() throws Exception {
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
     *
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

    CustomHashMap<Byte, String> getCiphersForSymbols() {
        return ciphersForSymbols;
    }
}
