package java8;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String args[]) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1, 10, Transaction.Type.GEOCERY));
        transactions.add(new Transaction(3, 30, Transaction.Type.GEOCERY));
        transactions.add(new Transaction(6, 60, Transaction.Type.GEOCERY));
        transactions.add(new Transaction(5, 50, Transaction.Type.GEOCERY));
        transactions.add(new Transaction(2, 20, Transaction.Type.A));
        transactions.add(new Transaction(4, 40, Transaction.Type.C));
        //(需求: 如果发现type为grocery的所有交易, 然后返回以交易值降序排序的交易ID集合)
        List<Integer> ids = transactions.parallelStream().filter(t -> t.getType() == Transaction.Type.GEOCERY).sorted(Comparator.comparing(Transaction::getValue).reversed()).map(Transaction::getId).collect(Collectors.toList());
        System.out.println(ids);

        Stream stream = Stream.of(1, 2, 3, 4);
        System.out.println(stream.map(a -> String.valueOf(a)).collect(Collectors.joining(",,")));


        Path path = Paths.get(System.getProperty("user.dir"), "/src/main/java/java8/StreamTest.java");
        try (Stream<String> stream1 = Files.lines(path)) {
            List<String> words = stream1.flatMap(line -> Stream.of(line.split(" "))).filter(word -> word.length() > 0).collect(Collectors.toList());
            System.out.println(words);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //peek 对每个元素执行操作并且返回一个新的Stream 【peek : 偷窥】注意执行顺序
        List<String> words = Stream.of("one", "two", "three", "four")
                .filter(p -> p.length() > 3)
                .peek(v -> System.out.println("Filtered Value:" + v))
                .map(String::toUpperCase)
                .peek(v -> System.out.println("Mapped Value:" + v))
                .collect(Collectors.toList());
        System.out.println(words);

        System.out.println(length(null));

        Integer sum = Stream.of(4, 3, 2, 1).reduce(1, (a, b) -> a + b);
        System.out.println(sum);
        sum = Stream.of(4, 3, 2, 1).reduce(Integer::sum).get();
        System.out.println(sum);

        ids = Stream.iterate(2, n -> n + 2).limit(10).collect(Collectors.toList());
        System.out.println(ids);
        //group
        Map<Transaction.Type, List<Transaction>> map = transactions.stream().collect(Collectors.groupingBy(Transaction::getType));
        System.out.println(map);
        //统计每个Transaction下value占的百分比
        Long total = transactions.parallelStream().mapToInt(Transaction::getValue).asLongStream().sum();
        Collection<String> result = transactions.parallelStream().mapToInt(Transaction::getValue).asLongStream().mapToDouble(value -> (double) value / total).boxed().mapToLong(weigth -> (long) (weigth * 100)).mapToObj(percentage -> percentage + "%").collect(Collectors.toList());
        System.out.println(result);
//        统计每个type下value占的百分比
        transactions.stream().collect(Collectors.groupingBy(Transaction::getType)).forEach((key, list) -> {
            System.out.println(key + ":" + Math.round(list.parallelStream().mapToInt(Transaction::getValue).asDoubleStream().sum() / total * 100) + "%");
        });
    }

    public static int length(String str) {
        return Optional.ofNullable(str).map(String::length).orElse(-1);
    }

}

class Transaction {
    private final int id;
    private final Integer value;
    private final Type type;

    public Transaction(int id, Integer value, Type type) {
        this.id = id;
        this.value = value;
        this.type = type;
    }

    public enum Type {
        A, B, C, D, GEOCERY
    }

    public int getId() {
        return id;
    }

    public Integer getValue() {
        return value;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return id + "-" + value + "-" + type;
    }
}
