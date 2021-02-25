package com.shpp.p2p.cs.ikripaka.assignment16;

import java.util.Iterator;

/**
 * This class represents custom HashMap implementation
 *
 * @param <K> - key type
 * @param <V> - value type
 */
public class CustomHashMap<K, V> implements Iterable<V> {

    private static final double SIZE_INCREASE_RATIO = 1.5;
    private static final int DEFAULT_SIZE = 15;
    /* Contains all CustomPriorityQueue elements */
    private Node<K, V>[] elements;
    /* Contains all CustomPriorityQueue keys */
    private CustomLinkedList<K> keys;
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
        keys = new CustomLinkedList<>();
    }

    /**
     * Adds new element to the CustomHashMap
     *
     * @param key   - key with which element to be associated
     * @param value - value to be associated with the specified key
     * @return - the previous value associated with key, or null if there was no mapping for key
     */
    public V put(K key, V value) {
        if (!keys.contains(key))
            checkCapacity();
        int hash = hash(key);
        Node<K, V> newNode = new Node<>(hash, key, value);
        int index = indexFor(hash, elements.length);
        if (elements[index] == null) {
            elements[index] = newNode;
            size++;
            keys.add(key);
        } else {
            Node<K, V> currentNode = findNodeWithSimilarKey(index, key);
            if (currentNode.key.equals(key)) {
                V oldValue = currentNode.value;
                currentNode.value = value;
                return oldValue;
            } else {
                currentNode.next = newNode;
                keys.add(key);
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
        int index = indexFor(hash, elements.length);
        Node<K, V> node = findNodeWithSimilarKey(index, key);
        if (!keys.contains(key)) {
            node.next = new Node<>(hash, key, value);
            keys.add(key);
            size++;
            return null;
        } else if (node.value == null) {
            node.value = value;
            return null;
        }
        return value;
    }

    /**
     * Checks array capacity and ensures capacity if it need
     */
    private void checkCapacity() {
        if (size + 1 > elements.length) {
            ensureCapacity();
        }
    }

    /**
     * Ensures array capacity
     */
    private void ensureCapacity() {
        Node<K, V>[] newArray = new Node[(int) (elements.length * SIZE_INCREASE_RATIO)];
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != null) {
                Node<K, V> currentNode = elements[i];
                while (currentNode.next != null) {
                    moveOneNode(newArray, currentNode);
                    currentNode = currentNode.next;
                }
                moveOneNode(newArray, currentNode);
            }
        }
        elements = newArray;
    }

    /**
     * Moves one node from one array to new one
     *
     * @param newArray - expanded array
     * @param node     - node to be moved
     */
    private void moveOneNode(Node<K, V>[] newArray, Node<K, V> node) {
        int newIndex = indexFor(node.hash, newArray.length);
        Node<K, V> newNode = new Node<>(node.hash, node.key, node.value);

        if (newArray[newIndex] == null) {
            newArray[newIndex] = newNode;
        } else {
            Node<K, V> newCurrentNode = newArray[newIndex];
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
            if (currentNode.key.equals(key)) {
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
     * @param  arrayCapacity - for which array size the index should be calculated
     * @return - array index
     */
    private int indexFor(int hash, int arrayCapacity) {
        return hash < 0 ? (-hash) % arrayCapacity : hash % arrayCapacity;
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
        if (keys.contains(key)) {
            int hash = hash(key);
            int index = indexFor(hash, elements.length);
            Node<K, V> node = findNodeWithSimilarKey(index, key);
            if (key.equals(node.key)) {
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
        if (keys.contains(key)) {
            int hash = hash(key);
            int index = indexFor(hash, elements.length);
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
        for (K key : keys) {
            if (get(key).equals(value)) {
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
        return keys.contains(key);
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
        if (keys.contains(key)) {
            size--;
            int hash = hash(key);
            int index = indexFor(hash, elements.length);
            Node<K, V> currentNode, previousNode = null;
            currentNode = elements[index];
            while (currentNode.next != null) {
                if (key.equals(currentNode.key)) {
                    break;
                }
                previousNode = currentNode;
                currentNode = currentNode.next;
            }

            if (key.equals(currentNode.key)) {
                V value = currentNode.value;
                if (previousNode == null && currentNode.next != null) {
                    elements[index] = currentNode.next;
                    currentNode.next = null;
                } else if (previousNode != null && currentNode.next != null) {
                    previousNode.next = currentNode.next;
                    currentNode.next = null;
                } else {
                    elements[index] = null;
                }
                keys.remove(keys.indexOf(key));
                return value;
            }
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
        int index = indexFor(hash, elements.length);
        Node<K, V> currentNode = findNodeWithSimilarKey(index, hash);
        if (key.equals(currentNode.key)) {
            V oldValue = currentNode.value;
            currentNode.value = value;
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
        int index = indexFor(hash, elements.length);
        Node<K, V> currentNode = findNodeWithSimilarKey(index, hash);
        if (key.equals(currentNode.key) && oldValue.equals(currentNode.value)) {
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

    /**
     * Returns Array of all keys
     *
     * @return - array with all keys
     */
    public K[] keySet() {
        return keys.toArray();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("[]");
        for (K key : keySet()) {
            if (keys.indexOf(key) == 0)
                builder.insert(builder.length() - 1, key + "=" + get(key));
            else
                builder.insert(builder.length() - 1, ", " + key + "=" + get(key));
        }

        return builder.toString();
    }


    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            private int index;

            @Override
            public boolean hasNext() {
                return index < keys.size();
            }

            @Override
            public V next() {
                return get(keys.get(index++));
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
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        /**
         * Constructs one node
         *
         * @param hash  - key hash
         * @param key   - key with which to be associated specified value
         * @param value - specified value
         */
        private Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }
}


