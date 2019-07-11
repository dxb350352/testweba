package arithmetic;

import java.util.Arrays;

/**
 * 插入排序
 * https://www.cnblogs.com/bjh1117/p/8335628.html
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {7, 6, 5, 4, 3, 2, 1, 8};
        System.out.println(Arrays.toString(arr));
        //外层向右的index，即作为比较对象的数据的index
        for (int index = 1; index < arr.length; index++) {
            //用作比较的数据
            int temp = arr[index];
            int leftindex = index - 1;
            //当比到最左边或者遇到比temp小的数据时，结束循环
            while (leftindex >= 0 && arr[leftindex] > temp) {
                arr[leftindex + 1] = arr[leftindex];
                leftindex--;
            }
            //把temp放到空位上
            arr[leftindex + 1] = temp;
            System.out.println(Arrays.toString(arr));
        }
    }
}
