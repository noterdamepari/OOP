package ru.nsu.sort;

import ru.nsu.graphs.Graph;

import java.util.List;

/**
 * Graph sorting interface
 */
public interface Sorter<V> {
    /**
     * Sort method
     */
    List<V> sort(Graph<V> graph);
}
