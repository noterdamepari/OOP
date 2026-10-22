package ru.nsu.sort;

import org.junit.jupiter.api.Test;
import ru.nsu.graphs.Graph;
import ru.nsu.graphs.AdjacencyListGraph;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TopoSorterTest {
    private final TopoSorter<String> sorter = new TopoSorter<>();

    @Test
    void sortEmptyGraph() {
        Graph<String> graph = new AdjacencyListGraph<>();

        List<String> result = sorter.sort(graph);

        assertTrue(result.isEmpty());
    }

    @Test
    void sortSingleVertex() {
        Graph<String> graph = new AdjacencyListGraph<>();
        graph.addVertex("A");

        List<String> result = sorter.sort(graph);

        assertEquals(List.of("A"), result);
    }

    @Test
    void sortSimpleGraph() {
        Graph<String> graph = new AdjacencyListGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");

        List<String> result = sorter.sort(graph);

        assertEquals(3, result.size());
        assertTrue(result.indexOf("A") < result.indexOf("B"));
        assertTrue(result.indexOf("B") < result.indexOf("C"));
    }

    @Test
    void sortGraphWithSeveralBranches() {
        Graph<String> graph = new AdjacencyListGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "D");

        List<String> result = sorter.sort(graph);

        assertEquals(4, result.size());
        assertTrue(result.indexOf("A") < result.indexOf("B"));
        assertTrue(result.indexOf("A") < result.indexOf("C"));
        assertTrue(result.indexOf("B") < result.indexOf("D"));
        assertTrue(result.indexOf("C") < result.indexOf("D"));
    }

    @Test
    void throwExceptionForCycleOfTwoVertices() {
        Graph<String> graph = new AdjacencyListGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        graph.addEdge("B", "A");

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> sorter.sort(graph)
        );

        assertEquals("Graph has a cycle", exception.getMessage());
    }
}
