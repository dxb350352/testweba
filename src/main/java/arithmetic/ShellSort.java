package arithmetic;

import java.util.Arrays;

/**
 * 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {7, 6, 5, 4, 3, 2, 1, 8};
        System.out.println(Arrays.toString(arr));
        //希尔排序
        int d = arr.length;
        while (true) {
            d = d / 2;
            for (int x = 0; x < d; x++) {
                for (int i = x + d; i < arr.length; i = i + d) {
                    int temp = arr[i];
                    int j;
                    for (j = i - d; j >= 0 && arr[j] > temp; j = j - d) {
                        arr[j + d] = arr[j];
                    }
                    arr[j + d] = temp;
                }
            }
            if (d == 1) {
                break;
            }
            System.out.println(Arrays.toString(arr));
        }
    }
}
