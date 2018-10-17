package java8;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class SpliteratorTest {
    public static void main(String args[]) {
        test1();
//        test2();
    }

    static void test1() {
        var list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        var spliterator = list.spliterator();
        //每次分割，都会分割剩余的前一半
        for (var splitor = spliterator.trySplit(); splitor != null; splitor = spliterator.trySplit()) {
            splitor.forEachRemaining(o -> {
                System.out.println(o + Thread.currentThread().getName());
            });
        }
        //不能再分时，会留最后一个
        spliterator.forEachRemaining(o -> {
            System.out.println(o + Thread.currentThread().getName());
        });
    }

    static void test2() {
        var list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        var spliterator = list.spliterator();

        CountDownLatch countDownLatch = new CountDownLatch(list.size());
        for (var i = 0; i < list.size(); i++) {
            new Thread(() -> {
                var splitor = spliterator.trySplit();
                if (splitor != null) {
                    splitor.forEachRemaining(o -> {
                        System.out.println(o + Thread.currentThread().getName());
                    });
                }else{
                    spliterator.forEachRemaining(o -> {
                        System.out.println(o + Thread.currentThread().getName()+"..............");
                    });
                }
                countDownLatch.countDown();
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
