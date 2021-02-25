package com.shpp.p2p.cs.ikripaka.assignment16;

import java.util.Iterator;

/**
 * Creates Collection (CustomArrayList)
 *
 * @param <T> - Collection type
 */
public class CustomArrayList<T> implements Iterable<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size = 0;

    /**
     * Constructs an isEmpty list with an initial capacity of ten.
     */
    public CustomArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs an isEmpty list with custom capacity.
     *
     * @param size
     */
    public CustomArrayList(int size) {
        elements = (T[]) new Object[size];
    }

    /**
     * Adds new element to the array
     *
     * @param value - element
     */
    public void add(T value) {
        checkCapacity();
        elements[size] = value;
        size++;
    }

    /**
     * Increases the capacity of this ArrayList instance
     */
    private void ensureCapacity() {
        int newCapacity = (int) (size * 1.5);
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }

    /**
     * Checks array capacity
     */
    private void checkCapacity() {
        if (size + 1 > elements.length) {
            ensureCapacity();
        }
    }

    /**
     * Checks index for correctness
     *
     * @param index - сhecked index
     */
    private void checkIndex(int index) {
        if (index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Adds new element to the array at the specified index
     *
     * @param index - index
     * @param value - element
     */
    public void add(int index, T value) {
        checkCapacity();
        checkIndex(index);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    /**
     * Clears customArray from all values
     */
    public void clear() {
        size = 0;
    }

    /**
     * Searches for object o in the array
     *
     * @param o - object that user want to find
     * @return - (true) if el is here / (false) if el isn't in the array
     */
    public boolean contains(Object o) {
        return findValueIndex(o, 0) != -1;
    }

    public int indexOf(Object o) {
        return findValueIndex(o, 0);
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
     * Returns element value at specified index
     *
     * @param index - el index
     * @return - element
     */
    public T get(int index) {
        checkIndex(index);
        if (isEmpty())
            throw new NullPointerException();
        return elements[index];
    }

    /**
     * Returns true if this list contains no elements
     *
     * @return - (true) if this list contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes element at the specified index
     *
     * @param index - index of el that have been deleted
     * @return - deleted element value
     */
    public T remove(int index) {
        if (isEmpty())
            throw new NullPointerException();
        checkIndex(index);
        T el = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - (index));
        size--;
        return el;
    }

    /**
     * Replaces value to another at the specified index
     *
     * @param index - index
     * @param value - the value to be changed
     * @return - replaced value value
     */
    public T set(int index, T value) {
        if (isEmpty())
            throw new NullPointerException();
        checkIndex(index);
        T el = elements[index];
        elements[index] = value;
        return el;
    }

    /**
     * Returns the number of elements at the list
     *
     * @return - array size
     */
    public int size() {
        return size;
    }

    /**
     * Reduces array capacity to current array size
     */
    public void trimToSize() {
        T[] newArray = (T[]) new Object[size];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }

    /**
     * Returns array with all specified values
     *
     * @return - array with data
     */
    public T[] toArray() {
        T[] newArray = (T[]) new Object[size];
        System.arraycopy(elements, 0, newArray, 0, size);
        return newArray;
    }


    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {

        return new Iterator<>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                T el = elements[index];
                index++;
                return el;
            }
        };
    }
}
