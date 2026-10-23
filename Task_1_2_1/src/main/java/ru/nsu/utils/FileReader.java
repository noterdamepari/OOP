package ru.nsu.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import ru.nsu.graphs.Graph;

/**
 * Graph reader class
 */
public class FileReader {
    /**
     * Graph reader method.
     *
     * @param file path to file
     * @param target graph
     */
    public static void read(Path file, Graph<String> target) throws IOException {
        try (BufferedReader in = Files.newBufferedReader(file)) {
            String line;
            while ((line = in.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                if (!line.contains("->")){
                    target.addVertex(line);
                    continue;
                }
                String[] parts = line.split("->");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("wrong format of file input");
                }
                String[] trimmed = Arrays.stream(parts).map(String::trim).toArray(String[]::new);
                if (trimmed[0].isEmpty() || trimmed[1].isEmpty()) {
                    throw new IllegalArgumentException("blank vrt name");
                }
                target.addVertex(trimmed[0]);
                target.addVertex(trimmed[1]);
                target.addEdge(trimmed[0], trimmed[1]);
            }
        }
    }
}
