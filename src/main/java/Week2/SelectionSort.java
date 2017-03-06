package Week2;

/**
 * Created by wangaichao on 17/3/6.
 */
public class SelectionSort {

    public SelectionSort(final int[] arr) {
        int i;
        int j;
        int temp;

        for (i = 0; i < arr.length - 1; i++) {
            for (j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}
