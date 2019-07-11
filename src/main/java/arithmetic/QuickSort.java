package arithmetic;

import java.util.Arrays;

public class QuickSort {
    public static int[] quick_sort(int arr[], int l, int r) {
        if (l < r) {
            int i = l, j = r, x = arr[l];
            while (i < j) {
                // 从右向左找第一个小于x的数
                while (i < j && arr[j] >= x) {
                    j--;
                }
                if (i < j) {
                    arr[i++] = arr[j];
                }
                // 从左向右找第一个大于等于x的数
                while (i < j && arr[i] < x) {
                    i++;
                }
                if (i < j) {
                    arr[j--] = arr[i];
                }
            }
            arr[i] = x;
            quick_sort(arr, l, i - 1);
            quick_sort(arr, i + 1, r);
        }
        System.out.println(Arrays.toString(arr));
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9, 8, 7, 1, 6, 5, 4, 3, 2, 10};
        int len = arr.length - 1;
        quick_sort(arr, 0, len);
    }

}
