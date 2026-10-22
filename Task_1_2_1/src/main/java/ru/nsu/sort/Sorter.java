package ru.nsu.sort;

import ru.nsu.graphs.Graph;

import java.util.List;

public interface Sorter<V> {
    List<V> sort(Graph<V> graph);
}
