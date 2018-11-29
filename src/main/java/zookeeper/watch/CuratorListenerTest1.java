package zookeeper.watch;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class CuratorListenerTest1 {
    static String path = "/p1";

    public static CuratorFramework getClient() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.130.230:2181")
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(6000)
                .connectionTimeoutMs(3000)
                .namespace("demo")
                .build();
        client.start();
        try {
            client.create().withMode(CreateMode.EPHEMERAL).forPath(path, "0".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }

    public static void main(String[] args) {
        CuratorFramework client = getClient();
        try {
            byte[] content = client.getData().usingWatcher(new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("监听器watchedEvent：" + watchedEvent.getType().name());
                }
            }).forPath(path);
            System.out.println("监听节点内容：" + new String(content));
            client.setData().forPath(path, "1".getBytes());
            Thread.sleep(100); // 此处需留意，如果没有睡眠则无法触发监听事件
            //??????第二个没触发
            client.setData().forPath(path, "2".getBytes());
            Thread.sleep(100); // 此处需留意，如果没有睡眠则无法触发监听事件
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }

}
