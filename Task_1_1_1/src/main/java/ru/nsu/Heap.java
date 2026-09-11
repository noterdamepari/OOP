package ru.nsu;

import java.util.ArrayList;

/**
 * Heap class.
 */
public class Heap {
    private final ArrayList<Integer> buffer;
    private int size;


    /**
     * Heap constructor.
     */
    public Heap() {
        this(16);
    }

    /**
     * Heap constructor with capacity param.
     *
     * @param cap начальный размер кучи.
     */
    public Heap(int cap) {
        buffer = new ArrayList<Integer>(cap);
        size = 0;
    }

    // получение id ребенка
    private int getParentIdx(int idx) {
        return (idx - 1) / 2;
    }

    // siftup method
    private void siftUp(int idx) {
        if (idx == 0) {
            return;
        }

        int parentIndex = getParentIdx(idx);
        if (buffer.get(idx) < buffer.get(parentIndex)) {
            swap(idx, parentIndex);
            siftUp(parentIndex);
        }
    }

    // siftdown method
    private void siftDown(int idx) {
        int leftChildIndex = idx * 2 + 1;
        if (leftChildIndex >= size) {
            return;
        }

        int rightChildIndex = idx * 2 + 2;
        int resultChildIndex = leftChildIndex;
        if (rightChildIndex < size && buffer.get(rightChildIndex) < buffer.get(leftChildIndex)) {
            resultChildIndex = rightChildIndex;
        }
        if (buffer.get(resultChildIndex) < buffer.get(idx)) {
            swap(idx, resultChildIndex);
        }

        siftDown(resultChildIndex);
    }

    // value swap
    private void swap(int idx1, int idx2) {
        int tmp = buffer.get(idx1);
        buffer.set(idx1, buffer.get(idx2));
        buffer.set(idx2, tmp);
    }

    /**
     * Insert to Heap Func.
     *
     * @param value добавляемый элемент.
     */
    public void insert(int value) {
        buffer.add(value);
        siftUp(size++);
    }

    /**
     * Get from Heap Func.
     *
     * @return полученный элемент
     */
    public int get() {
        if (size == 0) {
            throw new IllegalStateException("panic: heap is empty");
        }
        final int res = buffer.get(0);
        swap(0, --size);
        buffer.remove(size);
        siftDown(0);
        return res;
    }

    /**
     * Get from Heap Func.
     *
     * @param arr массив для сортировки.
     */
    public static void sort(int[] arr) {
        int len = arr.length;
        Heap h = new Heap(len);

        for (int el : arr) {
            h.insert(el);
        }

        for (int i = 0; i < len; i++) {
            int num = h.get();
            arr[i] = num;
        }
    }
}
