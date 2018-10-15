package java9;

import java.util.List;
import java.util.Set;

public class CollectionUtils {
    public static void main(String args[]) {
        Set<Integer> set = Set.of(1, 2, 3);
        set.forEach(e -> System.out.println(e));
        List<Integer> list = List.of(3, 2, 1);
        list.forEach(integer -> System.out.println(integer));
        //不可变集合，报错
        list.add(4);
    }
}
