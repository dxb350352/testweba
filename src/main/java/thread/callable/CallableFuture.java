package thread.callable;

import java.util.Date;
import java.util.concurrent.*;

public class CallableFuture {
    public static void main(String args[]) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future f1 = executorService.submit(new CallableTest("t1"));
        TimeUnit.SECONDS.sleep(1);
        Future f2 = executorService.submit(new CallableTest("t2"));
        TimeUnit.SECONDS.sleep(1);
        Future f3 = executorService.submit(new CallableTest("t3"));
        executorService.shutdown();
        try {
            System.out.println(f1.get());
            System.out.println(f2.get());
            System.out.println(f3.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
//            executorService.shutdown();
        }
        while (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("线程还没完");
        }
        System.out.println("线程完了");
    }


}

class CallableTest implements Callable {
    private String name;

    public CallableTest(String name) {
        this.name = name;
    }

    @Override
    public Object call() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        return this.name + " return " + new Date();
    }
}
