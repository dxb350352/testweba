package arithmetic;

public class QuickSort {
    public static int[] quick_sort(int arr[], int l, int r) {
        if (l < r) {
            //Swap(arr[l], arr[(l + r) / 2]); //将中间的这个数和第一个数交换 参见注1
            int i = l, j = r, x = arr[l];
            while (i < j) {
                while (i < j && arr[j] >= x) // 从右向左找第一个小于x的数
                    j--;
                if (i < j)
                    arr[i++] = arr[j];

                while (i < j && arr[i] < x) // 从左向右找第一个大于等于x的数
                    i++;
                if (i < j)
                    arr[j--] = arr[i];
            }
            arr[i] = x;
            quick_sort(arr, l, i - 1); // 递归调用
            quick_sort(arr, i + 1, r);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9, 8, 7, 1, 6, 5, 4, 3, 2, 10};
        int len = arr.length - 1;
        quick_sort(arr, 0, len);
    }

}
