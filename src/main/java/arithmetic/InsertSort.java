package arithmetic;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {7, 6, 5, 4, 3, 2, 1, 8};
        System.out.println(Arrays.toString(arr));
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                } else {
                    break;
                }
            }
            System.out.println(Arrays.toString(arr));
        }
    }
}
