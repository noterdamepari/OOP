package ru.nsu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapTest {
    @Test
    void SimpleSortTest() {
        int[] arr = new int[]{2,2,2,3,1,-6};
        int[] sortedArr = new int[]{-6,1,2,2,2,3};
        var res = Heap.Sort(arr);
        assertArrayEquals(sortedArr, res);
    }

    @Test
    void VoidSortTest() {
        int[] arr = new int[]{};
        var res = Heap.Sort(arr);
        assertArrayEquals(new int[]{}, res);
    }

    @Test
    void SimpleHeapTest(){
        Heap h = new Heap(null);
        h.Insert(5);
        h.Insert(8);
        h.Insert(1337);
        h.Insert(567);
        h.Insert(4);

        assertEquals(4, h.Get());
        assertEquals(5, h.Get());
        assertEquals(8, h.Get());
        assertEquals(567, h.Get());
        assertEquals(1337, h.Get());
    }

    @Test
    void OnlyOneHeapTest(){
        Heap h = new Heap(null);
        h.Insert(13);
        var res = h.Get();
        assertEquals(13, res);
    }

    @Test
    void EmptyHeapTest(){
        Heap h = new Heap(null);
        var res = h.Get();
        assertNull(res);
    }

    @Test
    void LargeHeapTest(){
        Heap h = new Heap(null);
        for (int i = 5000; i >= 0; i--){
             h.Insert(i);
        }
        for (int i = 0; i < 5001; i++){
            assertEquals(i, h.Get());
        }
    }
}