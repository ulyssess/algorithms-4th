package Week2;

import Week3.MergeSort;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by wangaichao on 17/3/6.
 */
public class TestSort {
    private static final int arraySize = 10000;
    private static final int randomSeed = 31415926;
    private static final int randomRange = 1000000;

    public static void main(final String[] args)
    {
        int i;
        int[] arr = new int[arraySize];


        StdRandom.setSeed(randomSeed);

        for (i = 0; i < arr.length; i++) {
            arr[i] = StdRandom.uniform(0, randomRange);
        }

        Stopwatch stopwatchBubble = new Stopwatch();

        BubbleSort bubbleSort = new BubbleSort(arr);

        StdOut.println("冒泡排序: " + stopwatchBubble.elapsedTime());

        for (i = 0; i < arr.length; i++) {
            StdOut.print(arr[i] + " ");
        }

        StdOut.println();


        StdRandom.setSeed(randomSeed);

        for (i = 0; i < arr.length; i++) {
            arr[i] = StdRandom.uniform(0, randomRange);
        }

        Stopwatch stopwatchSelect = new Stopwatch();

        SelectionSort selectionSort = new SelectionSort(arr);

        StdOut.println("选择排序: " + stopwatchSelect.elapsedTime());

        for (i = 0; i < arr.length; i++) {
            StdOut.print(arr[i] + " ");
        }

        StdOut.println();



        StdRandom.setSeed(randomSeed);

        for (i = 0; i < arr.length; i++) {
            arr[i] = StdRandom.uniform(0, randomRange);
        }

        Stopwatch stopwatchInsertion = new Stopwatch();

        InsertionSort insertionSort = new InsertionSort(arr);

        StdOut.println("插入排序: " + stopwatchInsertion.elapsedTime());

        for (i = 0; i < arr.length; i++) {
            StdOut.print(arr[i] + " ");
        }

        StdOut.println();



        StdRandom.setSeed(randomSeed);

        for (i = 0; i < arr.length; i++) {
            arr[i] = StdRandom.uniform(0, randomRange);
        }

        Stopwatch stopwatchShell = new Stopwatch();

        ShellSort shellSort = new ShellSort(arr);

        StdOut.println("希尔排序: " + stopwatchShell.elapsedTime());

        for (i = 0; i < arr.length; i++) {
            StdOut.print(arr[i] + " ");
        }

        StdOut.println();



        StdRandom.setSeed(randomSeed);

        for (i = 0; i < arr.length; i++) {
            arr[i] = StdRandom.uniform(0, randomRange);
        }

        Stopwatch stopwatchMerge = new Stopwatch();

        MergeSort mergeSort = new MergeSort(arr);

        StdOut.println("归并排序: " + stopwatchMerge.elapsedTime());

        for (i = 0; i < arr.length; i++) {
            StdOut.print(arr[i] + " ");
        }

        StdOut.println();
    }
}
