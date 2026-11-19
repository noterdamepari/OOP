package ru.nsu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class GrepTest {

    private static final int CHUNK = 1 << 16;

    @TempDir
    Path tempDir;

    private Path createFile(String content) throws IOException {
        Path file = tempDir.resolve("test.txt");
        Files.writeString(file, content, StandardCharsets.UTF_8);
        return file;
    }

    private List<Integer> find(String content, String pattern)
            throws IOException {
        return Grep.find(createFile(content).toString(), pattern);
    }

    @Test
    void emptyFile() throws IOException {
        assertEquals(List.of(), find("", "abc"));
    }

    @Test
    void emptyPattern() throws IOException {
        assertEquals(List.of(), find("abcdef", ""));
    }

    @Test
    void patternLongerThanText() throws IOException {
        assertEquals(List.of(), find("ab", "abcdef"));
    }

    @Test
    void noMatches() throws IOException {
        assertEquals(List.of(), find("abcdefghijk", "xyz"));
    }

    @Test
    void oneMatch() throws IOException {
        assertEquals(List.of(2), find("xxabcxx", "abc"));
    }

    @Test
    void matchAtBeginning() throws IOException {
        assertEquals(List.of(0), find("abcdef", "abc"));
    }

    @Test
    void matchAtEnd() throws IOException {
        assertEquals(List.of(2), find("xxabc", "abc"));
    }

    @Test
    void multipleMatches() throws IOException {
        assertEquals(
                List.of(0, 3, 6),
                find("abcabcabc", "abc")
        );
    }

    @Test
    void overlappingMatches() throws IOException {
        assertEquals(
                List.of(0, 1, 2, 3),
                find("aaaaa", "aa")
        );
    }

    @Test
    void overlappingMatchesWithLongerPattern()
            throws IOException {
        assertEquals(
                List.of(0, 2, 4),
                find("abababab", "abab")
        );
    }

    @Test
    void patternEqualToText() throws IOException {
        assertEquals(
                List.of(0),
                find("hello", "hello")
        );
    }

    @Test
    void repeatedCharacters() throws IOException {
        assertEquals(
                List.of(0, 1, 2, 3, 4),
                find("aaaaaaaa", "aaaa")
        );
    }

    @Test
    void separatorCharacterInsidePattern()
            throws IOException {
        assertEquals(
                List.of(0, 5),
                find("#$abc#$abc", "#$abc")
        );
    }

    @Test
    void newlineCharacters() throws IOException {
        assertEquals(
                List.of(3, 7),
                find("ab\nabc\nabc", "abc")
        );
    }

    @Test
    void unicodeCharacters() throws IOException {
        assertEquals(
                List.of(1, 4),
                find("A😀B😀", "😀")
        );
    }

    @Test
    void patternMuchLongerThanFile() throws IOException {
        String text = "abc".repeat(1000);
        String pattern = "abc".repeat(2000);

        assertEquals(List.of(), find(text, pattern));
    }

    @Test
    void largeNumberOfOverlappingMatches() throws IOException {
        String text = "a".repeat(100_000);
        String pattern = "aaaaa";

        List<Integer> actual = find(text, pattern);

        assertEquals(99_996, actual.size());
        assertEquals(0, actual.get(0));
        assertEquals(99_995, actual.get(actual.size() - 1));
    }

    @Test
    void warAndPeaceLargeTest() throws IOException {
        assertEquals(List.of(17518, 1290201), Grep.find("warandpeace.txt", "Гостиная"));
    }

    @Test
    void matchCrossesChunkBoundary() throws IOException {
        String content = "a".repeat(CHUNK - 2)
                + "ABCDE"
                + "z".repeat(100);

        assertEquals(
                List.of(CHUNK - 2),
                find(content, "ABCDE")
        );
    }


}
