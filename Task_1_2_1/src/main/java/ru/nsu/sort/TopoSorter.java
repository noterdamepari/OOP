package ru.nsu.sort;

import ru.nsu.graphs.Graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Topological sorting class
 */
public class TopoSorter<V> implements Sorter<V> {
    private enum Color {WHITE,GRAY,BLACK};
    HashMap<V, Color> visited;
    LinkedList<V> res;

    /**
     * Topological sorting class constructor
     */
    public TopoSorter(){
        visited = new HashMap<>();
        res = new LinkedList<>();
    }

    /**
     * Sort method
     */
    public List<V> sort(Graph<V> gr){
        for (V v : gr.getVertices()) {
            visited.put(v, Color.WHITE);
        }

        for (V v : gr.getVertices()) {
            if (visited.get(v) == Color.WHITE){
                visit(gr, v);
            }
        }

        return res;
    }

    private void visit(Graph<V> gr, V vrt){
        visited.put(vrt, Color.GRAY);
        for (V next : gr.getNeighbours(vrt)) {
            Color c = visited.get(next);
            if (c == Color.GRAY) {
                throw new RuntimeException("Graph has a cycle");
            }
            if (c == Color.WHITE) {
                visit(gr, next);
            }
        }
        visited.put(vrt, Color.BLACK);
        res.addFirst(vrt);
    }
}
