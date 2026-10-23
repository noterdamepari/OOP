package ru.nsu.utils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.nsu.graphs.AdjacencyListGraph;
import ru.nsu.graphs.Graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

class FileReaderTest {

    @TempDir
    Path tempDir;

    private Path createFile(String content) throws IOException {
        Path file = tempDir.resolve("graph.txt");
        Files.writeString(file, content);
        return file;
    }

    @Test
    void readVertices() throws IOException {
        Path file = createFile("A\nB\nC\n");
        Graph<String> graph = new AdjacencyListGraph<>();

        FileReader.read(file, graph);

        assertEquals(Set.of("A", "B", "C"), graph.getVertices());
        assertTrue(graph.getEdges().isEmpty());
    }

    @Test
    void readEdges() throws IOException {
        Path file = createFile("A -> B\nB -> C\n");
        Graph<String> graph = new AdjacencyListGraph<>();

        FileReader.read(file, graph);

        assertEquals(Set.of("A", "B", "C"), graph.getVertices());
        assertEquals(2, graph.getEdges().size());
    }

    @Test
    void readEdgeWithSpaces() throws IOException {
        Path file = createFile("  A  ->  B  \n");
        Graph<String> graph = new AdjacencyListGraph<>();

        FileReader.read(file, graph);

        assertEquals(Set.of("A", "B"), graph.getVertices());
        assertEquals(1, graph.getEdges().size());
    }

    @Test
    void readIsolatedVerticesAndEdges() throws IOException {
        Path file = createFile("A\nB -> C\nD\n");
        Graph<String> graph = new AdjacencyListGraph<>();

        FileReader.read(file, graph);

        assertEquals(Set.of("A", "B", "C", "D"), graph.getVertices());
        assertEquals(1, graph.getEdges().size());
    }

    @Test
    void ignoreEmptyLines() throws IOException {
        Path file = createFile("A\n\n   \nB -> C\n");
        Graph<String> graph = new AdjacencyListGraph<>();

        assertDoesNotThrow(() -> FileReader.read(file, graph));

        assertEquals(Set.of("A", "B", "C"), graph.getVertices());
        assertEquals(1, graph.getEdges().size());
    }

    @Test
    void readEmptyFile() throws IOException {
        Path file = createFile("");
        Graph<String> graph = new AdjacencyListGraph<>();

        FileReader.read(file, graph);

        assertTrue(graph.getVertices().isEmpty());
        assertTrue(graph.getEdges().isEmpty());
    }

    @Test
    void throwExceptionWhenEdgeHasMultipleArrows() throws IOException {
        Path file = createFile("A -> B -> C\n");
        Graph<String> graph = new AdjacencyListGraph<>();

        assertThrows(
                IllegalArgumentException.class,
                () -> FileReader.read(file, graph)
        );
    }

    @Test
    void throwExceptionWhenSourceVertexIsBlank() throws IOException {
        Path file = createFile("-> B\n");
        Graph<String> graph = new AdjacencyListGraph<>();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> FileReader.read(file, graph)
        );

        assertEquals("blank vrt name", exception.getMessage());
    }

    @Test
    void throwExceptionWhenTargetVertexIsBlank() throws IOException {
        Path file = createFile("A ->\n");
        Graph<String> graph = new AdjacencyListGraph<>();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> FileReader.read(file, graph)
        );

        assertEquals("wrong format of file input", exception.getMessage());
    }

    @Test
    void throwIOExceptionWhenFileDoesNotExist() {
        Path file = tempDir.resolve("missing.txt");
        Graph<String> graph = new AdjacencyListGraph<>();

        assertThrows(
                IOException.class,
                () -> FileReader.read(file, graph)
        );
    }
}
