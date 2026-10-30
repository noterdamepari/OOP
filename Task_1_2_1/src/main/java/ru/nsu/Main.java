package ru.nsu;


import java.io.IOException;
import java.nio.file.Path;
import ru.nsu.graphs.AdjacencyListGraph;
import ru.nsu.graphs.Graph;
import ru.nsu.utils.FileReader;

/**
 * Main class.
 */
public class Main {
    /**
     * Entrypoint.
     */
    public static void main(String[] args) throws IOException {
        Graph<String> gr = new AdjacencyListGraph<>();
        FileReader.read(Path.of("smth"), gr);
        System.out.println(gr);
        System.out.println(gr.getEdges().toString());
    }
}