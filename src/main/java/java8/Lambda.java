package java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class Lambda {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(5, 1, 2, 3, 4, 5);
        //单行
        list.sort((a, b) -> a.compareTo(b));
        System.out.println(list);
        //多行
        list.stream().forEach(a -> {
            System.out.println(a);
        });
        List<Integer> list2 = list.stream().map(a -> {
            return ++a;
        }).collect(Collectors.toList());
        System.out.println(list2);
        list2 = list.stream().filter(a -> a > 3).collect(Collectors.toList());
        System.out.println(list2);
        list2 = list.stream().distinct().collect(Collectors.toList());
        System.out.println(list2);
        System.out.println(list.stream().max(Lambda::go).get());
        list2 = Arrays.asList(8, 5, 6, 7);
        List<List<Integer>> list3 = new ArrayList<>();
        list3.add(list);
        list3.add(list2);
        list2 = list3.stream().flatMap(x -> x.stream()).distinct().collect(Collectors.toList());
        System.out.println(list2);
        list2 = list.stream().limit(2).skip(2).collect(Collectors.toList());
        System.out.println(list2);
        list.stream().forEachOrdered(a -> System.out.println(a));
        list2 = list.stream().sorted().collect(Collectors.toList());
        System.out.println(list2);
        list.stream().peek(a -> System.out.println(a));
        System.out.println(list.stream().reduce(Integer::max).get());
        //
        MyClass myClass = new MyClass();
        String reuslt = myClass.doClass("a", "b", "c", (a, b, c) -> {
            return a + b + c;
        });
        System.out.println(reuslt);


        //函数式接口
        // Function<T, R> -T作为输入，返回的R作为输出
        Function<String, String> function = x -> {
            System.out.println(x);
            return x + x;
        };
        System.out.println(function.apply("function test"));
        //Predicate<T> -T作为输入，返回的boolean值作为输出
        Predicate<Integer> predicate = i -> {
            System.out.println("predicate" + i);
            return true;
        };
        System.out.println(predicate.test(123));
        //Consumer<T> - T作为输入，执行某种动作但没有返回值
        Consumer<Lambda> consumer = l -> {
            System.out.println("consumer" + l);
        };
        consumer.accept(new Lambda());
        //Supplier<T> - 没有任何输入，返回T
        Supplier<String> supplier = () -> {
            return "supplier";
        };
        System.out.println(supplier.get());
        //BinaryOperator<T> -两个T作为输入，返回一个T作为输出，对于“reduce”操作很有用
        BinaryOperator<String> binaryOperator = (x, y) -> {
            return x + y;
        };
        System.out.println(binaryOperator.apply("Binary", "Operator"));

    }

    public static int go(int a, int b) {
        return a + b;
    }
}

interface MyInterFace {
    String doInterFace(String a, String b, String c);
}

class MyClass {
    String doClass(String a, String b, String c, MyInterFace mi) {
        return mi.doInterFace(a, b, c);
    }

}
