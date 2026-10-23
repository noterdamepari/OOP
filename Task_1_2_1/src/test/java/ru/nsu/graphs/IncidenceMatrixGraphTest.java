package ru.nsu.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.misc.Edge;

class IncidenceMatrixGraphTest {
    private IncidenceMatrixGraph<Integer> graph;

    @BeforeEach
    void setUp() {
        graph = new IncidenceMatrixGraph<>();
    }

    @Test
    void addEdgeIsDirected() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2);
        assertEquals(Set.of(2), graph.getNeighbours(1));
        assertTrue(graph.getNeighbours(2).isEmpty());
    }

    @Test
    void addEdgeWithMissingVertexThrows() {
        graph.addVertex(1);
        assertThrows(NoSuchElementException.class, () -> graph.addEdge(1, 2));
    }

    @Test
    void delEdge() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2);
        graph.delEdge(1, 2);
        assertTrue(graph.getEdges().isEmpty());
    }

    @Test
    void delMissingVertexThrows() {
        assertThrows(NoSuchElementException.class, () -> graph.delVertex(42));
    }

    @Test
    void getEdgesReturnsAllEdges() {
        for (int i = 1; i <= 3; i++) graph.addVertex(i);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.addEdge(3, 3);
        assertEquals(
                Set.of(new Edge<>(1, 2), new Edge<>(2, 3), new Edge<>(3, 1), new Edge<>(3, 3)),
                graph.getEdges());
    }

    @Test
    void growthKeepsEdges() {
        int n = 40; // начальная ёмкость 8, несколько расширений
        Set<Edge<Integer>> expected = new HashSet<>();
        for (int i = 0; i < n; i++) graph.addVertex(i);
        for (int i = 0; i < n - 1; i++) {
            graph.addEdge(i, i + 1);
            expected.add(new Edge<>(i, i + 1));
        }
        assertEquals(expected, graph.getEdges());
    }

    @Test
    void edgesAddedBeforeGrowthSurviveGrowth() {
        for (int i = 0; i < 8; i++) graph.addVertex(i);
        graph.addEdge(0, 7);
        graph.addEdge(3, 3);
        graph.addVertex(8); // триггерит extendMatrix
        graph.addEdge(8, 0);
        assertEquals(
                Set.of(new Edge<>(0, 7), new Edge<>(3, 3), new Edge<>(8, 0)),
                graph.getEdges());
    }

    @Test
    void deletingMiddleVertexMovesLastVertexCorrectly() {
        for (int i = 0; i < 5; i++) graph.addVertex(i);
        graph.addEdge(4, 0);
        graph.addEdge(0, 4);
        graph.addEdge(4, 4);
        graph.addEdge(4, 2);
        graph.addEdge(1, 2);

        graph.delVertex(2); // вершина 4 переезжает на индекс 2

        assertEquals(Set.of(0, 1, 3, 4), graph.getVertices());
        assertEquals(
                Set.of(new Edge<>(4, 0), new Edge<>(0, 4), new Edge<>(4, 4)),
                graph.getEdges());
    }

    @Test
    void deletingLastVertexLeavesNoStaleData() {
        for (int i = 0; i < 3; i++) graph.addVertex(i);
        graph.addEdge(2, 0);
        graph.addEdge(0, 2);
        graph.addEdge(2, 2);

        graph.delVertex(2);
        graph.addVertex(10); // займёт освободившийся индекс

        assertTrue(graph.getEdges().isEmpty());
    }
}