package java8;

import java.util.ArrayList;

public class PredicateTest {
    public static void main(String args[]) {
        var list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        var success = list.removeIf(e -> e > 3);
        System.out.println(list);
        System.out.println(success);
    }
}
