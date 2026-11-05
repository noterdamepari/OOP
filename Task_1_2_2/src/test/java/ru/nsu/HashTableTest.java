package ru.nsu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class HashTableTest {

    @Test
    void putAndGet() {
        HashTable<String, Integer> table = new HashTable<>();

        table.put("one", 1);
        table.put("two", 2);

        assertEquals(1, table.get("one"));
        assertEquals(2, table.get("two"));
    }

    @Test
    void putDuplicateKey() {
        HashTable<String, Integer> table = new HashTable<>();

        table.put("one", 1);

        assertThrows(IllegalArgumentException.class,
                () -> table.put("one", 2));

        assertEquals(1, table.get("one"));
    }

    @Test
    void removeElement() {
        HashTable<String, Integer> table = new HashTable<>();

        table.put("one", 1);

        assertEquals(true, table.check("one"));
        assertEquals(1, table.remove("one"));
        assertThrows(NoSuchElementException.class,
                () -> table.get("one"));
    }

    @Test
    void updateElement() {
        HashTable<String, Integer> table = new HashTable<>();

        table.put("one", 1);

        assertEquals(1, table.update("one", 10));
        assertEquals(10, table.get("one"));
    }

    @Test
    void handleCollisions() {
        HashTable<Integer, String> table = new HashTable<>(4);

        // Эти ключи могут попасть в одну корзину.
        table.put(1, "one");
        table.put(5, "five");
        table.put(9, "nine");

        assertEquals("one", table.get(1));
        assertEquals("five", table.get(5));
        assertEquals("nine", table.get(9));

        table.remove(5);

        assertEquals("one", table.get(1));
        assertEquals("nine", table.get(9));
    }

    @Test
    void resizeTable() {
        HashTable<Integer, Integer> table = new HashTable<>(2);

        for (int i = 0; i < 100; i++) {
            table.put(i, i * 10);
        }


        for (int i = 0; i < 100; i++) {
            assertEquals(i * 10, table.get(i));
        }
    }

    @Test
    void iterateOverElements() {
        HashTable<Integer, String> table = new HashTable<>();

        table.put(1, "one");
        table.put(2, "two");
        table.put(3, "three");

        int count = 0;

        for (Map.Entry<Integer, String> entry : table) {
            assertNotNull(entry.getKey());
            assertNotNull(entry.getValue());
            count++;
        }

        assertEquals(3, count);
    }

    @Test
    void iteratorDetectsModification() {
        HashTable<Integer, String> table = new HashTable<>();

        table.put(1, "one");

        Iterator<Map.Entry<Integer, String>> iterator = table.iterator();

        table.put(2, "two");

        assertThrows(ConcurrentModificationException.class,
                iterator::hasNext);
    }

    @Test
    void toStringWorks() {
        HashTable<String, Integer> table = new HashTable<>();

        assertNotNull(table.toString());

        table.put("one", 1);
        table.put("two", 2);

        String result = table.toString();

        assertTrue(result.contains("one"));
        assertTrue(result.contains("1"));
        assertTrue(result.contains("two"));
        assertTrue(result.contains("2"));
    }

    @Test
    void equalsDifferentTables() {
        HashTable<String, Integer> first = new HashTable<>();
        HashTable<String, Integer> second = new HashTable<>();

        first.put("one", 1);
        second.put("one", 2);

        assertNotEquals(first, second);
        assertNotEquals(first, null);
        assertNotEquals(first, "one");
    }

    @Test
    void equalsAndHashCode() {
        HashTable<String, Integer> first = new HashTable<>();
        HashTable<String, Integer> second = new HashTable<>();

        first.put("one", 1);
        first.put("two", 2);

        second.put("two", 2);
        second.put("one", 1);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }
}
