package com.shpp.p2p.cs.ikripaka.assignment16;

import java.util.Iterator;

/**
 * This class represents custom PriorityQueue implementation
 *
 * @param <T> - generic type that must have Comparable
 */
public class CustomPriorityQueue<T extends Comparable<T>> implements Iterable {

    private static final int DEFAULT_SIZE = 10;
    /* Contains all CustomPriorityQueue elements */
    private T[] elements;
    private int size = 0;

    /**
     * Constructs an isEmpty PriorityQueue with an initial capacity of ten.
     */
    public CustomPriorityQueue() {
        this(DEFAULT_SIZE);
    }

    /**
     * Constructs an isEmpty PriorityQueue with custom capacity.
     *
     * @param size
     */
    public CustomPriorityQueue(int size) {
        elements = (T[]) new Comparable[size];
    }

    /**
     * Adds element to PriorityQueue
     *
     * @param value - element that will be added
     */
    public void add(T value) {
        checkCapacity();
        elements[size] = value;
        int currentElement = size;
        int parent = (currentElement - 1) / 2;
        size++;
        while (parent >= 0 && elements[currentElement].compareTo(elements[parent]) < 0) {
            T el = elements[parent];
            elements[parent] = elements[currentElement];
            elements[currentElement] = el;

            currentElement = parent;
            parent = (currentElement - 1) / 2;
        }
    }

    /**
     * Retrieves and returns peek of the PriorityQueue
     *
     * @return - PriorityQueue peek
     */
    public T peek() {
        checkCapacity();
        return elements[0];
    }

    /**
     * Removes , retrieves and returns element in the head of the queue
     *
     * @return
     */
    public T poll() throws Exception {
        checkSize();
        checkCapacity();
        size--;
        T el = elements[0];
        elements[0] = elements[size];
        heapify();
        return el;
    }

    /**
     * Removes all elements from PriorityQueue
     */
    public void removeAll() {
        elements = (T[]) new Comparable[size];
        size = 0;
    }

    /**
     * Makes a binary heap correct (parent > children) from the beginning
     */
    private void heapify() {
        int leftChild, rightChild, theSmallestChild;
        int i = 0;

        while (true) {
            leftChild = i * 2 + 1;
            rightChild = i * 2 + 2;
            theSmallestChild = i;

            if (leftChild < size && elements[leftChild].compareTo(elements[theSmallestChild]) < 0) {
                theSmallestChild = leftChild;
            }
            if (rightChild < size && elements[rightChild].compareTo(elements[theSmallestChild]) < 0) {
                theSmallestChild = rightChild;
            }
            if (i == theSmallestChild) {
                break;
            }

            T value = elements[i];
            elements[i] = elements[theSmallestChild];
            elements[theSmallestChild] = value;
            i = theSmallestChild;
        }

    }


    /**
     * Checks size of the PriorityQueue
     * if size < bigger size it will increase capacity
     */
    private void checkCapacity() {
        if (size + 1 > elements.length) {
            ensureCapacity();
        }
    }

    /**
     * Increases the capacity of this ArrayList instance
     */
    private void ensureCapacity() {
        int newCapacity = (int) (size * 1.5);
        T[] newArray = (T[]) new Comparable[newCapacity];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }


    /**
     * Makes String data reproduction
     *
     * @return - String array reproduction
     */
    public String toString() {
        StringBuilder builder = new StringBuilder("[]");
        for (int i = 0; i < size; i++) {
            if (i == 0)
                builder.insert(builder.length() - 1, elements[i]);
            else
                builder.insert(builder.length() - 1, ", " + elements[i]);
        }
        return builder.toString();
    }

    /**
     * Returns the number of elements of collection
     */
    public int size() {
        return size;
    }

    /**
     * Checks if th PriorityQueue is empty
     */
    public boolean isEmpty() {
        if (size == 0)
            return true;
        return false;
    }

    /**
     * Searches for object o in the array
     *
     * @param o - object that user want to find
     * @return - (true) if el is here / (false) if el isn't in the array
     */
    public boolean contains(Object o) throws Exception {
        checkSize();
        return findValueIndex(o, 0) != -1;
    }

    /**
     * Searches for object o index in the array
     *
     * @param o       - object that user want to find
     * @param counter
     * @return - (any number > 0) if el is here / (-1) if el isn't in the array
     */
    private int findValueIndex(Object o, int counter) {
        if (counter < size) {
            if (o.equals(elements[counter])) {
                return counter;
            } else {
                return findValueIndex(o, ++counter);
            }
        }
        return -1;
    }

    /**
     * Throws Exception if PriorityQueue is empty
     */
    private void checkSize() throws Exception {
        if (isEmpty())
            throw new Exception("PriorityQueue is empty");
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator iterator() {
        return new Iterator() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public Object next() {
                return elements[index++];
            }
        };
    }
}
