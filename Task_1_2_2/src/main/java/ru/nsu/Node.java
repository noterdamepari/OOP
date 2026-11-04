package ru.nsu;

public class Node<K, V> {
    public K key;
    public V value;
    public Node<K, V> next;

    Node(){
        this.next = null;
    }

    Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}
