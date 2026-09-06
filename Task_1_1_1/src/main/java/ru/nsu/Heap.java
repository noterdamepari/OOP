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
     *
     * @param cap начальный размер кучи.
     */
    public Heap(Integer cap) {
        if (cap == null) {
            cap = 16;
        }
        buffer = new ArrayList<Integer>(cap);
        size = 0;
    }

    /**
     * Get the id of parent element.
     *
     * @param idx айди ребенка.
     * @return айди родителя
     */
    private int getParentIdx(int idx) {
        return (idx - 1) / 2;
    }

    /**
     * SiftUp.
     *
     * @param idx айди элемента.
     */
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

    /**
     * SiftDown.
     *
     * @param idx айди элемента.
     */
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

    /**
     * Swap.
     *
     * @param idx1 айди первого элемента.
     * @param idx2 айди первого элемента.
     */
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
    public Integer get() {
        if (size == 0) {
            System.out.println("panic: heap is empty");
            return null;
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
     * @param arr входной массив.
     * @return отсортированный массив.
     */
    public static int[] sort(int[] arr) {
        int len = arr.length;
        Heap h = new Heap(len);

        for (int i = 0; i < len; i++) {
            h.insert(arr[i]);
        }

        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            Integer num = h.get();
            if (num == null) {
                return res;
            }
            res[i] = num;
        }
        return res;
    }
}
