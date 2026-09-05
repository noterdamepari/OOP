package ru.nsu;

import java.util.ArrayList;

public class Heap {
    private final ArrayList<Integer> buffer;
    private int size;

    public Heap(Integer cap) {
        if (cap == null){
            cap = 16;
        }
        buffer = new ArrayList<Integer>(cap);
        size = 0;
    }
}
