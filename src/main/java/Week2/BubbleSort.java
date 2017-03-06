package Week2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by wangaichao on 17/3/6.
 */
public class BubbleSort {

    public BubbleSort(int[] arr) {
        int i;
        int j;

        for (i = 0; i < arr.length - 1; i++) {
            for (j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args)
    {
        int i;
        int[] arr = new int[50];

        for (i = 0; i < arr.length; i++) {
            arr[i] = StdRandom.uniform(0, 1000);
        }

        BubbleSort bubbleSort = new BubbleSort(arr);

        for (i = 0; i < arr.length; i++) {
            StdOut.print(arr[i] + " ");
        }
    }
}
