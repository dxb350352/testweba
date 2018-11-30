package zookeeper.transaction;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class Transaction {
    public static void main(String[] args) {
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.130.230:2181", new ExponentialBackoffRetry(1000, 3));
        try {
            client.inTransaction().check().forPath("/p1")
                    .and()
                    .create().withMode(CreateMode.EPHEMERAL).forPath("/p3", "data".getBytes())
                    .and()
                    .setData().forPath("/p3", "data2".getBytes())
                    .and()
                    .commit();

            //
            Executor executor = Executors.newFixedThreadPool(2);
            System.out.println(client.create().creatingParentsIfNeeded()
//                .inBackground()
                    .inBackground((curatorFramework, curatorEvent) -> {
                        System.out.println(curatorEvent.getType().name() + curatorEvent.getResultCode());
                    }, executor)
                    .forPath("/1/2/3"));
            client.getChildren().forPath("/").forEach(s -> {
                System.out.println(s + ".");
            });
            client.delete().deletingChildrenIfNeeded().forPath("/1");
            client.getChildren().forPath("/").forEach(s -> {
                System.out.println(s + "..");
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }
}
