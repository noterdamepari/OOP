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

    private int getParentIdx(int idx){
        return (idx-1)/2;
    }

    private void siftUP(int idx){
        if (idx == 0){
            return;
        }
        int parentIdx = getParentIdx(idx);
        if (buffer.get(idx) < buffer.get(parentIdx)){
            swap(idx, parentIdx);
            siftUP(parentIdx);
        }
    }

    private void siftDown(int idx){
        int lChildIdx = idx*2+1;
        if (lChildIdx>=size){
            return;
        }

        int rChildIdx = idx*2+2;
        int resChildIdx = lChildIdx;
        if (rChildIdx < size && buffer.get(rChildIdx) < buffer.get(lChildIdx)){
            resChildIdx = rChildIdx;
        }

        swap(idx, resChildIdx);
        siftDown(resChildIdx);
    }

    private void swap(int idx1, int idx2){
        int tmp = buffer.get(idx1);
        buffer.add(idx1, buffer.get(idx2));
        buffer.add(idx2, tmp);
    }

    public void Insert(int value){
        buffer.add(value);
        siftUP(size++);
    }

    public Integer Get(){
        if (size == 0){
            System.out.println("panic: heap is empty");
            return null;
        }
        int res = buffer.getFirst();
        swap(0, --size);
        buffer.remove(size);
        siftDown(0);
        return res;
    }
}
