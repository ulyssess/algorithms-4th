package Week3;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by wangaichao on 17/3/7.
 */
public class MergeSort {
    public MergeSort(int[] arr) {
        int i;

        int[] newArr = sort(arr);

        for (i = 0; i < arr.length; i++) {
            StdOut.print(newArr[i] + " ");
        }

        StdOut.println();
    }

    public int[] merge(int prevArr[], int nextArr[])
    {
        int i;
        int j;
        int k;

        int [] c = new int[prevArr.length + nextArr.length];

        for (i = 0, j = 0, k = 0; i < prevArr.length && j < nextArr.length; k++) {
            if (prevArr[i] < nextArr[j])
                c[k] = prevArr[i++];
            else
                c[k] = nextArr[j++];
        }

        for (; i < prevArr.length; i++, k++)
            c[k] = prevArr[i];

        for (; j < nextArr.length; j++, k++)
            c[k] = nextArr[j];

        return c;
    }

    public int[] sort(int arr[])
    {
        int mid = arr.length/2;

        if (mid == 0)
            return arr;

        int [] prevArr = new int[mid];
        int [] nextArr = new int[arr.length - mid];

        for(int i = 0; i < arr.length; i++) {
            if (i < mid) {
                prevArr[i] = arr[i];
            } else {
                nextArr[i - mid] = arr[i];
            }
        }

        int [] newPrevArr = sort(prevArr);
        int [] newNextArr = sort(nextArr);

        return merge(newPrevArr, newNextArr);
    }
}
