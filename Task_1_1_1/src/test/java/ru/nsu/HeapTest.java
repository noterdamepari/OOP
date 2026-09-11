package ru.nsu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapTest {
    @Test
    void problemStatementTest() {
        int[] arr = new int[]{5, 4, 3, 2, 1};
        int[] sortedArr = new int[]{1, 2, 3, 4, 5};
        Heap.sort(arr);
        assertArrayEquals(sortedArr, arr);
    }

    @Test
    void simpleSortTest() {
        int[] arr = new int[]{2, 2, 2, 3, 1, -6};
        int[] sortedArr = new int[]{-6, 1, 2, 2, 2, 3};
        Heap.sort(arr);
        assertArrayEquals(sortedArr, arr);
    }

    @Test
    void voidSortTest() {
        int[] arr = new int[]{};
        Heap.sort(arr);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    void simpleHeapTest() {
        Heap h = new Heap();
        h.insert(5);
        h.insert(8);
        h.insert(1337);
        h.insert(567);
        h.insert(4);

        assertEquals(4, h.get());
        assertEquals(5, h.get());
        assertEquals(8, h.get());
        assertEquals(567, h.get());
        assertEquals(1337, h.get());
    }

    @Test
    void onlyOneHeapTest() {
        Heap h = new Heap();
        h.insert(13);
        var res = h.get();
        assertEquals(13, res);
    }

    @Test
    void emptyHeapTest() {
        Heap h = new Heap();
        // var res = h.get();
        assertThrows(IllegalStateException.class, () -> h.get());
    }

    @Test
    void largeHeapTest() {
        Heap h = new Heap();
        for (int i = 5000; i >= 0; i--) {
            h.insert(i);
        }
        for (int i = 0; i < 5001; i++) {
            assertEquals(i, h.get());
        }
    }
}