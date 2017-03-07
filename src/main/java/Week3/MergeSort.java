package Week3;

/**
 * Created by wangaichao on 17/3/7.
 */
public class MergeSort {
    public MergeSort(int[] arr) {

        int [] newArr = new int[arr.length];

        sort(arr, 0, arr.length - 1, newArr);
    }

    private void merge(int arr[], int first, int mid, int last, int newArr[])
    {
        int i;
        int j;
        int k;

        for (i = first, j = mid + 1, k = 0; i <= mid && j <= last; k++) {
            if (arr[i] < arr[j])
                newArr[k] = arr[i++];
            else
                newArr[k] = arr[j++];
        }

        for (; i <= mid; i++, k++)
            newArr[k] = arr[i];

        for (; j <= last; j++, k++)
            newArr[k] = arr[j];

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
}
