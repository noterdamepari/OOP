package ru.nsu;

import java.util.Map;
import java.util.Objects;

/**
 * Node class.
 *
 * @param <K> key type
 * @param <V> value type
 */
public class Node<K, V> implements Map.Entry<K, V> {
    private K key;
    private V value;
    public Node<K, V> next;

    /**
     * Node constructor.
     */
    Node() {
        this.next = null;
    }

    /**
     * Node constructor.
     *
     * @param key   key
     * @param value value
     */
    Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    /**
     * Get key.
     *
     * @return key
     */
    @Override
    public K getKey() {
        return this.key;
    }

    /**
     * Get value.
     *
     * @return value
     */
    @Override
    public V getValue() {
        return this.value;
    }

    /**
     * Set value.
     *
     * @param value new value to be stored in this entry
     * @return old value
     */
    @Override
    public V setValue(V value) {
        V oldValue = this.value;
        this.value = value;
        return oldValue;
    }

    /**
     * Equals func.
     *
     * @param obj object to be compared for equality with this map entry
     * @return is equal (bool)
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry<?, ?> entry)) {
            return false;
        }

        return Objects.equals(key, entry.getKey())
                && Objects.equals(value, entry.getValue());
    }

    /**
     * Get HashCode func.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }

    /**
     * Get string presentation.
     *
     * @return string presentation
     */
    @Override
    public String toString() {
        return key + "=" + value;
    }
}
