package ru.nsu.sort;

import java.util.List;
import ru.nsu.graphs.Graph;

/**
 * Graph sorting interface.
 */
public interface Sorter<V> {
    /**
     * Sort method.
     *
     * @param gr Graph
     */
    List<V> sort(Graph<V> gr);
}
