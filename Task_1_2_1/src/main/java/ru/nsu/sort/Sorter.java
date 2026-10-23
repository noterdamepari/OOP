package ru.nsu.sort;

import ru.nsu.graphs.Graph;

import java.util.List;

/**
 * Graph sorting interface
 */
public interface Sorter<V> {
    /**
     * Sort method
     *
     * @param gr Graph
     */
    List<V> sort(Graph<V> gr);
}
