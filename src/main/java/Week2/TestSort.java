package Week2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by wangaichao on 17/3/6.
 */
public class TestSort {
    private static final int arraySize = 50;
    private static final int randomSeed = 31415926;
    private static final int randomRange = 1000;

    public static void main(final String[] args)
    {
        int i;
        int[] arr = new int[arraySize];


        StdRandom.setSeed(randomSeed);

        for (i = 0; i < arr.length; i++) {
            arr[i] = StdRandom.uniform(0, randomRange);
        }

        BubbleSort bubbleSort = new BubbleSort(arr);

        for (i = 0; i < arr.length; i++) {
            StdOut.print(arr[i] + " ");
        }

        StdOut.println();



        StdRandom.setSeed(randomSeed);

        for (i = 0; i < arr.length; i++) {
            arr[i] = StdRandom.uniform(0, randomRange);
        }

        SelectionSort selectionSort = new SelectionSort(arr);

        for (i = 0; i < arr.length; i++) {
            StdOut.print(arr[i] + " ");
        }

        StdOut.println();



        StdRandom.setSeed(randomSeed);

        for (i = 0; i < arr.length; i++) {
            arr[i] = StdRandom.uniform(0, randomRange);
        }

        InsertionSort insertionSort = new InsertionSort(arr);

        for (i = 0; i < arr.length; i++) {
            StdOut.print(arr[i] + " ");
        }

        StdOut.println();



        StdRandom.setSeed(randomSeed);

        for (i = 0; i < arr.length; i++) {
            arr[i] = StdRandom.uniform(0, randomRange);
        }

        ShellSort shellSort = new ShellSort(arr);

        for (i = 0; i < arr.length; i++) {
            StdOut.print(arr[i] + " ");
        }

        StdOut.println();
    }
}
