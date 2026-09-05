package ru.nsu;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[]{5,4,5,6,123,123,3};
        System.out.println(Arrays.toString(arr));
        int[] res = Heap.Sort(arr);
        System.out.println(Arrays.toString(res));
    }
}