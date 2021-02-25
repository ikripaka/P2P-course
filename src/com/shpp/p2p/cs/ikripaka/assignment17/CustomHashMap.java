package com.shpp.p2p.cs.ikripaka.assignment17;

import java.util.*;

/**
 * This class represents custom HashMap implementation
 *
 * @param <K> - key type
 * @param <V> - value type
 */
public class CustomHashMap<K, V> implements Iterable<K> {
    private static final double SIZE_INCREASE_RATIO = 1.5;
    private static final int DEFAULT_SIZE = 15;
    private static final double INCREASE_CONDITION_COEFFICIENT = 0.75;
    /* Contains all CustomPriorityQueue elements */
    private Node<K, V>[] elements;
    private int size = 0;

    /**
     * Constructs an empty HashMap with an initial capacity of ten.
     */
    public CustomHashMap() {
        this(DEFAULT_SIZE);
    }

    /**
     * Constructs an empty HashMap with custom capacity.
     *
     * @param size - size of HashMap that would be created
     */
    public CustomHashMap(int size) {
        elements = new Node[size];
    }

    /**
     * Adds new element to the CustomHashMap
     *
     * @param key   - key with which element to be associated
     * @param value - value to be associated with the specified key
     * @return - the previous value associated with key, or null if there was no mapping for key
     */
    public V put(K key, V value) {
        if (size + 1 > elements.length * INCREASE_CONDITION_COEFFICIENT) {
            ensureCapacity();
        }
        int hash = hash(key);
        Node<K, V> newNode = new Node<>(key, value);
        int index = indexFor(hash);
        if (elements[index] == null) {
            elements[index] = newNode;
            size++;
        } else {
            Node<K, V> currentNode = findNodeWithSimilarKey(index, key);
            if (Objects.equals(currentNode.key,key)) {
                V oldValue = currentNode.value;
                currentNode.value = value;
                return oldValue;
            } else {
                currentNode.next = newNode;
                size++;
            }
        }
        return null;
    }

    /**
     * If the specified key is not already associated
     * with a value (or is mapped to null) associates it
     * with the given value and returns null, else returns the current value.
     *
     * @param key   - key with which element to be associated
     * @param value - value to be associated with the specified key
     * @return
     */
    public V putIfAbsent(K key, V value) {
        int hash = hash(key);
        int index = indexFor(hash);
        Node<K, V> node = findNodeWithSimilarKey(index, key);
        if (!containsKey(key)) {
            node.next = new Node<>(key, value);
            size++;
            return null;
        } else if (Objects.equals(node.value, null)) {
            node.value = value;
            return null;
        }
        return value;
    }

