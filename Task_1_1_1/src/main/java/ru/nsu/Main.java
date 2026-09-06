package ru.nsu;

import java.util.Arrays;
import java.util.Scanner;


/**
 * Main class.
 */
public class Main {
    /**
     * Program entrypoint.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Amount of nums: ");
        int num = in.nextInt();
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = in.nextInt();
        }
        arr = Heap.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
