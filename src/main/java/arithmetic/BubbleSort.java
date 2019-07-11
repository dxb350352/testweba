package arithmetic;

import java.util.Arrays;

/**
 * 冒泡排序
 * https://www.cnblogs.com/bjh1117/p/8335628.html
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {7, 6, 5, 4, 3, 2, 1, 8};
        //排序轮数
        for (int i = 0; i < arr.length - 1; i++) {
            //比较次数
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println(Arrays.toString(arr));
        }
    }
}
