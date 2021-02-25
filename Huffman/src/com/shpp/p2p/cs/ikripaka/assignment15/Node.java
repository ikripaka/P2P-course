package com.shpp.p2p.cs.ikripaka.assignment15;

public class Node implements Comparable<Node> {
    private Node left, right;
    final int sum;
    final byte symbol;

    Node(Node left, Node right) {
        this.left = left;
        this.right = right;
        this.sum = left.sum + right.sum;
        symbol = 0;
    }

    Node(byte symbolByteCode, int sum) {
        left = null;
        right = null;
        this.sum = sum;
        this.symbol = symbolByteCode;
    }

    /**
     * Compares two Nodes
     * @param o - second node
     * @return - boolean array
     */
    @Override
    public int compareTo(Node o) {
        return Integer.compare(sum, o.sum);
    }

    boolean isNodeHasChildren(){ return left != null || right != null;}

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }
}
