package Week2;

/**
 * Created by wangaichao on 17/3/6.
 */
public class BubbleSort {

    public BubbleSort(final int[] arr) {
        int i;
        int j;
        int temp;

        for (i = 0; i < arr.length - 1; i++) {
            for (j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
