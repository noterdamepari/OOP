package ru.nsu.graphs;

import ru.nsu.misc.Edge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class AdjacencyListGraph<V> extends AbstractGraph<V> {
    private Map<V, Set<V>> graph;

    public AdjacencyListGraph(){
        graph = new HashMap<V, Set<V>>();
    }

    /**
     * @param v
     */
    @Override
    public void addVertex(V v) {
        if (graph.containsKey(v)){
            return;
        }
        Set<V> newAdjList = new HashSet<V>();
        graph.put(v, newAdjList);
    }

    /**
     * @param v
     */
    @Override
    public void delVertex(V v) {
        if (!graph.containsKey(v)){
            throw new NoSuchElementException();
        }
        for (Set<V> adjLists : graph.values()){
            adjLists.remove(v);
        }
        graph.remove(v);
    }

    /**
     * @param from
     * @param to
     */
    @Override
    public void addEdge(V from, V to) {
        if (!graph.containsKey(from) || !graph.containsKey(to)){
            throw new NoSuchElementException();
        }
        Set<V> adjList = graph.get(from);
        adjList.add(to);
    }

    /**
     * @param from
     * @param to
     */
    @Override
    public void delEdge(V from, V to) {
        if (!graph.containsKey(from) || !graph.containsKey(to)){
            return;
        }
        Set<V> adjList = graph.get(from);
        adjList.remove(to);
    }

    /**
     * @param v
     * @return
     */
    @Override
    public Set<V> getNeighbours(V v) {
        if (!graph.containsKey(v)) {
            throw new NoSuchElementException();
        }
        return new HashSet<>(graph.get(v));
    }

    /**
     * @return
     */
    @Override
    public Set<V> getVertices() {
        return new HashSet<>(graph.keySet());
    }

    /**
     * @return
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
