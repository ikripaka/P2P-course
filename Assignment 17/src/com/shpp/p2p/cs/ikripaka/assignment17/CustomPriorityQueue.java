package com.shpp.p2p.cs.ikripaka.assignment17;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.Iterator;


/**
 * This class represents custom PriorityQueue implementation
 *
 * @param <T> - generic type that must have Comparable
 */
public class CustomPriorityQueue<T> implements Iterable {
    //PriorityQueue
    private static final int DEFAULT_SIZE = 10;
    /* Contains all CustomPriorityQueue elements */
    private T[] elements;
    private int size = 0;
    private Comparator<? super T> comparator;

    /**
     * Constructs an isEmpty PriorityQueue with an initial capacity of ten.
     *
     * @param arrClass - class for array creating
     */
    public CustomPriorityQueue(Class<T> arrClass) {
        this(DEFAULT_SIZE, arrClass, null);
    }

    /**
     * Constructs an empty PriorityQueue with custom size
     *
     * @param size     - custom array size
     * @param arrClass - class for array creating
     */
    public CustomPriorityQueue(int size, Class<T> arrClass) {
        this(DEFAULT_SIZE, arrClass, null);
    }

    /**
     * Constructs an empty PriorityQueue with custom comparator
     *
     * @param arrClass   - class for array creating
     * @param comparator - comparator for comparing two variables
     */
    public CustomPriorityQueue(Class<T> arrClass, Comparator<? super T> comparator) {
        this(DEFAULT_SIZE, arrClass, comparator);
    }

    /**
     * Constructs an empty PriorityQueue with custom capacity and comparator.
     *
     * @param size       - custom array size
     * @param arrClass   -  class for array creating
     * @param comparator - comparator for comparing two variables
     */
    public CustomPriorityQueue(int size, Class<T> arrClass, Comparator<? super T> comparator) {
        if (size < 0)
            throw new ArrayIndexOutOfBoundsException();
        elements = (T[]) Array.newInstance(arrClass, size);
        this.comparator = comparator;
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

        if (comparator == null) {
            Object comparableCurrentObject = elements[currentElement];
            //change sign to opposite if you want min heap
            while (parent >= 0 && ((Comparable<? super T>) comparableCurrentObject).compareTo(elements[parent]) < 0) {
                T element = elements[parent];
                elements[parent] = elements[currentElement];
                elements[currentElement] = element;

                currentElement = parent;
                parent = (currentElement - 1) / 2;

                comparableCurrentObject = elements[currentElement];
            }
        } else {
            while (parent >= 0 && comparator.compare(elements[currentElement], elements[parent]) < 0) {
                T element = elements[parent];
                elements[parent] = elements[currentElement];
                elements[currentElement] = element;

                currentElement = parent;
                parent = (currentElement - 1) / 2;
            }
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
    public T poll() {
        checkSize();
        size--;
        T element = elements[0];
        elements[0] = elements[size];
        elements[size] = null;
        heapify();
        return element;
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

            // сделай метод сомпаре
            if (comparator == null) {

                if (leftChild < size) {
                    Object comparableObject = elements[leftChild];
                    if (((Comparable<? super T>) comparableObject).compareTo(elements[theSmallestChild]) < 0) {
                        theSmallestChild = leftChild;
                    }
                }
                if (rightChild < size) {
                    Object comparableObject = elements[rightChild];
                    if (((Comparable<? super T>) comparableObject).compareTo(elements[theSmallestChild]) < 0) {
                        theSmallestChild = rightChild;
                    }
                }

            } else {
                if (leftChild < size && comparator.compare(elements[leftChild], elements[theSmallestChild]) < 0) {
                    theSmallestChild = leftChild;
                }
                if (rightChild < size && comparator.compare(elements[rightChild], elements[theSmallestChild]) < 0) {
                    theSmallestChild = rightChild;
                }
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

    public T[] sort() {
        T[] sortedArray = (T[]) new Comparable[size];
        for (int i = 0; i < 200; i++) {
            sortedArray[i] = poll();
        }
        return sortedArray;
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
        return size == 0;
    }

    /**
     * Searches for object o in the array
     *
     * @param object - object that user want to find
     * @return - (true) if el is here / (false) if el isn't in the array
     */
    public boolean contains(Object object) {
        return !isEmpty() && findValueIndex(object, 0) != -1;
    }

    /**
     * Searches for object o index in the array
     *
     * @param object  - object that user want to find
     * @param counter -
     * @return - (any number > 0) if el is here / (-1) if el isn't in the array
     */
    private int findValueIndex(Object object, int counter) {
        if (counter < size) {
            if (object.equals(elements[counter])) {
                return counter;
            } else {
                return findValueIndex(object, ++counter);
            }
        }
        return -1;
    }

    /**
     * Throws Exception if PriorityQueue is empty
     */
    private void checkSize() {
        if (isEmpty())
            throw new ArrayIndexOutOfBoundsException();
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
