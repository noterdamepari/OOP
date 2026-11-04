package ru.nsu;

import java.util.NoSuchElementException;

public class HashMap<K, V> {
    Node<K, V>[] buffer;
    int size;
    int cap;

    @SuppressWarnings("unchecked")
    HashMap(int cap) {
        if (cap <= 0) {
            throw new IllegalArgumentException("cap cannot be below or equal zero");
        }
        this.cap = cap;
        this.size = 0;
        this.buffer = (Node<K, V>[]) new Node[cap];
        InitSentinels();
    }

    @SuppressWarnings("unchecked")
    private void ExtendMap() {
        Node<K, V>[] tmpbuffer = new Node[size];
        int s = 0;
        for (int i = 0; i < cap; i++) {
            Node<K, V> node = buffer[i].next;
            while (node != null) {
                tmpbuffer[s++] = new Node<>(node.key, node.value);
                node = node.next;
            }
        }
        cap *= 2;
        this.buffer = new Node[cap];
        size = 0;
        InitSentinels();
        for (int i = 0; i < s; i++) {
            Insert(tmpbuffer[i].key, tmpbuffer[i].value);
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
            if (node.key.equals(key)) {
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

    public int Insert(K key, V value) {
        if ((double) size / cap >= 0.75) {
            ExtendMap();
        }
        int hash = key.hashCode() & 0x7fffffff;
        Node<K, V> node = buffer[hash % cap];
        while (node.next != null) {
            node = node.next;
            if (node.key.equals(key)) {
                throw new IllegalArgumentException("Key already exists");
            }
        }
        node.next = new Node<>(key, value);
        size++;
        return 0;
    }

    public V Remove(K key) {
        Node<K, V> prev = findPrevNode(key);
        if (prev == null) {
            return null;
        }
        Node<K, V> node = prev.next;
        prev.next = node.next;
        node.next = null;
        size--;
        return node.value;
    }

    public V Get(K key) {
        Node<K, V> node = findNode(key);
        if (node == null) {
            throw new NoSuchElementException();
        }
        return node.value;
    }

    public V Update(K key, V newValue) {
        Node<K, V> node = findNode(key);
        if (node == null) {
            throw new NoSuchElementException();
        }
        node.value = newValue;
        return node.value;
    }

    public boolean Check(K key) {
        Node<K, V> node = findNode(key);
        return node != null;
    }


}
