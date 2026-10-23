package ru.nsu.graphs;

import java.util.Set;
import ru.nsu.misc.Edge;

public interface Graph<V> {

    /**
     * Add graph vertex.
     *
     * @param v vertex
     */
    void addVertex(V v);

    /**
     * Del graph vertex.
     *
     * @param v vertex
     */
    void delVertex(V v);

    /**
     * Add graph edge.
     *
     * @param from start vert
     * @param to final vert
     */
    void addEdge(V from, V to);

    /**
     * Delete graph edge.
     *
     * @param from start vert
     * @param to final vert
     */
    void delEdge(V from, V to);

    /**
     * Get neighbors func.
     *
     * @param v vertex
     * @return neighbours
     */
    Set<V> getNeighbours(V v);

    /**
     * Get vertices func.
     *
     * @return all vertices in graph
     */
    Set<V> getVertices();

    /**
     * Get edges func.
     *
     * @return all edges in graph
     */
    Set<Edge<V>> getEdges();
}
