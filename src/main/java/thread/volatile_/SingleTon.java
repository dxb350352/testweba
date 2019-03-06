package thread.volatile_;

import java.util.concurrent.atomic.AtomicLong;

public class SingleTon {
    private static volatile SingleTon singleTon;

    private SingleTon() {
        System.out.println("init");
    }

    public static SingleTon getInstance() {
        if (singleTon == null) {
            synchronized (SingleTon.class) {
                if (singleTon == null) {
                    singleTon = new SingleTon();
                }
            }
        }
        return singleTon;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SingleTon.getInstance();
            }).start();
        }
        AtomicLong al=new AtomicLong(1);
        System.out.println(al);
        al.compareAndSet(2,3);
        System.out.println(al);
        Thread.sleep(1000);

    }

}

