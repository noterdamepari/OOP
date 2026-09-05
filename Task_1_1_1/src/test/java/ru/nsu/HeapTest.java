package ru.nsu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapTest {
    @Test
    void SimpleTest() {
        int[] arr = new int[]{2,3,1};
        int[] res;
        res = Heap.Sort(arr);
        assertArrayEquals(new int[]{1,2,3}, res);
    }
}