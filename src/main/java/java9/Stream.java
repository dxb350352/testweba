package java9;

import java.util.List;

public class Stream {

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 34, 4, 54, 54, 5465, 76, 344);
        list.parallelStream().dropWhile(i -> i < 100).forEach(e -> System.out.println(e));
        System.out.println("..............");
        list.parallelStream().takeWhile(i -> i < 100).forEach(e -> System.out.println(e));
        System.out.println("..............");
    }
}
