package ru.nsu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class HeapTest {
    @Test
    void simpleSortTest() {
        int[] arr = new int[]{2, 2, 2, 3, 1, -6};
        int[] sortedArr = new int[]{-6, 1, 2, 2, 2, 3};
        var res = Heap.sort(arr);
        assertArrayEquals(sortedArr, res);
    }

    @Test
    void voidSortTest() {
        int[] arr = new int[]{};
        var res = Heap.sort(arr);
        assertArrayEquals(new int[]{}, res);
    }

    @Test
    void simpleHeapTest() {
        Heap h = new Heap(null);
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
        Heap h = new Heap(null);
        h.insert(13);
        var res = h.get();
        assertEquals(13, res);
    }

    @Test
    void emptyHeapTest() {
        Heap h = new Heap(null);
        var res = h.get();
        assertNull(res);
    }

    @Test
    void largeHeapTest() {
        Heap h = new Heap(null);
        for (int i = 5000; i >= 0; i--) {
            h.insert(i);
        }
        for (int i = 0; i < 5001; i++) {
            assertEquals(i, h.get());
        }
    }
}