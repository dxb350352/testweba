package zookeeper.watch;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.zookeeper.CreateMode;

public class CuratorListenerTest2 {

    public static void main(String[] args) {
        CuratorFramework client = CuratorListenerTest1.getClient();
        String path = "/p1";
        try {
            CuratorListener listener = new CuratorListener() {
                @Override
                public void eventReceived(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                    System.out.println("监听事件触发，event内容为：" + curatorEvent.getType().name());
                }
            };
            client.getCuratorListenable().addListener(listener);
            // 异步获取节点数据
            client.getData().inBackground().forPath(path);
            // 变更节点内容??没效果
            client.setData().forPath(path, "123".getBytes());
            Thread.sleep(100); // 此处需留意，如果没有睡眠则无法触发监听事件
            //??????第二个没触发
            client.setData().forPath(path, "321".getBytes());
            Thread.sleep(100); // 此处需留意，如果没有睡眠则无法触发监听事件
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }

}