    /**
     * Ensures array capacity
     */
    private void ensureCapacity() {
        Node<K, V>[] oldArray = elements;
        elements = new Node[(int) (elements.length * SIZE_INCREASE_RATIO)];
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != null) {
                Node<K, V> currentNode = oldArray[i];
                while (currentNode.next != null) {
                    moveOneNode(currentNode);
                    currentNode = currentNode.next;
                }
                moveOneNode(currentNode);
            }
        }
    }

    /**
     * Moves one node from one array to new one
     *
     * @param node - node to be moved
     */
    private void moveOneNode(Node<K, V> node) {
        int newIndex = indexFor(hash(node.key));
        Node<K, V> newNode = new Node<>(node.key, node.value);

        if (elements[newIndex] == null) {
            elements[newIndex] = newNode;
        } else {
            Node<K, V> newCurrentNode = elements[newIndex];
            while (newCurrentNode.next != null) {
                newCurrentNode = newCurrentNode.next;
            }
            newCurrentNode.next = newNode;
        }
    }

    /**
     * Finds
     *
     * @param index - array index where node is situated
     * @param key   - key with which to be associated specified value
     * @return - node with similar key or the last one
     */
    private Node<K, V> findNodeWithSimilarKey(int index, Object key) {
        Node<K, V> currentNode = elements[index];
        while (currentNode.next != null) {
            if (Objects.equals(currentNode.key, key)) {
                return currentNode;
            }
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    /**
     * Calculates index for element according to his hash
     *
     * @param hash - key hash
     * @return - array index
     */
    private int indexFor(int hash) {
        return Math.abs(hash) % elements.length;
    }

    /**
     * Calculates hash to the element key
     *
     * @param key - key with which to be associated specified value
     * @return - hash
     */
    private int hash(Object key) {
        return key == null ? 0 : key.hashCode();
    }

    /**
     * Retrieves and returns element value according to key
     *
     * @param key - key with which to be associated specified value
     * @return - element value or null if there are no key in the array
     */
    public V get(Object key) {
        if (containsKey(key)) {
            int hash = hash(key);
            int index = indexFor(hash);
            Node<K, V> node = findNodeWithSimilarKey(index, key);
            if (Objects.equals(key,node.key)) {
                return node.value;
            }
        }
        return null;
    }

    /**
     * Retrieves and returns element value according
     * to key or returns default value if there are no key in CustomHashMap
     *
     * @param key          - key with which to be associated specified value
     * @param defaultValue - value that returns if there are no key in the array
     * @return - value of specified element or default value of there are no key in array
     */
    public V getOrDefault(Object key, V defaultValue) {
        if (containsKey(key)) {
            int hash = hash(key);
            int index = indexFor(hash);
            Node<K, V> node = findNodeWithSimilarKey(index, key);
            return node.value;
        }
        return defaultValue;
    }

    /**
     * Searches for specified value in the CustomHashMap
     *
     * @param value - specified value to be searched
     * @return - (true) if element is available
     */
    public boolean containsValue(Object value) {
        for (Node<K, V> node : elements) {
            while (node.next != null) {
                if (Objects.equals(node.value, value)) {
                    return true;
                }
                node = node.next;
            }
            if (Objects.equals(node.value, value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Searches for specified key in the CustomHashMap
     *
     * @param key - specified key to be searched
     * @return - (true) if key is available
     */
    public boolean containsKey(Object key) {
        int index = indexFor(hash(key));
        if (elements[index] != null) {
            Node<K, V> currentNode = findNodeWithSimilarKey(index, key);
            return Objects.equals(currentNode.key, key);
        }
        return false;
    }

    /**
     * Checks if CustomHashMap is empty
     *
     * @return (true) if it's empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes the specified element according to the key
     *
     * @param key - key with which to be associated specified value
     * @return - element value or null if there are no such element
     */
    public V remove(Object key) {
        if (containsKey(key)) {
            size--;
            int hash = hash(key);
            int index = indexFor(hash);
            Node<K, V> current, previous = null;
            current = elements[index];
            while (current.next != null) {
                if (Objects.equals(key, current.key)) {
                    break;
                }
                previous = current;
                current = current.next;
            }

            V value = current.value;
            previous.next = current.next;
            return value;

        }
        return null;
    }

    /**
     * Clears all CustomHashMap
     */
    public void clear() {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * Replaces elements value if it is available
     *
     * @param key   - key with which to be associated specified value
     * @param value - value to be associated with the specified key
     * @return - old value or null if there are ni such element
     */
    public V replace(K key, V value) {
        int hash = hash(key);
        int index = indexFor(hash);
        Node<K, V> current = findNodeWithSimilarKey(index, hash);
        if (Objects.equals(key, current.key)) {
            V oldValue = current.value;
            current.value = value;
            return oldValue;
        }
        return null;
    }

    /**
     * Replaces current value to new one if old and current value is coincide
     *
     * @param key      - key with which to be associated specified value
     * @param oldValue - value expected to be associated with the specified key
     * @param newValue - value to be associated with the specified key
     * @return - true if the value was replaced
     */
    public boolean replace(K key, V oldValue, V newValue) {
        int hash = hash(key);
        int index = indexFor(hash);
        Node<K, V> currentNode = findNodeWithSimilarKey(index, hash);
        if (key.equals(currentNode.key) && Objects.equals(oldValue, currentNode.value)) {
            currentNode.value = newValue;
            return true;
        }
        return false;
    }

    /**
     * Returns CustomHashMap size
     *
     * @return - CustomHashMap size
     */
    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("[]");
        int counter = 0;
        for (Node<K, V> node : elements) {
            if (node != null) {
                while (node.next != null) {
                    builder.insert(builder.length() - 1, node.toString() + (counter == 0 ? "" : " ,"));
                    node = node.next;
                    counter++;
                }
                builder.insert(builder.length() - 1, node.toString() + (counter == 0 ? "" : " ,"));
                counter++;
            }
        }
        return builder.toString();
    }

    /**
     * Gets all keys for iterator
     *
     * @return - array that filled with keys
     */
    private K[] getAllKeys() {
        K[] allKeys = (K[]) new Object[size];
        int counter = 0;
        for (Node<K, V> node : elements) {
            if (node != null) {
                while (node.next != null) {
                    allKeys[counter++] = node.key;
                    node = node.next;
                }
                allKeys[counter++] = node.key;
            }
        }
        return allKeys;
    }

    /**
     * Returns an iterator over CustomHashMap keys of type {@code K}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private K[] allKeys = getAllKeys();
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < allKeys.length;
            }

            @Override
            public K next() {
                return allKeys[index++];
            }
        };
    }


    /**
     * Represents node in what information stored
     *
     * @param <K> - key type
     * @param <V> - value type
     */
    private class Node<K, V> {
        private final K key;
        private V value;
        private Node<K, V> next;

        /**
         * Constructs one node
         *
         * @param key   - key with which to be associated specified value
         * @param value - specified value
         */
        private Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Gets string node representation
         * @return string node representation
         */
        public String toString() {
            return key + "=" + value;
        }
    }
}


