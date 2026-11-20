package ru.nsu;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Searches for all occurrences of a pattern in a file using KMP.
 */
public class Grep {

    private static final int CHUNK = 1 << 16;

    private Grep() {
    }

    /**
     * Finds all occurrences of the given pattern in a file.
     *
     * @param fileName path to the input file
     * @param pattern pattern to search
     * @return list of starting positions of all matches
     * @throws IOException if io errors happen
     */
    public static List<Integer> find(String fileName, String pattern) throws IOException {
        List<Integer> result = new ArrayList<>();
        int m = pattern.length();
        if (m == 0) {
            return result;
        }

        char sep = '#';
        String head = pattern + sep;

        String tail = "";
        int consumed = 0;
        char[] buf = new char[CHUNK];

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName), StandardCharsets.UTF_8)) {
            int n;
            while ((n = reader.read(buf)) != -1) {
                int windowStart = consumed - tail.length();
                consumed += n;

                String s = head + tail + new String(buf, 0, n);
                int[] pi = prefixFunction(s);

                for (int i = m + 1; i < s.length(); i++) {
                    if (pi[i] == m) {
                        result.add(windowStart + i - 2 * m);
                    }
                }

                String body = s.substring(m + 1);
                int keep = Math.min(m - 1, body.length());
                tail = body.substring(body.length() - keep);
            }
        }
        return result;
    }


    private static int[] prefixFunction(String s) {
        int[] pi = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            int k = pi[i - 1];
            while (k > 0 && s.charAt(i) != s.charAt(k)) {
                k = pi[k - 1];
            }
            if (s.charAt(i) == s.charAt(k)) {
                k++;
            }
            pi[i] = k;
        }
        return pi;
    }
}
