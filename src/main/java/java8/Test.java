package java8;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        var list = Arrays.asList(1, 2, 3, 4);
        list.forEach(System.out::println);
    }
}
