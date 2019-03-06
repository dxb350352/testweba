package zookeeper.curator_recipes.conter;

import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DistributedAtomicLongDemo {

    private static final int QTY = 5;
    private static final String PATH = "/examples/counter1";

    public static void main(String[] args) throws IOException, Exception {
        List<DistributedAtomicLong> examples = Lists.newArrayList();
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.130.230:2181", new ExponentialBackoffRetry(1000, 3));
        client.start();
        ExecutorService service = Executors.newFixedThreadPool(QTY);
        for (int i = 0; i < QTY; ++i) {
            final DistributedAtomicLong count = new DistributedAtomicLong(client, PATH, new RetryNTimes(10, 10));

            examples.add(count);
            Callable<Void> task = () -> {
                try {
                    AtomicValue<Long> value = count.increment();
                    System.out.println("succeed: " + value.succeeded());
                    if (value.succeeded())
                        System.out.println("Increment: from " + value.preValue() + " to " + value.postValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            };
            service.submit(task);
        }

        service.shutdown();
        service.awaitTermination(10, TimeUnit.MINUTES);
        Thread.sleep(1111);
    }
}