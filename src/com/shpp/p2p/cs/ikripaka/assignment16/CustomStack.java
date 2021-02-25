package com.shpp.p2p.cs.ikripaka.assignment16;

import java.util.Iterator;

/**
 * Creates Collection (CustomStack)
 *
 * @param <T> - Collection type
 */

public class CustomStack<T> implements Iterable<T> {
    private Node<T> head = null;
    private int size = 0;

    /**
     * Pushes a value onto the top of this stack.
     */
    public T push(T value) {
        head = new Node<>(value, head);
        size++;
        return value;
    }

    /**
     * Removes the object at the top of this stack and returns that
     * object as the value of this function.
     */
    public T pop() {
        if (isEmpty())
            throw new NullPointerException();
        Node<T> next = head.next;
        T data = head.data;
        head.next = null;
        head = next;
        size--;
        return data;

    }

    /**
     * Looks at the object at the top of this stack without removing it
     * from the stack.
     * Returns NullPointerException is stack is empty
     */
    public T peek() {
        if (isEmpty())
            throw new NullPointerException();
        return head.data;
    }

    /**
     * Tests if this stack is isEmpty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Gives size of Stack to user
     * @return - Stack size
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
                return pop();
            }
        };
    }

    /**
     * One node for Stack
     * Has one link to next element
     */
    private static class Node<T> {
        private Node<T> next;
        private T data;

        Node(T data, Node<T> prev) {
            this.data = data;
            next = prev;
        }
    }


}
