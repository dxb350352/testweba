package java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SynchronizedListTest {
    public static void main(String args[]) {
        List<Integer> list = Collections.synchronizedList(Arrays.asList(1, 2, 3, 4, 5));

        synchronized (list) {
            //获取迭代器
            Iterator<Integer> iterator = list.iterator();
            //遍历
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }
    }
}
