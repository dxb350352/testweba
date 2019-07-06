package arithmetic;

import java.util.Arrays;

public class QuickSort {
    public static int[] qsort(int arr[], int start, int end) {
        int pivot = arr[start];
        int i = start;
        int j = end;
        while (i < j) {
            while ((i < j) && (arr[j] > pivot)) {
                j--;
            }
            while ((i < j) && (arr[i] < pivot)) {
                i++;
            }
            if ((arr[i] == arr[j]) && (i < j)) {
                i++;
            } else {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        if (i - 1 > start) {
            arr = qsort(arr, start, i - 1);
        }
        if (j + 1 < end) {
            arr = qsort(arr, j + 1, end);
        }
        System.out.println(Arrays.toString(arr));
        return (arr);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9, 8, 7, 1, 6, 5, 4, 3, 2, 10};
        int len = arr.length - 1;
        qsort(arr, 0, len);
    }

}
