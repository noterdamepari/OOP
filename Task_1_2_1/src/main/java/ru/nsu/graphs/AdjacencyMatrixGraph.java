package ru.nsu.graphs;

import ru.nsu.misc.Edge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Adjacency matrix graph implementation.
 */
public class AdjacencyMatrixGraph<V> extends AbstractGraph<V> {
    private final Map<V, Integer> idxs;
    private final List<V> verts;
    private char[][] matrix = new char[8][8];

    /**
     * Adjacency matrix graph constructor.
     */
    public AdjacencyMatrixGraph() {
        this.idxs = new HashMap<V, Integer>();
        this.verts = new ArrayList<V>();
    }

    /**
     * Add graph vertex.
     *
     * @param v vertex
     */
    @Override
    public void addVertex(V v) {
        if (idxs.containsKey(v)) {
            return;
        }
        if (verts.size() == matrix.length) {
            extendMatrix();
        }
        idxs.put(v, verts.size());
        verts.add(v);
    }

    private void extendMatrix(){
        int newCap = matrix.length * 2;
        char[][] newMatrix = new char[newCap][newCap];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, matrix[i].length);
        }
        matrix = newMatrix;
    }

    /**
     * Del graph vertex.
     *
     * @param v vertex
     */
    @Override
    public void delVertex(V v) {
        if (!idxs.containsKey(v)) {
            throw new NoSuchElementException();
        }

        int removedIdx = idxs.remove(v);
        int lastVerIdx = verts.size() - 1;
        V lastVer = verts.get(lastVerIdx);
        if (removedIdx != lastVerIdx) {
            verts.set(removedIdx, lastVer);
            idxs.put(lastVer, removedIdx);

            matrix[removedIdx] = matrix[lastVerIdx].clone();
            for (int i = 0; i < verts.size(); i++) {
                matrix[i][removedIdx] = matrix[i][lastVerIdx];
            }
        }

        Arrays.fill(matrix[lastVerIdx], (char)0);
        for (int i = 0; i < verts.size(); i++){
            matrix[i][lastVerIdx] = 0;
        }
        verts.remove(lastVerIdx);
    }

    private int getIdx(V v) {
        if (!idxs.containsKey(v)) {
            throw new NoSuchElementException();
        }
        return idxs.get(v);
    }

    /**
     * Add graph edge.
     *
     * @param from start vert
     * @param to final vert
     */
    @Override
    public void addEdge(V from, V to) {
        int i = getIdx(from);
        int j = getIdx(to);
        matrix[i][j] = 1;
    }

    /**
     * Delete graph edge.
     *
     * @param from start vert
     * @param to final vert
     */
    @Override
    public void delEdge(V from, V to) {
        int i = getIdx(from);
        int j = getIdx(to);
        matrix[i][j] = 0;
    }


    /**
     * Get neighbors func.
     *
     * @param v vertex
     * @return neighbours
     */
    @Override
    public Set<V> getNeighbours(V v) {
        int i = getIdx(v);
        Set<V> neighbours = new HashSet<V>();
        for (int j = 0; j < verts.size(); j++) {
            if (matrix[i][j] == 1){
                neighbours.add(verts.get(j));
            }
        }
        return new HashSet<>(neighbours);
    }

    /**
     * Get vertices func.
     *
     * @return all vertices in graph
     */
    @Override
    public Set<V> getVertices() {
        return new HashSet<>(verts);
    }

    /**
     * Get edges func.
     *
     * @return all edges in graph
     */
    @Override
    public Set<Edge<V>> getEdges() {
        Set<Edge<V>> edges = new HashSet<>();
        for (int i = 0; i < verts.size(); i++) {
            for (int j = 0; j < verts.size(); j++) {
                if (matrix[i][j] == 1) {
                    edges.add(new Edge<>(verts.get(i), verts.get(j)));
                }
            }
        }
        return edges;
    }
}
