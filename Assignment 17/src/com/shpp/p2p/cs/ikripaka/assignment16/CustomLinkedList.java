package com.shpp.p2p.cs.ikripaka.assignment16;

import java.util.Iterator;
import java.util.Objects;

/**
 * Creates Collection(CustomLinkedList)
 *
 * @param <T> - Collection type
 */
public class CustomLinkedList<T> implements Iterable<T> {
    private Node<T> head, end;
    private int size = 0;

    /**
     * Appends the specified value to the end of this list.
     *
     * @param value - value that appends
     */
    public void add(T value) {
        Node<T> newNode = new Node<>(value, null, end);
        if (size == 0) {
            head = newNode;
            end = head;
        } else {
            end.next = newNode;
            end = newNode;
        }
        size++;
    }

    /**
     * Adds new node to the end of the list
     * @param index - index of the specified element
     * @param value - value that inserts
     */
    public void add(int index, T value) {
        checkSize();
        checkIndex(index);
        Node<T> current;
        current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        Node<T> node = new Node<>(value, current, current.prev);
        current.prev = node;
        if (index != 0) current.prev.next = node;
        else head = node;
    }

    /**
     * Inserts the specified value at the beginning of this list.
     *
     * @param value - value that inserts
     */
    public void addFirst(T value) {
        if (size == 0) {
            head = new Node<>(value, null, null);
            end = head;
        } else {
            Node<T> newNode = new Node<>(value, head, null);
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    /**
     * Whether or not list is found
     *
     * @return - (true) - isEmpty/(false) - filled with element(s)
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Appends the specified value to the end of this list.
     *
     * @param value - ellement that appends ti the end
     */
    public void addLast(T value) {
        add(value);
    }

    /**
     * Removes all of the elements from this list.
     */
    public void clear() {
        size = 0;
        head = null;
        end = null;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index - index of element
     * @return - element data at the specified position
     */
    public T get(int index) {
        checkIndex(index);
        checkSize();
        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.data;
    }

    /**
     * Checks index (if it correct)
     *
     * @param index - current index
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Returns the head element in this list.
     *
     * @return - head element in this list
     */
    public T getFirst() {
        checkSize();
        return head.data;
    }

    /**
     * Returns the end element in this list.
     *
     * @return - end element in this list
     */
    public T getLast() {
        checkSize();
        return end.data;
    }

    /**
     * Returns true if this list contains the specified element.
     *
     * @param element - el to be searched
     * @return - (true) if el is in the list
     */
    public boolean contains(Object element) {
        return findValueIndex(element) != -1;
    }

    /**
     * Returns the index of the head occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     *
     * @param element - element that function search
     * @return - index of element of -1 if there are no such element
     */
    public int indexOf(Object element) {
        return findValueIndex(element);
    }

    /**
     * Searches for object o index in the array
     *
     * @param o - object that user want to find
     * @return - (any number > 0) if el is here / (-1) if el isn't in the array
     */
    private int findValueIndex(Object o) {
        Node<T> node = head;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, node.data)) {
                return i;
            }
            node = node.next;
        }
        return -1;
    }

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index - index of specified element
     * @return - element data that was in it
     */
    public T remove(int index) {
        checkSize();
        checkIndex(index);
        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        T element = node.data;
        node.next.prev = node.prev;
        node.prev.next = node.next;
        size--;
        return element;
    }

    /**
     * Checks size (if it is correct)
     *
     * @return - (true) correct / (false) incorrect
     */
    private void checkSize() {
        if (isEmpty())
            throw new NullPointerException();
    }

    /**
     * Removes and returns the head element from this list.
     *
     * @return - element data that was in it
     */
    public T removeFirst() {
        checkSize();
        T firstNodeData = head.data;
        if (size == 1) {
            head = null;
            end = null;
        } else {
            Node<T> newHead = head.next;
            head.next.prev = null;
            head.next = null;
            head = newHead;
        }
        size--;
        return firstNodeData;
    }

    /**
     * Removes and returns the end element from this list.
     *
     * @return - element data that was in it
     */
    public T removeLast() {
        checkSize();
        T lastNodeData = end.data;
        if (size == 1) {
            head = null;
            end = null;
        } else {
            end = end.prev;
            end.next = null;
        }
        size--;
        return lastNodeData;
    }

    /**
     * Replaces the element at the specified
     * position in this list with the specified element.
     *
     * @param index   - index of the specified element
     * @param element - element to what we want to change
     * @return - element data that was in it
     */
    public T set(int index, T element) {
        checkIndex(index);
        checkSize();
        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        T prevElement = node.data;
        node.data = element;
        return prevElement;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return - CustomLinkedList size
     */
    public int size() {
        return size;
    }

    /**
     * Returns an array containing all of the elements
     * in this list in proper sequence (from head to end element).
     *
     * @return - Object[] that include all elements
     */
    public T[] toArray() {
        T[] array = (T[]) new Object[size];
        Node<T> node = head;
        for (int i = 0; i < size; i++) {
            array[i] = node.data;
            node = node.next;
        }
        return array;
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {

            Node<T> current = head;

            /**
             * @return true if not reached the end item in the list
             */
            @Override
            public boolean hasNext() {
                return current != end.next;
            }

            /**
             * Saves the value of the current item;
             * Overrides current item as next
             *
             * @return a value of current node
             */
            @Override
            public T next() {
                T value = current.data;
                current = current.next;
                return value;
            }
        };
    }

    /**
     * Node for CustomLinkedList
     */
    private static class Node<T> {
        private Node<T> next, prev;
        private T data;

        Node(T data, Node<T> next, Node<T> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }


}
