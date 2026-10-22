package ru.nsu.graphs;

import ru.nsu.misc.Edge;

import java.util.Set;

public interface Graph<V> {
    void addVertex(V v);
    void delVertex(V v);
    void addEdge(V from, V to);
    void delEdge(V from, V to);
    Set<V> getNeighbours(V v);
    Set<V> getVertices();
    Set<Edge<V>> getEdges();
}
