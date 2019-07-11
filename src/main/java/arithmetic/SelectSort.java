package arithmetic;

import java.util.Arrays;

/**
 * 选择排序
 * https://www.cnblogs.com/bjh1117/p/8335628.html
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] a = {7, 6, 5, 4, 3, 2, 1};
        for (int i = 0; i < a.length - 1; i++) {
            // 无序区的最小数据数组下标
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                // 在无序区中找到最小数据并保存其数组下标
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            // 将最小元素放到本次循环的前端
            int temp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = temp;
            System.out.println(Arrays.toString(a));
        }
    }
}
