package ru.nsu.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GraphEqualsTest {

    private IncidenceMatrixGraph<Integer> graph1;
    private IncidenceMatrixGraph<Integer> graph2;

    @BeforeEach
    void setUp() {
        graph1 = new IncidenceMatrixGraph<>();
    }

    @Test
    void addEdgeIsDirected() {
        graph2 = new IncidenceMatrixGraph<>();
        graph1.addVertex(1);
        graph1.addVertex(2);
        graph1.addEdge(1, 2);
        graph2.addVertex(1);
        graph2.addVertex(2);
        graph2.addEdge(1, 2);
        assertEquals(graph1, graph2);
    }

    @Test
    void toStringTest() {
        graph1.addVertex(1);
        graph1.addVertex(2);
        graph1.addVertex(3);
        graph1.addEdge(1, 2);

        String result = graph1.toString();

        assertTrue(result.contains("3\n"));
        assertTrue(result.contains("1 -> 2\n"));
    }

    @Test
    void hashCodeStableForSameGraph() {
        graph1.addVertex(1);
        graph1.addVertex(2);
        graph1.addVertex(3);
        graph1.addEdge(1, 2);

        int hashCode = graph1.hashCode();

        assertEquals(hashCode, graph1.hashCode());
    }
}
