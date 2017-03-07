package Week2;

/**
 * Created by wangaichao on 17/3/7.
 *
 */
public class ShellSort {

    public ShellSort(final int[] arr) {
        int i;
        int j;
        int temp;

        int h = 1;
        while (h < arr.length/3) {
            h = 3*h + 1;
        }

        while (h >= 1) {
            for (i = h; i < arr.length; i++) {
                for (j = i; j >= h && arr[j] < arr[j-h]; j -= h) {
                        temp = arr[j];
                        arr[j] = arr[j - h];
                        arr[j - h] = temp;
                }
            }

            h = h/3;
        }
    }
}
