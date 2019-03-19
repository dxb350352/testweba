package com.dxb.guava.currentlimiting;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TestSemaphore {
    public static void main(String[] args) throws Exception {
        Semaphore semaphore = new Semaphore(2);
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        for (int i = 0; i < list.size(); i++) {
            final int it = i;
            Thread t = new Thread(() -> {
                try {
                    System.out.println(it);
                    //1.acquire会阻塞
                    semaphore.acquire();
                    System.out.println(list.get(it) + "--" + new Date());
                    TimeUnit.SECONDS.sleep(1);
                    semaphore.release();
                    //2.tryAcquire不阻塞
//                    if (semaphore.tryAcquire()) {
//                        System.out.println(list.get(it) + "--" + new Date());
//                        TimeUnit.SECONDS.sleep(1);
//                        semaphore.release();
//                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }
        TimeUnit.SECONDS.sleep(11);
    }
}
