package Week3;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by wangaichao on 17/3/21.
 */
public class QuickSort {
    public QuickSort(final int[] arr) {

        if (arr == null)
            throw new java.lang.NullPointerException();

        StdRandom.shuffle(arr);

        quick(arr, 0, arr.length - 1);
    }

    private int getIndex(int[] arr, int first, int last)
    {
        int separateIndex = first;
        int separate = arr[first];

        for (first++; first != last; ) {
            if (arr[first] >= separate) {
                int temp = arr[last];
                arr[last] = arr[first];
                arr[first] = temp;

                last--;
            } else {
                first++;
            }
        }

        if (separate <= arr[first]) {
            int temp = arr[first - 1];
            arr[first - 1] = arr[separateIndex];
            arr[separateIndex] = temp;

            return first - 1;
        } else {
            int temp = arr[first];
            arr[first] = arr[separateIndex];
            arr[separateIndex] = temp;
        }

        return first;
    }

    private void quick(int[] arr, int first, int last)
    {
        if (last < 0 || last <= first)
            return;

        if (last - first <= 1) {
            if (arr[first] > arr[last]) {
                int temp = arr[last];
                arr[last] = arr[first];
                arr[first] = temp;
            }

            return;
        }

        int index = getIndex(arr, first, last);

        quick(arr, first, index - 1);
        quick(arr, index + 1, last);
    }
}
