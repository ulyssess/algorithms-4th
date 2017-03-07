package Week3;

import Week2.BubbleSort;
import Week2.InsertionSort;
import Week2.SelectionSort;
import Week2.ShellSort;
import edu.princeton.cs.algs4.Merge;
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

        MergeSort mergeSort = new MergeSort(arr);

        for (i = 0; i < arr.length; i++) {
            StdOut.print(arr[i] + " ");
        }

        StdOut.println();
    }
}
