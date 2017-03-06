package Week2;

/**
 * Created by wangaichao on 17/3/6.
 */
public class InsertionSort {
    public InsertionSort(final int[] arr) {
        int i;
        int j;
        int temp;

        for (i = 0; i < arr.length; i++) {
            for (j = i; j > 0; j--) {
                if (arr[j] > arr[j - 1])
                    break;

                temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
            }
        }
    }
}
