package arithmetic;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int arr[] = new int[]{1, 2, 3, 4, 9, 8, 7, 6, 5};
        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println(Arrays.toString(arr));
        }
    }
}
