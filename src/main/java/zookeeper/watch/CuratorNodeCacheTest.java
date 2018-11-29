package zookeeper.watch;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;

import static zookeeper.watch.CuratorListenerTest1.getClient;

public class CuratorNodeCacheTest {
    public static void main(String[] args) throws Exception {
        CuratorFramework client = getClient();
        String path = "/p1";
        final NodeCache nodeCache = new NodeCache(client,path);
        nodeCache.start();
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("重新获得节点内容为：" + new String(nodeCache.getCurrentData().getData()));
            }
        });
        //????????还是
        client.setData().forPath(path,"456".getBytes());
        Thread.sleep(100); // 此处需留意，如果没有睡眠则无法触发监听事件
        client.setData().forPath(path,"789".getBytes());
        Thread.sleep(100); // 此处需留意，如果没有睡眠则无法触发监听事件
        client.setData().forPath(path,"123".getBytes());
        Thread.sleep(100); // 此处需留意，如果没有睡眠则无法触发监听事件
        client.setData().forPath(path,"222".getBytes());
        Thread.sleep(100); // 此处需留意，如果没有睡眠则无法触发监听事件
        client.setData().forPath(path,"333".getBytes());
        Thread.sleep(100); // 此处需留意，如果没有睡眠则无法触发监听事件
        client.setData().forPath(path,"444".getBytes());
        Thread.sleep(100); // 此处需留意，如果没有睡眠则无法触发监听事件
    }
}
