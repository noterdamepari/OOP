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

        table.Put("one", 1);
        table.Put("two", 2);

        assertEquals(1, table.Get("one"));
        assertEquals(2, table.Get("two"));
    }

    @Test
    void putDuplicateKey() {
        HashTable<String, Integer> table = new HashTable<>();

        table.Put("one", 1);

        assertThrows(IllegalArgumentException.class,
                () -> table.Put("one", 2));

        assertEquals(1, table.Get("one"));
    }

    @Test
    void removeElement() {
        HashTable<String, Integer> table = new HashTable<>();

        table.Put("one", 1);

        assertEquals(true, table.Check("one"));
        assertEquals(1, table.Remove("one"));
        assertThrows(NoSuchElementException.class,
                () -> table.Get("one"));
    }

    @Test
    void updateElement() {
        HashTable<String, Integer> table = new HashTable<>();

        table.Put("one", 1);

        assertEquals(1, table.Update("one", 10));
        assertEquals(10, table.Get("one"));
    }

    @Test
    void handleCollisions() {
        HashTable<Integer, String> table = new HashTable<>(4);

        // Эти ключи могут попасть в одну корзину.
        table.Put(1, "one");
        table.Put(5, "five");
        table.Put(9, "nine");

        assertEquals("one", table.Get(1));
        assertEquals("five", table.Get(5));
        assertEquals("nine", table.Get(9));

        table.Remove(5);

        assertEquals("one", table.Get(1));
        assertEquals("nine", table.Get(9));
    }

    @Test
    void resizeTable() {
        HashTable<Integer, Integer> table = new HashTable<>(2);

        for (int i = 0; i < 100; i++) {
            table.Put(i, i * 10);
        }


        for (int i = 0; i < 100; i++) {
            assertEquals(i * 10, table.Get(i));
        }
    }

    @Test
    void iterateOverElements() {
        HashTable<Integer, String> table = new HashTable<>();

        table.Put(1, "one");
        table.Put(2, "two");
        table.Put(3, "three");

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

        table.Put(1, "one");

        Iterator<Map.Entry<Integer, String>> iterator = table.iterator();

        table.Put(2, "two");

        assertThrows(ConcurrentModificationException.class,
                iterator::hasNext);
    }

    @Test
    void toStringWorks() {
        HashTable<String, Integer> table = new HashTable<>();

        assertNotNull(table.toString());

        table.Put("one", 1);
        table.Put("two", 2);

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

        first.Put("one", 1);
        second.Put("one", 2);

        assertNotEquals(first, second);
        assertNotEquals(first, null);
        assertNotEquals(first, "one");
    }

    @Test
    void equalsAndHashCode() {
        HashTable<String, Integer> first = new HashTable<>();
        HashTable<String, Integer> second = new HashTable<>();

        first.Put("one", 1);
        first.Put("two", 2);

        second.Put("two", 2);
        second.Put("one", 1);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }
}
