package ru.nsu.graphs;

import ru.nsu.misc.Edge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * incidence matrix graph implementation
 */
public class IncidenceMatrixGraph<V> extends AbstractGraph<V> {
    private Map<V, Integer> idxs;
    private List<V> verts;
    private Map<Edge<V>, Integer> eidxs;
    private List<Edge<V>> edges;
    private List<List<Integer>> matrix;

    /**
     * incidence matrix graph constructor
     */
    public IncidenceMatrixGraph() {
        idxs = new HashMap<V, Integer>();
        eidxs = new HashMap<>();
        verts = new ArrayList<V>();
        edges = new ArrayList<Edge<V>>();
        matrix = new ArrayList<List<Integer>>();
    }

    /**
     * Add graph vertex
     *
     * @param v vertex
     */
    @Override
    public void addVertex(V v) {
        if (idxs.containsKey(v)) {
            return;
        }
        idxs.put(v, verts.size());
        verts.add(v);
        List<Integer> newVertList = new ArrayList<Integer>(Collections.nCopies(edges.size(), 0));
        matrix.add(newVertList);
    }

    /**
     * Del graph vertex
     *
     * @param v vertex
     */
    @Override
    public void delVertex(V v) {
        if (!idxs.containsKey(v)){
            throw new NoSuchElementException();
        }
        int idx = idxs.get(v);
        for (Edge<V> e : new ArrayList<>(edges)) {
            if (e.from().equals(v) || e.to().equals(v)) {
                delEdge(e.from(), e.to());
            }
        }
        int last = verts.size() - 1;
        if (idx != last) {
            V moved = verts.get(last);
            verts.set(idx, moved);
            idxs.put(moved, idx);
            matrix.set(idx, matrix.get(last));
        }
        idxs.remove(v);
        verts.remove(last);
        matrix.remove(last);
    }

    /**
     * Add graph edge
     *
     * @param from start vert
     * @param to final vert
     */
    @Override
    public void addEdge(V from, V to) {
        int i = getIdx(from);
        int j = getIdx(to);

        Edge<V> e = new Edge<>(from, to);
        if (eidxs.containsKey(e)) {
            return;
        }
        for (List<Integer> row : matrix) {
            row.add(0);
        }
        int col = edges.size();
        if (i == j) {
            matrix.get(i).set(col, 2);
        } else {
            matrix.get(i).set(col, 1);
            matrix.get(j).set(col, -1);
        }
        eidxs.put(e, col);
        edges.add(e);
    }

    /**
     * Delete graph edge
     *
     * @param from start vert
     * @param to final vert
     */
    @Override
    public void delEdge(V from, V to) {
        Edge<V> e = new Edge<>(from, to);
        if (!eidxs.containsKey(e)) {
          return;
        }
        int col = eidxs.remove(e);
        int last = edges.size() - 1;

        if (col != last) {
            Edge<V> moved = edges.get(last);
            edges.set(col, moved);
            eidxs.put(moved, col);
            for (List<Integer> row : matrix) {
                row.set(col, row.get(last));
            }
        }
        for (List<Integer> row : matrix) {
            row.remove(last);
        }
        edges.remove(last);
    }

    /**
     * Get neighbors func
     *
     * @param v vertex
     * @return neighbours
     */
    @Override
    public Set<V> getNeighbours(V v) {
        List<Integer> row = matrix.get(getIdx(v));
        Set<V> neighbours = new HashSet<>();
        for (int col = 0; col < edges.size(); col++) {
            if (row.get(col) == 1) {
                neighbours.add(edges.get(col).to());
            }
        }
        return neighbours;
    }

    /**
     * Get vertices func
     *
     * @return all vertices in graph
     */
    @Override
    public Set<V> getVertices() {
        return new HashSet<>(verts);
    }

    /**
     * Get edges func
     *
     * @return all edges in graph
     */
    @Override
    public Set<Edge<V>> getEdges() {
        return new HashSet<>(edges);
    }

    private int getIdx(V v){
        if (!idxs.containsKey(v)) {
            throw new NoSuchElementException();
        }
        return idxs.get(v);
    }
}
