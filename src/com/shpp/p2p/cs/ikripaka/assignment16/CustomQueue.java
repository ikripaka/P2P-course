package com.shpp.p2p.cs.ikripaka.assignment16;

import java.util.Iterator;

/**
 * Creates Collection (CustomQueue)
 *
 * @param <T> - Collection type
 */
public class CustomQueue<T> implements Iterable<T> {
    private Node<T> head;
    private int size = 0;

    /**
     * Inserts the specified value into this queue
     *
     * @param value - value that inserts
     */
    public void add(T value) {
        Node<T> node = new Node<>(value, head);
        head = node;
        size++;
    }

    /**
     * Retrieves, but does not remove,
     * the head of this queue,
     * or returns null if this queue is isEmpty.
     *
     * @return - element in peek
     */
    public T peek() {
        Node<T> node = head;
        for (int i = 0; i < size - 1; i++) {
            node = node.next;
        }
        return node.data;
    }

    /**
     * Retrieves and removes the head of this queue,
     * or returns NullPointerException if this queue is isEmpty.
     *
     * @return - deleted element data
     */
    public T poll() {
        if (isEmpty())
            throw new NullPointerException();

        T data;
        if (size == 1) {
            data = head.data;
            head = null;
        } else {
            Node<T> current = head;
            for (int i = 0; i < size - 2; i++) {
                current = current.next;
            }
            data = current.next.data;
            current.next = null;
        }
        size--;
        return data;
    }

    /**
     * Tests if this queue is isEmpty.
     *
     * @return - (true) Queue is isEmpty/ (false) Queue has elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Gives size of Queue to user
     * @return - Queue size
     */
    public int getSize(){
        return size;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return !isEmpty();
            }

            @Override
            public T next() {
                return poll();
            }
        };
    }

    /**
     * Node for Queue
     */
    private static class Node<T> {
        private Node<T> next;
        private T data;

        Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

}

