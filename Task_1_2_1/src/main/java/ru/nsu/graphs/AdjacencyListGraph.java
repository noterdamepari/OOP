package ru.nsu.graphs;

import ru.nsu.misc.Edge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * adjacency list graph implementation
 */
public class AdjacencyListGraph<V> extends AbstractGraph<V> {
    private Map<V, Set<V>> graph;

    /**
     * adjacency list graph constructor
     */
    public AdjacencyListGraph() {
        graph = new HashMap<V, Set<V>>();
    }

    /**
     * Add graph vertex
     *
     * @param v vertex
     */
    @Override
    public void addVertex(V v) {
        if (graph.containsKey(v)) {
            return;
        }
        Set<V> newAdjList = new HashSet<V>();
        graph.put(v, newAdjList);
    }

    /**
     * Del graph vertex
     *
     * @param v vertex
     */
    @Override
    public void delVertex(V v) {
        if (!graph.containsKey(v)) {
            throw new NoSuchElementException();
        }
        for (Set<V> adjLists : graph.values()) {
            adjLists.remove(v);
        }
        graph.remove(v);
    }

    /**
     * Add graph edge
     *
     * @param from start vert
     * @param to final vert
     */
    @Override
    public void addEdge(V from, V to) {
        if (!graph.containsKey(from) || !graph.containsKey(to)) {
            throw new NoSuchElementException();
        }
        Set<V> adjList = graph.get(from);
        adjList.add(to);
    }

    /**
     * Delete graph edge
     *
     * @param from start vert
     * @param to final vert
     */
    @Override
    public void delEdge(V from, V to) {
        if (!graph.containsKey(from) || !graph.containsKey(to)) {
            return;
        }
        Set<V> adjList = graph.get(from);
        adjList.remove(to);
    }

    /**
     * Get neighbors func
     *
     * @param v vertex
     * @return neighbours
     */
    @Override
    public Set<V> getNeighbours(V v) {
        if (!graph.containsKey(v)) {
            throw new NoSuchElementException();
        }
        return new HashSet<>(graph.get(v));
    }

    /**
     * Get vertices func
     *
     * @return all vertices in graph
     */
    @Override
    public Set<V> getVertices() {
        return new HashSet<>(graph.keySet());
    }

    /**
     * Get edges func
     *
     * @return all edges in graph
     */
    @Override
    public Set<Edge<V>> getEdges() {
        Set<Edge<V>> edges = new HashSet<>();
        for (Map.Entry<V, Set<V>> entry : graph.entrySet()) {
            V from = entry.getKey();
            for (V to : entry.getValue()) {
                edges.add(new Edge<>(from, to));
            }
        }
        return edges;
    }
}
