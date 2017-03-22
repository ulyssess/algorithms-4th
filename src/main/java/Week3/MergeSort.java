package Week3;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by wangaichao on 17/3/7.
 */
public class MergeSort {
    public MergeSort(int[] arr) {

        int [] newArr = new int[arr.length];

        bottomUpSort(arr, newArr);
        //sort(arr, 0, arr.length - 1, newArr);
    }

    private void merge(int arr[], int first, int mid, int last, int newArr[])
    {
        int i;
        int j;
        int k;

        for (i = first, j = mid, k = 0; k <= last - first; k++) {
            if (i >= mid)
                newArr[k] = arr[j++];
            else if (j > last)
                newArr[k] = arr[i++];
            else if (arr[i] < arr[j])
                newArr[k] = arr[i++];
            else
                newArr[k] = arr[j++];
        }

        for (i = 0; i < k; i++)
            arr[first + i] = newArr[i];
    }

    public void sort(int arr[], int first, int last, int newArr[])
    {
        if (first < last) {
            int mid = (first + last) / 2;

            sort(arr, first, mid, newArr);
            sort(arr, mid + 1, last, newArr);

            merge(arr, first, mid, last, newArr);
        }
    }

    public void bottomUpSort(int arr[], int newArr[])
    {
        int step;
        int first;
        int mid;
        int last;

        for (step = 1; step < arr.length; step = step + step) {
            first = 0;
            mid = first + step;
            last = first + 2*step - 1;

            if (last >= arr.length)
                last = arr.length - 1;

            while (mid < arr.length) {
                merge(arr, first, mid, last, newArr);

                first = first + 2*step;
                mid = first + step;
                last = first + 2*step - 1;

                if (last >= arr.length)
                    last = arr.length - 1;
            }
        }
    }
}
