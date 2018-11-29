package zookeeper.watch;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.zookeeper.CreateMode;

import static zookeeper.watch.CuratorListenerTest1.getClient;

/**
 * Created by zhuzs on 2017/4/15.
 */
public class CuratorPathChildrenCacheTest {

    public static void main(String[] args) throws Exception {

        CuratorFramework client = getClient();
        String parentPath = "/p2";
        try {
            client.create().withMode(CreateMode.PERSISTENT).forPath(parentPath, "0".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, parentPath, true);
        pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                System.out.println("事件类型：" + event.getType() + "；操作节点：" + event.getData().getPath());
            }
        });

        String path = parentPath + "/c1";
        client.create().withMode(CreateMode.PERSISTENT).forPath(path);
        Thread.sleep(100); // 此处需留意，如果没有睡眠则无法触发监听事件
        client.getData().forPath(path);
        Thread.sleep(100); // 此处需留意，如果没有睡眠则无法触发监听事件
        client.setData().forPath(path,"1".getBytes());
        Thread.sleep(100); // 此处需留意，如果没有睡眠则无法触发监听事件
        client.setData().forPath(path,"2".getBytes());
        Thread.sleep(100); // 此处需留意，如果没有睡眠则无法触发监听事件
        client.delete().forPath(path);
        Thread.sleep(100); // 此处需留意，如果没有睡眠则无法触发监听事件
        client.delete().forPath(parentPath);


    }
}