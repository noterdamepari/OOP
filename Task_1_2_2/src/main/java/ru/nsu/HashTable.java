package ru.nsu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * HashTable class.
 *
 * @param <K> key type
 * @param <V> value type
 */
public class HashTable<K, V> implements Iterable<Map.Entry<K, V>> {
    private Node<K, V>[] buffer;
    private int size;
    private int cap;
    private int modCount;

    /**
     * HashTable constructor.
     */
    public HashTable() {
        this(16);
    }

    /**
     * HashTable constructor with capacity.
     *
     * @param cap capacity
     */
    @SuppressWarnings("unchecked")
    public HashTable(int cap) {
        if (cap <= 0) {
            throw new IllegalArgumentException("cap cannot be below or equal zero");
        }
        this.cap = cap;
        this.size = 0;
        this.buffer = (Node<K, V>[]) new Node[cap];
        this.modCount = 0;
        InitSentinels();
    }

    @SuppressWarnings("unchecked")
    private void ExtendMap() {
        Node<K, V>[] tmpbuffer = new Node[size];
        int s = 0;
        for (int i = 0; i < cap; i++) {
            Node<K, V> node = buffer[i].next;
            while (node != null) {
                tmpbuffer[s++] = new Node<>(node.getKey(), node.getValue());
                node = node.next;
            }
        }
        cap *= 2;
        this.buffer = new Node[cap];
        size = 0;
        InitSentinels();
        for (int i = 0; i < s; i++) {
            PutWithoutChecks(tmpbuffer[i].getKey(), tmpbuffer[i].getValue());
        }
    }

    private void InitSentinels() {
        for (int i = 0; i < cap; i++) {
            this.buffer[i] = new Node<>();
        }
    }

    private Node<K, V> findPrevNode(K key) {
        int hash = key.hashCode() & 0x7fffffff;
        Node<K, V> node = buffer[hash % cap];
        Node<K, V> prev;
        while (node.next != null) {
            prev = node;
            node = node.next;
            if (node.getKey().equals(key)) {
                return prev;
            }
        }
        return null;
    }

    private Node<K, V> findNode(K key) {
        Node<K, V> prev = findPrevNode(key);
        if (prev != null) {
            return prev.next;
        }
        return null;
    }

    private void PutWithoutChecks(K key, V value) {
        int hash = key.hashCode() & 0x7fffffff;
        Node<K, V> node = buffer[hash % cap];
        while (node.next != null) {
            node = node.next;
        }
        node.next = new Node<>(key, value);
        size++;
    }

    /**
     * Put entry to HashTable.
     *
     * @param key   key
     * @param value value
     */
    public void Put(K key, V value) {
        if (findNode(key) != null) {
            throw new IllegalArgumentException("Key already exists");
        }

        if ((double) size / cap >= 0.75) {
            ExtendMap();
        }
        int hash = key.hashCode() & 0x7fffffff;
        Node<K, V> node = buffer[hash % cap];
        while (node.next != null) {
            node = node.next;
        }
        node.next = new Node<>(key, value);
        size++;
        modCount++;
    }

    /**
     * Remove entry from HashTable.
     *
     * @param key key
     * @return entry value
     */
    public V Remove(K key) {
        Node<K, V> prev = findPrevNode(key);
        if (prev == null) {
            return null;
        }
        Node<K, V> node = prev.next;
        prev.next = node.next;
        node.next = null;
        size--;
        modCount++;
        return node.getValue();
    }

    /**
     * Get value from HashTable by key.
     *
     * @param key key
     * @return value
     */
    public V Get(K key) {
        Node<K, V> node = findNode(key);
        if (node == null) {
            throw new NoSuchElementException();
        }
        return node.getValue();
    }

    /**
     * Update entry in HashTable.
     *
     * @param key      key
     * @param newValue new value
     * @return old value
     */
    public V Update(K key, V newValue) {
        Node<K, V> node = findNode(key);
        if (node == null) {
            throw new NoSuchElementException();
        }
        V oldValue = node.setValue(newValue);
        modCount++;
        return oldValue;
    }

    /**
     * Check entry in HashTable.
     *
     * @param key key
     * @return is entry in HashTable (bool)
     */
    public boolean Check(K key) {
        Node<K, V> node = findNode(key);
        return node != null;
    }

    private class HashTableIterator implements Iterator<Map.Entry<K, V>> {
        private final int expectedModCount;
        private int currentBucket;
        private Node<K, V> currentNode;

        /**
         * Iterator constructor.
         */
        HashTableIterator() {
            expectedModCount = modCount;
            currentBucket = 0;
            currentNode = null;

            findNext();
        }

        private void checkForModification() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }

        private void findNext() {
            if (currentNode != null && currentNode.next != null) {
                currentNode = currentNode.next;
                return;
            }
            currentNode = null;
            while (currentBucket < cap) {
                Node<K, V> sentinel = buffer[currentBucket];
                currentBucket++;
                if (sentinel.next != null) {
                    currentNode = sentinel.next;
                    return;
                }
            }
        }

        /**
         * Has next iterator func.
         *
         * @return has next (bool)
         */
        @Override
        public boolean hasNext() {
            checkForModification();
            return currentNode != null;
        }

        /**
         * Get next entry.
         *
         * @return next entry
         */
        @Override
        public Map.Entry<K, V> next() {
            checkForModification();
            if (currentNode == null) {
                throw new NoSuchElementException();
            }
            Node<K, V> result = currentNode;
            findNext();
            return result;
        }
    }

    /**
     * Get Iterator.
     *
     * @return iterator
     */
    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new HashTableIterator();
    }

    /**
     * Equals func.
     *
     * @param obj the reference object with which to compare.
     * @return is equal (bool)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HashTable<?, ?>)) {
            return false;
        }
        HashTable<K, ?> other = (HashTable<K, ?>) obj;
        if (this.size != other.size) {
            return false;
        }
        for (Map.Entry<K, V> entry : this) {
            Node<K, ?> node = other.findNode(entry.getKey());
            if (node == null) {
                return false;
            }
            if (!Objects.equals(entry, node)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get hashcode.
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int result = 0;

        for (Map.Entry<K, V> entry : this) {
            result += entry.hashCode();
        }

        return result;
    }

    /**
     * Get string presentation.
     *
     * @return string presentation
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");

        Iterator<Map.Entry<K, V>> iterator = iterator();

        while (iterator.hasNext()) {
            Map.Entry<K, V> entry = iterator.next();

            result.append(entry.toString());

            if (iterator.hasNext()) {
                result.append(", ");
            }
        }

        result.append("}");

        return result.toString();
    }
}
